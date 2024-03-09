package matt.prim.uint

import matt.prim.bytestr.toByteString
import matt.prim.endian.MyByteOrder
import matt.prim.int.toByteArray

fun UInt.toByteString(order: MyByteOrder) = toByteArray(order).toByteString()
fun UInt.toByteArray(order: MyByteOrder) = toInt().toByteArray(order)
