package com.dhu.managerweb.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * creater: LIUYING
 * date:2018/8/26 20:15
 */
public class CollocationMenu {
    private static List<Collocation> menuList=new ArrayList<>();
    static {
        menuList.add(new Collocation((long)11,(long)4,(long)2));
        menuList.add(new Collocation((long)12,(long)7,(long)3));
        menuList.add(new Collocation((long)10,(long)5,(long)1));
        menuList.add(new Collocation((long)11,(long)4,(long)2));
    }

    /**
     * 通过clothesId获取到对应的搭配
     * @param clothesId
     * @return
     */
    public static Collocation findCollocation(Long clothesId){
        for(Collocation collocation: menuList){
            if (collocation.search(clothesId)){
                return collocation;
            }
        }
        return new Collocation();
    }
}
