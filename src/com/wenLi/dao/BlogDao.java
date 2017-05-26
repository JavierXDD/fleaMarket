package com.wenLi.dao;

import java.util.List;

import com.wenLi.entity.Blog;
import com.wenLi.util.entity.Page;

public interface BlogDao {

	Page<Blog> getBlogListByUid(int i, int j, String id);

	Blog findBlogById(String id);

	boolean saveOrUpdateMyBlog(Blog blog);

	Page<Blog> getAllBlogList(int i, int j);

	Page<Blog> getUsingBlogList(int i, int j);

	Page<Blog> getUncheckBlogList(int i, int j);

	Page<Blog> getUseingBlogByTypeId(int pc, int ps, String typeId);

	Page<Blog> getUseingBlogByMenuId(Integer pageCode, int i, String menuId);

	List<Blog> findNewBlog(Integer count);

	boolean closeBlog(Blog blog);

	boolean passBlog(Blog blog);

	boolean deleteBlog(Blog blog);

	

}
