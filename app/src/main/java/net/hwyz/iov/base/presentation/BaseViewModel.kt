package net.hwyz.iov.base.presentation

import androidx.lifecycle.ViewModel
import net.hwyz.iov.base.MviAction
import net.hwyz.iov.base.MviIntent
import net.hwyz.iov.base.MviResult
import net.hwyz.iov.base.MviState

abstract class BaseViewModel<I : MviIntent, S : MviState, A : MviAction, R : MviResult> :
    ViewModel(),
    MviViewModel<S> {

    protected abstract val actionProcessor: MviActionProcessor<A, R>

    abstract fun actionFromIntent(intent: I): A

    abstract suspend fun reducer(result: R)

}