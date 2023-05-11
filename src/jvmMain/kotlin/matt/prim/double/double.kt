package matt.prim.double

import java.nio.ByteBuffer
import kotlin.math.ceil
import kotlin.math.floor

const val DOUBLE_BYTE_LEN = 8

fun Double.toByteArray() = ByteBuffer.allocate(DOUBLE_BYTE_LEN).putDouble(this).array()

fun Double.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0.0

fun Double.isWholeNumber(): Boolean {
  return ceil(this) == floor(this)
}

fun Double.requireIsWholeNumber(): Double {
  require(isWholeNumber())
  return this
}
