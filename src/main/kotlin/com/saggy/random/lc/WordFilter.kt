package com.saggy.random.lc

/**
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.


Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".


Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
 */
class WordFilter(words: Array<String>) {

    private val combinationMap = mutableMapOf<String, Int>()

    init {
        words.forEachIndexed { index, word ->
            val prefixList = prefixList(word)

            word.forEachIndexed { i, _ ->
                prefixList.forEach { c ->
                    combinationMap[word.substring(i) + "_" + c] = index
                }
            }
        }
    }

    private fun prefixList(word: String): MutableList<String> {
        val prefixList = mutableListOf<String>()
        for (j in word.length downTo 0) {
            prefixList += word.substring(0, j)
        }
        return prefixList
    }

    fun f(prefix: String, suffix: String): Int {
        return combinationMap[suffix + "_" + prefix] ?: -1
    }

}