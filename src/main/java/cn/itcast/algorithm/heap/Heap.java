package cn.itcast.algorithm.heap;

/**
 * 堆代码
 *
 * @author malichun
 * @create 2022/08/29 0029 1:06
 */
public class Heap<T extends Comparable<T>> {
    // 存储堆中的元素
    private T[] items;
    // 记录堆中元素的个数
    private int N;

    public Heap(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    // 判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    // 交换堆中i索引和j索引处的值
    private void exch(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //往堆中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    // 使用上浮算法, 使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        // 通过循环不断的比较当前节点的值和其父节点的值, 如果发现父节点的值比当前节点的值小, 则交换位置
        while (k > 1) {
            // 比较当前节点和其父节点
            if (less(k / 2, k)) {
                exch(k / 2, k);
            } else {
                break;
            }
            k = k / 2;
        }
    }

    // 删除堆中最大的元素, 并返回这个最大元素
    public T delMax() {
        T max = items[1];
        // 交换索引1处的元素和最大索引处的元素, 让完全二叉树中最右侧的元素变为临时根节点
        exch(1, N);
        // 最大索引出的二元素删除掉
        items[N] = null;
        // 让元素个数-1
        N--;
        // 堆不再有序了, 让堆重新有序, 通过下沉
        sink(1);
        return max;
    }

    // 使用下浮算法, 使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        // 通过循环,不断的对比当前k节点和其字左子节点2k , 右子节点2K+1处的中的较大值的元素大小, 如果当前节点小,则需要交换位置
        while (2 * k <= N) { //找到子结点中的较大者
            int max;
            if (2 * k + 1 <= N) {
                //存在右子结点
                if (less(2 * k, 2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                //不存在右子结点
                max = 2 * k;
            }

            //比较当前结点和子结点中的较大者，如果当前结点不小，则结束循环
            if (!less(k, max)) {
                break;
            }
            //当前结点小，则交换，
            exch(k, max);
            k = max;
        }
    }

}
