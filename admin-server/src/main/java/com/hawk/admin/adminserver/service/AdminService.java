package com.hawk.admin.adminserver.service;

import com.hawk.admin.adminserver.entity.User;

import java.util.Map;

/**
 * AdminService
 *
 * @author hawk_zhang
 * @date 2018/10/22
 */
public interface AdminService {

    /**
     * 用户登陆验证
     * @param user 用户信息
     * @return map
     */
    Map<String,String> login(User user);
}
