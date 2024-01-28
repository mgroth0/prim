package matt.prim.str.cases

import matt.lang.anno.SeeURL
import matt.lang.platform.OS
import matt.lang.platform.Unix
import matt.lang.platform.Windows
import matt.prim.str.cap
import matt.prim.str.decap
import matt.prim.str.hasWhiteSpace
import matt.prim.str.lower

fun String.isCase(case: StringCase): Boolean {
    return case.isInThisCase(this)
}

fun String.convert(
    from: StringCase,
    to: StringCase
): String {
    return to.join(from.split(this))
}

fun List<String>.join(case: StringCase) = case.join(this)
fun String.split(case: StringCase) = case.split(this)


@SeeURL("https://www.wikiwand.com/en/Camel_case")
interface StringCase {
    fun isInThisCase(s: String): Boolean
    fun split(s: String): List<String>
    fun join(strings: List<String>): String
}

sealed class EnforcingStringCase : StringCase {
    final override fun split(s: String): List<String> {
        require(isInThisCase(s)) {
            """
Could not split string as $this
Input="$s"
            """.trimIndent()
        }
        return splitImpl(s)
    }

    protected abstract fun splitImpl(s: String): List<String>


    final override fun join(strings: List<String>): String {
        val r = joinImpl(strings)
        require(isInThisCase(r)) {
            """
Failed to correctly construct $this string
Input(${strings.size}):
${strings.joinToString("\n") { "\t$it" }}
Output="$r"
            """.trimIndent()

        }
        return r
    }

    protected abstract fun joinImpl(strings: List<String>): String

    final override fun toString(): String {
        return this::class.simpleName!!
    }
}

abstract class CamelCase(private val capFirst: Boolean) : EnforcingStringCase() {
    @SeeURL("https://www.approxion.com/capital-offenses-how-to-handle-abbreviations-in-camelcase/")
    @SeeURL("https://stackoverflow.com/questions/15526107/acronyms-in-camelcase")
    final override fun isInThisCase(s: String): Boolean {
        if (s.isEmpty()) return false
        if (s.hasWhiteSpace) return false
        if (s.any { !it.isLetterOrDigit() }) return false
        if (s.first().isUpperCase() != capFirst) return false
        var lastWasUpper = false
        s.forEach {
            val isUpper = it.isUpperCase()
            if (isUpper && lastWasUpper) {
                println(
                    """
                WARNING: If I allow upper case letters to touch, then camelCase can no longer be converted correctly between it and snake case without errors, because there is ambiguity when converting from snake case to camel case. See links above where people tend to agree that this touching uppers cannot be allowed.
            """.trimIndent()
                )
                return false
            }
            lastWasUpper = isUpper
        }
        return true
    }

    final override fun splitImpl(s: String): List<String> {
        if (s.isBlank()) {
            error("how would I split camel case of a blank string?")
        } else if (s.hasWhiteSpace) {
            error("how would I split camel case of a string with whitespace?")
        }
        val r = mutableListOf<String>()
        var returnS = ""
        s.forEachIndexed { index, it ->
            if (it.isDigit()) {
                returnS += it
            } else if (it.isLetter()) {
                if (index == 0) {
                    returnS += it
                } else if (it.isUpperCase()) {
                    r += returnS
                    returnS = ""
                    returnS += it
                } else {
                    returnS += it
                }
            } else {
                error("character $it in string \"${this}\" is not a letter")
            }
        }
        r += returnS
        return r
    }

    final override fun joinImpl(strings: List<String>): String {
        var r = ""
        strings.forEachIndexed { index, s ->
            r += if (!capFirst && index == 0) s.decap()
            else s.cap()
        }
        return r
    }
}

object DromedaryCase : CamelCase(capFirst = false)
object PascalCase : CamelCase(capFirst = true)


abstract class DelimiterCase(
    protected val delimiter: Char,
    protected val allowChars: List<Char> = listOf()
) : EnforcingStringCase() {
    private val delimiterString by lazy {
        delimiter.toString()
    }

    final override fun isInThisCase(s: String): Boolean {
        if (s.isEmpty()) return false
        var toCheck = ""
        s.forEach {
            if (it != delimiter && it !in allowChars) toCheck += it
        }
        if (toCheck.hasWhiteSpace) return false
        if (s.first() == delimiter) return false
        if (toCheck.any {
                !it.isLetterOrDigit()
            }) return false
        var lastWasDelim = false
        s.forEach {
            val isDelim = it == delimiter
            if (isDelim && lastWasDelim) return false
            lastWasDelim = isDelim
        }
        return passesAdditionalChecks(s)
    }

    protected open fun passesAdditionalChecks(s: String): Boolean = true

    final override fun joinImpl(strings: List<String>): String {
        return strings.joinToString(separator = delimiterString) {
            preparePart(it)
        }
    }

    protected open fun preparePart(s: String): String = s

    final override fun splitImpl(s: String): List<String> {
        return s.split(delimiter)
    }
}


object SnakeCase : DelimiterCase(delimiter = '_') {
    override fun passesAdditionalChecks(s: String): Boolean {
        return s.none { it.isUpperCase() }
    }

    override fun preparePart(s: String) = s.lower()
}

object KebabCase : DelimiterCase('-')

object LowerKebabCase : DelimiterCase('-') {
    override fun passesAdditionalChecks(s: String): Boolean {
        return s.split(delimiter).all { it.all { it.isLowerCase() } }
    }

    override fun preparePart(s: String): String {
        return s.lower()
    }
}

object TrainCase : DelimiterCase('-') {

    override fun passesAdditionalChecks(s: String): Boolean {
        return s.split(delimiter).all { it.first().isUpperCase() }
    }

    override fun preparePart(s: String): String {
        return s.cap()
    }

}

sealed class FileCase(delimiter: Char) : DelimiterCase(
    delimiter, allowChars = listOf('-', '_', '.')
)

object UnixFileCase : FileCase('/')
object PlatformFileCase : FileCase(
    delimiter = when (OS) {
        is Unix -> '/'
        Windows -> '\\'
    }
)

@SeeURL("https://www.wikiwand.com/en/Title_case")
object TitleCase : DelimiterCase(' ') {

    override fun passesAdditionalChecks(s: String): Boolean {
        return s.split(delimiter).all { it.first().isUpperCase() }
    }

    override fun preparePart(s: String): String {

        return s.cap()
    }
}