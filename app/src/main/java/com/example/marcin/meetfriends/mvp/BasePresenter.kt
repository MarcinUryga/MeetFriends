package com.example.marcin.meetfriends.mvp

import javax.inject.Inject

/**
 * Created by marci on 2017-11-09.
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter {

  @Inject lateinit var view: V

  override fun onViewCreated() {}

  override fun start() {}

  override fun resume() {}

  override fun pause() {}

  override fun stop() {}

  override fun destroy() {}
}