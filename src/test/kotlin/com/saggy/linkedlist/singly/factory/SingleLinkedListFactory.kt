package com.saggy.linkedlist.singly.factory

import com.saggy.linkedlist.singly.Node

class SingleLinkedListFactory {
    companion object {
        fun createLinkedList(count: Int, ascending: Boolean = false): Node {
            if (count < 0) {
                throw RuntimeException("count can not be negative")
            }
            return if (ascending) {
                val node = Node(count)
                for (i in count-1 downTo 1 ){
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


        fun createLoopLinkedList(count: Int, loopIndex: Int): Node {
            if(loopIndex >=count){
                throw RuntimeException("Bad request - loopCount should be less than count")
            }
            val node = createLinkedList(count,true)
            if(count ==1 && loopIndex==0){
                node.head!!.next= node.head
                return node
            }

            var loopNode :Node? = null
            var temp = node.head
            var i =0
            while (temp!!.next != null)
            {
               if(loopIndex ==i){
                   loopNode = temp
               }
               temp= temp.next
                i++
            }
            temp.next = loopNode
            return node
        }
    }

}