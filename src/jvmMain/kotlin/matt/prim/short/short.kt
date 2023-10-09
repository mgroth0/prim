package matt.prim.short

import java.nio.ByteBuffer
import java.nio.ByteOrder

fun Short.toByteArray(order: ByteOrder) = ByteBuffer.allocate(2).order(order).putShort(this).array()