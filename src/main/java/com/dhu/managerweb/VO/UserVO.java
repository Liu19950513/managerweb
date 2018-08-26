package com.dhu.managerweb.VO;

import lombok.Data;

/**
 * creater: LIUYING
 * date:2018/8/14 15:18
 */
@Data
public class UserVO {

    private Long userId;

    private String email;

    private String password;

    private String name;

    private Integer type;
}
