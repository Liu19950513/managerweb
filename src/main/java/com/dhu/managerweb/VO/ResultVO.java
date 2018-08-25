package com.dhu.managerweb.VO;

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

//    public ResultVO(T data,Integer code,String msg){
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }
//
//    public ResultVO(){
//    }
}
