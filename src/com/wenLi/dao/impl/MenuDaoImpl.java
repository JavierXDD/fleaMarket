package com.wenLi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenLi.dao.MenuDao;
import com.wenLi.entity.Menu;
import com.wenLi.entity.User;
import com.wenLi.util.entity.Page;

@Repository
public class MenuDaoImpl implements MenuDao{
    @Resource
    protected SessionFactory sessionFactory;

    //获取一级菜单列表
	@Override
	public Page<Menu> getMenuList(int pc, int ps) {
		Page<Menu> menuList = new Page<Menu>();
		menuList.setPc(pc);
		menuList.setPs(ps);

		String hql = "from Menu as menu where menu.parentsId = '0'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		menuList.setTr(tr);
		
		hql = "from Menu as menu where menu.parentsId = '0' "
				+ "order by menu.menuOrder  ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Menu> beanList = query.list();
		menuList.setBeanList(beanList);
		
		session.flush();      
        
		
		return menuList;
	}

	
	//根据id查板块
	@Override
	public Menu findMenuById(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session
				.createQuery("from Menu as menu where menu.id='"
						+ id + "'");
		Menu menu = (Menu) query.uniqueResult();
		session.flush();      
        
		return menu;
	}


	//保存修改
	@Override
	public boolean saveOrUpdateMenu(Menu menu) {
		boolean isok = false;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(menu);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}       
        
        
        return isok;
	}


	//获取最大序号
	@Override
	public Integer getMaxOrder() {
		String hql = "from Menu as menu where 1=1";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		return query.list().size()+1;
	}


	//禁用
	@Override
	public boolean forbiddenMenu(String menuId) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "UPDATE from Menu menu SET menu.isUse = 1 where menu.id ='"+ menuId +"'";
			session.createQuery(sql).executeUpdate();
			
			session.flush(); 
			return true;
		}catch(Exception e){
			return false;
		}
	}

	//启用
	@Override
	public boolean usingMenu(String menuId) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "UPDATE from Menu menu SET menu.isUse = 0 where menu.id ='"+ menuId +"'";
			session.createQuery(sql).executeUpdate();			
			session.flush(); 
			return true;
		}catch(Exception e){
			return false;
		}
	}


	//获取子菜单列表
	@Override
	public Page<Menu> getSonMenuList(int pc, int ps, String pId) {
		Page<Menu> menuList = new Page<Menu>();
		menuList.setPc(pc);
		menuList.setPs(ps);

		String hql = "from Menu as menu where menu.parentsId = '"+pId+"'";
		Session session = sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery(hql);
		int tr = query.list().size();
		menuList.setTr(tr);
		
		hql = "from Menu as menu where menu.parentsId = '"+pId+"' "
				+ "order by menu.menuOrder  ";
		query = (Query) session.createQuery(hql);
		query.setFirstResult((pc - 1) * ps);    	
		query.setMaxResults(ps);  
		List<Menu> beanList = query.list();
		menuList.setBeanList(beanList);
		
		session.flush(); 
		return menuList;
	}


	//保存子菜单
	@Override
	public boolean saveOrUpdateSonMenu(Menu menu) {
		boolean isok = false;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(menu);	
			session.flush(); 
			isok = true;
		}catch(Exception e){
			isok = false;
		}       
		return isok;
	}


	//在首页展示一级菜单
	@Override
	public boolean showMenu(String menuId) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "UPDATE from Menu menu SET menu.isShow = 0 where menu.id ='"+ menuId +"'";
			session.createQuery(sql).executeUpdate();			
			session.flush(); 
			return true;
		}catch(Exception e){
			return false;
		}
	}


	//不展示一级菜单
	@Override
	public boolean notShowMenu(String menuId) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "UPDATE from Menu menu SET menu.isShow = 1 where menu.id ='"+ menuId +"'";
			session.createQuery(sql).executeUpdate();			
			session.flush(); 
			return true;
		}catch(Exception e){
			return false;
		}
	}


	@Override
	public List<Menu> findUsingMenu() {
		String hql = "from Menu as menu where menu.isUse = 0 and menu.parentsId = '0'";
		Session session = sessionFactory.getCurrentSession();		
		Query query = (Query) session.createQuery(hql);
		List<Menu> menuList = query.list();
		return menuList;
	}


	@Override
	public List<Menu> findSonMenuByPid(String menuId) {
		String hql = "from Menu as menu where menu.isUse = 0 and menu.parentsId = '"+menuId+"'";
		Session session = sessionFactory.getCurrentSession();		
		Query query = (Query) session.createQuery(hql);
		List<Menu> menuList = query.list();
		return menuList;
	}


	@Override
	public List<Menu> findAllSonMenuList() {
		String hql = "from Menu as menu where menu.isUse = 0 and menu.parentsId != '0'";
		Session session = sessionFactory.getCurrentSession();		
		Query query = (Query) session.createQuery(hql);
		List<Menu> menuList = query.list();
		return menuList;
	}
}
