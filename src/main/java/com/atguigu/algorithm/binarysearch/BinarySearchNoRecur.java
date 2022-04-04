package com.atguigu.algorithm.binarysearch;

/**
 * @author malichun
 * @description
 * @date 2020/3/14 0014 22:57
 * @Version 1.0.0
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        //测试
        int[] arr={1,3,8,10,11,67,100};
        int index=binarySearch(arr,8);
        System.out.println("index="+index);
    }

    //二分查找的非递归实现

    /**
     *
     * @param arr 待查找的数组,arr是升序排序
     * @param target 需要查找的数
     * @return 返回对应的下标,-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while(left<=right){ //说明可以继续查找
            int mid=(left+right)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){ //向左查找
                right=mid-1; //向左边查找
            }else{
                left = mid+1; //向右边查找
            }
        }
        return -1;
    }

}
