package matt.prim.test


import matt.prim.base64.decodeFromBase64
import matt.prim.base64.encodeToBase64
import matt.prim.byte.hex.toHex
import matt.prim.ushort.toByteArray
import matt.test.JupiterTestAssertions.assertRunsInOneMinute
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class PrimTests {
    @Test
    fun hex() = assertRunsInOneMinute {
        assertEquals(
            "01",
            0x00000001.toByte().toHex()
        )
    }

    @Test
    fun uShortToByteArray() = assertRunsInOneMinute {
        assertContentEquals(
            byteArrayOf(0, 1),
            1.toUShort().toByteArray(),
        )
    }

    @Test
    fun base64RoundTrip() = assertRunsInOneMinute {
        val string = "abcd"
        val base64 = string.encodeToBase64()
        assertEquals(string, base64.decodeFromBase64().decodeToString())
    }
}