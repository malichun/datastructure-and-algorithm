package com.atguigu.datastructure.sort;


import java.util.Arrays;

/**
 * @author malichun
 * @description
 * @date 2020/3/5 0005 17:05
 * @Version 1.0.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr=new int[]{3,5,2,-1,9,10,-9,-90};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }


    public static void quickSort(int[] arr,int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while( l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while( arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while(arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if( l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if(arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if(arr[r] == pivot) {
                l += 1;
            }
        } // 最外面while的括号

        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quickSort(arr, l, right);
        }


    }


    public static void quickSort2(int[] arr,int left,int right){
        //递归终止条件
        if(left>=right)
            return;
        int base=arr[left]; //设置基底
        int r=right;
        int l=left+1;

        while(l!=r){
            while(arr[r]  > base && l<r){
                r--;
            }
            while(arr[l] < base && l<r){
                l++;
            }

            //交换
            int temp=0;

            temp=arr[r];
            arr[r]=arr[l];
            arr[l]= temp;
        }
        arr[left]=arr[l];
        arr[l]=base;

        System.out.println("l="+l+"  r="+r);
        System.out.println("arr="+Arrays.toString(arr));
        quickSort2(arr,left,r-1);
        quickSort2(arr, r+1, arr.length-1);


    }
}


