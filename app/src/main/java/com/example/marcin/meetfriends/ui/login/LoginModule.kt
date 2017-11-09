package com.example.marcin.meetfriends.ui.login

import dagger.Binds
import dagger.Module

/**
 * Created by marci on 2017-11-09.
 */
@Module
abstract class LoginModule {

  @Binds
  abstract fun bindView(view: LoginActivity): LoginContract.View

  @Binds
  abstract fun bindPresenter(presenter: LoginPresenter): LoginContract.Presenter
}