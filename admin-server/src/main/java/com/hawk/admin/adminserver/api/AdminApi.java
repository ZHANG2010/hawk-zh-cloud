package com.hawk.admin.adminserver.api;

import javafx.application.Application;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                        HttpServletResponse response,
                        HttpSession session){

        System.out.println("username:" + username + "  password:" + password + "  timestrap:"+timestrap);

        session.setAttribute("username",username);
        try {
            response.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
