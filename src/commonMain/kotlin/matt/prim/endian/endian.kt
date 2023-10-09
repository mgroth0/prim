package matt.prim.endian

import matt.prim.endian.MyByteOrder.BIG
import matt.prim.endian.MyByteOrder.LITTLE


enum class MyByteOrder {
    BIG, LITTLE;

    companion object {
        fun fromPython(pythonByteOrder: String) = when (pythonByteOrder) {
            "big"    -> BIG
            "little" -> LITTLE
            else     -> error("python byte order should not be ${pythonByteOrder}")
        }
    }
}


val MyByteOrder.python
    get() = when (this) {
        BIG    -> "big"
        LITTLE -> "little"
    }

