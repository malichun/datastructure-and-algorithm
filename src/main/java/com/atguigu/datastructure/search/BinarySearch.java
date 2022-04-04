package com.atguigu.datastructure.search;


import java.util.ArrayList;
import java.util.List;

/**
 * @author malichun
 * @description
 * @date 2020/3/8 0008 23:39
 * @Version 1.0.0
 */
public class BinarySearch {
    //注意：使用二分查找的前提是 该数组是有序的.
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,65};

        System.out.println(binarySearch(arr,0, arr.length-1, 2));

    }

    public static int binarySearch(int[] arr,int left,int right,int value){
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if(left>right){
            return -1;
        }

        int mid=(left+right)/2;
        int midValue=arr[mid];
        if(midValue == value){
            return mid;
        }

        if(midValue < value){
          return  binarySearch(arr,mid+1,right,value);  //要加上return**************
        }else{
          return   binarySearch(arr,left,mid-1, value);
        }
    }

    //完成一个课后思考题:
    /*
    *  课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
    *  有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
    * 思路分析
	 * 1. 在找到mid 索引值，不要马上返回
	 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
	 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
	 * 4. 将Arraylist返回
	 */
    public static List<Integer> binarySearch2(int[] arr,int left,int right,int findValue){
        if(left > right){
            return new ArrayList<>();
        }
        int mid = (left+right)/2;
        int midValue=arr[mid];

        if(findValue > midValue){
            return binarySearch2(arr,mid+1, right,findValue);
        }else if(findValue < midValue){
            return binarySearch2(arr,left,mid-1,findValue);
        }else{
            /*
             * 1. 在找到mid 索引值，不要马上返回
  			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
  			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
  			 * 4. 将Arraylist返回
             */
            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != findValue) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }
    }

}
