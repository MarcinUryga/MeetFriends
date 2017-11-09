package com.example.marcin.meetfriends.di

import android.content.Context
import com.example.marcin.meetfriends.MeetFriendsApplication
import dagger.Module
import dagger.Provides

/**
 * Created by marci on 2017-11-09.
 */

@Module
class ApplicationModule {

  @Provides
  @ApplicationContext
  fun provideAppContext(meetFriendsApplication: MeetFriendsApplication): Context {
    return meetFriendsApplication
  }
}