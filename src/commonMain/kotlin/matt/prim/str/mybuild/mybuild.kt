package matt.prim.str.mybuild

@DslMarker
annotation class StringDslMarker

fun string(op: MyStringDSL.()->Unit): String = MyStringDSL().apply(op).string

@StringDslMarker
class MyStringDSL {
  var string: String = ""
	private set

  private var delimiter = ""

  fun append(a: Any?) {
	if (string.isNotBlank() && delimiter.isNotBlank()) {
	  string += delimiter
	}
	string += a.toString()
  }

  operator fun Any?.unaryPlus() {
	append(this)
  }





  fun spaceDelimited(op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.delimiter = " "
	subDSL.apply(op)
	string += subDSL.string
  }

  fun parenthesis(op: MyStringDSL.()->Unit) {
	val subDSL = MyStringDSL()
	subDSL.apply(op)
	string += "(${subDSL.string})"
  }

}



