package com.trella.common.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.trella.common.extensions.hide
import com.trella.common.extensions.show
import com.trella.common.viewstate.BaseViewState
import com.trella.common.viewstate.consume
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<S : BaseViewState<S>, T : BaseViewModel<S>> : DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        viewModel.viewState.observe(this, Observer { viewstate ->
            viewstate.consume {
               doOnError {
                   it.message?.let { message ->
                       renderError(message)
                   }.also { renderLoading(false) }
               }
               doOnLoading {
                   renderLoading(true)
               }
               doOnSuccess {
                   renderPayload(it.payload!!).also {
                       renderLoading(
                           false
                       )
                   }
               }

           }
        })
        useView()
    }

    protected abstract fun getLayoutResource(): Int
    protected abstract fun getLoadingView(): View?
    protected abstract fun useView()


    fun renderError(message: String) {
        showMessage(message)
    }

    fun showMessage(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        )
            .show()
    }

    abstract fun renderPayload(viewState: S)

    fun renderLoading(isLoading: Boolean) {
        if (isLoading)
            getLoadingView()?.show()
        else
            getLoadingView()?.hide()
    }

}