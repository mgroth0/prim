package matt.prim.bytestr

import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.buildByteString

fun ByteArray.toByteString() = ByteString(this)


fun ByteArray.toByteString(
    startIndex: Int,
    byteCount: Int
) = ByteString(this, startIndex, endIndex = startIndex + byteCount)


fun String.encodeToByteString(): ByteString = encodeToByteArray().toByteString()



inline fun byteString(size: Int, provideByte: () -> Byte): ByteString =
    buildByteString(size) {
        repeat(size) {
            append(provideByte())
        }
    }


