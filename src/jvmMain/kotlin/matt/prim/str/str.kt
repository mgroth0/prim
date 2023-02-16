package matt.prim.str

import java.net.URLEncoder

/*https://stackoverflow.com/questions/4737841/urlencoder-not-able-to-translate-space-character*/
fun String.urlEncodePlusNotPercentForSpaces() = URLEncoder.encode(
  this, Charsets.UTF_8
).replace("+", "%20")