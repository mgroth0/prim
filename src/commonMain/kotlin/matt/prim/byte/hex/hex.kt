package matt.prim.byte.hex

import matt.lang.anno.Related

private val hexArray = "0123456789ABCDEF".toCharArray()

@Related(34872983)
fun ByteArray.toHex() = joinToString("") { it.toHex() }
fun UByteArray.toHex() = joinToString("") { it.toHex() }

fun Byte.toHex(): String {
    val isPositive = this > 0
    if (!isPositive) {
        error("unclear what you want here with ${this}. Do you want like, -${toUByte().toHex()} or do you want to see the absolute hex?")
    }
    return toUByte().toHex()
    /*val v = (this and 0xFF.toByte()).toInt()
    return charArrayOf(hexArray[v ushr 4], hexArray[v and 0x0F]).concatToString()*/
}

fun UByte.toHex(): String {
    val v = (this and 0xFF.toUByte()).toInt()
    return charArrayOf(hexArray[v ushr 4], hexArray[v and 0x0F]).concatToString()
}
