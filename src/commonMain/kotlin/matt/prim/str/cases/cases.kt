package matt.prim.str.cases

import matt.lang.anno.SeeURL
import matt.prim.str.CAPITAL_LETTER
import matt.prim.str.cap
import matt.prim.str.decap
import matt.prim.str.hasWhiteSpace
import matt.prim.str.mybuild.string

fun String.splitCamelCase(): List<String> {

    if (this.isBlank()) {
        error("how would I split camel case of a blank string?")
    } else if (this.hasWhiteSpace) {
        error("how would I split camel case of a string with whitespace?")
    }


    val r = mutableListOf<String>()
    var s = ""

    forEach {
        if (it.isLetter()) {
            if (it.isUpperCase()) {
                r += s
                s = ""
                s += it
            } else {
                s += it
            }
        } else {
            error("character $it in string \"${this}\" is not a letter")
        }
    }
    r += s
    return r
}

fun String.hyphenatedToCamelCase() = replaceWithCamelHumps('-')

fun String.replaceWithCamelHumps(delim: Char) = when (delim) {
    !in this -> this
    else     -> split(delim).let {
        var r = ""
        it.forEachIndexed { index, s ->
            r += if (index == 0) s
            else s.cap()
        }
        r
    }
}


fun String.isPascalCase(): Boolean {
    if (this.isEmpty()) return false
    if (this.hasWhiteSpace) return false
    if (this.any { !it.isLetter() }) return false
    if (this.first().isLowerCase()) return false
    var lastWasUpper = false
    forEach {
        val isUpper = it.isUpperCase()
        if (isUpper && lastWasUpper) return false
        lastWasUpper = isUpper
    }
    return true
}

@SeeURL("https://www.approxion.com/capital-offenses-how-to-handle-abbreviations-in-camelcase/")
@SeeURL("https://stackoverflow.com/questions/15526107/acronyms-in-camelcase")
fun String.isCamelCase(): Boolean {
    if (this.isEmpty()) return false
    if (this.hasWhiteSpace) return false
    if (this.any { !it.isLetter() }) return false
    if (this.first().isUpperCase()) return false
    var lastWasUpper = false
    forEach {
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

fun String.pascalCaseToSnakeCase(): String {
    require(isPascalCase()) {
        "\"$this\" is not pascal case"
    }
    return decap().camelCaseToSnakeCase()
}

fun String.camelCaseToSnakeCase(): String {
    require(isCamelCase()) {
        "\"$this\" is not camel case"
    }
    val camel = this
    return string {
        camel.forEach {
            if (it.isUpperCase()) {
                +'_'
                +it.lowercase()
            } else +it
        }
    }
}

fun String.isSnakeCase(): Boolean {
    if (this.isEmpty()) return false
    if (this.hasWhiteSpace) return false
    if (this.any { !(it.isLetter() || it == '_') }) return false
    if (this.any { it.isUpperCase() }) return false
    if (this.first() == '_') return false
    var lastWasUnderscore = false
    forEach {
        val isUnderscore = it == '_'
        if (isUnderscore && lastWasUnderscore) return false
        lastWasUnderscore = isUnderscore
    }
    return true
}

fun String.isKebabCase(): Boolean {
    if (this.isEmpty()) return false
    if (this.hasWhiteSpace) return false
    if (this.any { !(it.isLetter() || it == '-') }) return false
    if (this.first() == '-') return false
    var lastWasUnderscore = false
    forEach {
        val isUnderscore = it == '-'
        if (isUnderscore && lastWasUnderscore) return false
        lastWasUnderscore = isUnderscore
    }
    return true
}


fun String.snakeCaseToCamelCase(): String {
    require(isSnakeCase()) {
        "\"$this\" is not snake case"
    }
    val snake = this
    return string {
        val itr = snake.iterator()
        while (itr.hasNext()) {
            val c = itr.next()
            if (c == '_') {
                +itr.next().uppercase()
            } else +c
        }
    }
}



fun String.hyphenize(): String =
    replace(CAPITAL_LETTER) {
        "-${it.value.lowercase()}"
    }