package com.my.journeyplanner

import com.my.journeyplanner.helpers.countOccurrences
import org.junit.Assert
import org.junit.Test

class StringCountOccurrencesTests {
    @Test
    fun zeroMatchEmptyString() {
        Assert.assertEquals(0, "".countOccurrences(searchedString))
    }

    @Test
    fun zeroMatch() {
        Assert.assertEquals(0, zeroMatch.countOccurrences(searchedString))
    }

    @Test
    fun caseSensitiveZeroMatch() {
        Assert.assertEquals(0, caseSensitiveZeroMatch.countOccurrences(searchedString))
    }

    @Test
    fun oneMatchAtEnd() {
        Assert.assertEquals(1, oneMatchAtEnd.countOccurrences(searchedString))
    }

    @Test
    fun oneMatchAtStart() {
        Assert.assertEquals(1, oneMatchAtStart.countOccurrences(searchedString))
    }

    @Test
    fun twoMatchesAtStartAndEnd() {
        Assert.assertEquals(2, twoMatchesAtStartAndEnd.countOccurrences(searchedString))
    }

    @Test
    fun threeMatches() {
        Assert.assertEquals(3, threeMatches.countOccurrences(searchedString))
    }

    companion object {
        const val searchedString = "Match"
        const val zeroMatch = "snfnaf dvpvp vd ±!@£$%^&*() matc,h"
        val caseSensitiveZeroMatch = searchedString.toLowerCase()
        const val oneMatchAtEnd = "cdbh{}kd v,adv\"oan dov+=_-dvad';'$searchedString"
        const val oneMatchAtStart = "${searchedString}cdbhkd vadv oand<>ov~`d|\\vad';'"
        const val twoMatchesAtStartAndEnd = "${searchedString}{}[]:;\"\\|${searchedString}"
        val threeMatches = "c:os./?ci${searchedString.repeat(2)};£$%^&$%=&*(${searchedString} "
    }
}