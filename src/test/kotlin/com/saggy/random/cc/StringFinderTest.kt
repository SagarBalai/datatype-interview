package com.saggy.random.cc

import org.junit.jupiter.api.Test

import kotlin.test.assertEquals

internal class StringFinderTest{

    private val stringFinder=StringFinder()
    @Test
    internal fun `should return 0 when string is empty`() {
        // given
        val str =""

        //when
        val res = stringFinder.find(str)

        //then
        assertEquals(0L,res)
    }
    @Test
    internal fun `should return 2 when string has 2 sbustring`() {
        // given
        val str ="ABACABA"

        //when
        val res = stringFinder.find(str.toLowerCase())

        //then
        assertEquals(2L,res)
    }

    @Test
    fun `should return 1 for array`() {

        val result = stringFinder.thirdMax(intArrayOf(1,2,3))
        assertEquals(1,result)
    }


}