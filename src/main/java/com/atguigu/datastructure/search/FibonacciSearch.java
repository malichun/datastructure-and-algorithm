package com.atguigu.datastructure.search;


import java.util.Arrays;

/**
 * @author malichun
 * @description
 * @date 2020/3/11 0011 0:35
 * @Version 1.0.0
 */
public class FibonacciSearch {

    public static int maxSize=20;

    public static void main(String[] args) {

    }

    //因为后面我们mid=low+F(k-1)-1 ,需要使用到斐波那契数列,因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib(){
        int[]f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for(int i=2;i<maxSize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    //编写斐波那契查找算法
    /**
     * 使用非递归的方式编写算法
     * @param a 数组
     * @param key 我们需要查找的关键码值
     * @return 返回对应下标,如果没有返回-1
     */
//    public static int fibSearch(int[] a,int key){
//        int low=0;
//        int high=a.length-1;
//        int k = 0 ;//表示斐波那契分割数值的下标
//        int mid=0; //存放mid值
//        int[] f= fib();//获取到斐波那契数列
//        while(high>f[k]-1){
//            k++;
//        }
//        //因为f[k] 值,可能大于a的长度,因此我们需要使用Arrays类,构造一个新的数组,并指向啊
//        int[] temp = Arrays.copyOf(a,f[k]);//不足的部分会使用0填充
//        //实际上需要使用a数组的最后的数填充temp
//        for(int i=high+1;i<temp.length;i++){
//            temp[i]=a[high];
//        }
//    }
}
