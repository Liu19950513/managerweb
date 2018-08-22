package com.dhu.managerweb.UserVO;

import lombok.Data;

/**
 * http请求返回最外层对象
 * created by LiuYing
 * 2018-8-13
 * @param <T>
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;
}
