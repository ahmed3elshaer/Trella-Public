package com.trella.common.viewstate

interface MviViewState

abstract class BaseViewState<T>(open val status: Status = Status.IDLE,
                                open val payload: T? = null,
                                open val error: Throwable? = null) :
    MviViewState {
    enum class Status {
        LOADING, SUCCESS, FAILURE,IDLE
    }
}