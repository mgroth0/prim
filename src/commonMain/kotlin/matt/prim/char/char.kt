package matt.prim.char

private val VOWELS by lazy{ listOf(
  'A','E','I','O','U'
) }

val Char.isVowel get() = uppercaseChar() in VOWELS