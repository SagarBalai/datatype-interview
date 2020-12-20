package com.saggy.linkedlist.singly

/**
 * Single linked list implementation with data and reference to next Node.
 *
 * All of the operations perform as could be expected for a single-linked
 * list. Operations that index into the list will traverse the list from
 * the beginning.
 *
 * Many operations are implemented in iterative and recursive manner, default one is always iterative.
 *
 * Implementation is done with TDD Test driven development so it will always have 100% test coverage.
 * In tests, there are few ParameterizedTest to combine most of the positive and negative scenarios together.
 *
 * @author Sagar Balai
 */
class Node(private val data: Int) {
    var next: Node? = null
    var head: Node? = this

    /**
     * Calculate length of linked list.
     * ```
     * Time complexity :O(n)
     * To calculate length, we need to visit each node at most once so time complexity is O(n)
     * ```
     * @param isIterative flag indicates whether length is calculated in iterative/recursive manner, default is iterative
     */
    fun length(isIterative: Boolean = true): Int {
        return if (isIterative) {
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
     * It will add Node with given `element` value at `atIndex` position, default `atIndex` is 0
     * so by default add will insert new node at first index.
     *
     * Time complexity : O(n)
     * In default behavior, new element is added at first index so time complexity is O(1).
     * But adding element at specific index may need to travel complete linked list to add element at last index : O(n)
     *
     * @param element is data value for new node
     * @param atIndex is index where new node will be added
     * @return flag to indicate whether operation is successful
     * @throws RuntimeException if `atIndex` is negative or greater than length of list.
     */
    fun add(element: Int, atIndex: Int = 0): Boolean {
        if (atIndex < 0) {
            throw RuntimeException("Bad request - atIndex can not be negative")
        }
        if (atIndex == 0) {
            addFirst(element)
        } else {
            addElementAtIndex(element, atIndex)
        }
        return true
    }

    /**
     * Assumption: `atIndex` is positive number.
     *
     * Time complexity :O(n)
     */
    private fun addElementAtIndex(element: Int, atIndex: Int) {
        var i = 0
        var node = head
        while (i < atIndex - 1 && node!!.next != null) {
            node = node.next
            i++
        }
        if (i < atIndex - 1) {
            throw RuntimeException("Bad request - `atIndex` is greater than length of list")
        }
        val temp = node!!.next
        node.next = Node(element)
        node.next!!.next = temp
    }

    /**
     * Insert element at end of the linked list.
     *
     * Time complexity :O(n) -- Iterate complete list to reach end of linked list.
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
     * Insert element at start of the linked list/ as head in list.
     * Time complexity : O(1)
     *
     * @param new node with value as element.
     */
    fun addFirst(element: Int) {
        val temp = Node(element)
        if (head != null) {
            temp.next = head
        }
        head = temp
    }

    /**
     * Delete linked list.
     *
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
     * Get node value at given `index`.
     *
     * Time complexity : O(n)
     * Worst case, need to iterate complete linked list
     *
     * @param index of node in linked list
     * @param itr flag to indicate whether get operation in iterative/ recursive manner, default is iterative.
     * @return integer value of node at given index
     * @throws IndexOutOfBoundsException if index is negative or greater than list length.
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
     * It will check existence/isPresent of given element `element` in linked list.
     *
     * Time complexity : O(n)
     * Need to travel complete list in worst scenario so need to visit all node at most once.
     *
     * @param element is present or not in list
     * @param itr flag indicates whether exist is performed in iterative/recursive manner, default is iterative.
     * @return if element is present or not in list
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
     *  It will get Node at given position from last node in linked list.
     *
     *  Time complexity : length() is O(n) + get() is O(n)  == O(n)
     *  but this can be improved with single iteration with another pointer
     *
     *  @param position of node in linked list from last node.
     *  @return return value of node at given position
     *  @throws IndexOutOfBoundsException if position is out of range
     */
    fun getNodeFromEndOfListWithTwoIteration(position: Int): Int {
        val length = length()
        if (position > length) {
            throw IndexOutOfBoundsException("Index $position out of linked list range")
        }
        return get(length - position)
    }

    /***
     *  It will get Node at given position from last node in linked list.
     *
     *  Time complexity :O(n) -- With two pointers, can be found in one iteration
     *
     *  @param position of node in linked list from last node.
     *  @return return value of node at given position
     *  @throws IndexOutOfBoundsException if position is out of range
     */
    fun getNodeFromEndOfList(position: Int): Int {
        if(position <0){
            throw IndexOutOfBoundsException("position can not be negative")
        }

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
     * It will print complete linked list in string format.
     *
     * Time complexity : O(n) -- iterate over linked list.
     *
     * @return string representation of linked list.
     *
     * ```
     * eg. String representation of Linked list of size 10
     *     10->9->8->7->6->5->4->3->2->1->null
     *```
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
     * It will return middle element of linked list, this implementation uses length
     * so it needs two different iteration, one to get length and another to find actual middle element.
     *
     * Time complexity : O(n)
     * but it needs two separate iteration one for length and one to find middle O(n)+O(n).
     *
     * @return middle element of linked list.
     * ```
     *  eg. List = 1->2->3->4->5->6->null , middle is 3
     *      List = 1->2->3->4->5->null , middle is 3
     * ```
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

    /**
     * It will return middle element of linked list.
     *
     * Time complexity : O(n)
     * This implementation needs two pointers , one to iterate over list and another needed to keep track of middle element at that time.
     *
     * @return middle element of linked list.
     * ```
     *  eg. List = 1->2->3->4->5->6->null , middle is 3
     *      List = 1->2->3->4->5->null , middle is 3
     * ```
     */
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

    /**
     * It finds occurrences of given element in list.
     *
     * Time complexity: O(n) -- need to iterate complete linked list.
     *
     * @param element for which occurences need to find in list
     * ```
     * eg. list = 1->2->3->2->2-2->null, findOccurrence(2) will be 4
     *     list = 1->2->3->null, findOccurrence(4) will be 0
     * ```
     */
    fun findOccurrence(element: Int): Int {
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
     * It will find if linked list contains loop or not.
     *
     * To find loop in linked list, there are 2 ways
     * 1. Hashset -- to store Node(hashcode - physical address) and then check is it getting duplicated
     *    Time complexity O(n) as need single traversal of list but it needs extra space to store node
     *    so Auxiliary space : O(n)
     * 2. Two pointers : with one slow and one fast pointer.
     *    As name indicates Slow pointer will travel list slowly so lets say one node at time and fast pointer will skip two nodes in each iteration,
     *    If any point both pointer, pointing to same node then there is loop in list else if we get end of list then there is no loop in list.
     *    We need to iterate list until we get last node so <Strong> Time complexity :O(n) </Strong>
     * This is optimal approach to detect loop in linked list
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
     * Find length of loop in linked list, in this implementation first we need to find is loop present in list and then length.
     *
     * Time Complexity: O(n)
     *
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
     *
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

    fun moveLastNodeToFirstPosition() {
        swapNode(0,length()-1)
        //moveNodeToFirstPosition(length())
      /*  var secLast = head
        while (secLast!!.next != null && secLast.next!!.next != null){
            secLast =secLast.next
        }

        val last = secLast.next
        if(last != null){
            secLast.next = null
            last.next = head
            head = last
        }*/
    }

    fun moveNodeToFirstPosition(position: Int){
        swapNode(0,position-1)
     /*   if(position == 1 || head!!.next ==null){
            return
        }

        var i=0
        var prev = head
        while (prev!!.next !=null && prev.next!!.next !=null ){
            if(position-2 ==i){
                break
            }
            prev = prev.next
            i++
        }
        val node = prev.next
        prev.next = node!!.next
        node.next = head
        head = node
      */
    }


}