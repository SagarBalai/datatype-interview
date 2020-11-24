package com.saggy.linkedlist.singly

class Node(private val data: Int) {
    var next: Node? = null
    var head: Node? = this

    /**
     * Time complexity :O(n)
     */
    fun length(itr: Boolean = true): Int {
        return if (itr) {
            lengthIterative()
        } else {
            lengthRecursive(head)
        }
    }

    /**
     * Time complexity : O(n)
     */
    private fun lengthRecursive(node: Node?): Int {
        return if (node == null) return 0 else 1 + lengthRecursive(node.next)
    }

    /**
     * Time complexity :O(n)
     */
    private fun lengthIterative(): Int {
        var length = 0
        var temp: Node? = head
        while (temp != null) {
            length++
            temp = temp.next
        }
        return length
    }

    /**
     * Time complexity : O(n)
     */
    fun add(element: Int, atIndex: Int = 0): Boolean {
        if (atIndex == 0) {
            addFirst(element)
        } else {
            addElementAtIndex(element, atIndex)
        }
        return true
    }

    /**
     * Time complexity :O(n)
     */
    private fun addElementAtIndex(element: Int, atIndex: Int) {
        var i = 0
        var node = head
        while (i < atIndex - 1 && node!!.next != null) {
            node = node.next
            i++
        }
        val temp = node!!.next
        node.next = Node(element)
        node.next!!.next = temp
    }

    /**
     * Time complexity :O(n) -- Iterate complete list to reach end.
     * This can be improved to O(1) by adding additional pointer (last) to last Node in linked list.
     */
    fun addEnd(element: Int) {
        var temp = head
        while (temp!!.next != null) {
            temp = temp.next
        }
        temp.next = Node(element)
    }

    /**
     * Time complexity : O(1)
     */
    fun addFirst(element: Int) {
        val temp = Node(element)
        if (head != null) {
            temp.next = head
        }
        head = temp
    }

    /**
     * Time complexity : O(n), to make all node available for GC directly
     * Time complexity :O(1) -- make head null and everything available for GC recursively
     */
    fun delete(): Boolean {
        var cur = head
        var next = cur!!.next
        while (next != null) {
            cur!!.next = null
            cur = next
            next = next.next
        }
        head = null
        return true
    }

    /**
     * Time complexity : O(n)
     * Worst case, need to iterate complete linked list
     */
    fun get(index: Int, itr: Boolean = true): Int {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index $index is not in linked list range")
        }
        return if (itr) {
            getItr(index)
        } else {
            try {
                getRecursive(head, index)
            } catch (e: IndexOutOfBoundsException) {
                throw IndexOutOfBoundsException("Index $index is not in linked list range")
            }
        }
    }

    private fun getRecursive(node: Node?, index: Int): Int {
        if (node == null && index >= 0) throw IndexOutOfBoundsException()

        return if (index == 0 && node != null) node.data
        else getRecursive(node!!.next, index - 1)
    }

    /**
     * Time complexity : O(n)
     */
    private fun getItr(index: Int): Int {
        var i = 0
        var temp = head
        while (temp != null) {
            if (i == index) {
                return temp.data
            }
            temp = temp.next
            i++
        }
        throw IndexOutOfBoundsException("Index $index is not in linked list range")
    }

    /**
     *  Time complexity : O(n)
     */
    fun exist(element: Int, itr: Boolean = true): Boolean {
        return if (itr) {
            existItr(element)
        } else {
            existRec(head, element)
        }
    }

    /**
     *  Time complexity : O(n) -- In worst case, checking each node in recursive function
     */
    private fun existRec(node: Node?, element: Int): Boolean {
        return node != null && (node.data == element || existRec(node.next, element))
    }

    /**
     *  Time complexity : O(n) -- iterate complete list
     */
    private fun existItr(element: Int): Boolean {
        var temp = head
        while (temp != null) {
            if (temp.data == element) return true
            temp = temp.next
        }
        return false
    }

    /***
     *   Time complexity : length() is O(n) + get() is O(n)  == O(n)
     *   but this can be improved with single iteration with another pointer
     */
    fun getFromLastTwoIteration(position: Int): Int {
        val length = length()
        if (position > length) {
            throw IndexOutOfBoundsException("Index $position out of linked list range")
        }
        return get(length - position)
    }

    /***
     *   Time complexity :O(n) -- With two pointers, can be found in one iteration
     */
    fun getFromLast(position: Int): Int {
        var i = 1
        var temp = head
        var nthNode: Node? = null

        while (temp != null) {
            if (i >= position) {
                nthNode = if (nthNode == null) head
                else {
                    nthNode.next
                }
            }
            temp = temp.next
            i++
        }
        if (nthNode == null) {
            throw IndexOutOfBoundsException("Index $position out of linked list range")
        }
        return nthNode.data
    }

    /**
     * Time complexity : O(n) -- iterate over linked list
     */
    fun print(): String {
        val sb = StringBuilder()
        var temp = head
        while (temp != null) {
            sb.append("${temp.data}->")
            temp = temp.next
        }
        if (sb.isNotEmpty()) sb.append("null")
        return sb.toString()
    }

    /**
     * find middle element using length of linked list.
     * Time complexity : O(n)
     * but it needs two separate iteration one for length and one to find middle O(n)+O(n).
     */
    fun middleWithTwoIteration(): Int {
        val length = length()
        var i = 0
        var middle = head
        while (i < length / 2) {
            middle = middle!!.next
            i++
        }
        return middle!!.data
    }

    fun middle(): Int {
        var middle = head!!
        var temp = head

        while (temp!!.next != null && temp.next!!.next != null) {
            middle = middle.next!!
            temp = temp.next!!.next
        }
        return middle.data
    }

    fun findOccurance(element: Int): Int {
        var count = 0
        var temp = head

        while (temp != null) {
            if (temp.data == element) {
                count++
            }
            temp = temp.next
        }
        return count
    }

    /**
     * This is optimal approach to detect loop in linked list
     * Time Complexity: O(n)
     * Other approach is with hashing
     * Use ```HashSet``` to store Node and then check is it getting duplicated :: Time complexity O(n) as need single traversal but it needs extra space to store node data so Auxiliary space : O(n)
     */
    fun isLoopPresent(): Boolean {
        var loop = false
        var slowPtr = head
        var fastPtr = head

        while (slowPtr?.next != null
            && fastPtr?.next != null && fastPtr.next!!.next != null
        ) {
            slowPtr = slowPtr.next
            fastPtr = fastPtr.next!!.next
            if (slowPtr == fastPtr) {
                loop = true
                break
            }
        }
        return loop
    }

    /**
     * Time Complexity: O(n)
     */
    fun loopLength(): Int {
        var slowPtr = head
        var fastPtr = head
        var loop = false
        while (!(slowPtr?.next == null || fastPtr?.next == null || fastPtr.next!!.next == null)
        ) {
            slowPtr = slowPtr.next
            fastPtr = fastPtr.next!!.next
            if (slowPtr == fastPtr) {
                loop= true
                break
            }
        }
        var length = 1
        slowPtr =slowPtr!!.next
        while (loop && slowPtr != fastPtr) {
            length++
            slowPtr = slowPtr!!.next
        }
        return length
    }


}