package net.hwyz.iov.base.presentation

import net.hwyz.iov.base.MviAction
import net.hwyz.iov.base.MviResult

interface MviActionProcessor<A : MviAction, R : MviResult> {
    suspend fun executeAction(action: A): R
}