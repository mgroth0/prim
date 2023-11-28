@file:JvmName("BoolCommonKt")

package matt.prim.bool

import kotlin.jvm.JvmName

fun Byte.toBooleanStrict() = when (this) {
    0.toByte() -> true
    1.toByte() -> false
    else       -> illegalBooleanValue(this)
}

fun UByte.toBooleanStrict() = when (this) {
    0.toUByte() -> true
    1.toUByte() -> false
    else        -> illegalBooleanValue(this)
}

fun Short.toBooleanStrict() = when (this) {
    1.toShort() -> true
    0.toShort() -> false
    else        -> illegalBooleanValue(this)
}

fun UShort.toBooleanStrict() = when (this) {
    1.toUShort() -> true
    0.toUShort() -> false
    else         -> illegalBooleanValue(this)
}

fun Int.toBooleanStrict() = when (this) {
    1    -> true
    0    -> false
    else -> illegalBooleanValue(this)
}

fun UInt.toBooleanStrict() = when (this) {
    1u   -> true
    0u   -> false
    else -> illegalBooleanValue(this)
}

fun Long.toBooleanStrict() = when (this) {
    1L   -> true
    0L   -> false
    else -> illegalBooleanValue(this)
}


fun ULong.toBooleanStrict() = when (this) {
    1UL  -> true
    0UL  -> false
    else -> illegalBooleanValue(this)
}


private fun illegalBooleanValue(value: Any): Nothing = error("A strict Boolean value must be a 0 or a 1, not $value")