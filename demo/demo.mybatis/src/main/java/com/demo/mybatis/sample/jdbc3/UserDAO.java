package com.demo.mybatis.sample.jdbc3;

import com.demo.mybatis.sample.bean.User;

public interface UserDAO {

    User getUserByName(String name);
}
