package com.dhu.managerweb.controller;

import com.dhu.managerweb.VO.*;
import com.dhu.managerweb.client.ClothesClient;
import com.dhu.managerweb.client.UserClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * creater: LIUYING
 * date:2018/8/19 13:37
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    UserClient userClient;

    @Autowired
    ClothesClient clothesClient;

    /**
     * 用户登陆
     * 调用用户服务的登陆验证
     * @param loginUser
     * @param model
     * @return
     */
    @RequestMapping(value = "/account/login")
    public String login(@ModelAttribute UserVO loginUser, Model model,HttpServletRequest request ){
        if(loginUser.getEmail() == null){
            //验证失败
            model.addAttribute("message","请输入有效邮箱！");
            return "login";
        }
        //调用验证服务
        ResultVO<UserInfoVO> dataUser = userClient.validate(loginUser.getEmail(),loginUser.getPassword());
        if(dataUser.getData().getUserId() == null){
            //验证失败
            model.addAttribute("message","登陆失败！");
            return "login";
        }else{
            model.addAttribute("user",dataUser.getData());
            //Cookie cookies=new Cookie("userId",String.valueOf(dataUser.getData().getUserId()));
            request.getSession().setAttribute("userId",String.valueOf(dataUser.getData().getUserId()));
            //1为测评过的用户,跳转到recommend.html
            if(dataUser.getData().getStyle() != null){
                return "redirect:/manager/clothesRecommend?style="+dataUser.getData().getStyle()+"&skin="
                        +dataUser.getData().getSkin();
            }
            // 未测评过的用户跳转到test.html
            else {
                return "test";
            }
        }
    }

    /**
     * 用户注册
     * 调用用户服务的新增用户
     * @param registerUser
     * @param model
     * @return
     */
    @RequestMapping(value = "/account/register")
    public String register (@ModelAttribute UserVO registerUser, Model model){
        System.out.println("@@@"+registerUser.getEmail());
        if(registerUser.getEmail() == null){
            model.addAttribute("message","请输入邮箱！");
            return "register";
        }
        else {
            String msg = userClient.register(registerUser);
            model.addAttribute("message", msg);
            return "register";
        }
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

    @RequestMapping(value = "/test")
    public String toTest(){
        return "test";
    }

    @RequestMapping(value = "/recommend")
    public String toRecommend(){
        return "recommend";
    }

    @RequestMapping(value = "/userTest")
    public String test(HttpServletRequest request){

        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(Long.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        userInfoVO.setAge(Integer.valueOf(request.getParameter("age")));
        userInfoVO.setBody(request.getParameter("body"));
        userInfoVO.setHeight(Integer.valueOf(request.getParameter("height")));
        userInfoVO.setSkin(request.getParameter("skin"));
        userInfoVO.setStyle(request.getParameter("style"));
        userClient.update(userInfoVO);
        return "redirect:/manager/clothesRecommend?style="+request.getParameter("style")+"&skin="
                                                            +request.getParameter("skin");
    }

    @RequestMapping(value = "/clothesRecommend",method = RequestMethod.GET)
    public String recommend(HttpServletRequest request,Model model){
        String skin=request.getParameter("skin");
        String style=request.getParameter("style");
        System.out.println(skin+"-"+style);
        List<ClothesVO> clothesList = clothesClient.listForRecommend(style,skin);
        model.addAttribute("clothesList",clothesList);
        return "recommend";
    }


}
