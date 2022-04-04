package com.atguigu.algorithm.kmp;

/**
 * @author malichun
 * @description
 * @date 2020/3/18 0018 22:16
 * @Version 1.0.0
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1="";
        String str2="";
    }

    //获取到一个字符串(子串)的部分匹配值表
    public  int[] kmpNext(String dest){
        int[] next=new int[dest.length()];
        next[0]=0;//如果字符串的长度为1,部分匹配值就是0
        for(int i=1,j=0;i<dest.length();i++){

            //当dest.charAt(i) == dest.charAt(j) 满足时,部分匹配值就是要+1
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;

    }



}
