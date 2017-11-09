package com.example.marcin.meetfriends.ui.main

import com.example.marcin.meetfriends.di.ScreenScope
import com.example.marcin.meetfriends.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by marci on 2017-11-09.
 */
@ScreenScope
class MainPresenter @Inject constructor(

) : BasePresenter<MainContract.View>(), MainContract.Presenter {

}