package matt.prim.uint

import matt.prim.long.toByteArray
import java.nio.ByteBuffer
import java.nio.ByteOrder

fun UInt.toByteArray(order: ByteOrder) =
    ByteBuffer.allocate(4).put(this.toLong().toByteArray(order).toList().subList(4, 8).toByteArray()).array()
