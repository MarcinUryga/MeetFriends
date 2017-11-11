package com.example.marcin.meetfriends.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.beltaief.reactivefb.ReactiveFB
import com.beltaief.reactivefb.SimpleFacebookConfiguration
import com.beltaief.reactivefb.actions.ReactiveLogin
import com.beltaief.reactivefb.util.PermissionHelper
import com.example.marcin.meetfriends.R
import com.example.marcin.meetfriends.mvp.BaseActivity
import com.example.marcin.meetfriends.ui.main.MainActivity
import com.facebook.AccessToken
import com.facebook.login.DefaultAudience
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber


class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

  private val auth: FirebaseAuth = FirebaseAuth.getInstance()
  private val disposables = CompositeDisposable()

  public override fun onStart() {
    super.onStart()
    // Check if user is signed in (non-null) and update UI accordingly.
    if (auth.currentUser != null) {
      val currentUser = auth.currentUser
//    updateUI(currentUser)
    }
  }

  public override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)


    // init lib
    setContentView(R.layout.activity_login)

    registerlogin()
  }

  private fun registerlogin() {
    val disposableObserver = ReactiveLogin.loginWithButton(loginButton)
        .subscribe({ loginResult ->
          handleFacebookAccessToken(loginResult.accessToken)
        }, { error ->
          Timber.e(error.localizedMessage)
        })
    disposables.add(disposableObserver)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    // Pass the activity result back to the Facebook SDK
    ReactiveLogin.onActivityResult(requestCode, resultCode, data);
  }

  private fun handleFacebookAccessToken(token: AccessToken) {
    Timber.d("handleFacebookAccessToken: $token")

    val credential = FacebookAuthProvider.getCredential(token.token)

    RxFirebaseAuth.signInWithCredential(auth, credential)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
          progressBar.visibility = View.VISIBLE
        }
        .subscribe({ result ->
          Timber.d("signInWithCredential:success")
          val user = auth.currentUser
          Toast.makeText(this@LoginActivity, user?.email,
              Toast.LENGTH_SHORT).show()
          startActivity(Intent(this, MainActivity::class.java))
          finish()
        }, { error ->
          progressBar.visibility = View.INVISIBLE
          // If sign in fails, display a message to the user.
          Timber.e("signInWithCredential:failure ${error.localizedMessage}")
          Toast.makeText(this@LoginActivity, "Authentication failed.",
              Toast.LENGTH_SHORT).show()
        })
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}
