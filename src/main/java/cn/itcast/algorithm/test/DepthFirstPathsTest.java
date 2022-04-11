package cn.itcast.algorithm.test;

import cn.itcast.algorithm.graph.DepthFirstPaths;
import cn.itcast.algorithm.graph.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 图的路径查找, 找到一条就行了
 * Created by John.Ma on 2022/4/11 0011 0:40
 */
public class DepthFirstPathsTest {
    public static void main(String[] args) throws Exception{
        // 构建缓冲读取流 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(DepthFirstPathsTest.class.getClassLoader().getResourceAsStream("road_find.txt")));

        // 读取第一行数据6(顶点的数量)
        int total = Integer.parseInt(br.readLine());

        // 根据第一行数据构建一副图Graph
        Graph G = new Graph(total);
        // 读取第二行数据8(边的数量)
        int edgeNumbers = Integer.parseInt(br.readLine());
        // 继续通过循环读取每一条边关联的两个顶点, 调用addEdge方法添加边
        for(int i =1 ;i<= edgeNumbers;i++){
            String edge = br.readLine(); // 0 1
            String[] str = edge.split(" ");
            G.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }
        // 构建路径查找对象,并设置起点为0
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(G, 0);

        // 调用pathTo(4), 找到从起点0到终点4的路径, 返回一个stack对象
        Stack<Integer> path = depthFirstPaths.pathTo(4);

        // 遍历栈对象
        StringBuilder sb = new StringBuilder();
        for(Integer v: path){
            sb.append(v).append("-");
        }
        sb.substring(sb.lastIndexOf("-"));
        System.out.println(sb.toString());
    }
}
