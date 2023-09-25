package com.huo.huoapiinterface.controller;

import top.huo.huoclientsdk.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huo
 */
@RestController
public class NameController {

    @PostMapping("/api/name/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        return "POST 你的用户名字是：" + user.getUsername();
    }

}
