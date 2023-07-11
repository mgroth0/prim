package matt.prim.byte

import matt.prim.byte.hex.toHex
import kotlin.jvm.JvmInline

fun ByteArray.reasonablePrintableString(): String {
    return when {
        size == 0 -> "empty ByteArray"
        size == 1 -> "ByteArray[${first().bits}]"
        size <= 8 -> "ByteArray[${joinToString("") { it.toHex() }}]"
        else      -> "bytearray with size=$size"
    }
}


inline val Byte.bits get() = Bits(this)

sealed class Bit
object Up : Bit() {
    override fun toString() = "1"
}

object Down : Bit() {
    override fun toString() = "0"
}

@JvmInline
value class Bits(val byte: Byte) : Iterable<Bit> {
    /*see jvm bitset*/
    val first get() = byte.takeHighestOneBit()
    override fun iterator(): Iterator<Bit> {
        return (0..7).map { getBit(byte.toInt(), it) }.map {
            when (it) {
                0    -> Down
                1    -> Up
                else -> error("got weird bit: $it")
            }
        }.iterator()
    }

    private fun getBit(
        value: Int,
        position: Int
    ): Int {
        return (value shr position) and 1
    }

    override fun toString() = iterator().asSequence().toList().joinToString("")

}

fun ByteArray.toShort(): Short {
    return ((this[0].toInt() shl 8) or
            (this[1].toInt() and 0xff)).toShort()
}

fun ByteArray.toShort(offset: Int): Short {
    return ((this[offset + 0].toInt() shl 8) or
            (this[offset + 1].toInt() and 0xff)).toShort()
}

fun ByteArray.toInt(): Int {
    return (this[0].toInt() shl 24) or
            (this[1].toInt() and 0xff shl 16) or
            (this[2].toInt() and 0xff shl 8) or
            (this[3].toInt() and 0xff)
}

fun ByteArray.toInt(offset: Int): Int {
    return (this[offset + 0].toInt() shl 24) or
            (this[offset + 1].toInt() and 0xff shl 16) or
            (this[offset + 2].toInt() and 0xff shl 8) or
            (this[offset + 3].toInt() and 0xff)
}

fun ByteArray.toLong(): Long {
    return ((this[0].toInt() shl 56) or
            (this[1].toInt() and 0xff shl 48) or
            (this[2].toInt() and 0xff shl 40) or
            (this[3].toInt() and 0xff shl 32) or
            (this[4].toInt() and 0xff shl 24) or
            (this[5].toInt() and 0xff shl 16) or
            (this[6].toInt() and 0xff shl 8) or
            (this[7].toInt() and 0xff)).toLong()
}

fun ByteArray.toLong(offset: Int): Long {
    return ((this[offset + 0].toInt() shl 56) or
            (this[offset + 1].toInt() and 0xff shl 48) or
            (this[offset + 2].toInt() and 0xff shl 40) or
            (this[offset + 3].toInt() and 0xff shl 32) or
            (this[offset + 4].toInt() and 0xff shl 24) or
            (this[offset + 5].toInt() and 0xff shl 16) or
            (this[offset + 6].toInt() and 0xff shl 8) or
            (this[offset + 7].toInt() and 0xff)).toLong()
}