package matt.prim.uint

import matt.prim.long.toByteArray
import java.nio.ByteBuffer

fun UInt.toByteArray() =
  ByteBuffer.allocate(4).put(this.toLong().toByteArray().toList().subList(4, 8).toByteArray()).array()