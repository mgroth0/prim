package matt.prim.charset

import matt.lang.charset.j.DEFAULT_CHARSET
import java.nio.charset.CharsetDecoder


fun newDefaultCharsetDecoder(): CharsetDecoder = DEFAULT_CHARSET.newDecoder()
