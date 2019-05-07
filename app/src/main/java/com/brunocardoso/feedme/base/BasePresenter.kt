package com.brunocardoso.feedme.base

import com.brunocardoso.feedme.injection.component.DaggerPresenterInjector
import com.brunocardoso.feedme.injection.component.PresenterInjector
import com.brunocardoso.feedme.injection.module.ContextModule
import com.brunocardoso.feedme.injection.module.NetworkModule
import com.brunocardoso.feedme.ui.post.presenter.PostPresenter

abstract class BasePresenter<out V: BaseView>(protected val view: V) {
    /**
     * The inhector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}
    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostPresenter -> injector.inject(this)
        }
    }

}