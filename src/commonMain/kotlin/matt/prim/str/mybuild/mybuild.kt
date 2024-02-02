package matt.prim.str.mybuild

import matt.lang.function.Dsl
import matt.prim.str.EMPTY_STRING
import matt.prim.str.NEW_LINE_CHARS
import matt.prim.str.mybuild.api.LineDelimitedStringDsl
import matt.prim.str.mybuild.api.RootStringDsl
import matt.prim.str.mybuild.api.StringColumnsDsl
import matt.prim.str.mybuild.api.StringDsl
import matt.prim.str.mybuild.api.string
import matt.prim.str.times

internal abstract class StringDslBase<T> : StringDsl<T> {
    final override fun toString() = string
}

internal abstract class MyStringDsl<T> : StringDslBase<T>() {
    final override var string: String = ""
        protected set

    protected abstract val delimiter: String

    private var appendedFirst = false

    protected fun appendString(s: String) {
        if (appendedFirst) {
            string += delimiter
        }
        string += s
        appendedFirst = true
    }

    final override fun append(a: T) = appendString(a.toString())

    final override operator fun T.unaryPlus() = append(this)

    final override fun tabDelimited(op: Dsl<StringDsl<T>>) = delimited('\t', op)

    final override fun commaDelimited(op: Dsl<StringDsl<T>>) = delimited(',', op)

    protected fun delimited(
        tempDelim: Char,
        op: MyStringDsl<T>.() -> Unit,
    ) = delimited(tempDelim.toString(), op)

    private fun delimited(
        tempDelim: String,
        op: MyStringDsl<T>.() -> Unit,
    ) {
        applySubDsl(DelimitedStringDsl<T>(tempDelim), op)
    }

    private fun spaceDelimited(op: StringDsl<T>.() -> Unit) = delimited(' ', op)

    fun words(op: StringDsl<T>.() -> Unit) {
        spaceDelimited(op)
    }

    protected fun <D : StringDsl<*>> applySubDsl(
        subDsl: D,
        buildOp: Dsl<D>,
    ) {
        subDsl.apply(buildOp)
        appendString(subDsl.string)
    }
}

internal class DelimitedStringDsl<T>(override val delimiter: String) : MyStringDsl<T>() {
    constructor(delimiter: Char) : this(delimiter.toString())
}

internal class RootStringDslImpl<T> : MyStringDsl<T>(), RootStringDsl<T> {
    override val delimiter = EMPTY_STRING

    override fun newLine() {
        appendString("\n")
    }

    override fun lineDelimited(op: Dsl<LineDelimitedStringDsl<T>>) = applySubDsl(LineDelimitedStringDslImpl(), op)

    override fun columned(op: Dsl<StringColumnsDsl>) = applySubDsl(StringColumnsDslImpl(), op)
}

private open class LineDelimitedStringDslImpl<T> : MyStringDsl<T>(), LineDelimitedStringDsl<T> {
    final override var delimiter = "\n"
        private set

    final override var indent = 0
        set(value) {
            delimiter = "\n" + ('\t' * value)
            field = value
        }

    final override fun blankLine() {
        appendString("")
    }

    final override fun singleLine(op: Dsl<StringDsl<Any?>>) {
        val s = string(op).trim { it in NEW_LINE_CHARS }
        require(s.lines().size == 1) {
            "you promised it would be a single line, but I got ${s.count { it in NEW_LINE_CHARS }} new line characters!:\n$s"
        }
        appendString(s)
    }
}

private class StringColumnsDslImpl : LineDelimitedStringDslImpl<Nothing>(), StringColumnsDsl {
    override fun row(vararg elements: Any?) {
        singleLine {
            tabDelimited {
                elements.forEach {
                    append(it)
                }
            }
        }
    }
}
