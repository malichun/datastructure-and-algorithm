package com.atguigu.datastructure.search;

/**
 * @author malichun
 * @description
 * @date 2020/3/8 0008 23:40
 * @Version 1.0.0
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr={1,9,11,-1,34,89}; //没有顺序的数组

        int index=seqSearch(arr, 1);
        if(index==-1){
            System.out.println("没有查找到");
        }else{
            System.out.println("找到了,下标为:"+index);
        }
    }

    /**
     * 这里我们实现了线性查找是找到一个满足条件的值就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr,int value){
        //线性查找时逐一比对,发现有相同的值时就返回下标
        for(int i=0;i<arr.length;i++){
            if(arr[i]== value){
                return i;
            }
        }
        return -1;
    }
}
