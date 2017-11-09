package com.example.marcin.meetfriends.ui.main

import android.os.Bundle
import com.example.marcin.meetfriends.R
import com.example.marcin.meetfriends.mvp.BaseActivity
import dagger.android.AndroidInjection

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
