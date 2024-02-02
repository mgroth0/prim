package matt.prim.str.mybuild.extensions

import matt.lang.function.Op
import matt.prim.str.mybuild.RootStringDslImpl
import matt.prim.str.mybuild.api.LineDelimitedStringDsl
import matt.prim.str.mybuild.api.RootStringDsl
import matt.prim.str.mybuild.api.StringColumnsDsl
import matt.prim.str.mybuild.api.StringDsl

fun StringDsl<Any?>.parenthesis(op: RootStringDsl<Any?>.() -> Unit) {
    val subDSL = RootStringDslImpl<Any?>()
    subDSL.apply(op)
    +"(${subDSL.string})"
}

fun StringColumnsDsl.expandableRow(
    shortValue: Any?,
    possiblyLongValue: Any?,
) {
    val secondsString = possiblyLongValue.toString()
    if (secondsString.lines().size > 1) {
        row(shortValue.toString() + ":")
        indent {
            possiblyLongValue.toString().lines().forEach {
                row(it)
            }
        }
    } else {
        row(shortValue, possiblyLongValue)
    }
}

fun <T> StringDsl<T>.appendIfNotNull(a: T?) {
    if (a != null) {
        append(a)
    }
}

fun LineDelimitedStringDsl<*>.indent(op: Op) {
    indent += 1
    op()
    indent -= 1
}
