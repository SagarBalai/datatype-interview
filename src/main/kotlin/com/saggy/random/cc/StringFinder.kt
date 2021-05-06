package com.saggy.random.cc

/**
 * Mike likes strings. He is also interested in algorithms. A few days ago he discovered for himself a very nice problem:

You are given an AB-string S. You need to count the number of substrings of S, which have an equal number of 'A'-s and 'B'-s.

Do you know how to solve it? Good. Mike will make the problem a little bit more difficult for you.

You are given an ABC-string S. You need to count the number of substrings of S, which have an equal number of 'A'-s, 'B'-s and 'C'-s.

A string is called AB-string if it doesn't contain any symbols except 'A' or 'B'. A string is called ABC-string if it doesn't contain any symbols except 'A', 'B' or 'C'.

Input
The first line of the input contains an ABC-string S.

Output
Your output should contain the only integer, denoting the number of substrings of S, which have an equal number of 'A'-s, 'B'-s and 'C'-s.

The answer can go above a 32-bit integer. Please, use 64-bit integers for storing and processing data.

Constraints
1 ≤ |S| ≤ 1 000 000; where |S| denotes the length of the given ABC-string.

Example
Input:
ABACABA
Output:
2
 */
class StringFinder {

    fun find(str: String): Long {

        if(str ==null || str.isEmpty()){
            return 0L
        }
        var result = 0L
        val map = mutableMapOf<Char,Int>('a' to -1,'b' to -1,'c' to -1)
        val charactrArr = arrayOf('a','b','c');
        map[str[0]]=0
        map[str[1]]=1
        for (i in 2 until str.length){
            val char =str[i]
            map[char]=i;

            val remained = charactrArr.filter { ch->ch != char }

            var isStrPresent =true
            remained.forEach { ch ->isStrPresent =isStrPresent && i-map[ch]!!<3 }

            if(isStrPresent){
                result++
            }
        }
        return result
    }

    fun main(args: Array<String>){
        println("Hello kotlin")
    }

    fun thirdMax(nums: IntArray): Int {
        var first = Int.MIN_VALUE
        var second = Int.MIN_VALUE
        var third = Int.MIN_VALUE
        var thirdEvaluated =false
    //[1,2,-2147483648]
        for(num in nums){

            if(num >= third){
                if(num >= second){
                    if(num >= first){
                        if(num !=first){
                            third=second
                            second=first
                            first = num
                        }
                    }
                    else if(num !=second){
                        third=second
                        second = num
                    }
                }else{
                    thirdEvaluated=true
                    third =num
                }
            }
        }

        return if(thirdEvaluated) third else first

    }

}