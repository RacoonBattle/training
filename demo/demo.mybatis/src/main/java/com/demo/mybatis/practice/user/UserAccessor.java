package com.demo.mybatis.practice.user;

import com.demo.mybatis.sample.bean.User;

/**
 * 用户信息访问接口
 * 
 * @author zhongyuan.zhang
 */
public interface UserAccessor {

    /**
     * 新增用户信息
     * 
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据ID获取用户信息
     * 
     * @param id 用户ID
     * @return 用户不存在时返回null
     */
    User getUser(int id);

    /**
     * 根据用户名获取用户信息
     * 
     * @param name 用户名
     * @return 用户不存在时返回null
     */
    User getUser(String name);

    /**
     * 更新用户信息
     * 
     * @param user 新的用户信息
     */
    void updateUser(User user);

    /**
     * 删除指定用户
     * 
     * @param id 用户ID
     */
    void deleteUser(int id);

    /**
     * 批量删除用户
     * 
     * @param ids 用户ID列表
     */
    void deleteUsers(int[] ids);
}
