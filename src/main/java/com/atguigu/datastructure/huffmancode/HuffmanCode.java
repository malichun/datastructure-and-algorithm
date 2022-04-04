package com.atguigu.datastructure.huffmancode;


import java.io.*;
import java.util.*;

/**
 * @author malichun
 * @description
 * @date 2020/2/29 0029 23:36
 * @Version 1.0.0
 */
public class HuffmanCode {
    public static void main(String[] args) {

//////////////////////////////////////////////
     /*   //测试压缩文件
        String srcFile="d://1.csv";
        String dstFile="d://1.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件ok~~");
      */
//////////////////////////////////////////////
        //测试解压(文件恢复)
        String zipFile="d://1.zip";
        String dstFile="d://2.csv";
        unzipFile(zipFile, dstFile);
        System.out.println("解压成功~~");

//////////////////////////////////////////////

       /* String content= "i like like like java do you like a java";
        byte[] contentBytes=content.getBytes();
        System.out.println("原先的长度:"+contentBytes.length);

        byte[] huffmanCodesBytes= huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:"+Arrays.toString(huffmanCodesBytes)+" 长度为:"+huffmanCodesBytes.length);
*/
////////////////////////////////

       /* //测试一把
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("原来的字符串:"+new String(sourceBytes));
*/
////////////////////////////////
        //如何将数据进行解压(解码)


        //分步过程
        /*
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes="+nodes);

        //测试一把,创建的二叉树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历~~");
        huffmanTreeRoot.preOrder();

        //测试一把是否生成了对应的赫夫曼编码
       Map<Byte,String> huffmanCode= getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表"+huffmanCode);


        //测试
        byte[] huffmanCodesBytes=zip(contentBytes, huffmanCode);
        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodesBytes));
*/
        //发送huffmanCodeBytes数组

    }

////文件处理//////////////////////////////////////////////////
    //编写一个方法,完成对压缩文件的解压

    /**
     *
     * @param zipFile  准备解压的文件
     * @param dstFile  将文件解压到哪个路径
     */
    public static void unzipFile(String zipFile,String dstFile){

        //定义文件输入流
        InputStream is=null;

        //定义一个对象输入流
        ObjectInputStream ois = null;

        //定义文件的输出流
        OutputStream os = null;
        try{
            //创建文件输入流
            is=new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois= new ObjectInputStream(is);
            //读取bytes数组 ,huffmanBytes
            byte[] huffmanBytes=(byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes=(Map<Byte,String>)ois.readObject();
            //解码
            byte[] bytes=decode(huffmanCodes,huffmanBytes);
            //将byte[]数组写入到目标文件
            os=new FileOutputStream(dstFile);
            //写数据到目标文件
            os.write(bytes);
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //编写方法,将一个文件进行压缩

    /**
     *
     * @param srcFile 你传入的希望要锁的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建一个文件的输入流
        FileInputStream is =null;
        try {
            //创建文件输入流
            is= new FileInputStream(new File(srcFile));
            //创建一个和原文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件,到字节数组
            is.read(b);
            //直接对原文件进行压缩
            byte[] huffmanBytes=huffmanZip(b);

            //创建一个输出流,准备存放压缩文件
            os=new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos=new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);

            //这里我们以对象流的方式写入赫夫曼编码,是为了以后恢复原文件时使用
            //注意一定要把huffman编码写入压缩文件
            oos.writeObject(huffmanCodes);


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//完成数据的解压////////////////////////////////////////////////////
    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes:[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28] 长度为:17
    // 重新写转成赫夫曼的二进制字符串[1010100010...
    //2.赫夫曼编码对应的二进制的字符串"1010100010..." => 对应 赫夫曼编码=> "i like like like java do you like a java"


    //编写一个方法,完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //1.先得到huffmanBytes 对应的二进制字符串,形式 1010100010...
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行掉换,因为要反向查询
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合,存放byte
        List<Byte> list = new ArrayList<>();
        //i可以理解成索引,扫描stringBuilder(二进制字符串)
        for(int i=0;i<stringBuilder.length();){
            int count=1;
            boolean flag=true;
            Byte b= null;

            while(flag){
                //递增的取出key
                String key = stringBuilder.substring(i,i+count); //i不动,让count移动,知道匹配到一个字符
                b = map.get(key);
                if(b== null){ //说明还没有匹配到
                    count++;
                }else{
                    //匹配到
                    flag=false;
                }
            }
            list.add(b);
            i+=count; //直接让i直接移动到count
        }
        //当for循环结束后,list中就存放了所有的字符
        //把list中的数组放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for(int i = 0 ;i<b.length;i++){
            b[i]=list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转成一个二进制的字符串
     * @param b 传入的byte值
     * @param flag 表示是否需要补高位,如果是true表示需要补高位,如果false,表示不补,如果是最后一个字节,不需要补高位
     * @return 是该byte对应的二进制的字符串,注意是按补码返回
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if(flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }



//////////////////////////////////////////////////////


    /**
     * 使用一个方法,将前面的方法封装起来,便于我们的调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes){
        //1.获取每个节点转换成的List
        List<Node> nodes = getNodes(bytes);
        //2.根据nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //3.生成对应的赫夫曼编码(根据赫夫曼树来创建对应的赫夫曼编码)
        Map<Byte,String> huffmanCode= getCodes(huffmanTreeRoot);
        //4.根据赫夫曼编码对原始的数组压缩,压缩的到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodesBytes=zip(bytes, huffmanCode);
        return huffmanCodesBytes;
    }

    //编写一个方法,将字符串对应的byte数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码 压缩后的byte[]

    /**
     *
     * @param bytes 这时原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼处理后的byte[]
     *  String content= "i like like like java do you like a java";
     *  返回的是字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     *  => 对应的一个byte[] hufmanCodeBytes编码后的字符串数组,即8位对应一个byte,放入到byte数组中
     *  huffmanCodeBytes[0] =10101000(补码) => byte [推导 10101000 => 10101000 -1 => 1010111(反码) => 11011000]
     *
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //1.利用huffmanCodes 将byte 转成huffman编码对应的字符串
        StringBuilder stringBuilder= new StringBuilder();
        //遍历bytes 数组
        for(byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试stringBuilder:"+stringBuilder);

        //将"10101000101111111..."转成byte[]
        //统计返回的byte[] huffmanCOdeBytes 长度
        int len  ; //(stringBuilder+7)/8; //或者
        if(stringBuilder.length()%8==0){
            len=stringBuilder.length()/8;
        }else{
            len= stringBuilder.length()/8+1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;//记录是第几个byte
        for(int i=0;i<stringBuilder.length();i+=8){ //因为每8位对应一个byte,所以步长+8
            String strByte;
            if(i+8>stringBuilder.length()){ //最后不够8位
                strByte=stringBuilder.substring(i); //取到最后
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte,放入到huffmanCodeBytes
            huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1.将赫夫曼编码表存放到Map<Byte,String> 形式
    // 形式 32=01 97=100 100=11000 等等 (不一定完全一样)
    static Map<Byte,String> huffmanCodes=new HashMap<>();
    //2.在生成赫夫曼编码表时,需要去拼接这个路径,定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder=new StringBuilder();

    //为了调用方便,我们重载getCodes
    private static Map<Byte,String> getCodes(Node root){
       if(root == null){
           return null;
       }
       //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
       //处理root的右子树
        getCodes(root.right,"1", stringBuilder);
        return huffmanCodes;
    }
    /**
     * 功能:将传入的Node节点的所有叶子节点的赫夫曼编码得到,并放入到huffmanCodes集合中
     * @param node 传入节点
     * @param code 路径(左子结点0,右子结点1)
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        stringBuilder2.append(code);
        if(node!=null){ //如果node==null,不处理
            //判断当前node是叶子结点还是非叶子结点
            if(node.data == null){ //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else{ //说明是叶子结点
                //表示找到了某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    //前序遍历
    private static void  preOrder(Node root){
        if(root==null){
            System.out.println("赫夫曼树为空,不能遍历~~");
            return ;
        }
        root.preOrder();
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的就是List 形式[Node[data=97,weight=5]
     */
    private static List<Node> getNodes(byte[] bytes){
        //1.创建ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes,统计存储每个byte出现的次数-> map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for(byte b:bytes){
            if(!counts.containsKey(b)){
                counts.put(b, 1);
            }else{
                counts.put(b,counts.get(b)+1);
            }
        }

        //把每个键值对转成一个Node对象,并加入到nodes集合
        //遍历map
        for(Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //可以通过List创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size()>1){
            //排序,从小到大排
            Collections.sort(nodes);

            //取出最小的一颗二叉树
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1); //取出第二棵

            //创建一颗新的二叉树,它的根节点,没有根节点,只有权值,(所有的字符都是放在叶子节点)
            Node parent= new Node(null, leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            //将已经处理的两个二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        //nodes最后的这个节点就是哈夫曼树的根节点
       return  nodes.get(0);
    }

}


//创建Node,带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据(字符)本身,比如97(a)
    int weight;//权值,表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight; //从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
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