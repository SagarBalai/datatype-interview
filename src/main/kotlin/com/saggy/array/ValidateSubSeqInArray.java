package com.saggy.array;

/**
 * Given 2 non-empty arrays of integers, write a function that determines whether the second array is subsequence of the first one.
 * <p>
 * A subsequence of an array is a set of numbers that aren't necessarily adjacent in the array but that are in the same order as they
 * appear in the array.
 * <p>
 * For instance,the numbers [1,3,4] form a subsequence of the array [1,2,3,4], and so do the numbers [2,4].
 * Note that single number in the array and array itself are both valid subsequence of the array.
 */
public class ValidateSubSeqInArray {

    public boolean validateSubSequenceInArray(int[] array, int[] subSeq) {

        if(array.length < subSeq.length){
            return false;
        }
        int i=0,j=0;
        while (i<array.length && j<subSeq.length){
            if(array[i] == subSeq[j]){
                j++;
            }
            i++;
        }

        return j==subSeq.length;
    }
}
