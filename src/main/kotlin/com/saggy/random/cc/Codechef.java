package com.saggy.random.cc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Codechef {
    private  static Character[] charactrArr = new Character[]{'A', 'B', 'C'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string with abc :");
        String str = sc.nextLine();
        if (str.length() < charactrArr.length) {
            System.out.println(0);
            return;
        }
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < charactrArr.length; i++) {
            map.put(charactrArr[i], -1);
        }
        map.put(str.charAt(0), 0);
        map.put(str.charAt(1), 1);

        int result = 0;
        for (int i = 2; i < str.length(); i++) {
            Character chr = str.charAt(i);
            map.put(chr, i);

            boolean isStrPresent = isPresent(charactrArr, chr, map, i);

            if (isStrPresent) {
                result++;
            }
        }
        System.out.println(result);

    }

    public static boolean isPresent(Character[] arr, Character ch, Map<Character, Integer> map, int currentIndex) {
        boolean isPresent = true;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ch) {
                isPresent = isPresent && currentIndex - map.get(arr[i]) < 3;
            }
        }
        return isPresent;

    }
}