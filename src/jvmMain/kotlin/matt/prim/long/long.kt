package matt.prim.long

import java.nio.ByteBuffer
import java.nio.ByteOrder

fun Long.toByteArray(order: ByteOrder): ByteArray = ByteBuffer.allocate(8).order(order).putLong(this).array()
