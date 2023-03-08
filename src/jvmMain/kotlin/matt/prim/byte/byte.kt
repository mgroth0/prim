@file:JvmName("ByteKtJvm")


package matt.prim.byte

import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.nio.channels.WritableByteChannel
import java.util.Base64


fun ByteArray.toInt() = ByteBuffer.wrap(this).int
fun ByteArray.toLong() = ByteBuffer.wrap(this).long
fun ByteArray.toFloat() = ByteBuffer.wrap(this).float
fun ByteArray.toDouble() = ByteBuffer.wrap(this).double


fun InputStream.efficientlyTransferTo(out: OutputStream) = Channels.newChannel(this).efficientlyTransferTo(out)
fun ReadableByteChannel.efficientlyTransferTo(out: OutputStream) = efficientlyTransferTo(Channels.newChannel(out))
fun InputStream.efficientlyTransferTo(out: WritableByteChannel) = Channels.newChannel(this).efficientlyTransferTo(out)


/*
* based on:
*  https://thomaswabner.wordpress.com/2007/10/09/fast-stream-copy-using-javanio-channels/
* */
fun ReadableByteChannel.efficientlyTransferTo(out: WritableByteChannel) {
  val buffer: ByteBuffer = ByteBuffer.allocateDirect(16*1024)
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

fun ByteArray.encodeToBase64() = Base64.getEncoder().encodeToString(this)
fun ByteArray.decodeFromBase64() = Base64.getDecoder().decode(this)


fun ByteArray.encodeToURLBase64() = Base64.getUrlEncoder().encodeToString(this)
fun ByteArray.decodeFromURLBase64() = Base64.getUrlDecoder().decode(this)