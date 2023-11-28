@file:JvmName("FloatCommonKt")

package matt.prim.float

import kotlin.jvm.JvmName


fun Float.isNaNInfiniteOrZero() = isNaN() || isInfinite() || this == 0f