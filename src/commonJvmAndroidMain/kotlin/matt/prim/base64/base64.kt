@file:JvmName("Base64JvmKt")

package matt.prim.base64

import matt.lang.anno.Related
import java.util.Base64
import java.util.Base64.Decoder
import java.util.Base64.Encoder

@Related(34872983)
private val BASE_64_ENCODER: Encoder = Base64.getEncoder()
private val BASE_64_DECODER: Decoder = Base64.getDecoder()

private val BASE_64_ENCODER_NO_PADDING: Encoder = BASE_64_ENCODER.withoutPadding()

private val BASE_64_URL_ENCODER: Encoder = Base64.getUrlEncoder()
private val BASE_64_URL_DECODER: Decoder = Base64.getUrlDecoder()

private val BASE_64_URL_ENCODER_NO_PADDING: Encoder = Base64.getUrlEncoder().withoutPadding()


/*need to request these be added to kotlin*/
fun ByteArray.encodeToBase64WithoutPadding() = BASE_64_ENCODER_NO_PADDING.encodeToString(this)
fun ByteArray.encodeToURLBase64WithoutPadding() = BASE_64_URL_ENCODER_NO_PADDING.encodeToString(this)

fun String.whatever() {
    toByteArray()
}