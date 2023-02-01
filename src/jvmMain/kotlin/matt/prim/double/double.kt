package matt.prim.double

import java.nio.ByteBuffer

const val DOUBLE_BYTE_LEN = 8

fun Double.toByteArray() = ByteBuffer.allocate(DOUBLE_BYTE_LEN).putDouble(this).array()

fun Double.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0.0