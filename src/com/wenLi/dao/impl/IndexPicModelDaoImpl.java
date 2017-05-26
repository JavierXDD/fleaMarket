package com.wenLi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenLi.dao.IndexPicModelDao;
import com.wenLi.entity.IndexPicModel;
import com.wenLi.util.entity.Page;
@Repository
public class IndexPicModelDaoImpl implements IndexPicModelDao{
    @Resource
    protected SessionFactory sessionFactory;

	@Override
	public Page<IndexPicModel> getIndexPicList(int pc, int ps) {
		Page<IndexPicModel> indexPicList = new Page<IndexPicModel>();
		indexPicList.setPc(pc);
		indexPicList.setPs(ps);

		String hql = "from IndexPicModel as indexPic where 1=1";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		indexPicList.setTr(tr);
		
		hql = "from IndexPicModel as indexPic where 1=1 ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<IndexPicModel> beanList = query.list();
		indexPicList.setBeanList(beanList);
		
		session.flush();      
        
		
		return indexPicList;
	}

	@Override
	public IndexPicModel findIndexPicById(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from IndexPicModel as indexPic where indexPic.id='"
						+ id + "'");
		IndexPicModel indexPic = (IndexPicModel) query.uniqueResult();
		session.flush();      
        
		return indexPic;
	}

	
	@Override
	public boolean saveOrUpdateIndexPic(IndexPicModel indexPic) {
		boolean isok = false;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(indexPic);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}          
        return isok;
	}

	@Override
	public boolean forbiddenIndexPic(String id) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "UPDATE from IndexPicModel indexPic SET indexPic.isUse = 1 where indexPic.id ='"+ id +"'";
			session.createQuery(sql).executeUpdate();
			
			session.flush(); 
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean usingIndexPic(String id) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "UPDATE from IndexPicModel indexPic SET indexPic.isUse = 0 where indexPic.id ='"+ id +"'";
			session.createQuery(sql).executeUpdate();
			
			session.flush(); 
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Integer getMaxOrder() {
		String hql = "from IndexPicModel as indexPic where 1=1";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		return query.list().size()+1;
		
	}

	@Override
	public List<IndexPicModel> getUsingIndexPicList() {
		String hql = "from IndexPicModel as indexPic where indexPic.isUse=0";
		Session session = sessionFactory.getCurrentSession();
		Query query = (Query) session.createQuery(hql);
		List<IndexPicModel> indexPicList = query.list();
		return indexPicList;
	}
}
