package com.my.journeyplanner.features.searchquery.framework.helpers

fun String.countOccurrences(searchedString: String) = split(searchedString).size - 1