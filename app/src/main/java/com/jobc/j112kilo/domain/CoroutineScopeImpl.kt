package com.jobc.j112kilo.domain

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class CoroutineScopeImpl() : CoroutineScope, Cancellable {

    private var job: Job = Job()
    private var dispatcher: CoroutineContext = Dispatchers.Main + job
    private var jobDispatcher: CoroutineDispatcher = Dispatchers.IO

    override val coroutineContext: CoroutineContext
        get() {
            if (job.isCancelled) {
                job = Job()
                dispatcher = Dispatchers.Main + job
            }
            return dispatcher
        }

    protected suspend fun <T> ioAsync(block: suspend () -> T) : T =
        withContext(jobDispatcher) { block() }

    override fun cancel() {
        job.cancel(CancellationException())
    }
}