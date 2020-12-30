package com.saggy.array

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidateSubSeqInArrayTest {
    private val subject = ValidateSubSeqInArray()


    @Test
    internal fun `ValidateSubSeqInArrayTest - should return true for same array`() {
        // given
        val array = intArrayOf(1, 2, 3, 4)

        // when
        val result = subject.validateSubSequenceInArray(array, array)

        // then
        assertTrue(result)
    }

    @ParameterizedTest
    @CsvSource("1st element;1,2,3,4,5; 1", "first 2 elements;1,2,3,4,5; 1,2", "Last 2 elements;1,2,3,4,5; 4,5",
        "Middle elements; 1,2,3,4,5; 3,4", "Last element;1,2,3,4,5; 5", "2nd element;1,2,3,4,5;2",delimiter = ';')
    internal fun `ValidateSubSeqInArrayTest - should return true for valid scenarios`(testName:String,arrayStr:String?, subSeqStr:String?) {
        //given
        val array= arrayStr?.split(",")?.map { it.toInt() }?.toIntArray()
        val subSeq=subSeqStr?.split(",")?.map { it.toInt() }?.toIntArray()

        // when
        val result = subject.validateSubSequenceInArray(array, subSeq)

        // then
        assertTrue(result)
    }

    @ParameterizedTest
    @CsvSource("Element does not exist;1,2,3,4,5; 1,7", "Element does not exist;1,2,3,4,5; 1,2,7",
        "SubSequence length is greater than array; 1,2,3,4,5; 1,2,3,4,5,4,5", "Reverse order;1,2,3,4,5;5,4",
        "Distinct array;3;1",delimiter = ';')
    internal fun `ValidateSubSeqInArrayTest - should return false for invalid scenarios`(testName:String,arrayStr:String, subSeqStr:String) {
        //given
        val array=arrayStr.split(",").map { it.toInt() }.toIntArray()
        val subSeq=subSeqStr.split(",").map { it.toInt() }.toIntArray()

        // when
        val result = subject.validateSubSequenceInArray(array, subSeq)

        // then
        assertFalse(result)
    }
}