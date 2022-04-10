package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue.Queue;

/**
 * Created by John.Ma on 2022/4/9 0009 19:44
 * 广度优先搜索
 */
public class BreadthFirstSearch {
    // 索引代表顶点,值表示当前顶点是否已经被搜索
    private boolean[] marked;
    // 记录有多少个顶点与s相通
    private int count;
    // 队列用来存储待搜索的邻接表的点
    private Queue<Integer> waitSearch;

    // 构建广度优先搜索对象, 使用广度优先搜索找出G图中s顶点的所有相邻顶点
    public BreadthFirstSearch(Graph G, int s){
        this.marked = new boolean[G.V()];
        this.count = 0;
        this.waitSearch = new Queue<>();

        bfs(G, s);
    }

    // 使用广度优先搜索找出G图中v顶点的所有相邻顶点
    private void bfs(Graph G, int v){
        System.out.print(v + " ->");
        marked[v] = true;
        // 让顶点v进入队列,待搜索
        waitSearch.enqueue(v);
        // 通过循环, 如果队列不为空, 则从队列中弹出一个待搜索的顶点进行搜索
        while(!waitSearch.isEmpty()){
            // 弹出一个待搜索的顶点
            Integer wait = waitSearch.dequeue();
            for(Integer w : G.adj(wait)){
                if(!marked[w]){
                    marked[w] = true;
                    System.out.print(w + " ->");
                    waitSearch.enqueue(w);
                    count++;
                }
            }
        }
        System.out.println();
    }

    // 判断w顶点与s顶点是否相通
    public boolean marked(int w){
        return marked[w];
    }

    // 获取与顶点s相通的所有顶点的总数
    public int count(){
        return count;
    }

}
