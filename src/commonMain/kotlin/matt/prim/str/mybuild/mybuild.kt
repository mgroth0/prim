package matt.prim.str.mybuild


@DslMarker
annotation class StringDslMarker

@StringDslMarker
interface StringDSL {

}

fun string(op: MyStringDSL.()->Unit): String = MyStringDSL().apply(op).string

class MyStringDSL: StringDSL {
  var string: String = ""
	private set

  private var delimiter = ""

  fun append(a: Any?) {
	if (string.isNotEmpty() && delimiter.isNotEmpty()) {
	  string += delimiter
	}
	string += a.toString()
  }

  operator fun Any?.unaryPlus() {
	append(this)
  }


  fun blankLine() {
	+"\n"
  }

  fun words(op: MyStringDSL.()->Unit) = spaceDelimited(op)


  fun delimited(tempDelim: Char, op: MyStringDSL.()->Unit) = delimited(tempDelim.toString(), op)
  fun delimited(tempDelim: String, op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.delimiter = tempDelim
	subDSL.apply(op)
	+subDSL.string
  }

  fun spaceDelimited(op: MyStringDSL.()->Unit) = delimited(' ', op)
  fun lineDelimited(op: MyStringDSL.()->Unit) = delimited('\n', op)
  fun tabDelimited(op: MyStringDSL.()->Unit) = delimited('\t', op)
  fun commaDelimited(op: MyStringDSL.()->Unit) = delimited(',', op)


  fun parenthesis(op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.apply(op)
	+"(${subDSL.string})"
  }

}



