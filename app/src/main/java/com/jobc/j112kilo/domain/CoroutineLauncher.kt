package com.jobc.j112kilo.domain

import kotlinx.coroutines.*

interface CoroutineLauncher : CoroutineScope {

    fun CoroutineLauncher.LaunchSafely(
        onLoading: (Boolean) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) : Job = launch {
        var isCancellation = false
        onLoading(true)
        try {
            block()
        } catch (error: Throwable) {
            error.printStackTrace()
            isCancellation = error is CancellationException
            if (!isActive) return@launch
            onError?.invoke(error)
        } finally {
            if (!isCancellation) onLoading(false)
        }
    }
}