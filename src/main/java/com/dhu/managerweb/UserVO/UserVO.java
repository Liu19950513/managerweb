package com.dhu.managerweb.UserVO;

import lombok.Data;

/**
 * creater: LIUYING
 * date:2018/8/14 15:18
 */
@Data
public class UserVO {

    private String code;

    private Long userId;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;

}
