package com.jobc.j112kilo.domain

interface Interactor<T: InteractorOut> : Cancellable {
    fun setupInteractorOut(out: T)
}