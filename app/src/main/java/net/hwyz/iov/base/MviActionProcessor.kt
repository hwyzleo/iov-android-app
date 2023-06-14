package net.hwyz.iov.base

interface MviActionProcessor<A : MviAction, R : MviResult> {
    suspend fun executeAction(action: A): R
}