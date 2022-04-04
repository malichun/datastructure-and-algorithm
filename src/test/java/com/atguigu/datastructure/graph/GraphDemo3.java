package com.atguigu.datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by John.Ma on 2022/4/4 0004 10:51
 */
public class GraphDemo3 {
    public static void main(String[] args) {
        // 测试一把图是否创建ok
        int n = 5; // 结点个数
        String[] vertexs = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph3 graph = new Graph3(5);
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

        graph.dfs();
        System.out.println();
        graph.bfs();
    }
}

class Graph3{
    private ArrayList<String> vertexList;// 存储顶点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges ;// 表示边的数目
    // 定义数组boolean[], 记录某个结点是否被访问过
    private boolean[] isVisited ;


    // 构造器
    public Graph3(int n){
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    public int getFirstNeighbour(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbour(int index, int w){
        for (int i = w + 1; i < vertexList.size(); i++) {
            if(edges[index][w] > 0){
                return i;
            }
        }
        return -1;
    }

    public void dfs(int index){
        System.out.print(vertexList.get(index) + "->");
        isVisited[index] = true;

        int w = getFirstNeighbour(index);

        while(w!=-1){
            if(!isVisited[w]){
                dfs(w);
            }
            w = getNextNeighbour(index, w);
        }
    }

    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        for(int i =0;i<vertexList.size();i++){
            if(!isVisited[i]){
                dfs(i);
            }
        }
    }

    public void bfs(int index){
        int u; // 前驱结点
        int w; // 邻接结点
        System.out.print( vertexList.get(index) + "->");
        isVisited[index] = true;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(index);
        while(!queue.isEmpty()){
            u = queue.removeFirst();

            w = getFirstNeighbour(u);
            while(w != -1) {
                if(!isVisited[w]){
                    System.out.print( vertexList.get(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
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


    public void insertVertex(String vertexValue){
        vertexList.add(vertexValue);
    }

    public void insertEdge(int v1, int v2, int value){
        edges[v1][v2] = value;
    }
}
