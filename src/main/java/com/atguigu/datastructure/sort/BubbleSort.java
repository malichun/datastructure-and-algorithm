package com.atguigu.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author malichun
 * @description
 * @date 2020/3/2 0002 21:44
 * @Version 1.0.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        //测试冒泡排序
        bubbleSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

    }

    public static void bubbleSort(int[] arr){
        //冒泡排序的时间复杂度O(n2),自己写出
        int temp=0 ; //临时变量
        boolean flag=false;//标识变量,标识是否进行过交换
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j< arr.length-1-i;j++){
                //如果前面的数比后面的数大,则交换
                if(arr[j] > arr[j+1]){ //把大的放后面
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if(!flag){
                break;
            }else{
                flag=false;//重置flag,进行下次判断
            }
        }
    }
}
