package com.wenLi.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenLi.entity.User;
import com.wenLi.service.UserService;
import com.wenLi.util.entity.Page;
import com.wenLi.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	
	
	//登录
	@Override
	public User userLogin(String nickname,String password) {
		
		User user = userDao.userLogin(nickname,password);
		
		return user;
	}

	
	//注册 保存用户
	@Override
	public boolean saveUser(User user) {
		return userDao.saveUser(user);
	}

	//根据用户名查用户
	@Override
	public User findUserByNickname(String nickname) {
		User user = userDao.findUserByNickname(nickname);
		return user;
	}

	//获取所有管理员
	@Override
	public Page<User> getAdmin(int pc,int ps) {
		Page<User> adminList = userDao.getAdmin(pc,ps);
		return adminList;
	}


	//根据id查用户
	@Override
	public User getUserById(String id) {
		User user = userDao.getUserById(id);
		return user;
	}


	//获取所有用户
	@Override
	public Page<User> getAllUsers(int pc, int ps) {
		Page<User> userList = userDao.getAllUser(pc,ps);
		return userList;
	}


	//后台创建或更新用户
	@Override
	public boolean saveOrUpdateUser(User user)  {
		
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("/properties/other.properties"); 
		  
		
		if(user.getId() != ""){
			user.setPassword(userDao.getUserById(user.getId()).getPassword());
		}else{
			try {
				props.load(in);
				String initialPassword = props.getProperty("initialPassword");
				user.setPassword(initialPassword);	
				user.setIsUse(0);
			} catch (IOException e) {
				System.out.println("***************  未找到初始密码配置文件  ***************");
				e.printStackTrace();
			}			
		}
		
		return userDao.saveOrUpdateUser(user);
	}


	//禁用用户
	@Override
	public User forbiddenUser(String id) {
		
		return userDao.forbiddenUser(id);
	}


	//启用用户
	public User usingUser(String userId) {
		
		return userDao.usingUser(userId);
	}

}
