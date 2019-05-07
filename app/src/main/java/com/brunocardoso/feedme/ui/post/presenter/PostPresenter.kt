package com.brunocardoso.feedme.ui.post.presenter

import com.brunocardoso.feedme.R
import com.brunocardoso.feedme.base.BasePresenter
import com.brunocardoso.feedme.base.BaseView
import com.brunocardoso.feedme.network.PostApi
import com.brunocardoso.feedme.ui.post.view.PostView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadPosts()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadPosts() {
        view.showLoading()
        subscription = postApi
            .getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { postList -> view.updatePosts(postList) },
                { view.showError(R.string.unknown_error) }
            )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}