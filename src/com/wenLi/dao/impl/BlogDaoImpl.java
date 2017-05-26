package com.wenLi.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenLi.dao.BlogDao;
import com.wenLi.entity.Blog;
import com.wenLi.entity.IndexPicModel;
import com.wenLi.util.entity.Page;
@Repository
public class BlogDaoImpl implements BlogDao{
    @Resource
    protected SessionFactory sessionFactory;

	@Override
	public Page<Blog> getBlogListByUid(int pc, int ps, String id) {
		Page<Blog> blogList = new Page<Blog>();
		blogList.setPc(pc);
		blogList.setPs(ps);

		String hql = "from Blog as blog where blog.userId ='"+id+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		blogList.setTr(tr);
		
		hql = "from Blog as blog where blog.userId ='"+id+"'"
				+ " order by blog.modifyTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Blog> beanList = query.list();
		blogList.setBeanList(beanList);
		
		session.flush();      
        
		
		return blogList;
	}

	@Override
	public Blog findBlogById(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from Blog as blog where blog.id='"
						+ id + "'");
		Blog blog = (Blog) query.uniqueResult();
		session.flush();
		return blog;
	}

	@Override
	public boolean saveOrUpdateMyBlog(Blog blog) {
		boolean isok = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String modifyTime = dateFormat.format(  new Date() ); 	
		blog.setModifyTime(modifyTime);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(blog);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}          
        return isok;

	}

	@Override
	public Page<Blog> getAllBlogList(int pc, int ps) {
		Page<Blog> blogList = new Page<Blog>();
		blogList.setPc(pc);
		blogList.setPs(ps);

		String hql = "from Blog as blog where 1=1 ";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		blogList.setTr(tr);
		
		hql = "from Blog as blog where 1=1 "
				+ " order by blog.modifyTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Blog> beanList = query.list();
		blogList.setBeanList(beanList);
		
		session.flush();      
	
		return blogList;
	}

	@Override
	public Page<Blog> getUsingBlogList(int pc, int ps) {
		Page<Blog> blogList = new Page<Blog>();
		blogList.setPc(pc);
		blogList.setPs(ps);

		String hql = "from Blog as blog where blog.isUse = 1 or blog.isUse = 0";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		blogList.setTr(tr);
		
		hql = "from Blog as blog where blog.isUse = 1 or blog.isUse = 0"
				+ " order by blog.modifyTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Blog> beanList = query.list();
		blogList.setBeanList(beanList);
		
		session.flush();      
	
		return blogList;
	}

	@Override
	public Page<Blog> getUncheckBlogList(int pc, int ps) {
		Page<Blog> blogList = new Page<Blog>();
		blogList.setPc(pc);
		blogList.setPs(ps);

		String hql = "from Blog as blog where blog.isUse = 0 or blog.isUse = 2";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		blogList.setTr(tr);
		
		hql = "from Blog as blog where blog.isUse = 0 or blog.isUse = 2"
				+ " order by blog.modifyTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Blog> beanList = query.list();
		blogList.setBeanList(beanList);
		
		session.flush();      
	
		return blogList;
	}

	@Override
	public Page<Blog> getUseingBlogByTypeId(int pc, int ps, String typeId) {
		Page<Blog> blogList = new Page<Blog>();
		blogList.setPc(pc);
		blogList.setPs(ps);

		String hql = "from Blog as blog where (blog.isUse = 1 or blog.isUse = 0) and blog.typeId='"+typeId+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		blogList.setTr(tr);
		
		hql = "from Blog as blog where (blog.isUse = 1 or blog.isUse = 0) and blog.typeId='"+typeId+"'"
				+ " order by blog.modifyTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Blog> beanList = query.list();
		blogList.setBeanList(beanList);
		
		session.flush();      
	
		return blogList;
	}

	@Override
	public Page<Blog> getUseingBlogByMenuId(Integer pc, int ps, String menuId) {
		Page<Blog> blogList = new Page<Blog>();
		blogList.setPc(pc);
		blogList.setPs(ps);

		String hql = "from Blog as blog where (blog.isUse = 1 or blog.isUse = 0) and blog.menuId='"+menuId+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		blogList.setTr(tr);
		
		hql = "from Blog as blog where (blog.isUse = 1 or blog.isUse = 0) and blog.menuId='"+menuId+"'"
				+ " order by blog.modifyTime desc ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Blog> beanList = query.list();
		blogList.setBeanList(beanList);
		
		session.flush();      
	
		return blogList;
	}

	@Override
	public List<Blog> findNewBlog(Integer count) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from Blog as blog where 1=1 order by blog.createTime desc");
		query.setFirstResult(0);    	
		query.setMaxResults(count);
		List<Blog> blogList = query.list();
		session.flush();
		return blogList;
	}

	@Override
	public boolean closeBlog(Blog blog) {
		boolean isok = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String modifyTime = dateFormat.format( new Date() ); 	
		blog.setModifyTime(modifyTime);
		blog.setIsUse(2);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(blog);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}          
        return isok;
	}

	@Override
	public boolean passBlog(Blog blog) {
		boolean isok = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String modifyTime = dateFormat.format( new Date() ); 	
		blog.setModifyTime(modifyTime);
		blog.setIsUse(1);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(blog);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}          
        return isok;
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		boolean isok = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(blog);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}          
        return isok;
	}

	


}
