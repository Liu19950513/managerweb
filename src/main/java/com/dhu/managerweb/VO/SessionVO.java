package com.dhu.managerweb.VO;

import lombok.Data;

/**
 * creater: LIUYING
 * date:2018/8/24 13:09
 */
@Data
public class SessionVO {
    private Long userId;
    private String name;

    public SessionVO(Long userId,String name){
        this.userId=userId;
        this.name=name;
    }

}
