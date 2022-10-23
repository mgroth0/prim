package matt.prim.long

import java.nio.ByteBuffer

fun Long.toByteArray() = ByteBuffer.allocate(8).putLong(this).array()