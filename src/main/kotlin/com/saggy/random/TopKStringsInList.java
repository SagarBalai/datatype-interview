package com.saggy.random;

import kotlin.Pair;

import java.util.*;

public class TopKStringsInList {
    public String[] findSolutionWithSort(List<String> strList, int k) {
        String[] result = new String[k];

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String str : strList) {
            Integer value = map.get(str);
            map.put(str, value == null ? 1 : value + 1);
        }

        Pair<String, Integer>[] arr = new Pair[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            arr[i++] = new Pair(key, map.get(key));
        }

        Arrays.sort(arr, new Comparator<Pair<String, Integer>>() {
            public int compare(Pair<String, Integer> s1, Pair<String, Integer> s2) {
                int diff = s1.component2().intValue() - s2.component2().intValue();
                if (diff < 0) return 1;
                else if (diff == 0) return 0;
                else return -1;
            }
        });

        for (int j = 0; j < k; j++) {
            result[j] = arr[j].component1();
        }

        return result;
    }

    public String[] findSolutionWithPivotal(List<String> strList, int k) {
        String[] result = new String[k];


        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : strList) {
            Integer value = map.get(str);
            map.put(str, value == null ? 1 : value + 1);
        }

        Pair<String, Integer>[] arr = new Pair[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            arr[i++] = new Pair(key, map.get(key));
        }

//        10,32,1,76,5,7,11,10
       i=0;
        int pivot = arr[arr.length - k - 1].component2();

        for (int j = 0; j < arr.length; j++)
        {
            // If current element is smaller
            // than the pivot
            if (arr[j].component2() < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                Pair<String,Integer> temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        return result;
    }

    private  int partition(int[] arr, int l,
                                int r)
    {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++) {
            if (arr[j] <= x) {
                // Swapping arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
            }
        }

        // Swapping arr[i] and arr[r]
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
    }

    public int kthSmallest(int[] arr, int l,
                                  int r, int k)
    {
        // If k is smaller than number of elements
        // in array
        if (k > 0 && k <= r - l + 1) {
            // Partition the array around last
            // element and get position of pivot
            // element in sorted array
            int pos = partition(arr, l, r);

            // If position is same as k
            if (pos - l == k - 1)
                return arr[pos];

            // If position is more, recur for
            // left subarray
            if (pos - l > k - 1)
                return kthSmallest(arr, l, pos - 1, k);

            // Else recur for right subarray
            return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
        }

        // If k is more than number of elements
        // in array
        return Integer.MAX_VALUE;
    }

}
