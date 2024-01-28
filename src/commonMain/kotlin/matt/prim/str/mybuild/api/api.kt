package matt.prim.str.mybuild.api

import matt.lang.function.Dsl
import matt.lang.idea.ReportBuilder
import matt.prim.str.mybuild.RootStringDslImpl

fun string(op: RootStringDsl<Any?>.() -> Unit): String = RootStringDslImpl<Any?>().apply(op).string
fun lineDelimitedString(op: LineDelimitedStringDsl<Any?>.() -> Unit): String = RootStringDslImpl<Any?>().apply {
    lineDelimited(op)
}.string


@DslMarker
annotation class StringDslMarker

@StringDslMarker
interface StringDslLike : ReportBuilder

@StringDslMarker
interface StringDsl<T> {
    val string: String
    operator fun T.unaryPlus()
    fun append(a: T)
    fun tabDelimited(op: Dsl<StringDsl<T>>)
    fun commaDelimited(op: Dsl<StringDsl<T>>)
}






interface RootStringDsl<T> : StringDsl<T> {
    fun newLine()
    fun lineDelimited(op: Dsl<LineDelimitedStringDsl<T>>)
    fun columned(op: Dsl<StringColumnsDsl>)
}


interface LineDelimitedStringDsl<T> : StringDsl<T> {
    var indent: Int
    fun blankLine()
    fun singleLine(op: Dsl<StringDsl<Any?>>)
}





interface StringColumnsDsl : LineDelimitedStringDsl<Nothing> {
    fun row(vararg elements: Any?)
}


