package matt.prim.pw.endian

import matt.lang.common.NEVER
import matt.prim.endian.MyByteOrder.BIG
import matt.prim.endian.MyByteOrder.LITTLE
import java.nio.ByteOrder

fun platformByteOrder() =
    when (ByteOrder.nativeOrder()) {
        ByteOrder.BIG_ENDIAN -> BIG
        ByteOrder.LITTLE_ENDIAN -> LITTLE
        else -> NEVER
    }
