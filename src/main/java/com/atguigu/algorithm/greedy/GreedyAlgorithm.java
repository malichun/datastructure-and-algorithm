package com.atguigu.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author malichun
 * @description
 * @date 2020/3/1 0001 23:28
 * @Version 1.0.0
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {


        //将各个电台放入到broadcasts
        HashSet<String> hashSet1=new HashSet<String>(){{
            add("北京");
            add("上海");
            add("天津");
        }};

        HashSet<String> hashSet2=new HashSet<String>(){{
            add("广州");
            add("北京");
            add("深圳");
        }};

        HashSet<String> hashSet3=new HashSet<String>(){{
            add("成都");
            add("上海");
            add("杭州");
        }};

        HashSet<String> hashSet4=new HashSet<String>(){{
            add("上海");
            add("天津");
        }};

        HashSet<String> hashSet5=new HashSet<String>(){{
            add("杭州");
            add("大连");
        }};

        //创建广播电台,放入到HashMap中管理
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>(){{
            put("k1",hashSet1);
            put("k2",hashSet2);
            put("k3",hashSet3);
            put("k4",hashSet4);
            put("k5",hashSet5);
        }};

        //allAreas存放所有的地区
        HashSet<String> allAreas = new HashSet<String>(){{
            add("北京");
            add("上海");
            add("天津");
            add("广州");
            add("深圳");
            add("成都");
            add("杭州");
            add("大连");
        }};

        //创建一个ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合,在遍历过程中,存放遍历过程中电台覆盖地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();


        //定义一个maxKey,保存在一次遍历过程中,能够覆盖最多未覆盖的地区对应的电台的key
        //如果maxKey不为空,则会加入到selects里面
        String maxKey=null;
        while(allAreas.size()!=0){ // allAreas在不停的减少,如果allAreas不为0,则表示还未覆盖所有地区
            //每进行一次while,
            maxKey=null;
            //遍历broadcast,取出对应的key
            for(String key:broadcasts.keySet()){
                //没进行一次for;
                tempSet.clear();

                //当前这个key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);

                //体现贪心算法,每次都是最新的
                //如果当前这个集合包含的未覆盖地区的数量比max指向的集合未覆盖的地区还要多,则替换maxKey
                if(tempSet.size()>0 && (maxKey==null || tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey=key;
                }
            }
            //如果经过几轮遍历,说明选中的有了,将maxKey加入到selects
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxkey指向的广播电台覆盖的地区,从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是:"+selects);


    }
}


