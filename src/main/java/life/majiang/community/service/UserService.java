package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: T.ADOLPH
 * Date: 2020/8/26 17:20
 * Version: 1.0
 * Describe:
 * @author T.ADOLPH
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        User dbUser = userMapper.findUserByAccountId(user.getAccountId());
        if (dbUser == null) {
            // 数据库没有相同AccountID的用户，插入
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
        } else {
            // 有则修改相关属性
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            userMapper.update(dbUser);
        }
    }
}
