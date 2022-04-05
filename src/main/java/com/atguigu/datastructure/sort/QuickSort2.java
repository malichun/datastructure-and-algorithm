package com.atguigu.datastructure.sort;


import java.util.Arrays;

/**
 * 单向排序
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] a = {9,2,6,3,8,0,7,1};
        quick(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static void quick(int[] a, int l , int h){
        if(l >= h){
            return;
        }
        int pivot = partition(a, l,h);
        quick(a, l , pivot -1);
        quick(a, pivot + 1, h);
    }

    public static int partition(int[] a, int l, int h){
        int pivot = a[h]; //基准点

        int i = l;
        for (int j = l; j < h ; j++) {
            if(a[j] < pivot){
                if(i!=j) {
                    swap(a, i, j);
                }
                i++;
            }
        }
        if(i!=h){
            swap(a, i, h);
        }
        System.out.println(Arrays.toString(a));
        return i;
    }

    public static void swap(int a[], int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
