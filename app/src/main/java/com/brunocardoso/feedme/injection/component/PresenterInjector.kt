package com.brunocardoso.feedme.injection.component

import com.brunocardoso.feedme.base.BaseView
import com.brunocardoso.feedme.injection.module.ContextModule
import com.brunocardoso.feedme.injection.module.NetworkModule
import com.brunocardoso.feedme.ui.post.presenter.PostPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(postPresenter: PostPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}