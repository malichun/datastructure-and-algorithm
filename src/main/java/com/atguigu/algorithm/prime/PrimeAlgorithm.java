package com.atguigu.algorithm.prime;

import java.util.Arrays;

/**
 * MST树 最小生成树
 * prime算法
 */
public class PrimeAlgorithm {
    public static void main(String[] args) {
        final int INF = 10000;
        // 测试看看图是否创建ok
        char[] data = new char[]{'A', 'B' ,'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        // 邻接矩阵的关系使用二维数组表示, 10000这个大数,表示两个点不联通
        int[][] weight = new int[][]{
            /*A*/
            {INF, 5, 7, INF, INF, INF, 2},
            {5, INF, INF, 9, INF, INF, 3},
            {7, INF, INF, INF, 8, INF, INF},
            {INF, 9, INF, INF, INF, 4, INF},
            {INF, INF, 8, INF, INF, 5, INF},
            {INF, INF, INF, 4, 5, INF, 6},
            {2, 3, INF, INF, 4, INF, INF}
        };
        // 创建MGraph对象
        MGraph graph = new MGraph(verxs);
        // 创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);


        // 输出
        minTree.showGraph(graph);
        System.out.println("==================================");
        minTree.prim(graph, 1);
    }
}

// 创建最小生成树 -> 村庄的图
class MinTree{


    // 创建图的邻接矩阵
    /**
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data , int[][] weight){
        int i,j;
        for(i=0;i<verxs;i++){ // 顶点
            graph.data[i] = data[i];
            for(j =0; j<verxs;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    // 显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link: graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 编写prim算法,得到最小生成树
    /**
     *
     * @param graph 图
     * @param v  表示从图的第几个顶点开始生成'A' -> 0   'B' -> 1 ...
     */
    public void prim(MGraph graph, int v){
        // visited[] 标记结点(顶点)是否被访问过, 默认元素的值都是0,表示没有访问过
        int[] visited = new int[graph.verxs];
        // 把当前结点标记为已访问
        visited[v] =1;

        // h1和h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; // 将minWeight 初始成一个大数,后面在遍历过程中会被替换
               for (int k = 1; k < graph.verxs; k++) { // 因为有graph.verxs顶点数目,prim算法结束后有 (graph.verxs - 1)条边

            // 这个是确定每一次生成的子图,和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) { // i结点表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) { // j结点表示还没有访问过的结点
                    if(visited[i]==1 && visited[j]==0 && graph.weight[i][j] < minWeight){
                        // 替换minWeight(寻找已经访问过的节点和未访问过的节点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i; // 记录结点,可以记录路径
                        h2 = j;
                    }
                }
            }

            //找到一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2]+"> 权值:"+ minWeight);
            // 将当前这个结点标记为已经访问
            visited[h2] =1;
            // minWeight 重新设置
            minWeight = 10000;

        }

    }
}

class MGraph{
    int verxs; // 表示图的节点个数
    char[] data; // 存放节点数据
    int[][] weight; // 存放边,就是邻接矩阵

    public MGraph(int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}
