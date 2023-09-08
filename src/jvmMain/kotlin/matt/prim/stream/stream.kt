package matt.prim.stream

import matt.lang.safeconvert.requireIsByte
import matt.prim.byte.toDouble
import matt.prim.byte.toInt
import matt.prim.byte.toLong
import matt.prim.byte.toShort
import matt.prim.double.toByteArray
import matt.prim.int.toByteArray
import matt.prim.long.toByteArray
import java.io.InputStream
import java.io.OutputStream

fun InputStream.readBool() = when (val b = read()) {
    1    -> true
    0    -> false
    else -> error("expected 0 or 1 for bool but got $b")
}

fun InputStream.readShort() = readNBytes(2).toShort()
fun InputStream.readInt() = readNBytes(4).toInt()
fun InputStream.readLong() = readNBytes(8).toLong()
fun InputStream.readByte() = read().requireIsByte()
fun InputStream.readDouble() = readNBytes(8).toDouble()


fun OutputStream.writeBool(bool: Boolean): Unit = if (bool) write(1) else write(0)
fun OutputStream.writeInt(int: Int): Unit = write(int.toByteArray())
fun OutputStream.writeLong(long: Long): Unit = write(long.toByteArray())
fun OutputStream.writeDouble(double: Double): Unit = write(double.toByteArray())
fun OutputStream.writeByte(byte: Byte): Unit = write(byte.toInt())