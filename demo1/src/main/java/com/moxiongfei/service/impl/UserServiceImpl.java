package com.moxiongfei.service.impl;

import com.moxiongfei.bean.User;
import com.moxiongfei.bean.UserExample;
import com.moxiongfei.mapper.UserMapper;
import com.moxiongfei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.selectByUsernameAndPassword(user);
    }

    @Override
    public int regist(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        long count = userMapper.countByExample(userExample);
        if (count == 0) {                                                      //如果新注册的用户名不存在，则可以注册，并在注册成功后返回0
            userMapper.insert(user);
            return 0;
        }
        return 1;                                                              //如果新注册的用户名存在，则不可以注册，并返回1
    }
}
