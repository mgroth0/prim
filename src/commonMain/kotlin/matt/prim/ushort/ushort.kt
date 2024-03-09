package matt.prim.ushort

import matt.prim.bytestr.toByteString
import matt.prim.endian.MyByteOrder
import matt.prim.short.toByteArray

fun UShort.toByteString(order: MyByteOrder) = toByteArray(order).toByteString()
fun UShort.toByteArray(order: MyByteOrder) = toShort().toByteArray(order)
