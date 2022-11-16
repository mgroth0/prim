package matt.prim.double

import java.nio.ByteBuffer

fun Double.toByteArray() = ByteBuffer.allocate(8).putDouble(this).array()