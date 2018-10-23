package com.hawk.admin.adminserver.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminServerApplication
 *
 * @author hawk_zhang
 * @date 2018/10/22
 */
@RestController
@RequestMapping("admin")
public class AdminApi {

    @RequestMapping("login.do")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){

        return username+"  "+password+ "  登陆成功！";
    }
}
