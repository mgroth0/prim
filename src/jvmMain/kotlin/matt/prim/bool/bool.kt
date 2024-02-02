package matt.prim.bool

fun Boolean.toByteArray() = byteArrayOf(if (this) 1.toByte() else 0.toByte())
