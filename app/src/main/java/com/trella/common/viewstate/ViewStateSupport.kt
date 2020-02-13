package com.trella.common.viewstate

interface ViewStateConsumerScope<V> {
    fun doOnLoading(block: () -> Unit)
    fun doOnSuccess(block: (V) -> Unit)
    fun doOnError(block: (Throwable) -> Unit)
}

fun <Payload> BaseViewState<Payload>.consume(block: ViewStateConsumerScope<Payload>.() -> Unit) {
    block(object : ViewStateConsumerScope<Payload> {
        override fun doOnLoading(block: () -> Unit) {
            if (status == BaseViewState.Status.LOADING) {
                block()
            }
        }

        override fun doOnSuccess(block: (Payload) -> Unit) {
            if (status == BaseViewState.Status.SUCCESS) {
                block(payload!!)
            }
        }

        override fun doOnError(block: (Throwable) -> Unit) {
            if (status == BaseViewState.Status.FAILURE) {
                if (error != null) {
                    block(error!!)
                }

            }
        }

    })
}