@file:JvmName("ByteKtJvm")

package matt.prim.byte

import java.nio.ByteBuffer
fun ByteArray.toInt() = ByteBuffer.wrap(this).int
fun ByteArray.toLong() = ByteBuffer.wrap(this).long
fun ByteArray.toFloat() = ByteBuffer.wrap(this).float
fun ByteArray.toDouble() = ByteBuffer.wrap(this).double

