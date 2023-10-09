package matt.prim.ushort

import java.nio.ByteBuffer
import java.nio.ByteOrder

fun UShort.toByteArray(order: ByteOrder) = ByteBuffer.allocate(2).order(order).putChar(toInt().toChar()).array()
