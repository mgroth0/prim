package matt.prim.double

import java.nio.ByteBuffer
import java.nio.ByteOrder

const val DOUBLE_BYTE_LEN = 8

fun Double.toByteArray(order: ByteOrder) = ByteBuffer.allocate(DOUBLE_BYTE_LEN).order(order).putDouble(this).array()




