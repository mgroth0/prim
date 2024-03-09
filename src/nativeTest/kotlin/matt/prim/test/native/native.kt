package matt.prim.test.native

import matt.prim.str.urlEncode
import kotlin.test.Test


class PrimNativeTests() {
    /*
    I am seeing very weird non-fatal warnings like:

    i: <matt.flow:prim> @ /Users/matthewgroth/registered/ide/all/k/prim/src/commonMain/kotlin/matt/prim/str/str.kt:16:49: Function 'encode' can not be called: No function found for symbol 'net.thauvin.erik.urlencoder/UrlEncoderUtil.encode|encode#static(kotlin.String;kotlin.String;kotlin.Boolean){}[0]'

    And I see these even when the function isn't called (its as if a the warning was emitted while the classpath was being scanned at runtime or something)

    So I making this test to see if `ncode` truly cannot be called... and if so what error I get. And of course, once fixed, this will be to ensure it stays fixed.

    Fixed it!
    https://github.com/ethauvin/urlencoder/issues/12

     */
    @Test
    fun canCallUrlEncode() {
        "".urlEncode()
    }
}
