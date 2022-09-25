package matt.prim.byte

import kotlin.jvm.JvmInline

inline val Byte.bits get() = Bits(this)

@JvmInline value class Bits(val byte: Byte) {
  /*see jvm bitset*/
  val first get() = byte.takeHighestOneBit()
}

