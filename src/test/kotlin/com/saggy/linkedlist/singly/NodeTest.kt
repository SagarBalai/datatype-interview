package com.saggy.linkedlist.singly

import com.saggy.linkedlist.singly.factory.SingleLinkedListFactory.Companion.createLinkedList
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class NodeTest {
    @Test
    internal fun `length - should return 1 for one node linked list`() {
        // given
        val node = Node(1)

        //when
        val length = node.length()

        //then
        assertEquals(1, length)
    }

    @Test
    internal fun `length - should return 4 for four node linked list`() {
        // given
        val node = createLinkedList(2)

        //when
        val length = node.length()

        //then
        assertEquals(2, length)
    }

    @Test
    internal fun `add - should insert element at first position`() {
        // given
        val node = Node(1)

        // when
        val flag = node.add(2)

        // then
        assertTrue(flag)
        assertEquals(2, node.length())
        assertEquals(2, node.get(0))
    }

    @Test
    internal fun `addFirst - should insert element at first position`() {
        // given
        val node = createLinkedList(10)

        // when
        node.addFirst(100)

        // then
        assertEquals(11, node.length())
        assertEquals(100, node.get(0))
    }

    @Test
    internal fun `get - should get IndexOutOfBound exception when get element at index greater than list length`() {
        // given
        val node = createLinkedList(5)
        val index = node.length() + 1

        // when
        val result = assertThrows(IndexOutOfBoundsException::class.java) { node.get(index) }

        // then
        assertEquals("Index $index is not in linked list range", "" + result.message)
    }

    @Test
    internal fun `delete - should return true`() {
        // given
        val node = createLinkedList(10)

        // when
        node.delete()

        // then
        assertEquals(0, node.length())
    }

    @Test
    internal fun `add - should insert element at the end of linked list`() {
        // given
        val node = createLinkedList(5)
        val len = node.length()

        // when
        node.addEnd(11)

        // then
        assertEquals(len + 1, node.length())
        assertEquals(11, node.get(node.length() - 1))

        // when
        node.addEnd(21)

        // then
        assertEquals(len + 2, node.length())
        assertEquals(21, node.get(len + 1))
    }

    @Test
    internal fun `add - should insert element at 5th Index in linkedlist with length 10`() {
        // given
        val node = createLinkedList(10)
        val old = node.get(5)

        //when
        node.add(100, 5)

        // then
        assertEquals(100, node.get(5))
        assertEquals(old, node.get(6))
        assertEquals(11, node.length())
    }
}