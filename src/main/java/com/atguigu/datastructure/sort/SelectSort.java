package com.atguigu.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author malichun
 * @description
 * @date 2020/3/2 0002 22:34
 * @Version 1.0.0
 */
public class SelectSort {
    public static void main(String[] args) {

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);


        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    //选择排序
    public static void selectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            int min=arr[i];
            for(int j=i+1;j<arr.length-1;j++){
                if(min > arr[j]){ //假定最小值,并不是真的最小
                    min=arr[j]; //重置min值
                    minIndex=j; //重置minIndex
                }
                if(minIndex!=i){
                    arr[minIndex]=arr[i];
                    arr[minIndex]=min;
                }
            }
        }
    }
}
