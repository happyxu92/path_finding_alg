package com.happy.algo.util;

import java.util.Arrays;

/**
 * @author: create by happy
 * @date: 2021/4/8
 */
public class Printer {
    public static <T> void printArray(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static <T> void print2dArray(T[][] array2d) {
        System.out.println("--------");
        for (T[] array: array2d) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println("--------");
    }

    public static void printArray(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printDouble2DArray(double[][] array2d) {
        System.out.println("--------");
        for (double[] array: array2d) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println("--------");
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[3];
        a[0] = 1;
        a[1] = 1;
        a[2] = 1;
        printArray(a);
    }

}
