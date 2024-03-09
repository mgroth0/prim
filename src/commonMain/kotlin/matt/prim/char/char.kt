package matt.prim.char

import kotlin.random.Random

private val VOWELS_STRICT by lazy {
    listOf(
        'A',
        'E',
        'I',
        'O',
        'U'
    )
}

val Char.isStrictVowel get() = uppercaseChar() in VOWELS_STRICT

val DIGIT_CHARS = '0'..'9'

fun Random.nextLetterEitherCase() {
    ALPHABET_UPPER.random(this).let {
        if (nextBoolean()) {
            it.uppercaseChar()
        } else {
            it.lowercaseChar()
        }
    }
}

val ALPHABET_UPPER = ('A'..'Z').toList()
val ALPHABET_LOWER = ('a'..'z').toList()
val ALPHABET_ALL by lazy {
    ALPHABET_UPPER + ALPHABET_LOWER
}
val ALPHANUMERIC_ALL by lazy {

    CharArray(3) { 'a' }.concatToString()

    ALPHABET_ALL + DIGIT_CHARS
}

val VOWELS_LOOSE = VOWELS_STRICT + 'Y'
val CONSONANTS_STRICT = ALPHABET_UPPER.filter { it !in VOWELS_LOOSE }.toTypedArray()

fun CharArray.toCharString() = CharString(this)

class CharString(
    input: CharArray
) {
    private val data = input.copyOf()

    override fun equals(other: Any?): Boolean = other is CharString && data.contentEquals(other.data)

    override fun hashCode(): Int = data.contentHashCode()

    override fun toString(): String = "CharString(size=${data.size},chars=${data.joinToString(
        prefix = "[",
        postfix = "]"
    ) { it.toString() }})"
}

