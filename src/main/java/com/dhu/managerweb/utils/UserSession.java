package com.dhu.managerweb.utils;

import com.dhu.managerweb.VO.SessionVO;

/**
 * creater: LIUYING
 * date:2018/8/24 13:07
 */
public class UserSession {
    private static ThreadLocal<SessionVO> local=new ThreadLocal<>();

    public static void setSession(SessionVO session){
        local.set(session);
    }

    public static SessionVO getSession(){
        return local.get();
    }

    public static void remove(){
        local.remove();
    }
}
