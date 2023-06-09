package net.hwyz.iov.ui.page.my

import net.hwyz.iov.base.MviActionProcessor
import net.hwyz.iov.data.http.HttpService
import javax.inject.Inject

open class MyProcessor @Inject constructor(
    private var service: HttpService,
) :
    MviActionProcessor<MyAction, MyResult> {

    override suspend fun executeAction(action: MyAction): MyResult {
        return when (action) {
            MyAction.CheckLocalUser -> MyResult.DefaultResult
        }
    }
}