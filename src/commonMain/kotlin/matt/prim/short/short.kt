package matt.prim.short

import matt.prim.bytestr.toByteString
import matt.prim.endian.MyByteOrder
import matt.prim.endian.MyByteOrder.BIG
import matt.prim.endian.MyByteOrder.LITTLE
import org.kotlincrypto.endians.BigEndian.Companion.toBigEndian
import org.kotlincrypto.endians.LittleEndian.Companion.toLittleEndian

fun Short.toByteString(order: MyByteOrder) = toByteArray(order).toByteString()
fun Short.toByteArray(order: MyByteOrder): ByteArray =
    when (order) {
        BIG    -> toBigEndian().toByteArray()
        LITTLE -> toLittleEndian().toByteArray()
    }
