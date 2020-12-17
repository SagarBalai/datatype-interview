package com.saggy.linkedlist.singly.factory

import com.saggy.linkedlist.singly.Node

/**
 * An object that creates new single linked list. It creates linked list with multiple variation as
 * 1. Linked list with  ascending/ descending order
 * 2. Looped linked list
 * 3. Pairwise sorted linked list
 *
 * way to create linked list
 * ```
 *      createLinkedList(10,true)
 * ```
 * @author: Sagar Balai
 */
class SingleLinkedListFactory {
    companion object {

        /**
         * Create linked list with number of nodes are count with default descending order.
         * Order can be changed by passing `ascending` flag to true.
         * @param count number of elements in linked list
         * @param ascending is flag for order in linked list
         * @return Linked list node
         * @throws RuntimeException if count is negative.
         */
        fun createLinkedList(count: Int, ascending: Boolean = false): Node {
            if (count < 0) {
                throw RuntimeException("count can not be negative")
            }
            return if (ascending) {
                val node = Node(count)
                for (i in count - 1 downTo 1) {
                    node.add(i)
                }
                node
            } else {
                val node = Node(1)
                for (i in 2..count) {
                    node.add(i)
                }
                node
            }
        }

        /**
         * Create Signle linked list with loop.
         * @param count number of elements in linked list
         * @param loopIndex index of node where want to create loop
         * @return looped linked list
         * @throws RuntimeException if loopIndex is greater than length of linked list.
         *
         * ```
         * eg.  Loop linked list with count: 5 and loopIndex: 2
         *      1 -> 2-> 3 ->4 ->5
         *               |       |
         *                <- <- <-
         * ```
         */
        fun createLoopLinkedList(count: Int, loopIndex: Int): Node {
            if (loopIndex >= count) {
                throw RuntimeException("Bad request - loopCount should be less than count")
            }
            val node = createLinkedList(count, true)
            if (count == 1 && loopIndex == 0) {
                node.head!!.next = node.head
                return node
            }

            var loopNode: Node? = null
            var temp = node.head
            var i = 0
            while (temp!!.next != null) {
                if (loopIndex == i) {
                    loopNode = temp
                }
                temp = temp.next
                i++
            }
            temp.next = loopNode
            return node
        }

        /**
         * Creates pairwise sorted single linked list with given number of elements as count.
         * @param count is number of elements in linked list
         * @return linked list node
         * @throws RuntimeException if count is negative.
         * ```
         * eg.
         * 1. Odd length single linked list
         *      4->5->2->3->1->null
         * 2. Even length single linked list
         *      9->10->7->8->5->6->3->4->1->2->null
         * ```
         */
        fun createPairWiseSortedLinkedList(count: Int): Node {
            if (count < 0) {
                throw RuntimeException("Bad request - count can not be negative")
            }
            if (count == 1) {
                return Node(1)
            }

            val node = Node(count - 1)
            node.addEnd(count)
            var i = count - 2
            while (i > 1) {
                node.addEnd(i - 1)
                node.addEnd(i)
                i -= 2
            }
            if (i > 0) {
                node.addEnd(i)
            }
            return node
        }
    }

}