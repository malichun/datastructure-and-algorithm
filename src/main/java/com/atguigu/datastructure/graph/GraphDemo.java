package com.atguigu.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 *    A B C D E
 *  A 0 1 1 0 0
 *  B 1 0 1 1 1
 *  C 1 1 0 0 0
 *  D 0 1 0 0 0
 *  E 0 1 0 0 0
 */
public class GraphDemo{
    public static void main(String[] args) {
        // 测试一把图是否创建ok
        int n = 5; // 结点个数
        String[] vertexs = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(5);
        // 循环的添加顶点
        for(String vertexValue: vertexs){
            graph.insertVertex(vertexValue);
        }
        // 添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        // 显示邻接矩阵
        graph.showGraph();

        // 深度优先遍历
        System.out.println("----深度优先遍历----");
        graph.dfs();
        System.out.println();
        // 广度优先遍历
        System.out.println("---- 广度优先遍历----");
        graph.bfs();
        System.out.println();
    }
}


class Graph {

    private ArrayList<String> vertexList;// 存储顶点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges ;// 表示边的数目
    // 定义数组boolean[], 记录某个结点是否被访问过
    private boolean[] isVisited ;


    // 构造器
    public Graph(int n){
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    // 得到第一个邻接结点的下标 w

    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标,否则返回-1
     */
    public int getFirstNeighbour(int index){
        for(int j = 0;j< vertexList.size();j++){
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbour(int v1, int v2){
        for(int j = v2 + 1;j<vertexList.size();j++){
            if(edges[v1][j] > 0){ // 第几层访问
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    // i: 第一次就是0
    private void dfs(boolean[] isVisited, int i){
        // 首先我们访问该节点
        System.out.print(getValueByIndex(i) + "->");
        // 将节点设置为已经访问过
        isVisited[i] = true;
        // 查找i的第一个邻接结点w
        int w = getFirstNeighbour(i);
        while(w != -1){ // 说明有邻接结点
            if(!isVisited[w]){
                // 没有访问过
                dfs(isVisited, w);
            }
            // 如果w结点已经被访问过,
            w = getNextNeighbour(i, w);
        }
    }

    // 对dfs进行重载, 遍历所有的结点,并进行dfs
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        // 遍历所有的节点,进行dfs[回溯]
        for(int i = 0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    // 对一个节点进行广度优先遍历的方法
    private void bfs(int i){
        int u; // 队列的头结点对应的下标
        int w ; // 邻接结点的下标
        // 队列,结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(vertexList.get(i) + "->");
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            // 取出队列的头结点下标
            u = queue.removeFirst();
            // 得到第一个邻接结点的下标
            w = getFirstNeighbour(u);
            while(w != -1){ // 找到了
                // 是否访问过
                if(!isVisited[w]){
                    System.out.print(vertexList.get(w) + "->");
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u 为前驱结点,找w后面的下一个邻接点
                w = getNextNeighbour(u, w);
            }
        }


    }

    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                bfs(i);
            }
        }
    }




    // 图中常用的方法
    // 返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    // 显示图对应的矩阵
    public void showGraph(){
        Arrays.stream(edges).map(Arrays::toString).forEach(System.err::println);
    }

    // 得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    // 返回结点i(下标) 对应的数据 0->"A"  1-"B"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    // 返回V1和V2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }


    // 插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 v1表示点的下标,即是第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2 v2表示第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight; // 无向图
        numOfEdges ++;
    }



}
