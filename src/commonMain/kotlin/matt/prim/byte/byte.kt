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
object Up: Bit() {
  override fun toString() = "1"
}

object Down: Bit() {
  override fun toString() = "0"
}

@JvmInline value class Bits(val byte: Byte): Iterable<Bit> {
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

  private fun getBit(value: Int, position: Int): Int {
	return (value shr position) and 1
  }

  override fun toString() = iterator().asSequence().toList().joinToString("")

}


