@file:JvmName("EndianJvmAndroidKt")

package matt.prim.pw.endian

import matt.lang.NEVER
import matt.prim.endian.MyByteOrder
import matt.prim.endian.MyByteOrder.BIG
import matt.prim.endian.MyByteOrder.LITTLE
import java.nio.ByteOrder


val MyByteOrder.java
    get() = when (this) {
        BIG    -> ByteOrder.BIG_ENDIAN
        LITTLE -> ByteOrder.LITTLE_ENDIAN
    }

val java.nio.ByteOrder.myByteOrder
    get() = when (this) {
        java.nio.ByteOrder.BIG_ENDIAN    -> BIG
        java.nio.ByteOrder.LITTLE_ENDIAN -> LITTLE
        else                             -> NEVER
    }