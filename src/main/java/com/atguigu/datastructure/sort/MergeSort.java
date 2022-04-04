package com.atguigu.datastructure.sort;

import java.util.Arrays;

/**
 * @author malichun
 * @description
 * @date 2020/3/8 0008 13:17
 * @Version 1.0.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr=new int[]{23,43,3,43,532,53,24,2342,342};
        mergeSort(arr,0,arr.length-1, new int[arr.length]);
        System.out.println(Arrays.toString(arr));
    }

    //分+合的方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2 ;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1, right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并的方法
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i=left;//初始化i,左边有序序列的初始索引
        int j=mid+1;// 初始化j,右边有序序列的初始索引
        int t= 0 ;//指向temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列,有一边处理完毕为止
        while(i<= mid && j<= right){
            if(arr[i]<=arr[j]){
                temp[t]=arr[i];
                i++;
            }else{
                temp[t]=arr[j];
                j++;
            }
            t++;
        }

        //(二)
        //把有剩余数据的一边的数据一次全部填充到temp
        while(i<= mid){
            temp[t]=arr[i];
            t++;
            i++;
        }
        while(j<=right){
            temp[t]=arr[j];
            t++;
            j++;
        }

        //(三)
        //将temp数组元素拷贝到arr
        //注意:并不是每次都是拷贝所有
        t=0;
        int tempLeft=left;
        while(tempLeft <= right){
            arr[tempLeft]=temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
}
