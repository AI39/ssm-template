package com.moxiongfei.service;

import com.moxiongfei.bean.User;

public interface UserService {
    User login(User user);
    int regist(User user);
}
