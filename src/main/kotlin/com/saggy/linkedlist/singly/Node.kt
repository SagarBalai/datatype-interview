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

    fun middle() =  middleNode().data

    private fun middleNode(node: Node=head!!): Node {
        var middle = node
        var temp = node

        while (temp.next != null && temp.next!!.next != null) {
            middle = middle.next!!
            temp = temp.next!!.next!!
        }
        return middle
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
                loop = true
                break
            }
        }
        var length = if (loop) 1 else 0
        slowPtr = slowPtr!!.next
        while (loop && slowPtr != fastPtr) {
            length++
            slowPtr = slowPtr!!.next
        }
        return length
    }

    /**
     * There are multiple ways to find palindrome in linked list as below
     * 1. Simple solution is two have extra pointer in Node ie prev and then with two nodes (start, last) can be done in O(n)
     * 2. With help of STACK. While iterating till middle element, push to stack and after middle pop and check. Both needs to be same. Can be done in O(n) but need extra space O(n/2)~O(n)
     * 3. Reversing complete linked list and checking both one
     * 4. Reversing only second half and then with start and middle pointer, cab be done in O(n) and once done again reverse second half to preserve original list.
     *
     * So here we implement without any additional data structure, will implement 4
     */
    fun isPalindrome(): Boolean {
        // 1->2->3->4->5->6->7->null

        val length = length()
        var middlePrevNode: Node? = null

        for (i in 0 until length / 2) {
            middlePrevNode = if (middlePrevNode == null) head else middlePrevNode.next
        }
        if (length % 2 != 0) {
            middlePrevNode = middlePrevNode!!.next
        }
        var prev: Node? = reverseCur(middlePrevNode!!.next)
        var temp = head
        middlePrevNode.next = prev

        var isPalindrome = true
        while (prev != null) {
            if (temp!!.data != prev.data) {
                isPalindrome = false
                break
            }
            prev = prev.next
            temp = temp.next
        }
        middlePrevNode.next = reverseCur(middlePrevNode.next)
        return isPalindrome
    }

    private fun reverseCur(node: Node?): Node? {
        var cur = node
        var prev: Node? = null

        while (cur != null) {
            val next = cur.next
            cur.next = prev
            prev = cur
            cur = next
        }
        return prev
    }

    fun reverse(): Node {
        var prev: Node? = null
        var cur = head

        while (cur != null) {
            val next = cur.next
            cur.next = prev
            prev = cur
            cur = next
        }
        head = prev
        return head!!
    }

    fun mergeSort(ascending: Boolean= true) {
        head = mergeSortRec(head)
    }

    private fun mergeSortRec(node: Node?): Node? {
        if (node?.next == null) {
            return node
        }
        val middle = middleNode(node)
        val middleNext = middle.next
        middle.next = null

        val first = mergeSortRec(node)
        val second = mergeSortRec(middleNext)
        return mergeList(first,second)
    }


    private fun mergeList(node1: Node?, node2: Node?): Node? {
        if(node1==null) return node2
        if(node2==null) return node1

        return if (node1.data<node2.data){
            node1.next=mergeList(node1.next,node2)
            node1
        } else{
            node2.next=mergeList(node1,node2.next)
            node2
        }
    }

}