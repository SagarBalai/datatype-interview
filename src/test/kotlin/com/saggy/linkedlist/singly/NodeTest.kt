package com.saggy.linkedlist.singly

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class NodeTest {
    @Test
    internal fun `length - should return 1 for one node linked list`() {
        // given
        val node = Node(1)

        //when
        val length = node.length()

        //then
        assertEquals(1,length)
    }

}