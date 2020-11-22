package com.saggy.linkedlist.singly

open class Node(val data: Int) {
    var next: Node? = null

    fun length(): Int {
        var length = 0
        var head :Node?= this
        while (head != null) {
            length++
            head = head.next
        }
        return length
    }
}