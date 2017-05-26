package com.wenLi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wenLi.entity.Blog;
import com.wenLi.entity.IndexPicModel;
import com.wenLi.entity.Menu;
import com.wenLi.service.BlogService;
import com.wenLi.service.IndexPicModelService;
import com.wenLi.service.MenuService;
import com.wenLi.util.entity.Page;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(value="isLogin")
public class WelcomController {
	@Resource
	private MenuService menuService;
	@Resource
	private BlogService blogService;
	@Resource
	private IndexPicModelService indexPicModelService;
	
	//跳转到首页
    @RequestMapping(value="/")
    public String welcome(Model model){

    	//首页图
    	List<IndexPicModel> indexPicList = indexPicModelService.getUsingIndexPicList();
    	model.addAttribute("indexPicList", indexPicList);
    	
    	//板块
    	List<Menu> menuList = menuService.findUsingMenu();
    	model.addAttribute("menuList", menuList);
    	return "/jsp/indexDoor";
    }
    
    //跳转到后台首页
    @RequestMapping(value = "/toManagerIndex")
    public String toManagerIndex(Model model){
    	return "/jsp/manager/managerIndex";    	
    }
    
    //转到分类列表-全部
    @RequestMapping(value = "/toListByType")
    public String toListByType(Model model,
    		@RequestParam(value = "menuId", required = true) String menuId,
    		@RequestParam(value = "pc", required = true) String pc){
    	List<Menu> typeList = menuService.findSonMenuByPid(menuId);
    	Integer pageCode = Integer.parseInt(pc);
    	Page<Blog> blogList = blogService.getUseingBlogByMenuId(pageCode,6,menuId);
    	
    	model.addAttribute("blogList", blogList);
    	model.addAttribute("typeList", typeList);
    	Menu chooseMenu = menuService.findMenuById(menuId);
    	model.addAttribute("chooseMenu", chooseMenu);
    	return "/jsp/list";    	
    }
    
    //获取分类列表-各类
    @RequestMapping(value = "/getListByType")
    public String getListByType(Model model,
    		@RequestParam(value = "typeId", required = true) String typeId,
    		@RequestParam(value = "pc", required = true) String pc){
    	Menu chooseType = menuService.findMenuById(typeId);
    	Menu chooseMenu = menuService.findMenuById(chooseType.getParentsId());
    	List<Menu> typeList = menuService.findSonMenuByPid(chooseType.getParentsId());    	   	
    	Integer pageCode = Integer.parseInt(pc);
    	Page<Blog> blogList = blogService.getUseingBlogByTypeId(pageCode,6,typeId);
    	
    	model.addAttribute("typeList", typeList);
    	model.addAttribute("blogList", blogList);
    	model.addAttribute("chooseMenu", chooseMenu);
    	model.addAttribute("chooseType", chooseType);
    	return "/jsp/list";    	
    } 
    
    //用户信息统计
    @RequestMapping(value = "/toUserAnalysis")
    public String toUserAnalysis(Model model){

    	return "/jsp/fr/userAnalysis";    	
    } 
    //交易信息统计
    @RequestMapping(value = "/toBlogAnalysis")
    public String toBlogAnalysis(Model model){

    	return "/jsp/fr/blogAnalysis";    	
    } 
    //评论统计
    @RequestMapping(value = "/toMessageAnalysis")
    public String toMessageAnalysis(Model model){

    	return "/jsp/fr/messageAnalysis";    	
    } 
    
    //评论统计
    @RequestMapping(value = "/toHelp")
    public String toHelp(Model model){

    	return "/jsp/help";    	
    } 
}
