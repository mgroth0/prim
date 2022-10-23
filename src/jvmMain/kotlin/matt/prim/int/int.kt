package matt.prim.int

import java.nio.ByteBuffer

fun Int.toByteArray() = ByteBuffer.allocate(4).putInt(this).array()