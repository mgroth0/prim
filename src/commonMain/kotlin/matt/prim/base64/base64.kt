@file:OptIn(ExperimentalEncodingApi::class, ExperimentalEncodingApi::class)

package matt.prim.base64

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


fun ByteArray.encodeToBase64() = Base64.encode(this)

fun ByteArray.decodeFromBase64() = Base64.decode(this)
fun ByteArray.encodeToURLBase64() = Base64.UrlSafe.encode(this)

fun ByteArray.decodeFromURLBase64() = Base64.UrlSafe.decode(this)

fun String.encodeToBase64() = encodeToByteArray().encodeToBase64()
fun String.decodeFromBase64() = Base64.decode(this)
fun String.encodeToURLBase64() = encodeToByteArray().encodeToURLBase64()
fun String.decodeFromURLBase64() = encodeToByteArray().decodeFromURLBase64()
fun String.decodeFromURLBase64AsString() = decodeFromURLBase64().decodeToString()
/*String(decodeFromURLBase64(), Charsets.UTF_8)*/

