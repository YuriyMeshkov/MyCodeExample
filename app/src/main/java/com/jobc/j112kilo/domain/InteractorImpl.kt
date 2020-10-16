package com.jobc.j112kilo.domain

abstract class InteractorImpl<T : InteractorOut> :
    Interactor<T>, CoroutineScopeImpl(), CoroutineLauncher{

    protected lateinit var out: T

    override fun setupInteractorOut(out: T) {
        this.out = out
    }
}