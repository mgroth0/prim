package matt.prim.stream

import matt.lang.safeconvert.requireIsByte
import matt.prim.byte.toDouble
import matt.prim.byte.toInt
import matt.prim.byte.toLong
import matt.prim.byte.toShort
import matt.prim.double.toByteArray
import matt.prim.int.toByteArray
import matt.prim.long.toByteArray
import matt.prim.pw.endian.myByteOrder
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteOrder

fun InputStream.readBool() = when (val b = read()) {
    1 -> true
    0 -> false
    else -> error("expected 0 or 1 for bool but got $b")
}

fun InputStream.readShort(order: ByteOrder) = readNBytes(2).toShort(order.myByteOrder)
fun InputStream.readInt(order: ByteOrder) = readNBytes(4).toInt(order.myByteOrder)
fun InputStream.readLong(order: ByteOrder) = readNBytes(8).toLong(order.myByteOrder)
fun InputStream.readByte() = read().requireIsByte()
fun InputStream.readDouble(order: ByteOrder) = readNBytes(8).toDouble(order)


fun OutputStream.writeBool(
    bool: Boolean,
): Unit = if (bool) write(1) else write(0)

fun OutputStream.writeInt(
    int: Int,
    order: ByteOrder
): Unit = write(int.toByteArray(order.myByteOrder))

fun OutputStream.writeLong(
    long: Long,
    order: ByteOrder
): Unit = write(long.toByteArray(order))

fun OutputStream.writeDouble(
    double: Double,
    order: ByteOrder
): Unit = write(double.toByteArray(order))

fun OutputStream.writeByte(byte: Byte): Unit = write(byte.toInt())