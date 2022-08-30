package cn.itcast.algorithm.test;

import cn.itcast.algorithm.priority.MaxPriorityQueue;

/**
 * 
 * 测试优先队列
 * @author malichun
 * @create 2022/08/30 0030 21:10
 */
public class MaxPriorityQueueTest {
    public static void main(String[] args) {
        // 创建优先队列
        MaxPriorityQueue<String> queue = new MaxPriorityQueue<>(10);

        // 往队列中存储元素
        queue.insert("A");
        queue.insert("B");
        queue.insert("C");
        queue.insert("D");
        queue.insert("E");
        queue.insert("F");
        queue.insert("G");

        // 从队列中获取最大的元素
        while(!queue.isEmpty()){
            String s = queue.delMax();
            System.out.println(s);
        }
    }
}
