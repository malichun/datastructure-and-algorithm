package com.atguigu.datastructure.tree;


/**
 * @author malichun
 * @description 顺序存储二叉树:完全二叉树
 * @date 2020/3/12 0012 22:49
 * @Version 1.0.0
 */
public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        //创建一个ArrayBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(0); //如果按照前序遍历

    }

}

//编写一个ArrayBinaryTree,实现顺序存储二叉树
class ArrayBinaryTree{
    private int[] arr;///存储数据节点的数组

    public ArrayBinaryTree(int[] arr){
        this.arr=arr;
    }

    //编写一个方法,完成顺序存储二叉树的前序遍历
    //index表示数组的下标
    public void preOrder(int index){
        //如果数组为空或者 arr.length ==0
        if(arr==null || arr.length==0){
            System.out.println("数组为空,不能按照二叉树的前序遍历");
            return;
        }
        //输出当前的元素
        System.out.println(arr[index]);

        //向左递归遍历
        if(2*index+1 < arr.length){
           preOrder(2*index+1);
        }
        if(2*index+2 < arr.length){
            preOrder(2*index+2);
        }

    }

    public void infixOrder(int index){
        if(arr == null || arr.length==0){
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        if(index*2 +1 <arr.length){
            infixOrder(index*2+1);
        }
        System.out.println(arr[index]);
        if(index*2+2<arr.length){
            infixOrder(index*2+2);
        }
    }

}