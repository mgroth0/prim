package matt.prim.converters

import matt.lang.anno.Open
import matt.lang.convert.BiConverter
import matt.prim.str.elementsToString
import matt.prim.str.takeIfNotBlank

interface StringListConverter<T> : BiConverter<StringList, T> {
    companion object {
        fun <T> fromStringConverterAsList(
            stringConverter: StringConverter<T>
        ) = StringListByElementConverter(stringConverter)

        fun <T> fromStringConverterAsSingular(
            stringConverter: StringConverter<T>
        ) = StringListBySingleElementConverter(stringConverter)

        fun <T> fromStringConverterAsSingularOrEmpty(
            stringConverter: StringConverter<T>
        ) = StringListBySingleElementOrNullConverter(stringConverter)
    }

    fun toStringList(t: T): StringList
    fun fromStringList(s: StringList): T

    @Open
    override fun convertToA(b: T): StringList = toStringList(b)

    @Open
    override fun convertToB(a: StringList) = fromStringList(a)
    @Open
    fun emptyIsNull(): StringListConverter<T?> = object : StringListConverter<T?> {
        override fun toStringList(t: T?): StringList = if (t == null) emptyList() else this@StringListConverter.toStringList(t)

        override fun fromStringList(s: StringList): T? = if (s.isEmpty()) null else this@StringListConverter.fromStringList(s)
    }
}


interface StringConverter<T> : BiConverter<String, T> {
    fun toString(t: T): String
    fun fromString(s: String): T

    @Open
    override fun convertToA(b: T): String = toString(b)

    @Open
    override fun convertToB(a: String) = fromString(a)

    @Open
    fun asSingularStringListConverter() = StringListConverter.fromStringConverterAsSingular(this)

    @Open
    fun nullAsBlank() = object : BiConverter<String, T?> {
        override fun convertToB(a: String): T? = a.takeIfNotBlank()?.toB()

        override fun convertToA(b: T?): String = b?.toA() ?: ""


    }

}


object ByteArrayStringConverter : StringConverter<ByteArray> {
    override fun toString(t: ByteArray) = t.decodeToString()
    override fun fromString(s: String) = s.encodeToByteArray()
    val inverted by lazy {
        invert()
    }
}


typealias StringList = List<String>


class StringListByElementConverter<T>(private val elementConverter: StringConverter<T>) : StringListConverter<List<T>> {
    override fun toStringList(t: List<T>): StringList = t.map { elementConverter.toString(it) }

    override fun fromStringList(s: StringList): List<T> = s.map { elementConverter.fromString(it) }
}

class StringListBySingleElementConverter<T>(private val elementConverter: StringConverter<T>) : StringListConverter<T> {
    override fun toStringList(t: T): StringList = listOf(elementConverter.toString(t))

    override fun fromStringList(s: StringList): T = elementConverter.fromString(
        s.singleOrNull() ?: error("only 1 element allowed, but got ${s.size}: ${s.elementsToString()}")
    )

}

class StringListBySingleElementOrNullConverter<T>(private val elementConverter: StringConverter<T>) :
    StringListConverter<T?> {
    override fun toStringList(t: T?): StringList {
        if (t == null) return emptyList()
        return listOf(elementConverter.toString(t))
    }

    override fun fromStringList(s: StringList): T? {
        if (s.isEmpty()) return null
        return elementConverter.fromString(
            s.singleOrNull() ?: error("only 0-1 elements allowed, but got ${s.size}: ${s.elementsToString()}")
        )
    }

}


object StringListStringListConverter : StringListConverter<StringList> {
    override fun toStringList(t: StringList) = t
    override fun fromStringList(s: StringList) = s
}
