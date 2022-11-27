package matt.prim.ushort

import java.nio.ByteBuffer

fun UShort.toByteArray() = ByteBuffer.allocate(2).putChar(toInt().toChar()).array()
