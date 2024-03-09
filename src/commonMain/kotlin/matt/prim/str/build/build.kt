package matt.prim.str.build

import matt.prim.str.times

fun StringBuilder.appendLine(
    tabs: Int,
    string: String
) = appendLine('\t' * tabs + string)

val StringBuilder.t get() = append('\t')
