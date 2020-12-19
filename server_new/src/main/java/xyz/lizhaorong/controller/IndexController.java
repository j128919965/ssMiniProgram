package xyz.lizhaorong.controller;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.lizhaorong.entity.User;
import xyz.lizhaorong.service.UserService;
import xyz.lizhaorong.util.support.Response;

import java.io.IOException;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    @ResponseBody
    public Response<User> login(String code) throws IOException {
        String wxid = userService.getWxid(code);
        User u = userService.getUserByWxid(wxid);
        Response<User> response = new Response<>();

        if(u!=null){
            log.debug("user "+u.getName()+" login success");
            return response.success(u);
        }

        u = userService.createUser(wxid);
        userService.afterCreateUser(u);
        log.debug("create and return user");
        return response.success(u);
    }

    @PostMapping("/login")
    @ResponseBody
    public Response<User> login(String username,String password){
        User user = userService.getUserByUsernameAndPassword(username, SecureUtil.md5(password));
        if (user==null){
            return new Response<User>().failure("用户名或密码错误！");
        }
        return new Response<User>().success(user);
    }

    @PostMapping("/refresh")
    @ResponseBody
    public Response<User> refresh(Integer uid){
        User user = userService.getUserByUid(uid);
        return new Response<User>().success(user);
    }

    @PostMapping("/register")
    @ResponseBody
    public Response<User> register(String username,String password){
        User user = userService.register(username,SecureUtil.md5(password));
        if (user==null){
            return new Response<User>().failure("用户名已存在！");
        }
        return new Response<User>().success(user);

    }


    @PostMapping("/user")
    @ResponseBody
    public Response<Boolean> updateUser(String uid,String opt,String data){
        userService.updateUser(uid,opt,data);
        return Response.staticSuccess();
    }
}
