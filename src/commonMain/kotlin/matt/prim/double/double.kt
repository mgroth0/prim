
package matt.prim.double

import matt.prim.bytestr.toByteString
import matt.prim.endian.MyByteOrder
import matt.prim.long.toByteArray
import kotlin.math.ceil
import kotlin.math.floor


fun Double.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0.0


fun Double.isWholeNumber(): Boolean = ceil(this) == floor(this)


fun Double.requireIsWholeNumber(): Double {
    require(isWholeNumber()) {
        "$this should be a whole number"
    }
    return this
}

fun Double.verifyWholeToInt(): Int {
    requireIsWholeNumber()
    return toInt()
}



const val DOUBLE_BYTE_LEN = 8


const val JAVA_DOES_CANONICALIZE_NAN = false

fun Double.toByteString(order: MyByteOrder, canonicalizeNaN: Boolean) = toByteArray(order, canonicalizeNaN = canonicalizeNaN).toByteString()
fun Double.toByteArray(
    order: MyByteOrder,
    canonicalizeNaN: Boolean
): ByteArray {
    val bits = if (canonicalizeNaN) toBits() else toRawBits()
    return bits.toByteArray(order)
}
