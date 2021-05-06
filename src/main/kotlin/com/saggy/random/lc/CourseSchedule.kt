package com.saggy.random.lc

/**
 *
 * There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.

You will start on the 1st day and you cannot take two or more courses simultaneously.

Return the maximum number of courses that you can take.



Example 1:

Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
Output: 3
Explanation:
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
Example 2:

Input: courses = [[1,2]]
Output: 1
Example 3:

Input: courses = [[3,2],[4,3]]
Output: 0


Constraints:

1 <= courses.length <= 104
1 <= durationi, lastDayi <= 104

 */
class CourseSchedule {

    // memoising
    fun scheduleCourse(courses: Array<IntArray>): Int {
        val sortedCorse = courses.sortedBy { it.component2() }

        val memo = Array(courses.size) { IntArray(sortedCorse[sortedCorse.size-1][1] + 1) }
        return countDpWithMemo(sortedCorse, 0, 0, memo)
    }

    private fun countDpWithMemo(courses: List<IntArray>,
        index: Int, curDay: Int, memo: Array<IntArray>
    ): Int {
        if (index >= courses.size) return 0

        if (memo[index][curDay] != 0) {
            return memo[index][curDay]
        }

        val count = if (courses[index][1] >= curDay + courses[index][0]) {
            (1 + countDpWithMemo(courses, index + 1, curDay + courses[index][0], memo))
                .coerceAtLeast(countDpWithMemo(courses, index + 1, curDay,memo))
        } else {
            countDpWithMemo(courses, index + 1, curDay,memo)
        }

        memo[index][curDay] = count
        return count
    }






    fun scheduleCourseRec(courses: Array<IntArray>): Int {
        return countRecSolution(courses.sortedBy { it.component2() }, 0, 0)
    }


    private fun countRecSolution(courses: List<IntArray>, index: Int, curDay: Int): Int {
        if (index >= courses.size) return 0

        return if (courses[index][1] >= curDay + courses[index][0]) {
            (1 + countRecSolution(courses, index + 1, curDay + courses[index][0]))
                .coerceAtLeast(countRecSolution(courses, index + 1, curDay))
        } else {
            countRecSolution(courses, index + 1, curDay)
        }
    }



    /* duration lastDay
     //[[100,200],[1000,1250],[200,1300],[2000,3200]]
            99  100 101  199  200  250 300 1000   10 1
      0         100 100  100  100
      1
      2
      3


     //[[100,200]   [1000,1250],    [200,1300],     [2000,3200]]
    --------------------------------------------------------------
    0    0              0               0               0
    99   0              0               0               0
    100  1              1               1               1
  1000   1              1               2               2
  1100   1              2               2               2
  1200   1              2               2               2
  1250   1              2               2               2
  1300   1              2               3               3
  1400
  3100   1              2               3               3
  3200   1              2               3               3

      //[[100,200]   [1000,1250],    [200,1300],     [2000,3200]]
    --------------------------------------------------------------
    0    0              0               0               0
    99   0              0               0               0
    100  100            100             100             100
    200  100            100             100             100
    300  100            100             300             300
    400  100            100             100             100
    500
    600
    800
    900
  1000
  1100
  1200
  1250   100            100             100             100
  1300   100            100             100             100
  1400   100            100             100             100
  3100   100            100             100             100
  3200   100            100             100             100




  4
  5
  6


     */


}