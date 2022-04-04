package com.atguigu.algorithm.dynamic;

import java.util.Arrays;

/**
 * @author malichun
 * @description
 * @date 2020/3/17 0017 23:15
 * @Version 1.0.0
 */
public class KnasackProblem {

    public static void main(String[] args) {
        int[] w={1,4,3} ;//物品的重量
        int[] val = {1500,3000,2000}; //物品的价值 val[i] 就是前面将的v[i]
        int m=4 ;//被告的容量
        int n=val.length;//物品的个数

        //创建二维数组,占一行,0行,0列
        //v[i][j] 表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v=new int[n+1][m+1];

        //为了记录放入商品的情况,我们定一个二维数组
        int[][] path=new int[n+1][m+1];

        //初始化第一行和第一列,这里在本程序中,可以不处理,因为默认就是0
        for(int i=0;i<v.length;i++){
            v[i][0]=0;//将第一列设置为0
        }
        for(int j=0;j<v[0].length;j++){
            v[0][j]=0;//将第一行设置为0
        }

        //根据前面的公式来动态规划处理
        for(int i=1;i<v.length;i++){ //不处理第一行 ,i是从1开始的
            for(int j=1;j<v[0].length;j++){ //不处理第一列 , j是从1开始的
                //公式
                if(w[i-1]>j){ //因为我们的程序从1开始的,因此原来工时中的w[i]=> w[i-1]
                    v[i][j]=v[i-1][j];//用上一个
                }
                if(j>=w[i-1]){ //背包容量比新加入的物品大,会有多余空间
//                    v[i][j]=Math.max(
//                            v[i-1][j],  //原先最大的
//                            val[i-1]+v[i-1][j-w[i-1]] // 新增的一个和剩余空间最大的
//                    );
                    //为了记录商品存放的背包的情况,我们不能直接简单的使用公式,需要使用if else替代
                    if(v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录下来
                        path[i][j]=1;
                    }else{
                        v[i][i]=v[i-1][j];
                    }
                }

            }

        }







        //输出一下v,看看目前情况
        for(int i=0;i<v.length;i++){
            System.out.println(Arrays.toString(v[i]));
        }

        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
//		for(int i = 0; i < path.length; i++) {
//			for(int j=0; j < path[i].length; j++) {
//				if(path[i][j] == 1) {
//					System.out.printf("第%d个商品放入到背包\n", i);
//				}
//			}
//		}

        //动脑筋
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;  //列的最大下标
        while(i > 0 && j > 0 ) { //从path的最后开始找
            if(path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i-1]; //w[i-1]
            }
            i--;
        }

    }
}
