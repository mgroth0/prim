
package matt.prim.float

import matt.prim.bytestr.toByteString
import matt.prim.endian.MyByteOrder
import matt.prim.int.toByteArray

fun Float.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0f



const val FLOAT_BYTE_LEN = 4

fun Float.toByteString(order: MyByteOrder, canonicalizeNaN: Boolean) = toByteArray(order, canonicalizeNaN = canonicalizeNaN).toByteString()

fun Float.toByteArray(
    order: MyByteOrder,
    canonicalizeNaN: Boolean
): ByteArray {
    val bits = if (canonicalizeNaN) toBits() else toRawBits()
    return bits.toByteArray(order)
}
