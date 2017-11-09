package com.example.marcin.meetfriends

import com.example.marcin.meetfriends.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by marci on 2017-11-09.
 */
class MeetFriendsApplication : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerApplicationComponent.builder().create(this)
  }

}