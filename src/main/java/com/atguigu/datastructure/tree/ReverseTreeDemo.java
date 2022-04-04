package com.atguigu.datastructure.tree;

/**
 * Created by John.Ma on 2020/5/29 0029 17:45
 */
public class ReverseTreeDemo {
    public void reverseTree(TreeNode root){
        if(root== null){
            return;
        }
        if(root.left == null && root.right== null){
            return;
        }

        TreeNode temp = null;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        if(root.left != null){
            reverseTree(root.left);
        }
        if(root.right != null ){
            reverseTree(root.right);
        }
    }


}

class TreeNode{
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val ){
        this.val = val ;
    }
}

