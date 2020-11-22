package com.saggy.linkedlist.singly

class Node(private val data: Int) {
    private var next: Node? = null
    private var head: Node? = this

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
    fun getFromLast(position: Int): Int {
        val length = length()
        if (position > length) {
            throw IndexOutOfBoundsException("Index $position out of linked list range")
        }
        return get(length - position)
    }

    /***
     *   Time complexity :O(n) -- With two pointers, can be found in one iteration
     */
    fun getFromLastImproved(position: Int): Int {
        var i = 1
        var temp = head
        var nthNode: Node? = null

        while (temp != null) {
            if (i >= position) {
                nthNode = if (nthNode == null) head
                else{
                    nthNode!!.next
                }
            }
            temp = temp.next
            i++
        }
        if(nthNode == null){
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
}