package com.example.shirodemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuwencong on 2019/4/19
 */
@Controller
@Slf4j
public class ShiroController {


    @PostMapping("/doLogin")
    public String doLogin(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index";//验证成功跳转页面
        } catch (AuthenticationException e) {
            log.error("验证失败");
            e.printStackTrace();
        }
        return "redirect:/login";//验证失败跳转页面
    }

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }
}
