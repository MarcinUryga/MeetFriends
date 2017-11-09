package com.example.marcin.meetfriends.ui.login

import android.os.Bundle
import com.example.marcin.meetfriends.R
import com.example.marcin.meetfriends.mvp.BaseActivity
import dagger.android.AndroidInjection

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
  }
}
