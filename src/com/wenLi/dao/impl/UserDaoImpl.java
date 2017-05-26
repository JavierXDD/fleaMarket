package com.wenLi.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenLi.dao.UserDao;
import com.wenLi.entity.User;
import com.wenLi.util.entity.Page;

@Repository
public class UserDaoImpl implements UserDao{

    @Resource
    protected SessionFactory sessionFactory;

    //登录
    @Override
    public User userLogin(String nickname,String password){
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from User as user where user.nickname='"
						+ nickname + "' and user.password = '"+ password + "'");
		User user = (User) query.uniqueResult();
		session.flush();      
        
		
		return user;
    }
    
    
	//保存、注册用户
	@Override
	public boolean saveUser(User user) {
	
      
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String createTime = dateFormat.format( new Date() ); 
		user.setCreateTime(createTime);
		user.setUpdateTime(new Date());
		user.setIsManager(0);
		user.setIsUse(0);
		
		boolean isok = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(user);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}       
        
        
        return isok;
	
	}
	
	//根据用户名查用户
	@Override
	public User findUserByNickname (String nickname){
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from User as user where user.nickname='"
						+ nickname + "'");
		User user = (User) query.uniqueResult();
		session.flush();      
        
		
		return user;
	}

	//获取所有管理员
	@Override
	public Page<User> getAdmin(int pc,int ps) {
		
		Page<User> userList = new Page<User>();
		userList.setPc(pc);
		userList.setPs(ps);

		String hql = "from User as user where user.isManager= 1 or user.isManager = 2";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		userList.setTr(tr);
		
		hql = "from User as user where user.isManager= 1 or user.isManager = 2"
				+ "order by user.updateTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		@SuppressWarnings("unchecked")
		List<User> beanList = query.list();
		userList.setBeanList(beanList);
		
		session.flush();      
        
		
		return userList;
	}


	//根据id查用户
	@Override
	public User getUserById(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from User as user where user.id='"
						+ id + "'");
		User user = (User) query.uniqueResult();
		session.flush();      
        
		
		return user;
	}


	//获取所有用户
	@Override
	public Page<User> getAllUser(int pc, int ps) {
		Page<User> userList = new Page<User>();
		userList.setPc(pc);
		userList.setPs(ps);

		String hql = "from User as user where user.isManager= 0 ";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		userList.setTr(tr);
		
		hql = "from User as user where user.isManager= 0"
				+ "order by user.updateTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		@SuppressWarnings("unchecked")
		List<User> beanList = query.list();
		userList.setBeanList(beanList);
		
		session.flush();      
        
		
		return userList;
	}


	//后台创建或更新用户
	@Override
	public boolean saveOrUpdateUser(User user) {
		user.setUpdateTime(new Date());
		boolean isok = false;
	
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(user);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}       
        
        
        return isok;
	}


	//禁用用户
	@Override
	public User forbiddenUser(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "UPDATE from User user SET user.isUse = 1 where user.id ='"+ id +"'";
		session.createQuery(sql).executeUpdate();
		
		Query query = (Query) session
				.createQuery("from User as user where user.id='"
						+ id + "'");
		User user = (User) query.uniqueResult();
		session.flush();      
		
		return user;
	}


	//启用用户
	@Override
	public User usingUser(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "UPDATE from User user SET user.isUse = 0 where user.id ='"+ id +"'";
		session.createQuery(sql).executeUpdate();
		
		Query query = (Query) session
				.createQuery("from User as user where user.id='"
						+ id + "'");
		User user = (User) query.uniqueResult();
		session.flush();      
		
		return user;		
	}

}
