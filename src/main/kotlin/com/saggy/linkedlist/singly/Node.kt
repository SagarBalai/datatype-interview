package com.saggy.linkedlist.singly

class Node(private val data: Int) {
    var next: Node? = null
    var head: Node? = this

    fun length(itr: Boolean = true): Int {
        if (itr) {
            return lengthIterative()
        } else {
            return lengthRecursive(head)
        }
    }

    private fun lengthRecursive(node: Node?): Int {
        return if (node == null) return 0 else 1 + lengthRecursive(node.next)
    }

    private fun lengthIterative(): Int {
        var length = 0
        var temp: Node? = head
        while (temp != null) {
            length++
            temp = temp.next
        }
        return length
    }

    fun add(element: Int, atIndex: Int = 0): Boolean {
        if (atIndex == 0) {
            addFirst(element)
        } else {
            addElementAtIndex(element, atIndex)
        }
        return true
    }

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

    fun addEnd(element: Int) {
        var temp = head
        while (temp!!.next != null) {
            temp = temp.next
        }
        temp.next = Node(element)
    }

    fun addFirst(element: Int) {
        val temp = Node(element)
        if (head != null) {
            temp.next = head
        }
        head = temp
    }

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

    fun get(index: Int): Int {
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
}