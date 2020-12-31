package com.saggy.array.twoD

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.AggregateWith
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class RiverSizeSolutionTest {
    private val subject = RiverSizeSolution()

    @Test
    internal fun `calculateRiverSize - should return empty results for empty matrix`() {
        // given
        var array = Array(5) { IntArray(5) }

        // when
        val result = subject.calculateRiverSize(array)

        // then
        assertTrue(result.isEmpty())
    }

    @Test
    internal fun `calculateRiverSize - should return one element for matrix with single 1`() {
        // given
        var array = Array(5) { IntArray(5) }
        array[2][2] = 1

        // when
        val result = subject.calculateRiverSize(array)

        // then
        assertEquals(1, result.size)
        assertEquals(1, result[0])
    }

    @Test
    internal fun `calculateRiverSize - example in java doc`() {
        // given
        var array = Array(5) { IntArray(5) }
        array[0][0] = 1;array[0][3] = 1
        array[1][0] = 1;array[1][2] = 1
        array[2][2] = 1;array[2][4] = 1
        array[3][0] = 1;array[3][2] = 1;array[3][4] = 1
        array[4][0] = 1;array[4][2] = 1;array[4][3] = 1

        // when
        val result = subject.calculateRiverSize(array)

        // then
        assertEquals(5, result.size)
        val expected = listOf<Int>(2, 1, 5, 2, 2)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @CsvSource(
        "[[1, 0, 0, 1, 0], [1, 0, 1, 0, 0],[0, 0, 1, 0, 1],[1, 0, 1, 0, 1],[1, 0, 1, 1, 0]];[2, 1, 5, 2, 2]",
        "[[0]];[ ]", "[[1]];[1]", "[[1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0]];[3,2,1]",
        "[[1, 0, 0, 1], [1, 0, 1, 0], [0, 0, 1, 0], [1, 0, 1, 0]];[2,1,3,1]",
        "[[1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0],[1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0],[0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1],[1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0],[1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1]];[2, 1, 10, 11, 5, 2, 1]",
        "[[1, 0, 0, 0, 0, 0, 1],[0, 1, 0, 0, 0, 1, 0],[0, 0, 1, 0, 1, 0, 0],[0, 0, 0, 1, 0, 0, 0],[0, 0, 1, 0, 1, 0, 0],[0, 1, 0, 0, 0, 1, 0],[1, 0, 0, 0, 0, 0, 1]];[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]",
        "[[1, 0, 0, 0, 0, 0, 1],[0, 1, 0, 0, 0, 1, 0],[0, 0, 1, 0, 1, 0, 0],[0, 0, 1, 1, 1, 0, 0],[0, 0, 1, 0, 1, 0, 0],[0, 1, 0, 0, 0, 1, 0],[1, 0, 0, 0, 0, 0, 1]];[1, 1, 1, 1, 7, 1, 1, 1, 1]",
        "[[0, 0, 0, 0, 0, 0, 0],[0, 0, 0, 0, 0, 0, 0],[0, 0, 0, 0, 0, 0, 0],[0, 0, 0, 0, 0, 0, 0],[0, 0, 0, 0, 0, 0, 0],[0, 0, 0, 0, 0, 0, 0],[0, 0, 0, 0, 0, 0, 0]];[ ]",
        "[[1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1],[1, 1, 1, 1, 1, 1, 1]];[49]",
        "[[1, 1, 0, 0, 0, 0, 1, 1],[1, 0, 1, 1, 1, 1, 0, 1],[0, 1, 1, 0, 0, 0, 1, 1]];[3,5,6]",
        "[[1, 1, 0],[1, 0, 1],[1, 1, 1],[1, 1, 0],[1, 0, 1],[0, 1, 0],[1, 0, 0],[1, 0, 0],[0, 0, 0],[1, 0, 0],[1, 0, 1],[1, 1, 1]];[10,1,1,2,6]",
        delimiter = ';'
    )
    internal fun `calculateRiverSize - should return expected output`(
        @AggregateWith(TwoDArrayAggregator::class) array: Array<IntArray>,
        @AggregateWith(ListAggregator::class) expected: List<Int>
    ) {
        // when
        val result = subject.calculateRiverSize(array)

        // then
        assertEquals(expected, result)

    }

    internal class TwoDArrayAggregator : ArgumentsAggregator {
        @Throws(ArgumentsAggregationException::class)
        override fun aggregateArguments(accessor: ArgumentsAccessor, context: ParameterContext): Array<IntArray> {
            val string = accessor.getString(0)
            if (!string.contains(",")) {
                val elem = string.substring(string.indexOf("[[") + 2, string.indexOf("]]")).trim().toInt()
                var array = Array(1) { IntArray(1) }
                array[0][0] = elem
                return array
            } else {
                val splits = string.substring(1, string.length - 2).split("],")

                val cols = splits[0].split(",").size
                var array = Array(splits.size) { IntArray(cols) }

                for (i in splits.indices) {
                    val trim = splits[i].trim()
                    val subSequence = trim.subSequence(startIndex = 1, endIndex = trim.length)
                    val col = subSequence.split(",")
                    for (j in 0 until cols) {
                        array[i][j] = col[j].trim().toInt()
                    }
                }
                return array
            }
        }
    }

    internal class ListAggregator : ArgumentsAggregator {
        @Throws(ArgumentsAggregationException::class)
        override fun aggregateArguments(accessor: ArgumentsAccessor, context: ParameterContext): List<Int> {
            var string = accessor.getString(1)
            string = string.trim().substring(1, string.length - 1)

            if (string.trim().isEmpty()) return emptyList()

            val splits = string.split(",")
            var list = mutableListOf<Int>()
            for (i in splits.indices) {
                list.add(splits[i].trim().toInt())
            }
            return list
        }
    }
}
