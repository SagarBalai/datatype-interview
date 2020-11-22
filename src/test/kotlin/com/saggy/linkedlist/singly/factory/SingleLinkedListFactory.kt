package com.saggy.linkedlist.singly.factory

import com.saggy.linkedlist.singly.Node

class SingleLinkedListFactory {
    companion object{
        fun createLinkedList(count:Int): Node {
            val node = Node(1)
            for (i in 2..count){
                node.add(i)
            }
            return node
        }
    }

}