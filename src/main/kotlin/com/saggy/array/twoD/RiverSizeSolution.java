package com.saggy.array.twoD;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * You are given a 2d array (matrix) of potentially unequal height and width containing only 0s and 1s.
 * Each 0 represents land and each 1 represents part of river. A river consists of any number of 1s that are that are either horizontally
 * or vertically adjacent(but not diagonally adjacent). The number of adjacent 1s forming a river determine its size.
 * <p>
 * Note that river can twist. In other words, it doesnt have to be straight vertical/ horizontal line; it can be L shaped for example.
 * <p>
 * Write a function that returns an array of the sizes of all rivers represented in the input matrix. The size don't need to be in any particular order.
 * ```
 * eg.
 * [1,0,0,1,0]
 * [1,0,1,0,0]
 * [0,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,1,0]
 * ```
 * Question falls under BackTracking algorithms.
 */
public class RiverSizeSolution {

    public List<Integer> calculateRiverSize(int[][] matrix) {
        List<Integer> sol = new ArrayList<Integer>();

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] isVisited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!isVisited[i][j]) {
                    computeRiverSize(matrix, i, j, isVisited, sol);
                }
            }
        }
        return sol;
    }

    private void computeRiverSize(int[][] matrix, int curI, int curJ, boolean[][] isVisited, List<Integer> sol) {
        int riverSize = 0;

        Stack<int[]> nodesToExplore = new Stack<int[]>();
        nodesToExplore.add(new int[]{curI, curJ});
        while (!nodesToExplore.isEmpty()) {
            int[] top = nodesToExplore.pop();
            int i = top[0];
            int j = top[1];

            if (isVisited[i][j] || matrix[i][j] == 0) {
                continue;
            }

            riverSize++;
            isVisited[i][j] = true;

            // add adjacent node for exploreNode stack..
            if (i + 1 < matrix.length) {
                nodesToExplore.push(new int[]{i + 1, j});
            }
            if (i - 1 > 0) {
                nodesToExplore.push(new int[]{i - 1, j});
            }

            if (j + 1 < matrix[0].length) {
                nodesToExplore.push(new int[]{i, j + 1});
            }
            if (j - 1 > 0) {
                nodesToExplore.push(new int[]{i, j - 1});
            }
        }

        if (riverSize > 0) {
            sol.add(riverSize);
        }
    }


}
