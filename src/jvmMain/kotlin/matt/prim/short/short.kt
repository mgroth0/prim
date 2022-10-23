package matt.prim.short

import java.nio.ByteBuffer

fun Short.toByteArray() = ByteBuffer.allocate(2).putShort(this).array()