package com.example.marcin.meetfriends.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.marcin.meetfriends.R
import com.example.marcin.meetfriends.mvp.BaseActivity
import com.example.marcin.meetfriends.ui.main.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber


class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

  private val auth: FirebaseAuth = FirebaseAuth.getInstance()
  private val callbackManager: CallbackManager = CallbackManager.Factory.create()

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
    setContentView(R.layout.activity_login)
    loginButton.setReadPermissions("email", "public_profile")
    loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
      override fun onSuccess(loginResult: LoginResult) {
        Timber.d("facebook:onSuccess: $loginResult")
        handleFacebookAccessToken(loginResult.accessToken)
      }

      override fun onCancel() {
        Timber.d("facebook:onCancel")
        // ...
      }

      override fun onError(error: FacebookException) {
        Timber.e("facebook:onError $error")
        // ...
      }
    })
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    // Pass the activity result back to the Facebook SDK
    callbackManager.onActivityResult(requestCode, resultCode, data)
  }

  private fun handleFacebookAccessToken(token: AccessToken) {
    Timber.d("handleFacebookAccessToken: $token")

    val credential = FacebookAuthProvider.getCredential(token.token)

    auth.signInWithCredential(credential)
        .addOnCompleteListener(this) { task ->
          if (task.isSuccessful) {
            // Sign in success, update UI with the signed-in user's information
            Timber.d("signInWithCredential:success")
            val user = auth.currentUser
            Toast.makeText(this@LoginActivity, user?.email,
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
          } else {
            // If sign in fails, display a message to the user.
            Timber.e("signInWithCredential:failure ${task.exception}")
            Toast.makeText(this@LoginActivity, "Authentication failed.",
                Toast.LENGTH_SHORT).show()
//            updateUI(null)
          }

          // ...
        }
  }


}
