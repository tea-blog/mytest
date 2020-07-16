package com.anpai.shoesservice.controller;

import com.anpai.commomutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/7 11:15
 */
@CrossOrigin
@Api(tags = "用户登陆")
@RestController
@RequestMapping("/shoesservice/user")
public class StoreLoginController {

    // login2
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    // info
    @GetMapping("info")
    public R info(){
        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        return R.ok().data("roles","[admin]").data("token","admin").data("name","nanfangzhe").data("avatar", avatar);
    }

    @GetMapping("logout")
    public R logout(){
        System.out.println("~退出成功！");

        return R.ok().data("token","");
    }

}
