package com.dhu.managerweb.client;

import com.dhu.managerweb.UserVO.ResultVO;
import com.dhu.managerweb.UserVO.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * creater: LIUYING
 * date:2018/8/19 12:33
 */

@FeignClient(name = "user",url = "http://localhost:9000/user")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/validate/{email}/{password}")
    ResultVO<UserVO> validate(@PathVariable("email") String email, @PathVariable("password") String password);

    @PostMapping("/register/")
    String register(@ModelAttribute UserVO userVO);


}
