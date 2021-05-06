package com.saggy.random

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TopKStringsInListTest {

    private val subject = TopKStringsInList()
    @Test
    internal fun `find - should top 2 strings`() {
        val list = mutableListOf<String>("asd", "asf", "asd", "asf", "asd", "asf", "asd", "asf", "hgf", "fdgh")

        val result = subject.findSolutionWithSort(list,2)

        assertEquals(2, result.size)
        assertTrue { result.contains("asd")
        result.contains("asf")}

    }
    @Test
    internal fun `findSolutionWithPivotal - should top 2 strings`() {
        val list = mutableListOf<String>("asd", "asf", "asd", "asf", "asd", "asf", "asd", "asf", "hgf", "fdgh")

        val result = subject.findSolutionWithPivotal(list,2)

        assertEquals(2, result.size)
        assertTrue { result.contains("asd")
        result.contains("asf")}

    }

    @Test
    internal fun `pivotal test`() {

        val arr = intArrayOf(10,2,50,4,60,5,55,53,7,789)
        subject.kthSmallest(arr,0,arr.size-1,3);
    }
}