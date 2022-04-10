package cn.itcast.algorithm.graph;

/**
 * Created by John.Ma on 2022/4/9 0009 2:47
 */
public class DepthFirstSearchTest {
    public static void main(String[] args) {
        // 准备Graph对象
        Graph G = new Graph(13);
        G.addEdge(0,6);
        G.addEdge(0,2);
        G.addEdge(0,1);
        G.addEdge(0,5);
        G.addEdge(5,3);
        G.addEdge(5,4);
        G.addEdge(3,4);
        G.addEdge(4,6);
        G.addEdge(7,8);
        G.addEdge(9,10);
        G.addEdge(9,11);
        G.addEdge(9,12);
        G.addEdge(11,12);
        G.show();

        // 准备深度优先搜索
        // DepthFirstSearch search = new DepthFirstSearch(G, 0);
        BreadthFirstSearch search = new BreadthFirstSearch(G, 0);

        // 测试与某个顶点想通的顶点数量
        int count = search.count();
        System.out.println("与起点0相通的顶点的数量为: "+ count);

        // 测试某个顶点与起始顶点是否相通
        boolean marked1 = search.marked(5);
        System.out.println("顶点5和顶点0是否相通:"+marked1);

        boolean marked2 = search.marked(7);
        System.out.println("顶点7和顶点0是否相通:"+marked2);
    }
}
