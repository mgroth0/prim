package matt.prim.int

import matt.prim.endian.MyByteOrder
import matt.prim.endian.MyByteOrder.BIG
import matt.prim.endian.MyByteOrder.LITTLE
import kotlin.math.ceil
import kotlin.math.floor

class UInt8


fun Int.toByteArray(
    order: MyByteOrder
): ByteArray {
    val buffer = ByteArray(4)
    when (order) {
        BIG    -> {

            buffer[3] = (this shr 0).toByte()
            buffer[2] = (this shr 8).toByte()
            buffer[1] = (this shr 16).toByte()
            buffer[0] = (this shr 24).toByte()
        }

        LITTLE -> {
            buffer[0] = (this shr 0).toByte()
            buffer[1] = (this shr 8).toByte()
            buffer[2] = (this shr 16).toByte()
            buffer[3] = (this shr 24).toByte()
        }
    }
    return buffer

}


fun Double.ceilInt() = ceil(this).toInt()
fun Double.floorInt() = floor(this).toInt()
fun Float.ceilInt() = ceil(this).toInt()
fun Float.floorInt() = floor(this).toInt()