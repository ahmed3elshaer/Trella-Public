package com.trella.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel<T> : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    protected abstract val viewStateImpl: MutableLiveData<T>

    val viewState: LiveData<T> get() = viewStateImpl

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun add(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }


    fun post(state: T) {
        viewStateImpl.value = state
    }

    fun previousValue() = viewStateImpl.value!!

    fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }


}