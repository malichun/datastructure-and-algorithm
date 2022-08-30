package cn.itcast.algorithm.heap;

/**
 * @author malichun
 * @create 2022/08/29 0029 23:46
 */
public class HeapSort {
    // 判断heap堆中索引i处的元素是否小于索引j处的元素
    private static boolean less(Comparable[] heap, int i, int j){
        return heap[i].compareTo(heap[j]) < 0;
    }

    // // 交换heap堆中i索引出的值
    private static void exch(Comparable[] heap, int i, int j){
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 根据原数组source, 构造出堆
    private static void createHeap(Comparable[] source, Comparable[] heap){
        // 把source中元素拷贝到heap[]中, 此时heap中的元素形成了一个堆
        System.arraycopy(source, 0,heap, 1, source.length);

        // 对堆中的元素做下沉调整(从长度的一半处开始往索引1处扫描)
        for (int i = heap.length /2; i >0 ; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    // 对source数组的数据从小到大排序
    // 类似于堆的删除
    public static void sort(Comparable[] source){
        Comparable[] heap = new Comparable[source.length + 1];
        // 构建堆
        createHeap(source, heap);

        // 定义一个变量, 记录未排序的元素中最大的索引
        int n = heap.length -1;
        // 通过循环交换1索引处的元素和未排序的元素中最大索引处的元素
        while(n != 1){
            exch(heap, 1, n);
            // 排除交换后最大元素所在的索引, 让它不要参与堆的下沉
            n--;
            // 需要对索引1处的元素进行下沉调整
            sink(heap, 1,n);
        }
        // 把heap中的数据复制到原数组source
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    // 在heap堆中, 对target处的元素做下沉, 范围是0~range
    private static void sink(Comparable[] heap, int target, int range){
        while (2*target<=range){
            // 找出当前节点的较大的子节点
            int max ;
            if(2*target + 1 <= range){
                if(less(heap, 2*target, 2*target+1)){
                    max = 2 * target+1;
                }else{
                    max = 2 * target;
                }
            }else{
                max = 2* target;
            }

            // 2.比较当前节点的值和较大子节点的值
            if(!less(heap, target, max)){
                break;
            }
            exch(heap, target, max);

            target = max;
        }
    }
}
