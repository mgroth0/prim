package matt.prim.float

import java.nio.ByteBuffer

const val FLOAT_BYTE_LEN = 4

fun Float.toByteArray() = ByteBuffer.allocate(FLOAT_BYTE_LEN).putFloat(this).array()

fun Float.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0f
