package com.wenLi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenLi.dao.BlogDao;
import com.wenLi.dao.IndexPicModelDao;
import com.wenLi.dao.MenuDao;
import com.wenLi.entity.Blog;
import com.wenLi.entity.Menu;
import com.wenLi.service.BlogService;
import com.wenLi.util.entity.Page;
@Service
@Transactional
public class BlogServiceImpl implements BlogService{
	@Resource
	private BlogDao  blogDao;
	@Resource
	private MenuDao  menuDao;

	@Override
	public Page<Blog> getBlogListByUid(int i, int j, String id) {
		
		return blogDao.getBlogListByUid(i,  j, id);
	}

	@Override
	public Blog findBlogById(String id) {
		return blogDao.findBlogById(id);
	}

	@Override
	public boolean saveOrUpdateMyBlog(Blog blog) {
		if(blog.getMenuId() != "" && blog.getMenuId() != null){
			Menu menu = menuDao.findMenuById(blog.getMenuId());
			blog.setMenuName(menu.getName());
			if(blog.getIsUse()==1){
				blog.setIsUse(0);
			}
		}
		if(blog.getTypeId() != "" && blog.getTypeId() != null){
			Menu type = menuDao.findMenuById(blog.getTypeId());
			blog.setTypeName(type.getName());			
		}

		return blogDao.saveOrUpdateMyBlog(blog);
	}

	@Override
	public Page<Blog> getAllBlogList(int i, int j) {
		return blogDao.getAllBlogList(i,j);
	}

	@Override
	public Page<Blog> getUsingBlogList(int i, int j) {
		return blogDao.getUsingBlogList(i,j);
	}

	@Override
	public Page<Blog> getUncheckBlogList(int i, int j) {
		return blogDao.getUncheckBlogList(i,j);
	}

	@Override
	public Page<Blog> getUseingBlogByTypeId(int pc, int ps, String typeId) {
		return blogDao.getUseingBlogByTypeId(pc,ps,typeId);
	}

	@Override
	public Page<Blog> getUseingBlogByMenuId(Integer pageCode, int i, String menuId) {
		return blogDao.getUseingBlogByMenuId(pageCode,i,menuId);
	}

	@Override
	public List<Blog> findNewBlog(Integer count) {
		return blogDao.findNewBlog(count);
	}

	@Override
	public boolean closeBlog(Blog blog) {
		return blogDao.closeBlog(blog);
	}

	@Override
	public boolean passBlog(Blog blog) {
		return blogDao.passBlog(blog);
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		return blogDao.deleteBlog(blog);
	}


}
