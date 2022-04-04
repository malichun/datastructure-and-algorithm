package com.atguigu.datastructure.binarysorttree;

/**
 * @author malichun
 * @description
 * @date 2020/3/1 0001 10:33
 * @Version 1.0.0
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

        //测试一下删除叶子结点


        binarySortTree.delNode(12);


        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);

        System.out.println("=============");
        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        binarySortTree.delNode(7);


        System.out.println("root=" + binarySortTree.getRoot());


        System.out.println("删除结点后");
        binarySortTree.infixOrder();

    }

}


class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //查找要删除的结点
    public Node search(int value){
        if(root==null){
            return null;
        }
        return root.search(value);
    }

    //查找父结点
    public Node searchParen(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    //编写方法:
    //1.返回的以node为根节点的二叉排序的最小结点的值
    //2.删除node为根节点的二叉排序树的最小节点
    public int delRightTreeMin(Node node){
        Node target=node;
        //循环的查找左子节点,就会找到最小值
        while(target.left!=null){
            target=target.left;
        }
        //这时 target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value){
        if(root==null){
            return;
        }else{
            //1.需求先取找到要删除的节点 targetNode
            Node targetNode=root.search(value);
            //如果没有找到要删除的节点
            if(targetNode==null){
                return;
            }
            //如果我们发现了当前这颗二叉排序数只有一个节点
            if(root.left==null && root.right==null){
                root = null;
                return;
            }

            //去找到targetNode的父节点
            Node parent=searchParen(value);
            //如果要删除的节点是叶子节点
            if(targetNode.left==null && targetNode.right==null){
                //判断targetNode是父节点的左子节点,还是右子节点
                if(parent.left!=null && parent.left.value==value){
                    parent.left=null;
                }else if(parent.right!=null && parent.right.value==value){
                    parent.right=null;
                }
            }else if(targetNode.left!=null && targetNode.right!=null){
                int minVal=delRightTreeMin(targetNode.right); //右子树最小的删除掉
                targetNode.value=minVal;
            }else{ //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if(targetNode.left!=null){
                    if(parent!=null){  //这边需要注意!!!1,如果到root
                        //如果targeNode是parent的左子节点
                        if(parent.left.value == value){
                            parent.left=targetNode.left;
                        }
                    }else{ //root结点
                        root=targetNode.left;
                    }
                }else{ //如果要删除的节点有右子结点
                    if(parent!=null){
                        //如果targetNode是parent的左子结点
                        if(parent.left.value==targetNode.value){
                            parent.left=targetNode.right;
                        }else{
                            parent.right=targetNode.right;
                        }
                    }else{
                        root=targetNode.right;
                    }
                }

            }

        }

    }

    public void add(Node node){
        if(root==null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void infixOrder(){
        if(root==null){
            System.out.println("二叉树为空~~");
            return;
        }
        root.infixOrder();
    }

}

//创建Node结点
class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value=value;
    }

    //查找要删除的结点

    /**
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点,否则返回null
     */
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if(value<this.value){ //如果查找的值小于当前结点,向左子树递归查找
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else{ //如果查找的值不小于当前结点,向右子树递归查找
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父结点
     * @param value 要找到结点的值
     * @return 返回的是要删除的节点的父节点,如果没有就返回null
     */
    public Node searchParent(int value){
        //如果当前结点就是要删除的节点的父结点,就返回
        if((this.left!=null && this.left.value==value) ||
                (this.right!=null && this.right.value==value)) {
            return this;
        }else{
            //如果查找的值小于当前结点的值,并且当前结点的左子节点不为空
            if(value<this.value && this.left!=null){
                return this.left.searchParent(value);
            }else if(value > this.value && this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点的方法
    //递归的形式添加结点,注意需要满足二叉排序树的要求
    public void add(Node node){
        if(node==null){
            return ;
        }
        //判断传入结点的值,和当前子树的根节点的值关系
        if(node.value<this.value){
            //如果当前结点左子节点为null
            if(this.left==null){
                this.left=node;
            }else{
                //递归的左子树添加
                this.left.add(node);
            }
        }else{
            if(this.right==null){
                this.right=node;
            }else{
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

}