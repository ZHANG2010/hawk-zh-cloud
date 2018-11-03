package com.hawk.admin.adminserver.api;

import com.hawk.admin.adminserver.common.TokenUtils;
import com.hawk.admin.adminserver.entity.User;
import com.hawk.admin.adminserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * AdminServerApplication
 * @author hawk_zhang
 * @date 2018/10/22
 */
@RestController
@RequestMapping("admin")
public class AdminApi {

    @Autowired
    AdminService adminService;

    @PostMapping("login.do")
    public String login(  @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("timestrap") String timestrap,
                        HttpServletResponse response ){

        System.out.println("username:" + username + "  password:" + password + "  timestrap:"+timestrap);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Map<String,String> newUser = adminService.login(user);
        if(newUser == null){
            try {
                response.sendRedirect("login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        String token = "123456";
        Cookie cookie = new Cookie("token",token);
        cookie.setMaxAge(24*3600);
        response.addCookie(cookie);
        try {
            response.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("validateLogin.do")
    @ResponseBody
    public String validateLogin(@RequestHeader("authorization") String authorization,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("timestrap") String timestrap) throws NoSuchAlgorithmException {
        System.out.println("username:" + username + "  password:" + password + "  timestrap:" + timestrap + " Authorization:"+authorization);
    //    return "{\"state\":\"success\"}";
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.creatToken();
        return "{\"state\":\""+token+"\"}";
    }
}
