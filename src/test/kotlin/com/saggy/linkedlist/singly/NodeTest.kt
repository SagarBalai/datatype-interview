package com.saggy.linkedlist.singly

import com.saggy.linkedlist.singly.factory.SingleLinkedListFactory.Companion.createLinkedList
import com.saggy.linkedlist.singly.factory.SingleLinkedListFactory.Companion.createLoopLinkedList
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
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
    internal fun `get - should get IndexOutOfBound exception when index parameter is negative`() {
        // given
        val node = createLinkedList(5)

        // when
        val result = assertThrows(IndexOutOfBoundsException::class.java) { node.get(-1) }

        // then
        assertEquals("Index -1 is not in linked list range", "" + result.message)
    }

    @Test
    internal fun `get - should get element at index`() {
        // given
        val node = createLinkedList(50)

        // when
        val result = node.get(25)

        // then
        assertEquals(25, result)
    }

    @Test
    internal fun `get recursive- should get IndexOutOfBound exception when get element at index greater than list length`() {
        // given
        val node = createLinkedList(5)
        val index = node.length() + 1

        // when
        val result = assertThrows(IndexOutOfBoundsException::class.java) { node.get(index, false) }

        // then
        assertEquals("Index $index is not in linked list range", "" + result.message)
    }

    @Test
    internal fun `get recursive- should get element at index`() {
        // given
        val node = createLinkedList(50)

        // when
        val result = node.get(25, false)

        // then
        assertEquals(25, result)
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

    @Test
    internal fun `length - should return length with recursive logic`() {
        // given
        val node = createLinkedList(25)

        // when
        val length = node.length(false)

        // then
        assertEquals(25, length)
    }

    @Test
    internal fun `length iterative- should return 0 for deleted linkedlist`() {
        // given
        val node = createLinkedList(20)
        assertEquals(20, node.length())
        node.delete()

        // when
        val length = node.length()

        //then
        assertEquals(0, length)

        // when
        node.add(12)

        // then
        assertEquals(1, node.length())
        assertEquals(12, node.get(0))
    }

    @Test
    internal fun `length recursive - should return 0 for deleted linkedlist`() {
        // given
        val node = createLinkedList(20)
        assertEquals(20, node.length(false))
        node.delete()

        // when
        val length = node.length(false)

        //then
        assertEquals(0, length)

        // when
        node.add(12)

        // then
        assertEquals(1, node.length(false))
        assertEquals(12, node.get(0))
    }

    @Test
    internal fun `exist - should return false if element does not exist in linked list`() {
        // given
        val node = createLinkedList(20)

        // when
        val exist = node.exist(100)

        // then
        assertFalse(exist)
    }

    @Test
    internal fun `exist - should return true if element exist in linked list`() {
        // given
        val node = createLinkedList(20)

        // when
        val exist = node.exist(15)

        // then
        assertTrue(exist)
    }

    @Test
    internal fun `exist recursive - should return false if element does not exist in linked list`() {
        // given
        val node = createLinkedList(20)

        // when
        val exist = node.exist(100, false)

        // then
        assertFalse(exist)
    }

    @Test
    internal fun `exist recursive- should return true if element exist in linked list`() {
        // given
        val node = createLinkedList(20)

        // when
        val exist = node.exist(15)

        // then
        assertTrue(exist)
    }

    @Test
    internal fun `getFromLastTwoIteration - should return second last element when nth node is 2`() {
        // given
        val node = createLinkedList(20)

        // when
        val elem = node.getFromLastTwoIteration(2)

        // then
        assertEquals(2, elem)

        // when
        node.add(100)

        // then
        assertEquals(100, node.getFromLastTwoIteration(21))
    }

    @Test
    internal fun `getFromLastTwoIteration - should throw IndexOutOfBound when called with index greater than length`() {
        // given
        val node = createLinkedList(20)
        val index = 22

        // when
        val result = assertThrows(IndexOutOfBoundsException::class.java) { node.getFromLastTwoIteration(22) }

        // then
        assertEquals("Index $index out of linked list range", result.message)
    }

    @Test
    internal fun `print - should return visual representation of linked list`() {
        // given
        val node = createLinkedList(10)

        // when
        val str = node.print()

        // then
        assertEquals("10->9->8->7->6->5->4->3->2->1->null", str)

        // when
        node.delete()

        //then
        assertTrue(node.print().isEmpty())
    }

    @Test
    internal fun `getFromLast - should return second last element when nth node is 2`() {
        // given
        val node = createLinkedList(20)

        // when
        val elem = node.getFromLast(2)

        // then
        assertEquals(2, elem)

        // when
        node.add(100)

        // then
        assertEquals(100, node.getFromLast(21))
    }

    @Test
    internal fun `getFromLast - should return 1 for one node linked list`() {
        // given
        val node = createLinkedList(1)

        // when
        val elem = node.getFromLast(1)

        // then
        assertEquals(1, elem)
    }

    @Test
    internal fun `middleWithTwoIteration - should return first element for single node linked list`() {
        // given
        val node = createLinkedList(1)

        // when
        val middle = node.middleWithTwoIteration()

        // then
        assertEquals(1, middle)
    }

    @Test
    internal fun `middleWithTwoIteration - should return middle element`() {
        // given
        val node = createLinkedList(5)

        // when
        val middle = node.middleWithTwoIteration()

        // then
        assertEquals(3, middle)
    }

    @Test
    internal fun `middle - should return first element for single node linked list`() {
        // given
        val node = createLinkedList(1)

        // when
        val middle = node.middle()

        // then
        assertEquals(1, middle)
    }

    @Test
    internal fun `middle - should return middle element`() {
        // given
        val node = createLinkedList(5)

        // when
        val middle = node.middle()

        // then
        assertEquals(3, middle)
    }

    @Test
    internal fun `middle - should return middle element for even node linked list`() {
        // given
        val node = createLinkedList(6)

        // when
        val middle = node.middle()

        // then
        assertEquals(4, middle)
    }

    @Test
    internal fun `findOccurrence - should return zero when element is not present`() {
        // given
        val node = createLinkedList(10)

        // when
        val count = node.findOccurance(20)

        // then
        assertEquals(0, count)
    }

    @Test
    internal fun `findOccurrence - should return five when element is present five times`() {
        // given
        val node = createLinkedList(10)
        node.add(5)
        node.add(5)
        node.add(5)
        node.add(5)

        // when
        val count = node.findOccurance(5)

        // then
        assertEquals(5, count)
    }

    @Test
    internal fun `isLoopPresent - should return false for single node linked list`() {
        // given
        val node = Node(1)

        // when
        val loopPresent = node.isLoopPresent()

        // then
        assertFalse(loopPresent)
    }

    @Test
    internal fun `isLoopPresent - should return true for linked list with loop`() {
        // given
        val node = createLoopLinkedList(5, 0)

        // when
        val loopPresent = node.isLoopPresent()

        // then
        assertTrue(loopPresent)
    }

    @Test
    internal fun `loopLength - should return 0`() {
        // given
        val node = createLinkedList(10)

        // when & then
        assertEquals(0, node.loopLength())
    }

    @Test
    internal fun `loopLength - should return true for linked list with loop`() {
        // given
        val node = createLoopLinkedList(5, 0)

        // when
        val length = node.loopLength()

        // then
        assertEquals(5, length)
    }

    @Test
    internal fun `isPalindrome - should return false for linked list without duplicate node data`() {
        // given
        val node = createLinkedList(10)

        // when
        val isPalindrome = node.isPalindrome()

        // then
        assertFalse(isPalindrome)
    }

    @Test
    internal fun `isPalindrome - should return true for even count palindrome linked list`() {
        // given
        val node = createLinkedList(3, true)
        node.addEnd(3)
        node.addEnd(2)
        node.addEnd(1)

        // when
        val isPalindrome = node.isPalindrome()

        // then
        assertTrue(isPalindrome)
    }

    @Test
    internal fun `isPalindrome - should return true for odd count palindrome linked list`() {
        // given
        val node = createLinkedList(3, true)
        node.addEnd(2)
        node.addEnd(1)
        val nodeStr = node.print()

        // when
        val isPalindrome = node.isPalindrome()

        // then
        assertTrue(isPalindrome)
        assertEquals(nodeStr, node.print())
    }

    @Test
    internal fun `isPalindrome - should return false for odd count palindrome linked list`() {
        // given
        val node = createLinkedList(3, true)
        node.addEnd(1)
        node.addEnd(2)
        val nodeStr = node.print()
        println(nodeStr)

        // when
        val isPalindrome = node.isPalindrome()

        // then
        assertFalse(isPalindrome)
        assertEquals(nodeStr, node.print())
    }


    @Test
    internal fun `reverse - should return same linked list for single node`() {
        // given
        val node = createLinkedList(1)

        // when
        val reverse = node.reverse()

        // then
        assertEquals(node, reverse)
    }

    @Test
    internal fun `reverse - should reverse linked list`() {
        // given
        val node = createLinkedList(10)

        // when
        val reverse = node.reverse()

        // then
        val originalNode =
            createLinkedList(10) // this can be replaced with clone node in given after clone implementation
        for (i in 0 until 10) {
            assertEquals(originalNode.get(i), reverse.get(9 - i))
        }
    }

    @Test
    internal fun `sort - should sort complete descending linked list`() {
        // given
        val node = createLinkedList(5)

        // when
        node.mergeSort(true)

        // then
        assertEquals("1->2->3->4->5->null", node.print())
    }

    @Test
    internal fun `sort - should return same linked list for already ascending sorted linked list`() {
        // given
        val node = createLinkedList(5, true)
        val nodeStr = node.print()

        // when
        node.mergeSort(true)

        // then
        assertEquals(nodeStr, node.print())
    }

    @Test
    internal fun `sort - should sort`() {
        // given
        val node = createLinkedList(2)
        node.add(15);node.add(21)
        node.add(463);node.add(42)
        node.add(32)
        println(node.print())

        // when
        node.mergeSort()
        assertEquals("1->2->15->21->32->42->463->null", node.print())
    }

    @Test
    internal fun `sort - should sort linked list with random numbers`() {
        // given
        val node = createLinkedList(2)
        node.add(32); node.add(15)
        node.add(21); node.add(463)
        node.add(42);node.add(11)
        node.add(25);node.add(16)
        node.add(25);node.add(16)
        node.add(25);node.add(16)
        node.add(25);node.add(16)

        // when
        node.mergeSort()

        // then
        assertEquals("1->2->11->15->16->16->16->16->21->25->25->25->25->32->42->463->null", node.print())
    }

    @Test
    internal fun `sort - should return descending sorted linked list`() {
        // given
        val node = createLinkedList(5)
        val nodeStr = node.print()

        // when
        node.mergeSort(false)

        // then
        assertEquals(nodeStr, node.print())
    }

    @Test
    internal fun `sort - should sort linked list in descending order with random numbers`() {
        // given
        val node = createLinkedList(2)
        node.add(32); node.add(15)
        node.add(21); node.add(463)
        node.add(42);node.add(11)
        node.add(25);node.add(16)
        node.add(25);node.add(16)
        node.add(25);node.add(16)
        node.add(25);node.add(16)

        // when
        node.mergeSort(false)

        // then
        assertEquals("463->42->32->25->25->25->25->21->16->16->16->16->15->11->2->1->null", node.print())
    }

    @Test
    internal fun `removeDuplicateInSortedList - should return single node linked list for same element`() {
        // given
        val node = createLinkedList(1)
        node.mergeSort()

        // when
        node.removeDuplicateInSortedList()

        // then
        assertEquals("1->null", node.print())

        // given
        for (i in 0..10) {
            node.add(1)
        }

        // when
        node.removeDuplicateInSortedList()

        // then
        assertEquals("1->null", node.print())
    }

    @Test
    internal fun `removeDuplicateInSortedList - should return same linked list with distinct elements`() {
        // given
        val node = createLinkedList(5)

        //when
        node.removeDuplicateInSortedList()

        //then
        assertEquals(5, node.length())
        assertEquals("5->4->3->2->1->null", node.print())

        // when
        node.add(1, 4)
        node.add(1, 4)
        node.add(1, 4)
        node.add(2, 3)
        node.add(2, 3)
        node.add(3, 2)
        node.add(3, 2)
        node.add(5);node.add(5)
        node.add(5);node.add(5)
        node.add(5);node.add(5)
        node.add(5);node.add(5)
        assertTrue(node.length() > 10)

        node.removeDuplicateInSortedList()

        // then
        assertEquals(5, node.length())
        assertEquals("5->4->3->2->1->null", node.print())
    }

    @Test
    internal fun `removeDuplicateInUnSortedList - should return original linked list`() {
        // given
        val node = createLinkedList(10)
        val nodeStr = node.print()

        // when
        node.removeDuplicateInUnSortedList()

        // then
        assertEquals(nodeStr, node.print())
        assertEquals(10, node.length())
    }

    @Test
    internal fun `removeDuplicateInUnSortedList - multiple scenarios`() {
        // given
        val node = createLinkedList(10)
        val nodeStr = node.print()
        node.addEnd(2);node.addEnd(2)
        node.addEnd(3);node.addEnd(4)
        node.addEnd(5);node.addEnd(2)
        node.addEnd(6);node.addEnd(5)
        node.addEnd(7);node.addEnd(8)

        // when
        node.removeDuplicateInUnSortedList()

        // then
        assertEquals(nodeStr, node.print())
        assertEquals(10, node.length())

        // when
        node.add(15)
        node.removeDuplicateInUnSortedList()

        // then
        assertEquals(11, node.length())
        assertEquals("15->$nodeStr", node.print())

        // when
        node.add(5,6)
        node.add(8,8)
        node.add(9,8)
        node.add(35)
        node.removeDuplicateInUnSortedList()

        // then
        assertEquals(12, node.length())
        assertEquals("35->15->$nodeStr", node.print())
    }

    @Test
    internal fun `swap - should swap elements`() {
        // given
        val node = createLinkedList(2)
        val length = node.length()

        // when
        node.swapNode(0,1)

        // then
        assertEquals(length, node.length())
        assertEquals("1->2->null",node.print())
    }

    @Test
    internal fun `swap - should swap elements for single node list`() {
        // given
        val node = createLinkedList(1)
        val length = node.length()

        // when
        node.swapNode(0,0)

        // then
        assertEquals(length, node.length())
        assertEquals("1->null",node.print())
    }

    @Test
    internal fun `swap - multiple scenarios`() {
        // given
        val node = createLinkedList(5)
        val nodeStr = node.print()

        // when
        node.swapNode(2,2)

        // then
        assertEquals(nodeStr,node.print())

        // when
        node.swapNode(0,1)

        // then
        assertEquals("4->5->3->2->1->null",node.print())

        // when
        node.swapNode(3,4)

        // then
        assertEquals("4->5->3->1->2->null",node.print())
    }


    @ParameterizedTest
    @CsvSource("1,10", "10,1", "-1,3","3,-1","10,10")
    fun `swap - should throw Bad request exception for all bad inputs, negatiive scenarios `(
        firstIndex: Int,
        secIndex: Int
    ) {
        // given
        val node = createLinkedList(5)

        // when
        val result =assertThrows(Exception::class.java){
            node.swapNode(firstIndex, secIndex)
        }

        // then
        assertEquals("Bad request",result.message)
    }
}