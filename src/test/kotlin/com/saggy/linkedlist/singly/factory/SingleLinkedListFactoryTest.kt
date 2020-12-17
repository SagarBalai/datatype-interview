package com.saggy.linkedlist.singly.factory

import com.saggy.linkedlist.singly.factory.SingleLinkedListFactory.Companion.createLinkedList
import com.saggy.linkedlist.singly.factory.SingleLinkedListFactory.Companion.createLoopLinkedList
import com.saggy.linkedlist.singly.factory.SingleLinkedListFactory.Companion.createPairWiseSortedLinkedList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class SingleLinkedListFactoryTest {

    @Test
    internal fun `createLinkedList - should throw exception when count is negative`() {
        // when & then
        val result = assertThrows(Exception::class.java) { createLinkedList(-1) }
        assertEquals("count can not be negative", result.message)
    }

    @Test
    internal fun `createLinkedList - should create linked list with 5 nodes when count is 5`() {
        // when
        val node = createLinkedList(5)

        // then
        assertEquals(5, node.length())
    }

    @Test
    internal fun `createLinkedList - should create linked list with head as lowest element when lowTohigh is true`() {
        // when
        val node = createLinkedList(5, true)

        // then
        assertEquals(1, node.get(0))
        assertEquals("1->2->3->4->5->null", node.print())
    }

    @Test
    internal fun `createLinkedList - should create linked list with head as highest element as default behaviour`() {
        // when
        val node = createLinkedList(5)

        // then
        assertEquals(5, node.get(0))
        assertEquals("5->4->3->2->1->null", node.print())
    }

    @Test
    internal fun `createLoopLinkedList - should create loop from 2nd index in 5 node linked list`() {
        // when
        val node = createLoopLinkedList(5, 2)

        //then
        assertTrue(node.isLoopPresent())
        assertEquals(3, node.loopLength())
    }

    @Test
    internal fun `createLoopLinkedList - should create loop from zero index in 5 node linked list`() {
        // when
        val node = createLoopLinkedList(5, 0)

        //then
        assertTrue(node.isLoopPresent())
        assertEquals(5, node.loopLength())
    }

    @Test
    internal fun `createLoopLinkedList - should create 1 length loop node`() {
        // when
        val node = createLoopLinkedList(1, 0)

        //then
        assertTrue(node.isLoopPresent())
        assertEquals(1, node.loopLength())
    }

    @Test
    internal fun `createLoopLinkedList - should throw an exception of loopIndex is greater than length`() {
        // when & then
        val result = assertThrows(RuntimeException::class.java) { createLoopLinkedList(5, 10) }
        assertEquals("Bad request - loopCount should be less than count", result.message)
    }

    @ParameterizedTest
    @CsvSource("1", "2", "4", "5", "10", "23")
    internal fun `createSortedLinkedList - should return list`(count: Int) {
        // given
        val node1 = createLinkedList(count)
        node1.pairWiseSwapNode()

        // when
        val node = createPairWiseSortedLinkedList(count)

        // then
        assertEquals(node1.print(), node.print())
    }


}