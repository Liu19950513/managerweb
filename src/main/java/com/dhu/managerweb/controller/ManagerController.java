package com.dhu.managerweb.controller;

import com.dhu.managerweb.UserVO.ResultVO;
import com.dhu.managerweb.UserVO.UserVO;
import com.dhu.managerweb.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * creater: LIUYING
 * date:2018/8/19 13:37
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    UserClient userClient;

    @RequestMapping(value = "/account/login")
    public String login(@ModelAttribute UserVO loginUser, Model model ){
        if(loginUser.getEmail() == null){
            //验证失败
            model.addAttribute("message","请输入有效邮箱！");
            return "login";
        }
        //调用验证服务
        ResultVO<UserVO> dataUser = userClient.validate(loginUser.getEmail(),loginUser.getPassword());
        if(dataUser.getData().getUserId() == null){
            //验证失败
            model.addAttribute("message","登陆失败！");
            return "login";
        }else{
            model.addAttribute("user",dataUser.getData());
            return "home";
        }
    }

    @RequestMapping(value = "/account/register")
    public String register (@ModelAttribute UserVO registerUser, Model model){
        String msg = userClient.register(registerUser);
        model.addAttribute("message",msg);
        return "register";
    }

    /**
     * 以下方法都是页面跳转
     * @return
     */
    @RequestMapping(value = "/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value ="/home")
    public String toHome(){
        return "home";
    }

    @RequestMapping(value = "/register")
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "/test/skin")
    public String testSkin(){
        return "test/style";
    }

    @RequestMapping(value = "/test/style/{style}")
    public String testSyle(@PathVariable String style, Model model){
        model.addAttribute("style",style);
        return "test/body";
    }

    @RequestMapping(value = "/test/body/{body}")
    public String testBody(@PathVariable String body, Model model){
        model.addAttribute("body",body);
        return "test/info";
    }

    //test
    @RequestMapping(value = "/test/info/{age}/{height}")
    public String testInfo(@PathVariable String age,@PathVariable String height, Model model){
        model.addAttribute("age",age);
        return "test/info";
    }
}
