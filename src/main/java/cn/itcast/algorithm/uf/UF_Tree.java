package cn.itcast.algorithm.uf;

/**
 *
 *  优化并查集, 每个结点的元素存放父节点
 * 并查集 union-find disjoint sets
 * Created by John.Ma on 2022/4/10 0010 0:22
 */
public class UF_Tree {
    // 记录结点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组和个数
    private int count;

    // 初始化并查集, (N: 有多少个元素)
    public UF_Tree(int N){
        // 初始化分组的数量, 默认情况下,有N个分组
        this.count = N;

        // 初始化eleAndGroup 数组
        this.eleAndGroup = new int[N];

        // 初始化eleAndGroup中的元素及其所在的组的标识符
        // 默认情况下, 让eleAndGroup数组的索引作为并查集的每个节点的元素
        // 并且让每个索引出的值就是该索引(该元素所在的组的标识符)
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }

    // 获取当前并查集中的数据有多少个分组
    public int count(){
        return count;
    }

    // 元素p所在分组的标识符
    public int find(int p){
        while(true){
            if(p == eleAndGroup[p]){
                return p;
            }
            p = eleAndGroup[p];
        }
    }

    // 判断并查集中元素p和元素q是否在同一分组中
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // 把p元素所在分组和q元素所在分组合并(p合并到q中)
    public void union(int p, int q){
        // 找到p元素和q元素所在组对应的树的根节点
        int pRoot = find(p);
        int qRoot = find(q);
        // 如果p和q已经在同一分组,则不需要合并了
        if(pRoot == qRoot){
            return;
        }

        // 合并 , 让p所在树的跟结点为q所在树的根节点
        eleAndGroup[pRoot] = qRoot;

        // 组的数量 - 1
        this.count--;
    }
}
