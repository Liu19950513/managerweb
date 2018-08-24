package com.dhu.managerweb.VO;

import lombok.Data;

/**
 * creater: LIUYING
 * date:2018/8/15 20:49
 */

@Data
public class UserInfoVO {
    private Long userId;
    private String style;
    private Integer age;
    private String body;
    private String skin;
    private Integer height;

    public UserInfoVO(){
    }

    public  UserInfoVO(Long userId,String style,Integer age,Integer height,String body,String skin){
        this.userId = userId;
        this.age = age;
        this.body = body;
        this.height = height;
        this.style = style;
        this.skin = skin;
    }
}
