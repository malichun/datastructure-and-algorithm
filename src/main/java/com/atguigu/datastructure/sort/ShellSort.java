package com.atguigu.datastructure.sort;

/**
 * @author malichun
 * @description
 * @date 2020/3/5 0005 22:00
 * @Version 1.0.0
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr= {8,9,1,7,2,3,5,4,6,0};


    }

    //使用逐步推导的方式来编写希尔排序
    public static void shellSort(int[] arr){
        //根据前面的逐步分析,使用循环处理
        int temp=0;

        for(int gap = arr.length/2;gap>0;gap /= 2){
            for(int i = gap;i<arr.length;i++){
                //遍历各组中所有元素共gap组,每组有个元素),步长gap
                for(int j=i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
        }
    }



}
