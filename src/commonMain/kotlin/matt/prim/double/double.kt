@file:JvmName("DoubleCommonKt")

package matt.prim.double

import kotlin.jvm.JvmName
import kotlin.math.ceil
import kotlin.math.floor


fun Double.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0.0


fun Double.isWholeNumber(): Boolean = ceil(this) == floor(this)


fun Double.requireIsWholeNumber(): Double {
    require(isWholeNumber()) {
        "$this should be a whole number"
    }
    return this
}

fun Double.verifyWholeToInt(): Int {
    requireIsWholeNumber()
    return toInt()
}



