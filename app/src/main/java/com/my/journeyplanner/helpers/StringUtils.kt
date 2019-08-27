package com.my.journeyplanner.helpers

fun String.countOccurrences(searchedString: String, index: Int = 0): Int {
    if (index >= this.length) return 0

    val thisStringIndex = this.indexOf(searchedString, index)
    val count = if (thisStringIndex > 0) 1 else 0
    val thisStringIndexIncremented =
        if (count > 0) index + thisStringIndex + searchedString.length else this.length

    return count + countOccurrences(searchedString, thisStringIndexIncremented)
}