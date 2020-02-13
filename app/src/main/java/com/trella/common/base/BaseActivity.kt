package com.trella.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.trella.common.extensions.hide
import com.trella.common.extensions.show
import com.trella.common.viewstate.BaseViewState
import com.trella.common.viewstate.consume
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<E, S : BaseViewState<E>, T : BaseViewModel<S>> :
    DaggerAppCompatActivity(), ViewStates {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(
                (this.javaClass
                    .genericSuperclass as ParameterizedType)
                    .actualTypeArguments[2] as Class<T>
            )
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
                    renderPayload(it!!).also {
                        renderLoading(
                            false
                        )
                    }
                }

            }
        })
        triggerEvents()
        setupViews()
    }

    protected abstract fun getLayoutResource(): Int
    protected abstract fun getLoadingView(): View?
    protected abstract fun triggerEvents()
    protected abstract fun setupViews()


    override fun renderError(message: String) {
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

    abstract fun renderPayload(payload: E)

    override fun renderLoading(isLoading: Boolean) {
        if (isLoading)
            getLoadingView()?.show()
        else
            getLoadingView()?.hide()
    }


}

interface ViewStates {
    fun renderLoading(isLoading: Boolean)

    fun renderError(message: String)
}