package com.wenLi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenLi.dao.UserRecordDao;
import com.wenLi.entity.Blog;
import com.wenLi.entity.UserRecord;

/**
 * 用户操作记录
 * @author xjw
 *
 */
@Repository
public class UserRecordDaoImpl implements UserRecordDao {

	 @Resource
	 protected SessionFactory sessionFactory;
	
	//禁用
	@Override
	public boolean saveRecord(UserRecord userRecord) {
		boolean isok = false;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(userRecord);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}  
		return isok;
	}

	@Override
	public List<UserRecord> findRecordByUserId(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from UserRecord as ur where ur.userId='"+id+"' order by ur.recordTime desc");
		List<UserRecord> recordList = query.list();
		session.flush();
		return recordList;
	}

}
