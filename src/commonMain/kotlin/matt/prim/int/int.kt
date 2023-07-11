package matt.prim.int

class UInt8


fun Int.toByteArray(): ByteArray {
    val buffer = ByteArray(4)
    buffer[3] = (this shr 0).toByte()
    buffer[2] = (this shr 8).toByte()
    buffer[1] = (this shr 16).toByte()
    buffer[0] = (this shr 24).toByte()
    return buffer
}