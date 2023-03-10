package matt.prim.base64

import java.util.Base64
import java.util.Base64.Decoder
import java.util.Base64.Encoder

private val BASE_64_ENCODER: Encoder = Base64.getEncoder()
private val BASE_64_DECODER: Decoder = Base64.getDecoder()

private val BASE_64_ENCODER_NO_PADDING: Encoder = BASE_64_ENCODER.withoutPadding()

private val BASE_64_URL_ENCODER: Encoder = Base64.getEncoder()
private val BASE_64_URL_DECODER: Decoder = Base64.getDecoder()

fun ByteArray.encodeToBase64() = BASE_64_ENCODER.encodeToString(this)
fun ByteArray.encodeToBase64WithoutPadding() = BASE_64_ENCODER_NO_PADDING.encodeToString(this)
fun ByteArray.decodeFromBase64() = BASE_64_DECODER.decode(this)
fun ByteArray.encodeToURLBase64() = BASE_64_URL_ENCODER.encodeToString(this)
fun ByteArray.decodeFromURLBase64() = BASE_64_URL_DECODER.decode(this)

fun String.encodeToBase64() = toByteArray().encodeToBase64()
fun String.decodeFromBase64() = BASE_64_DECODER.decode(this)
fun String.encodeToURLBase64() = toByteArray().encodeToURLBase64()
fun String.decodeFromURLBase64() = toByteArray().decodeFromURLBase64()
fun String.decodeFromURLBase64AsString() = String(decodeFromURLBase64(), Charsets.UTF_8)




