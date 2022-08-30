package cn.itcast.algorithm.test;

import cn.itcast.algorithm.priority.MinPriorityQueue;

/**
 * 最小优先队列
 * @author malichun
 * @create 2022/08/30 0030 21:53
 */
public class MinPriorityQueueTest {
    public static void main(String[] args) {
        // 创建最小优先队列对象
        MinPriorityQueue<String> queue = new MinPriorityQueue<>(10);

        // 往队列中插入数据
        queue.insert("G");
        queue.insert("F");
        queue.insert("E");
        queue.insert("D");
        queue.insert("C");
        queue.insert("B");
        queue.insert("A");

        // 通过循环获取小最小优先队列中的元素
        while(!queue.isEmpty()){
            String min = queue.delMin();
            System.out.println(min);
        }
    }
}
