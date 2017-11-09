package com.example.marcin.meetfriends.ui.login

import com.example.marcin.meetfriends.di.ScreenScope
import com.example.marcin.meetfriends.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by marci on 2017-11-09.
 */
@ScreenScope
class LoginPresenter @Inject constructor(

) : BasePresenter<LoginContract.View>(), LoginContract.Presenter {
}