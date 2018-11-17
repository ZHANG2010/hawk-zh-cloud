package com.hawk.admin.adminserver.service.impl;

import com.hawk.admin.adminserver.entity.User;
import com.hawk.admin.adminserver.mapper.UserMapper;
import com.hawk.admin.adminserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * AdminServiceImpl
 *
 * @author hawk_zhang
 * @date 2018/10/22
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
     private UserMapper userMapper;

    @Override
    public Map<String,String> login(User user) {
       return userMapper.login(user);
    }
}
