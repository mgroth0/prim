package matt.prim.long

import matt.prim.bytestr.toByteString
import matt.prim.endian.MyByteOrder
import matt.prim.endian.MyByteOrder.BIG
import matt.prim.endian.MyByteOrder.LITTLE
import org.kotlincrypto.endians.BigEndian.Companion.toBigEndian
import org.kotlincrypto.endians.LittleEndian.Companion.toLittleEndian

fun Long.toByteString(order: MyByteOrder) = toByteArray(order).toByteString()
fun Long.toByteArray(order: MyByteOrder): ByteArray =
    when (order) {
        BIG    -> toBigEndian().toByteArray()
        LITTLE -> toLittleEndian().toByteArray()
    }
