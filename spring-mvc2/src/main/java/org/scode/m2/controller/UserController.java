package org.scode.m2.controller;

import org.scode.m2.entity.User;
import org.scode.m2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author wyh
 * @date 2019/3/21 8:07 PM
 * @description
 */
@Controller
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("userIndex")
    public String userInfoPage(Model model) {
        this.logger.info("开始执行userInfoPage.");
        model.addAttribute("user", this.userService.getUserInfo());
        return "userInfo";
    }

    @GetMapping("userInfo")
    @ResponseBody
    public User userInfo() {
        this.logger.info("开始执行userInfo.");
        return this.userService.getUserInfo();
    }

    @GetMapping("matrix/{id}")
    @ResponseBody
    public String matrix(@PathVariable int id, @MatrixVariable Map<String, String> map) {
        this.logger.info("开始执行userInfo.");
        return map.toString() + "_" + id;
    }
}
