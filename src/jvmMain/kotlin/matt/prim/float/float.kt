package matt.prim.float

import java.nio.ByteBuffer
import java.nio.ByteOrder

const val FLOAT_BYTE_LEN = 4

fun Float.toByteArray(
    order: ByteOrder
) = ByteBuffer.allocate(FLOAT_BYTE_LEN).order(order).putFloat(this).array()

fun Float.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0f
