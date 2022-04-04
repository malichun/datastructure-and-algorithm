package com.atguigu.datastructure.linkedList;

import java.util.Stack;

/**
 * @author malichun
 * @description
 * @date 2020/2/22 0022 14:16
 * @Version 1.0.0
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
        //smith
        //tom
        //jack


    }
}
