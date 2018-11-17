package com.hawk.admin.adminserver.mapper;

import com.hawk.admin.adminserver.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
/**
 * User
 * @author hawk_zhang
 * @date 2018/11/2
 */
@Repository
public interface UserMapper {

    /**
     * 用户登陆验证
     * @param user 用户信息
     * @return map
     */
    Map<String,String> login(User user);
}