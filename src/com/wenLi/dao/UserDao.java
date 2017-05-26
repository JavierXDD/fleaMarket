package com.wenLi.dao;

import com.wenLi.entity.User;
import com.wenLi.util.entity.Page;
/**
 * 用户
 * @author xjw
 *
 */
public interface UserDao {

	//保存、注册用户
	public boolean saveUser(User user);
	//根据用户名查用户
	public User findUserByNickname(String nickname);
	//登录
	public User userLogin(String nickname, String password);
	//获取所有管理员
	public Page<User> getAdmin(int pc,int ps);
	//根据id查用户
	public User getUserById(String id);
	//获取所有用户
	public Page<User> getAllUser(int pc, int ps);
	//后台创建或更新用户
	public boolean saveOrUpdateUser(User user);
	//禁用用户
	public User forbiddenUser(String id);
	//启用用户
	public User usingUser(String userId);
}
