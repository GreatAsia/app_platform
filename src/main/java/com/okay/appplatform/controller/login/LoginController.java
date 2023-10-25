package com.okay.appplatform.controller.login;


import com.okay.appplatform.domain.RetResponse;
import com.okay.appplatform.domain.RetResult;
import com.okay.appplatform.domain.user.UserRequest;
import com.okay.appplatform.service.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(description = "登录接口")
@Controller

public class LoginController {


    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public void defIndex(HttpServletRequest request,HttpServletResponse response) throws Exception{
        WebUtils.issueRedirect(request, response, "/login");
    }

    @GetMapping(value = "/login")
    public String index() {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            return "redirect:/api/home";
        }else{
            ModelAndView modelAndView = new ModelAndView();
            return "html/login";
        }

    }

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public RetResult<Object> login(UserRequest user) {

        //UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //currentUser.login(token);
            return RetResponse.makeOKRsp().setMsg("请检查用户名密码").setCode(400);
        }
        return RetResponse.makeOKRsp().setMsg("登录成功").setCode(100);
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";


    }



    @GetMapping(value = "/404")
    public String to404(){
        return "html/page-error-404";
    }

    @GetMapping(value = "/405")
    public String to405(){
       return "html/page-error-405";
    }


}
