package com.saggy.linkedlist.singly

class Node(private val data: Int) {
    var next: Node? = null
    var head: Node? = this

    fun length(): Int {
        var length = 0
        var temp: Node? = head
        while (temp != null) {
            length++
            temp = temp.next
        }
        return length
    }

    fun add(element: Int): Boolean {
        val temp = Node(element)
        if (head == null) {
            head = temp
        }
        temp.next = head
        head = temp
        return true
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