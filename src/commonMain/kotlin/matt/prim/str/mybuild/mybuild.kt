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





  fun words(op: MyStringDSL.() -> Unit) = spaceDelimited(op)
  fun spaceDelimited(op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.delimiter = " "
	subDSL.apply(op)
	+subDSL.string
  }
  fun lineDelimited(op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.delimiter = "\n"
	subDSL.apply(op)
	+subDSL.string
  }

  fun parenthesis(op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.apply(op)
	+"(${subDSL.string})"
  }

}



