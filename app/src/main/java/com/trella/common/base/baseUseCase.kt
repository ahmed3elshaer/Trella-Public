package com.trella.common.base

import com.trella.common.schedulers.BaseSchedulerProvider
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import javax.inject.Inject

abstract class BaseUseCase(private val baseSchedulerProvider: BaseSchedulerProvider) {
    fun <X> applySchedulers(): MaybeTransformer<X, X> {
        return MaybeTransformer { up ->
            up.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui())
        }
    }

    fun <X> applySchedulersSingle(): SingleTransformer<X, X> {
        return SingleTransformer { up ->
            up.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui())
        }
    }

    fun applySchedulersCompletable(): CompletableTransformer {
        return CompletableTransformer { up ->
            up.subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui())
        }
    }
}