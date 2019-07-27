package com.sj.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ChenSijia
 * @Date 2019/7/9 12:24
 */
public class TransUtils {

    public static <K,V> Map<K, V> transTwoList2Map(List<K> list, List<V> antherList){
        Map<K, V> map = new HashMap<>();
        int i = 0;
        for(K s : list){
            map.put(s, antherList.get(i));
            i++;
        }
        return map;
    }
}
