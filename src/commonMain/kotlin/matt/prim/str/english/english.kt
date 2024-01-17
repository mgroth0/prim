package matt.prim.str.english



fun maybePlural(
    count: Int,
    noun: String
): String {
    return if (count == 1) noun else "${noun}s"
}