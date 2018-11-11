package com.hawk.admin.adminserver.api;

import com.hawk.admin.adminserver.common.TokenUtils;
import com.hawk.admin.adminserver.entity.User;
import com.hawk.admin.adminserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private AdminService adminService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("login.do")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("timestrap") String timestrap,
                        HttpServletResponse response ) throws IOException {

        System.out.println("username:" + username + "  password:" + password + "  timestrap:"+timestrap);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (redisTemplate.hasKey(username)){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 测试 RedisTemplate ！>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

        if(stringRedisTemplate.hasKey(username)){
            String pwRedis = stringRedisTemplate.opsForValue().get(username);
            if (pwRedis.equals(password)){
                response.sendRedirect("index.html");
                System.out.println("**********************用户名密码与 Redis 缓存中一致！*****************************");
                return null;
            }else {
                response.sendRedirect("login.html");
                System.out.println("**********************密码与 Redis 缓存中不一致，请重新输入！*****************************");
                return null;
            }
        }

            Map<String,String> newUser = adminService.login(user);
            if(newUser == null){
                response.sendRedirect("login.html");
                System.out.println("**********************用户名密码错误，请重新输入！*****************************");
                return null;
            }

        stringRedisTemplate.opsForValue().set(username,password);
            System.out.println("**********************用户名密码存入 Redis 缓存中！*****************************");

            String token = "123456";
            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(24*3600);
            response.addCookie(cookie);
            response.sendRedirect("index.html");
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
