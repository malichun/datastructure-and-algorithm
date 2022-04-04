package com.atguigu.datastructure.avl;

/**
 * @author malichun
 * @description
 * @date 2020/3/1 0001 10:39
 * @Version 1.0.0
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7,8}; //测试左旋
//        int[] arr={10,12, 8, 9, 7, 6}; //测试右旋
        int[] arr={10, 11, 7, 6, 8, 9};//测试双旋
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在没有做平衡处理之前~~~");
        System.out.println("树的高度:"+avlTree.getRoot().height()); //4
        System.out.println("树的左子树的高度:"+avlTree.getRoot().leftHeight()); //1
        System.out.println("树的左子树的高度:"+avlTree.getRoot().rightHeight()); //3
        System.out.println("当前的根结点:"+avlTree.getRoot().value);
        System.out.println("中序遍历");
        avlTree.infixOrder();

    }
}

//创建AVLTree
class AVLTree{
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

    public void preOrder(){
        if(root==null){
            System.out.println("二叉树为空~~");
            return;
        }
        root.preOrder();
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

    //返回左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }

    //返回以当前结点为根节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(), right==null ? 0:right.height())+1;
    }

    //左旋转的方法
    //以当前结点为根节点左旋
    private void leftRotate(){
        //1.创建新的结点,以当前根结点的值
        Node newNode = new Node(this.value);
        //2.把新的结点的左子树设置成当前结点的左子树
        newNode.left=this.left;
        //3.把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right=this.right.left;
        //4.把当前结点的值替换成右子节点的值
        value=right.value;
        //5.把当前结点的右子树设置成当前结点右子树的右子树
        this.right=this.right.right;
        //6.当前结点的左子节点(左子节点)设置成新的结点
        this.left=newNode;

    }

    //右旋的方法
    private void rightRotate(){
        Node newNode = new Node(this.value);
        newNode.left=this.left.right;
        newNode.right=this.right;
        this.value=this.left.value;
        this.left=this.left.left;
        this.right=newNode;

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

        ///////////////////
        //当添加完一个节点后,如果右子树的高度-左子树的高度 >1 ,左旋转
        if(rightHeight()-leftHeight()>1){
            if(this.right!=null && this.right.rightHeight()< this.right.leftHeight()){
                this.right.rightRotate();
                this.leftRotate();
            }else {
                this.leftRotate();
            }
            return; //必须要
        }
        if(leftHeight()-rightHeight()>1){
            //左下角左旋,如果它的左子树的右子树高度大于它的左子树的高度
            if(this.left!=null && this.left.leftHeight()<this.left.rightHeight()){
                //先对当前结点的左结点(左子树) -> 左旋转
                this.left.leftRotate();
                //再对当前结点进行右旋转
                this.rightRotate();
            }else{
                //单纯右旋
                this.rightRotate();
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

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

}
