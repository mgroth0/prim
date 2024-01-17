@file:OptIn(ExperimentalEncodingApi::class, ExperimentalEncodingApi::class)

package matt.prim.base64

import matt.lang.NEVER
import matt.lang.anno.Related
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.math.ceil


@Related(34872983)
fun ByteArray.encodeToBase64() = Base64.encode(this)

fun ByteArray.decodeFromBase64() = Base64.decode(this)
fun ByteArray.encodeToURLBase64() = Base64.UrlSafe.encode(this)

fun ByteArray.decodeFromURLBase64() = Base64.UrlSafe.decode(this)

fun String.encodeToBase64() = encodeToByteArray().encodeToBase64()
fun String.decodeFromBase64() = Base64.decode(this)


private object Base64Ratio {
    const val CHARS = 4
    const val BYTES = 3
}

fun String.decodeFromBase64UpToNBytes(n: Int): ByteArray {
    val numCharsToDecode = ceil(n / Base64Ratio.BYTES.toDouble()).toInt() * Base64Ratio.CHARS
    val s = this.take(numCharsToDecode)
    val decoded = s.decodeFromBase64()
    val decodedSize = decoded.size
    return when {
        decodedSize == n -> {
            decoded
        }

        decodedSize < n  -> {
            decoded
        }

        decodedSize > n  -> {
            decoded.copyOf(n)
        }

        else             -> NEVER
    }
}

fun String.encodeToURLBase64() = encodeToByteArray().encodeToURLBase64()
fun String.decodeFromURLBase64() = encodeToByteArray().decodeFromURLBase64()
fun String.decodeFromURLBase64AsString() = decodeFromURLBase64().decodeToString()
/*String(decodeFromURLBase64(), DEFAULT_CHARSET)*/

