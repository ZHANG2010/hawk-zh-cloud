package com.hawk.admin.adminserver.api;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AdminServerApplication
 *
 * @author hawk_zhang
 * @date 2018/10/22
 */
@RestController
@RequestMapping("admin")
public class AdminApi {

    @PostMapping("login.do")
    public void login(  @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("timestrap") String timestrap,
                        HttpServletResponse response ){

        System.out.println("username:" + username + "  password:" + password + "  timestrap:"+timestrap);
        String token = "123456";
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(24*3600);
        response.addCookie(cookie);
        try {
            response.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("validateLogin.do")
    @ResponseBody
    public String validateLogin(@RequestHeader("authorization") String authorization,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("timestrap") String timestrap){
        System.out.println("username:" + username + "  password:" + password + "  timestrap:" + timestrap + " Authorization:"+authorization);
        return "{\"state\":\"success\"}";
    }
}
