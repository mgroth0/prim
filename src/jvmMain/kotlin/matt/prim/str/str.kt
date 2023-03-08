package matt.prim.str

import matt.prim.byte.decodeFromURLBase64
import matt.prim.byte.encodeToBase64
import matt.prim.byte.encodeToURLBase64
import java.net.URLEncoder
import java.util.Base64

/*https://stackoverflow.com/questions/4737841/urlencoder-not-able-to-translate-space-character*/
fun String.urlEncode() = URLEncoder.encode(
  this, Charsets.UTF_8
).replace("+", "%20")


fun String.encodeToBase64() = toByteArray().encodeToBase64()
fun String.decodeFromBase64() = Base64.getDecoder().decode(this)


fun String.encodeToURLBase64() = toByteArray().encodeToURLBase64()
fun String.decodeFromURLBase64() = String(toByteArray().decodeFromURLBase64(), Charsets.UTF_8)