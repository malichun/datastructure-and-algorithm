package cn.itcast.algorithm.test;

import cn.itcast.algorithm.uf.UF_Tree_Weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 畅通工程, 使用并查集
 * Created by John.Ma on 2022/4/10 0010 1:54
 */
public class Traffic_Project_Test {
    public static void main(String[] args) throws IOException {
        // 构建一个缓冲读取流
        BufferedReader br = new BufferedReader(new InputStreamReader(Traffic_Project_Test.class.getClassLoader().getResourceAsStream("traffic_project.txt")));

        // 读取第一行数据20
        int totalNumber = Integer.parseInt(br.readLine());
        // 构建一个并查集对象
        UF_Tree_Weighted uf = new UF_Tree_Weighted(totalNumber);
        // 读取第二行数据7
        int roadNumber = Integer.parseInt(br.readLine());
        // 循环读取7条道路
        for (int i = 0; i < roadNumber; i++) {
            String line = br.readLine();
            String[] str = line.split(" ");
            int p =Integer.parseInt(str[0]);
            int q =Integer.parseInt(str[1]);
            // 调用并查集对象的union方法,让两个城市相通
            uf.union(p, q);
        }

        // 获取当前并查集中分组的数量 - 1 就可以得到还需要修建的道路的数目
        int groupNum = uf.count();
        System.out.println( "需要修建的道路的数目: " + (groupNum - 1));

    }
}
