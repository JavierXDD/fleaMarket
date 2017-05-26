package com.wenLi.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenLi.dao.MessageDao;
import com.wenLi.entity.Message;
import com.wenLi.util.entity.Page;

@Repository
public class MessageDaoImpl implements MessageDao{
    @Resource
    protected SessionFactory sessionFactory;

	@Override
	public Page<Message> findMessageByBlogId(int pc,int ps,String id) {
		Page<Message> massageList = new Page<Message>();
		massageList.setPc(pc);
		massageList.setPs(ps);

		String hql = "from Message as message where message.blogId = '"+id+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		massageList.setTr(tr);
		
		hql = "from Message as message where message.blogId = '"+id+"'"
				+ "order by message.sendTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		@SuppressWarnings("unchecked")
		List<Message> beanList = query.list();
		massageList.setBeanList(beanList);
		
		session.flush();      
        
		
		return massageList;
	}

	@Override
	public Page<Message> findAllMessage(int pc,int ps) {
		Page<Message> massageList = new Page<Message>();
		massageList.setPc(pc);
		massageList.setPs(ps);

		String hql = "from Message as message where 1=1";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		massageList.setTr(tr);
		
		hql = "from Message as message where 1=1"
				+ "order by message.sendTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		@SuppressWarnings("unchecked")
		List<Message> beanList = query.list();
		massageList.setBeanList(beanList);
		
		session.flush();      
		
		return massageList;
	}

	@Override
	public Message findMessageById(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from Message as message where message.id='"
						+ id + "'");
		Message message = (Message) query.uniqueResult();
		session.flush();      	
		return message;
	}

	@Override
	public boolean saveMessage(Message message) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String sendTime = dateFormat.format( new Date() ); 
		message.setSendTime(sendTime);
		
		boolean isok = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(message);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}              
        return isok;
	}

	@Override
	public boolean deleteMessage(Message message) {
		
		boolean isok = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(message);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}              
        return isok;
	}

	@Override
	public Page<Message> findAllMessageByUserId(int pc, int ps, String id) {
		Page<Message> massageList = new Page<Message>();
		massageList.setPc(pc);
		massageList.setPs(ps);

		String hql = "from Message as message where message.senderId='"+id+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		massageList.setTr(tr);
		
		hql = "from Message as message where message.senderId='"+id+"'"
				+ "order by message.sendTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		@SuppressWarnings("unchecked")
		List<Message> beanList = query.list();
		massageList.setBeanList(beanList);
		
		session.flush();      
		
		return massageList;
	}

	@Override
	public Page<Message> findReplyMessageByUserId(int pc, int ps, String id) {
		Page<Message> massageList = new Page<Message>();
		massageList.setPc(pc);
		massageList.setPs(ps);

		String hql = "from Message as message where message.receiverId='"+id+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		massageList.setTr(tr);
		
		hql = "from Message as message where message.receiverId='"+id+"'"
				+ "order by message.sendTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		@SuppressWarnings("unchecked")
		List<Message> beanList = query.list();
		massageList.setBeanList(beanList);
		
		session.flush();      
		
		return massageList;
	}
}
