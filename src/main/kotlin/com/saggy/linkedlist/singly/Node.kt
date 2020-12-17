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

    fun middle() = middleNode().data

    private fun middleNode(node: Node = head!!): Node {
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

    fun mergeSort(ascending: Boolean = true) {
        head = mergeSortRec(head, ascending)
    }

    private fun mergeSortRec(node: Node?, ascending: Boolean): Node? {
        if (node?.next == null) {
            return node
        }
        val middle = middleNode(node)
        val middleNext = middle.next
        middle.next = null

        val first = mergeSortRec(node, ascending)
        val second = mergeSortRec(middleNext, ascending)
        return mergeList(first, second, ascending)
    }


    private fun mergeList(
        node1: Node?,
        node2: Node?,
        ascending: Boolean
    ): Node? {
        if (node1 == null) return node2
        if (node2 == null) return node1

        if (ascending) {
            return if (node1.data < node2.data) {
                node1.next = mergeList(node1.next, node2, ascending)
                node1
            } else {
                node2.next = mergeList(node1, node2.next, ascending)
                node2
            }
        } else {
            return if (node1.data > node2.data) {
                node1.next = mergeList(node1.next, node2, ascending)
                node1
            } else {
                node2.next = mergeList(node1, node2.next, ascending)
                node2
            }
        }
    }

    /**
     * It will remove duplicates in sorted linked list.
     * It needs full iteration over list, Time Complexity: O(n) but as sorted list is pre-requisite
     * so with merge sort time complexity is O(nLogn)
     */
    fun removeDuplicateInSortedList() {
        var result = head
        var temp = head!!.next
        while (temp != null) {
            if (result!!.data != temp.data) {
                result.next = temp
                result = result.next
            }
            temp = temp.next
        }
        result!!.next = null
    }

    /**
     * There are multiple ways to remove duplicates
     * 1. Using two loops
     *    For each element in list, iterate complete list to find out whether it is present or not.
     *    This can be optimized with searching element from next index in list but still time complexity is O(n2)
     * 2. with Sorting : Sort list first and then use removeDuplicateInSortedList to remove duplicates, Time complexity: O(nLogn)+O(n) ==> O(nLogn)
     * 3. Using Hash -- Need single iteration so Time Complexity : O(n) but need additional space
     *    to store distinct elements so space complexity can be O(n) for list with all distinct elements.
     *
     * Below implementation is using Hash method.
     */
    fun removeDuplicateInUnSortedList() {
        val set = mutableSetOf<Int>()
        var result = head!!
        set.add(result.data)
        var temp = result.next
        while (temp != null) {
            if (!set.contains(temp.data)) {
                result.next = temp
                result = result.next!!
            }
            set.add(temp.data)
            temp = temp.next
        }
        result.next = null
    }

    fun swapNode(firstIndex: Int, secIndex: Int) {
        val minIndex = if (firstIndex < secIndex) firstIndex else secIndex
        if (minIndex < 0) {
            throw RuntimeException("Bad request- index cannot be negative: $minIndex")
        }

        val maxIndex = if (firstIndex > secIndex) firstIndex else secIndex

        var temp = head
        var i = 0

        var prev1: Node? = null
        while (minIndex != 0 && temp != null) {
            if (minIndex == i) {
                break
            }
            i++
            prev1 = temp
            temp = temp.next
        }

        if (minIndex > i) {
            throw RuntimeException("Bad request - minimum index : $minIndex is greater than length of list, please pass within list range")
        }

        // this is added here to handle input (length+n,length+m) .. n,m can be any positive number
        // If we are sure that inputs are always proper means within list range then this can be first validation in behavior.
        // As out of range length can be handled in last condition, we are sure at least minIndex is in list range (0..listLength)
        if (firstIndex == secIndex) {
            return
        }

        var prev2: Node? = null
        while (temp != null) {
            if (maxIndex == i) {
                break
            }
            i++
            prev2 = temp
            temp = temp.next
        }

        if (maxIndex > i) {
            throw RuntimeException("Bad request - maximum index : $minIndex is greater than length of list, please pass within list range")
        }

        val first = if (prev1 == null) head else prev1.next
        val second = prev2!!.next

        if (prev1 == null) head = second else prev1.next = second
        prev2.next = first

        val next1 = first!!.next
        first.next = second!!.next
        second.next = next1
    }

    /**
     * Below method will swap adjusant elements. Need to iterate list once so time complexity: O(n)
     * Need constant space to store prev, cur and next node info so time complexity is constant.
     *
     * There are two basic scenarios:
     * 1. Even length list
     *    Input : 10->9->8->7->6->5->4->3->2->1->null
     *    Output: 9->10->7->8->5->6->3->4->1->2->null
     * 2. Odd length List
     *    Input : 9->8->7->6->5->4->3->2->1->null
     *    Output: 8->9->6->7->4->5->2->3->1->null
     *
     *  It is implemented in recursive and iterative way.
     *  In recursive way, it will fill stack for every recursive function call so recursive is not good option with large list as stack Memory is always less so high chance to get StackOverflow error.
     */
    fun pairWiseSwapNode(itr: Boolean = true) {
        if (itr) {
            pairWiseSwapIterative()
        } else {
            head = pairSwapRec(head)
        }
    }

    private fun pairWiseSwapIterative() {
        var cur = head
        var prev: Node? = null
        head = cur!!.next ?: cur

        while (cur != null) {
            val next = cur.next
            cur.next = next?.next
            next?.next = cur
            prev?.next = next ?: cur

            prev = cur
            cur = cur.next
        }
    }

    private fun pairSwapRec(node: Node?): Node? {
        if (node?.next == null) {
            return node
        }

        val next = node.next
        node.next = pairSwapRec(next!!.next)
        next.next = node
        return next
    }

    //9->10->7->8->5->6->3->4->1->2->null
    fun isPairWiseSorted(): Boolean {
        var cur = head

        while (cur?.next != null) {
            if (cur.data > cur.next!!.data) {
                return false
            }
            cur = cur.next!!.next
        }
        return true
    }


}