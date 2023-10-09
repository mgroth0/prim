package matt.prim.double

import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.ceil
import kotlin.math.floor

const val DOUBLE_BYTE_LEN = 8

fun Double.toByteArray(order: ByteOrder) = ByteBuffer.allocate(DOUBLE_BYTE_LEN).order(order).putDouble(this).array()

fun Double.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0.0

fun Double.isWholeNumber(): Boolean {
    return ceil(this) == floor(this)
}


fun Double.requireIsWholeNumber(): Double {
    require(isWholeNumber()) {
        "$this should be a whole number"
    }
    return this
}
