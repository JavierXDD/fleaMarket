package com.wenLi.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenLi.controller.MenuController;
import com.wenLi.dao.MenuDao;
import com.wenLi.entity.Menu;
import com.wenLi.service.MenuService;
import com.wenLi.util.entity.Page;

/**
 * 菜单管理
 * @author xjw
 *
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDao menuDao;

	//获取菜单列表
	@Override
	public Page<Menu> getMenuList(int pc, int ps) {
		Page<Menu> menuList = menuDao.getMenuList(pc,ps);
		return menuList;
	}

	//根据id查板块
	@Override
	public Menu findMenuById(String id) {
		Menu menu = menuDao.findMenuById(id);
		return menu;
	}

	
	//保存、修改板块（一级菜单）
	@Override
	public boolean saveOrUpdateMenu(Menu menu) {
		menu.setParentsId("0");
		return menuDao.saveOrUpdateMenu(menu);
	}

	//获取最大序号
	@Override
	public Integer getMaxOrder() {
		
		return menuDao.getMaxOrder();
	}

	//禁用
	@Override
	public boolean forbiddenMenu(String menuId) {
		
		return menuDao.forbiddenMenu(menuId);
	}

	//启用
	@Override
	public boolean usingMenu(String menuId) {
		
		return menuDao.usingMenu(menuId);
	}

	//获取子菜单列表
	@Override
	public Page<Menu> getSonMenuList(int pc, int ps, String pId) {
		
		return menuDao.getSonMenuList(pc,ps,pId);
	}

	
	//保存子菜单
	@Override
	public boolean saveOrUpdateSonMenu(Menu menu) {
		
		return menuDao.saveOrUpdateSonMenu(menu);
	}

	//在首页展示一级菜单
	@Override
	public boolean showMenu(String menuId) {
		return menuDao.showMenu(menuId);
	}

	//不展示一级菜单
	@Override
	public boolean notShowMenu(String menuId) {
		return menuDao.notShowMenu(menuId);
	}

	@Override
	public List<Menu> findUsingMenu() {
		return menuDao.findUsingMenu();
	}

	@Override
	public List<Menu> findSonMenuByPid(String menuId) {
		return menuDao.findSonMenuByPid(menuId);
	}

	@Override
	public List<Menu> findAllSonMenuList() {
		return menuDao.findAllSonMenuList();
	}


}
