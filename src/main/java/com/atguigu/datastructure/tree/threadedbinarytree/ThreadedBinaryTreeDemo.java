package com.atguigu.datastructure.tree.threadedbinarytree;



/**
 * @author malichun
 * @description
 * @date 2020/3/12 0012 23:28
 * @Version 1.0.0
 */
public class ThreadedBinaryTreeDemo {

}


//定义ThreadedBinaryTree,实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化,需要创建要给指向当前结点的前驱结点的指针
    //在进行递归进行线索化时,pre中是保留前一个结点
    private HeroNode pre=null;

    //编写对二叉树进行线索化的中序线索化的方法

    /**
     *
     * @param heroNode 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode heroNode){
        //如果node==null,不能线索化
        if(heroNode==null){
            return;
        }
        //中序
        //(一)先线索化左子树
        threadedNodes(heroNode.getLeft());
        //(二)线索化当前结点[有难度]

        //处理当前结点的前驱结点
        if(heroNode.getLeft()==null){
            //让当前结点的左指针指向前驱结点
            heroNode.setLeft(pre);
            //修改当前结点左指针的类型
            heroNode.setLeftType(1); //指向的是前驱结点
        }

        //处理后继结点
        if(pre!=null && pre.getRight()==null){
            //让前驱结点的右指针指向当前结点
            pre.setRight(heroNode);
            pre.setRightType(1);
        }
        //!!!
        //每处理一个结点,让当前结点是下一个结点的前驱结点
        pre=heroNode;
        //(三)在线索化右子树
        threadedNodes(heroNode.getRight());

    }


    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空,无法遍历");
        }
    }
    //后续遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    //后序遍历
    public HeroNode postOrderSearch(int no ){
        if(root!=null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }

    public void delNode(int no){
        if(root!=null){
            //如果只有一个root节点,这里立即判断root是不是要删除的节点
            if(root.getNo()==no){
                root =null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树,不能删除~");
        }

    }

}

//先创建结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;  //默认为null
    private HeroNode right; //默认null
    //说明
    //1.如果leftTYpe=0,表示指向的是左子树,如果为1,则表示指向前驱结点
    //2.如果rightType=0,表示指向的是右子树,如果为1,则表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除结点
    //1.如果删除的结点时叶子节点,则删除该节点
    //2.如果删除的节点是非叶子节点,则删除该子树
    public void delNode(int no ){
        //思路
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if(this.left!=null && this.left.no==no){
            this.left=null;
            return ;
        }
        if(this.right!=null && this.right.no==no){
            this.right=null;
            return ;
        }
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this); //先输出父结点
        //递归向左子树查找
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历的方法
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left!=null){
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    //后续遍历
    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     *
     * @param no 查找
     * @return 如果返回则返回该node,如果没有找到则返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("前序查找");
        //比较当前结点是不是
        if(this.no==no){
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode resNode=null;
        if(this.left!=null){
            resNode= this.left.preOrderSearch(no);
        }
        if(resNode!=null){ //说明左子树找到了
            return resNode;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }

        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        //判断当前结点的左子节点是否为空,如果不为空,则递归中序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("进入中序查找");
        //如果找到,则返回,如果没有找到,就和当前结点比较,如果是则返回当前结点
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }

        return resNode;


    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        //如果左子树没有找到,则向右子树递归进行后序遍历查找
        if(this.right!=null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("进入后序查找");
        if(this.no==no){
            return this;
        }
        return resNode;

    }


}