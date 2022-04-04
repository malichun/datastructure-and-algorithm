package com.atguigu.datastructure.tree;

import java.util.Arrays;

/**
 * @author malichun
 * @description
 * @date 2020/3/13 0013 0:59
 * @Version 1.0.0
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序,大顶堆
        int[] arr={4, 6 ,8 ,5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr){
        int temp=0;
        System.out.println("堆排序!!");

//        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));

        //完成我们最终代码
        //1)将无序列表建成一个对,根据升序排序需求选择大顶堆或小顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr, i,arr.length);
        }

        //
        for(int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }

    }

    //将一个数组(二叉树)调整成一个大顶堆
    /**
     *
     * 功能:完成 将以i对应的非叶子节点的树调整成大顶堆
     * 举个例子  int[] arr={4, 6 ,8 ,5, 9};  => i=1 => adjustHeap =>{4, 6 ,8 ,5, 9};
     * 如果我们再一次掉用 adjustHeap 传入的是i=0 => {9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i   表示非叶子节点的在数组中的索引
     * @param length 对多少个元素进行调整, length是在逐渐减少
     */
    public static void  adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1. k = i * 2 + 1 k 是 i结点的左子结点
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if(k+1 < length && arr[k] < arr[k+1]) { //说明左子结点的值小于右子结点的值
                k++; // k 指向右子结点
            }
            if(arr[k] > temp) { //如果子结点大于父结点
                arr[i] = arr[k]; //把较大的值赋给当前结点
                i = k; //!!! i 指向 k,继续循环比较
            } else {
                break;//!
            }
        }
        //当for 循环结束后，我们已经将以i 为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
