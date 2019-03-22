package org.scode.m2.controller;

import org.scode.m2.dto.Customer;
import org.scode.m2.entity.User;
import org.scode.m2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("getImg")
    @ResponseBody
    public byte[] getImg() throws IOException {
        this.logger.info("开始读取文件.");
        Resource resource = new ClassPathResource("p1.jpeg");
        return FileCopyUtils.copyToByteArray(resource.getFile());
    }

    @RequestMapping("getHandler41")
    @ResponseBody
    public String handler41(@RequestBody String requestBody) {
        this.logger.info(requestBody);
        return "success";
    }

    @RequestMapping("xmlInfo")
    @ResponseBody
    public String xmlInfo(@RequestBody Customer customer) {
        this.logger.info("customer info " + customer);
        return "success";
    }
}
