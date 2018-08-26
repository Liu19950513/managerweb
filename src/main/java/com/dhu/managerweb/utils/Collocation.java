package com.dhu.managerweb.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * creater: LIUYING
 * date:2018/8/26 20:17
 */
@Data
public class Collocation {
    private Long clothes1=(long)0;
    private Long clothes2;
    private Long clothes3;
    
    public Collocation(Long clothes1,Long clothes2,Long clothes3){
        this.clothes1 = clothes1;
        this.clothes2 = clothes2;
        this.clothes3 = clothes3;
    }

    public Collocation(){}

    public boolean search(Long clothesId){
        if (clothesId.equals(this.clothes1) || clothesId.equals(this.clothes2)
        || clothesId.equals(this.clothes3)){
            return true;
        }
        return false;
    }

}
