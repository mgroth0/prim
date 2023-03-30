package matt.prim.byte.hex

import kotlin.experimental.and

private val hexArray = "0123456789ABCDEF".toCharArray()

/*RELATED TO matt.prim.base64. TODO: They structured together*/

fun ByteArray.toHex() = joinToString("") { it.toHex() }

fun Byte.toHex(): String {
  val v = (this and 0xFF.toByte()).toInt()
  return charArrayOf(hexArray[v ushr 4], hexArray[v and 0x0F]).concatToString()
}
