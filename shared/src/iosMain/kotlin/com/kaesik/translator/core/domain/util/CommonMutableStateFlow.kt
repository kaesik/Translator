package com.kaesik.translator.core.domain.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class CommonMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>
) : CommonStateFlow<T>(flow), MutableStateFlow<T>{

    override val replayCache: List<T>
        get() = flow.replayCache

    override val subscriptionCount: StateFlow<Int>
        get() = flow.subscriptionCount

    override var value: T
        get() = flow.value
        set(value) {
            flow.value = value
        }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        flow.resetReplayCache()
    }

    override fun tryEmit(value: T): Boolean {
        return flow.tryEmit(value)
    }

    override suspend fun emit(value: T) {
        flow.emit(value)
    }

    override fun compareAndSet(expect: T, update: T): Boolean {
        return flow.compareAndSet(expect, update)
    }

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        flow.collect(collector)
    }
}
