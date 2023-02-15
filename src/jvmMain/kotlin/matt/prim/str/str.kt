package matt.prim.str

import java.net.URLEncoder

fun String.urlEncodePlusNotPecentForSpaces() = URLEncoder.encode(
  this, Charsets.UTF_8
)