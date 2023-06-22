@file:JvmName("StrJvmKt")

package matt.prim.str

import matt.prim.str.mybuild.string
import kotlin.jvm.JvmName
import kotlin.random.Random

fun String.containsAny(vararg strings: String): Boolean {
    return strings.any { contains(it) }
}

/*like python!*/
fun <T> String.join(itr: Iterable<T>, op: (T) -> String = { it.toString() }) =
    itr.joinToString(separator = this) { op(it) }

fun maybePlural(count: Int, noun: String): String {
    return if (count == 1) noun else "${noun}s"
}

fun randomAlphanumericString(length: Int, random: Random = Random): String {
    val candidates = ALPHABET + ALPHABET.map { it.lowercaseChar() } + (0..9).map { it.toString().toCharArray().first() }
    var r = ""
    repeat(length) {
        r += candidates.random(random)
    }
    return r
}

fun String.ensurePrefix(s: String) = s + removePrefix(s)
fun String.ensureSuffix(s: String) = removeSuffix(s) + s


fun String.removePrefixAndOrSuffix(s: String) = removePrefix(s).removeSuffix(s)

fun String.replaceLastChar(new: String): String {
    return replaceAt(indices.last, new)
}

fun String.replaceAt(index: Int, new: String): String {
    require(index in indices)
    val before = if (index == 0) {
        ""
    } else {
        substring(0 until index)
    }
    val after = if (index == indices.last) {
        ""
    } else {
        substring(index + 1..indices.last)
    }
    return before + new + after
}

fun String.remove(s: String) = replace(s, "")

fun String.remove(vararg chars: Char) = run {
    var r = this
    chars.forEach {
        r = r.replace(it.toString(), "")
    }
    r
}

fun String.shuffled(rand: Random = Random): String {
    return toList().shuffled(rand).joinToString(separator = "") { it.toString() }
}

fun String.incEach(): String {
    return string {
        forEach {
            +it.inc().toString()
        }
    }
}

fun String.insertRandomly(char: Char, rand: Random = Random): String {
    return StringBuilder(this).insert(rand.nextInt(0, length), char).toString()
}

fun String.lineIndexOfIndex(i: Int): Int {

    if (isEmpty()) {
        if (i == 0) return 0
        else error("no")
    }
    lineSequence().fold(-1 to -1) { acc: Pair<Int, Int>, line: String ->
        val next = (acc.first + 1) to (acc.second + line.length + 1)
        if (next.second >= i) {
            return next.first
        }
        next
    }
    error("index too high")
}

fun String.lineNumOfIndex(i: Int) = lineIndexOfIndex(i) + 1


val isKotlin1_4OrEarlier by lazy { KotlinVersion.CURRENT.major <= 1 && KotlinVersion.CURRENT.minor <= 4 }

object CharCheck {
    init {
        if (isKotlin1_4OrEarlier) {
            error("OPPOSITE OF THE FOLLOWING")    /*if (KotlinVersion.CURRENT.isAtLeast(1, 5)) {*/
            error("delete Char.code below")
            error("update decap")
            error("update cap")
            error("update lower")
            error("update upper")
        }
    }
}


/*
1.4:
val Char.code
  get() = toInt()
*/


fun String.lower() = lowercase()

/*1.4: toLowerCase()*/
fun String.upper() = uppercase()/*1.4: toUpperCase()*/

infix fun String.loweq(s: String): Boolean {
    return this.lower() == s.lower()
}

infix fun String.lowin(s: String): Boolean {
    return this.lower() in s.lower()
}

infix fun String.lowinbi(s: String): Boolean {
    val l1 = this.lower()
    val l2 = s.lower()
    return l1 in l2 || l2 in l1
}

fun String.hasWhitespace() = any { it.isWhitespace() }

fun String.startsWithAny(atLeastOne: String, vararg more: String): Boolean {
    if (startsWith(atLeastOne)) return true
    more.forEach { if (startsWith(it)) return true }
    return false
}


abstract class DelimiterAppender(s: String = "") {
    private val sb = StringBuilder(s)
    abstract val delimiter: String
    fun append(a: Any?) {
        sb.append(delimiter)
        sb.append(a)
    }

    operator fun plusAssign(a: Any?) = append(a)
    override fun toString() = sb.toString()
}

class LineAppender(s: String = "") : DelimiterAppender(s) {
    override val delimiter = "\n"
}


fun Int.prependZeros(untilNumDigits: Int): String {
    var s = this.toString()
    while (s.length < untilNumDigits) {
        s = "0$s"
    }
    return s
}

fun String.addSpacesUntilLengthIs(n: Int, prepend: Boolean = false): String {
    var s = this
    if (prepend) {
        while (s.length < n) {
            s = " " + s
        }
    } else {
        while (s.length < n) {
            s += " "
        }
    }
    return s
}


fun String.addSpacesEvenlyUntilLengthIs(n: Int): String {
    var prepend = false
    var s = this
    while (s.length < n) {
        if (prepend) {
            s = " $s"
        } else {
            s += " "
        }
        prepend = !prepend
    }

    return s
}

fun String.addNewLinesUntilNumLinesIs(n: Int): String {
    var s = this
    while (s.lines().count() < n) {
        s += "\n"
    }
    return s
}


val DOWN_ARROW = "↓"
val UP_ARROW = "↑"


fun String.substringAfterIth(c: Char, num: Number): String {
    val intNum = num.toInt()
    if (this.count { it == c } < intNum) {
        return this
    } else {
        var next = 0
        println("this=${this}")
        println("num=${intNum}")
        println("c=${c}")
        this.forEachIndexed { index, char ->
            if (char == c) {
                next++
            }
            println("char=${char}")
            println("index=${index}")
            println("next=${next}")
            if (next == intNum) {
                println("returning!")
                return this.substring(index + 1)
            } else {
                println("not returning")
            }
        }
        error("should never get here")
    }
}


val String.hasWhiteSpace
    get() = " " in this || "\n" in this || "\r" in this

fun String.toIntOrNullIfBlank() = if (isBlank()) null else this.toInt()
fun String.toDoubleOrNullIfBlank() = if (isBlank()) null else this.toDouble()
fun String.toBooleanOrNullIfBlank() = if (isBlank()) null else this.toBoolean()


fun String.truncate(maxChars: Int): String {
    if (length <= maxChars) return this
    else return this.substring(0, maxChars)
}

fun String.truncateWithElipses(maxChars: Int): String {
    if (length <= maxChars) return this
    else return this.substring(0, maxChars) + " ..."
}

fun String.truncateOrAddSpaces(exactNumChars: Int): String {
    if (length <= exactNumChars) return this.addSpacesUntilLengthIs(exactNumChars)
    else return this.substring(0, exactNumChars)
}

fun String.truncateOrCenterInSpaces(exactNumChars: Int): String {
    if (length <= exactNumChars) return this.addSpacesEvenlyUntilLengthIs(exactNumChars)
    else return this.substring(0, exactNumChars)
}


const val elipses = " ..."
fun String.truncateWithElipsesOrAddSpaces(exactNumChars: Int): String {
    val numCharsBeforeElipses = exactNumChars - 4
    require(numCharsBeforeElipses >= 0)
    if (length <= exactNumChars) return this.addSpacesUntilLengthIs(exactNumChars)
    else return this.substring(0, numCharsBeforeElipses) + elipses
}


fun String.truncateWithElipsesOrAddSpacesAsNeeded(allowableLengths: IntRange): String {
    return if (this.length in allowableLengths) this
    else if (this.length < allowableLengths.first) truncateWithElipsesOrAddSpaces(allowableLengths.first)
    else truncateWithElipsesOrAddSpaces(allowableLengths.last)
}


val ALPHABET = arrayOf(
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
    'X', 'Y', 'Z'
)

val VOWELS = arrayOf('A', 'E', 'I', 'O', 'U', 'Y')
val CONSENENTS = ALPHABET.filter { it !in VOWELS }.toTypedArray()


operator fun String.get(intRange: IntRange) = subSequence(intRange.first, intRange.last + 1)
fun String.throttled() = "THROTTLED STRING OF LENGTH $length (\"${this[0..100]}\"...)"

fun String.toHyphenCase(): String {
    if (isBlank()) return this
    return this[0].lowercase() + toCharArray().map { it.toString() }.drop(1)
        .joinToString(separator = "") { if (it[0].isUpperCase()) "-${it[0].lowercase()}" else it }
}

class StringLineBuilder(private var s: String) {
    fun get() = s
    override fun toString() = s
    operator fun plusAssign(a: Any?) {
        s += "\n$a"
    }

    infix fun tab(a: Any?) {
        s += "\n\t$a"
    }
}

fun <T> T?.orBlank(op: (T) -> String = { toString() }) = this?.let { op(it) } ?: ""
fun String.takeIfNotBlank() = takeIf { isNotBlank() }
fun Iterable<String>.filterNotBlank() = filterNot { it.isBlank() }
fun String.nonBlankLines() = lines().filterNotBlank()
fun Iterable<String>.trimmed() = map { it.trim() }
fun Iterable<String>.startingWith(prefix: String) = filter { it.startsWith(prefix) }


val NEW_LINE_CHARS = listOf('\n', '\r')

/*dont change order, sometimes we need to check for the double char thing first*/
val NEW_LINE_STRINGS = listOf("\r\n", "\n", "\r")


fun <T> Iterable<T>.concat(op: ((T) -> CharSequence)? = null) = joinToString("", transform = op)
fun <T> Array<T>.concat(op: ((T) -> CharSequence)? = null) = joinToString("", transform = op)


fun <T> Iterable<T>.joinWithSpaces(op: ((T) -> CharSequence)? = null) = joinToString(" ", transform = op)
fun <T> Array<T>.joinWithSpaces(op: ((T) -> CharSequence)? = null) = joinToString(" ", transform = op)

fun <T> Iterable<T>.joinWithCommas(op: ((T) -> CharSequence)? = null) = joinToString(",", transform = op)
fun <T> Array<T>.joinWithCommas(op: ((T) -> CharSequence)? = null) = joinToString(",", transform = op)


fun <T> Iterable<T>.joinWithPeriods(op: ((T) -> CharSequence)? = null) = joinToString(".", transform = op)
fun <T> Array<T>.joinWithPeriods(op: ((T) -> CharSequence)? = null) = joinToString(".", transform = op)

fun <T> Iterable<T>.joinWithForwardSlashes(op: ((T) -> CharSequence)? = null) = joinToString("/", transform = op)
fun <T> Array<T>.joinWithForwardSlashes(op: ((T) -> CharSequence)? = null) = joinToString("/", transform = op)

fun <T> Iterable<T>.joinWithNewLines(op: ((T) -> CharSequence)? = null) = joinToString("\n", transform = op)
fun <T> Array<T>.joinWithNewLines(op: ((T) -> CharSequence)? = null) = joinToString("\n", transform = op)

fun <T> Iterable<T>.joinWithNewLinesAndTabs(op: ((T) -> CharSequence)? = null) =
    joinToString("\n", transform = { "\t${op?.invoke(it) ?: it.toString()}" })

fun <T> Array<T>.joinWithNewLinesAndTabs(op: ((T) -> CharSequence)? = null) =
    joinToString("\n", transform = { "\t${op?.invoke(it) ?: it.toString()}" })

fun <T> Iterable<T>.strings() = map { it.toString() }
fun <T> Array<T>.strings() = map { it.toString() }.toTypedArray()

fun <T> Iterable<T>.elementsToString(delimiter: String = ",") = joinToString(prefix = "[", postfix = "]", separator = delimiter)
fun <T> Array<T>.elementsToString() = joinToString(prefix = "[", postfix = "]", separator = ",")


fun <T> Iterable<T>.joinWithBars(op: ((T) -> CharSequence)? = null) = joinToString(" | ", transform = op)


operator fun String.times(n: Int): String {
    require(n >= 0) {
        "tried to multiply string by negative number ($n)"
    }
    if (n == 0) return ""
    var r = ""
    repeat(n) {
        r += this
    }
    return r
}

operator fun Char.times(n: Int): String {
    require(n >= 0)
    if (n == 0) return ""
    var r = ""
    repeat(n) {
        r += this
    }
    return r
}

fun String.isLong() = toLongOrNull() != null
fun String.isInt() = toIntOrNull() != null
fun String.isBoolean() = toBooleanStrictOrNull() != null
fun String.isDouble() = toDoubleOrNull() != null
fun String.isFloat() = toFloatOrNull() != null

/*kinda how JetBrains wants us to do it*/
fun String.cap() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
/*if I go back to 1.4: this.capitalize()*/


/*kinda how JetBrains wants us to do it*/
fun String.decap() =
    replaceFirstChar { it.lowercase() }
/*if I go back to 1.4: this.decapitalize()*/


fun String.numberOf(char: Char) = count { it == char }


fun String.toByteArrayCommon() = encodeToByteArray()


internal val CAPITAL_LETTER = Regex("[A-Z]")


fun String.prependZerosUntilLengthIs(num: Int): String {
    var s = this
    while (s.length < num) {
        s = "0$s"
    }
    return s
}

