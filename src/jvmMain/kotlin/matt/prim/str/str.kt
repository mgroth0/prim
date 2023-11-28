package matt.prim.str

import matt.lang.charset.DEFAULT_CHARSET
import java.net.URLEncoder

/*https://stackoverflow.com/questions/4737841/urlencoder-not-able-to-translate-space-character*/
fun String.urlEncode() = URLEncoder.encode(
    this, DEFAULT_CHARSET
).replace("+", "%20")


//actual fun String.toByteArrayCommon() = encodeToByteArray()