package com.dhu.managerweb.controller;

import com.dhu.managerweb.VO.*;
import com.dhu.managerweb.client.ClothesClient;
import com.dhu.managerweb.client.UserClient;
import com.dhu.managerweb.utils.Collocation;
import com.dhu.managerweb.utils.CollocationMenu;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
            request.getSession().setAttribute("user",dataUser.getData());
            //model.addAttribute("user",dataUser.getData());
            //1为测评过的用户,跳转到recommend.html
            if(dataUser.getData().getStyle() != null){
                return "redirect:/manager/clothesRecommend/1";
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
     * 用户测评
     * UserClient调用用户问服务将用户测评的信息保存到用户DB
     */
    @RequestMapping(value = "/userTest")
    public String test(HttpServletRequest request, Model model){
        UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user");
        if(user != null) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setUserId(Long.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            userInfoVO.setAge(Integer.valueOf(request.getParameter("age")));
            userInfoVO.setBody(request.getParameter("body"));
            userInfoVO.setHeight(Integer.valueOf(request.getParameter("height")));
            userInfoVO.setSkin(request.getParameter("skin"));
            userInfoVO.setStyle(request.getParameter("style"));
            userClient.update(userInfoVO);
            model.addAttribute("user", userInfoVO);
            return "forward:/manager/clothesRecommend";
        }
        else{
            model.addAttribute("message","请先登陆！");
            return "login";
        }
    }

    /**
     * 服装推荐
     * 调用推荐微服务（现在只是调用服装微服务）
     * 根据用户的肤色和喜好风格，根据选取的type返回对应类型的衣服
     */
    @RequestMapping(value = "/clothesRecommend/{type}",method = RequestMethod.GET)
    public String recommend(HttpServletRequest request,
                            @PathVariable(value = "type") String type, Model model){
        UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user");
        if(user!=null) {
            List<ClothesVO> clothesList = clothesClient.listForRecommend(user.getStyle(), user.getSkin());
            model.addAttribute("clothesList", clothesList);
            model.addAttribute("type", type);
            model.addAttribute("user", user);
            return "recommend";
        }else {
            model.addAttribute("message","请先登陆！");
            return "login";
        }
    }

    /**
     * 根据选取的衣服搭配一整套服装
     * 调用推荐搭配微服务（现在只是服装微服务）
     */
    @RequestMapping(value = "/collocation/{clothesId}",method = RequestMethod.GET)
    public String collocation(HttpServletRequest request, @PathVariable(value = "clothesId") Long clothesId , Model model){
        UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user");
        if(user != null) {
            Collocation searchResult = CollocationMenu.findCollocation(clothesId);
            System.out.println("clothesID：" + clothesId);
            if (searchResult.getClothes1() != 0) {
                List<ClothesInfoVO> clothesInfoVO = clothesClient.listForCollocation(searchResult);
                model.addAttribute("clothesList", clothesInfoVO);
                model.addAttribute("user",user);
                return "collocation";
            }
            else {
                return "home";
            }
        }
        else{
            model.addAttribute("message","请先登陆！");
            return "login";
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
    public String toTest(HttpServletRequest request, Model model){
        UserInfoVO user = (UserInfoVO) request.getSession().getAttribute("user");
        if(user!= null) {
            return "test";
        }
        else{
            model.addAttribute("message","请先登陆！");
            return "login";
        }
    }

}
