package com.dhu.managerweb.client;

import com.dhu.managerweb.VO.ResultVO;
import com.dhu.managerweb.VO.UserInfoVO;
import com.dhu.managerweb.VO.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * creater: LIUYING
 * date:2018/8/19 12:33
 */

@FeignClient(name = "user",url = "http://localhost:9000/user")
public interface UserClient {

    @GetMapping("/validate/{email}/{password}")
    ResultVO<UserInfoVO> validate(@PathVariable("email") String email, @PathVariable("password") String password);

    @PostMapping("/register/")
    String register(@RequestBody UserVO userVO);

    @PostMapping("/update")
    void update(@RequestBody UserInfoVO userInfoVO);

    @PostMapping("/userInfoForRecommend")
    UserInfoVO listForRecommend(@RequestParam(value = "1") Long userId);

}
