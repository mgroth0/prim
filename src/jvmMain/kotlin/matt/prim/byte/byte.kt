@file:JvmName("ByteKtJvm")


package matt.prim.byte

import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.nio.channels.WritableByteChannel


fun ByteArray.toFloat(order: ByteOrder) = ByteBuffer.wrap(this).order(order).float
fun ByteArray.toDouble(order: ByteOrder) = ByteBuffer.wrap(this).order(order).double


fun InputStream.efficientlyTransferTo(out: OutputStream) = Channels.newChannel(this).efficientlyTransferTo(out)
fun ReadableByteChannel.efficientlyTransferTo(out: OutputStream) = efficientlyTransferTo(Channels.newChannel(out))
fun InputStream.efficientlyTransferTo(out: WritableByteChannel) = Channels.newChannel(this).efficientlyTransferTo(out)


/*
* based on:
*  https://thomaswabner.wordpress.com/2007/10/09/fast-stream-copy-using-javanio-channels/
* */
fun ReadableByteChannel.efficientlyTransferTo(out: WritableByteChannel) {
    val buffer: ByteBuffer = ByteBuffer.allocateDirect(16 * 1024)
    while (this.read(buffer) != -1) {
        buffer.flip()
        out.write(buffer)
        buffer.compact()
    }
    buffer.flip()
    while (buffer.hasRemaining()) {
        out.write(buffer)
    }
}


