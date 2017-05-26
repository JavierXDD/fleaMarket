package com.wenLi.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wenLi.entity.Menu;
import com.wenLi.service.MenuService;
import com.wenLi.util.entity.Page;


/**
 * 板块菜单控制
 * @author xjw
 *
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	
	//跳转到板块列表
    @RequestMapping(value="/toMenuList")
    public String toMenuList(Model model){
    	Page<Menu> menuList = menuService.getMenuList(1 ,5);
    	model.addAttribute("menuList", menuList);
    	return "/jsp/manager/menu/menuList";
    }
    
	//跳转到板块详情页
	@RequestMapping(value="/toMenuDetail")
	public String toMenuDetail(Model model,
			@RequestParam(value = "id", required = true) String id) {
		Menu menu = menuService.findMenuById( id);		
		model.addAttribute("menu", menu);
		return "/jsp/manager/menu/menuDetail";
	}
	
	//跳转到板块编辑页
	@RequestMapping(value="/toMenuEdit")
	public String toMenuEdit(Model model,
			@RequestParam(value = "id", required = false) String id) {
		if(id != null && id !=""){			
			Menu menu = menuService.findMenuById(id);		
			model.addAttribute("menu", menu);
			model.addAttribute("msg", "编辑");
		}else{
			Integer menuOrder = menuService.getMaxOrder();
			model.addAttribute("msg", "新建");
			model.addAttribute("menuMaxOrder", menuOrder);
		}

		return "/jsp/manager/menu/menuEdit";
	}
	
	//上传板块图片
	@RequestMapping(value="/upMenuPic")
	public String upMenuPic(Model model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		// 添加真实文件
		// 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024 * 1024,
				new File("C:/f/temp"));
		// 解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("utf-8");
		// 解析，得到List
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = sfu.parseRequest(request);
			FileItem fi = list.get(0);
			
			//读取配置文件中的板块配图储存位置
			Properties props = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("/properties/other.properties");			
			props.load(in);
			String menuPicUrl = props.getProperty("menuPicUrl");
			
			// 1. 得到文件保存的路径
			String root = request.getServletContext().getRealPath("media/"+menuPicUrl);
			// 2. 生成二层目录 1). 得到文件名称 2). 得到hashCode 3). 转发成16进制 4). 获取前二个字符用来生成目录
			String filename = URLEncoder.encode(fi.getName(), "utf-8");// 获取上传的文件名称
			// 处理文件名的绝对路径问题
			int index = filename.lastIndexOf("\\");
			if (index != -1) {
				filename = filename.substring(index + 1);
			}
			// 给文件名称添加uuid前缀，处理文件同名问题
			String savename =  filename;
			// 1. 得到hashCode
			int hCode = filename.hashCode();
			String hex = Integer.toHexString(hCode);
			// 2. 获取hex的前两个字母，与root连接在一起，生成一个完整的路径
			File dirFile = new File(root, hex.charAt(0) + "/" + hex.charAt(1));
			// 3. 创建目录链
			dirFile.mkdirs();
			// 4. 创建目录文件
			File destFile = new File(dirFile, savename);
			// 5. 保存
			fi.write(destFile);

			String allUrl = dirFile + "\\" +savename;
			String[] splitStr=allUrl.split("\\\\");			
			String picUrl = splitStr[8]+"\\"+splitStr[9]+"\\"+splitStr[10]+"\\"+splitStr[11]+"\\"+splitStr[12];									
			
			String menuId = list.get(1).getString("utf-8");
			String name = list.get(2).getString("utf-8");
			String url = list.get(3).getString("utf-8");
			Integer menuOrder = Integer.parseInt(list.get(4).getString("utf-8"));
			Integer isUse = Integer.parseInt(list.get(5).getString("utf-8"));
			Integer isShow = Integer.parseInt(list.get(6).getString("utf-8"));
			String text = list.get(7).getString("utf-8");
			String thumbnailUrl = list.get(8).getString("utf-8");
			
			Menu menu = new Menu();
			menu.setId(menuId);
			menu.setIsShow(isShow);
			menu.setIsUse(isUse);
			menu.setMenuOrder(menuOrder);
			menu.setName(name);
			menu.setPicUrl(picUrl);
			menu.setText(text);
			menu.setUrl(url);
			menu.setParentsId("0");
			menu.setThumbnailUrl(thumbnailUrl);
			model.addAttribute("menu", menu);
			model.addAttribute("menuMaxOrder", menuOrder);
			
		} catch (FileUploadException e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了100KB！");
				return "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "/jsp/manager/menu/menuEdit";
	}
	
	
	//新建、更新板块
	@RequestMapping(value="/saveOrUpdateMenu")
	public String saveOrUpdateMenu(Model model,
			@ModelAttribute("menu") Menu menu) {
		boolean isok = menuService.saveOrUpdateMenu(menu);
		if(isok){
			model.addAttribute("msg", "保存成功");
			return this.toMenuList(model);
		}else{
			model.addAttribute("msg", "保存失败");
			return this.toMenuList(model);
		}

	}
	
	//禁用板块
	@ResponseBody
    @RequestMapping(value="/forbiddenMenu")
    public String forbiddenMenu(Model model,
    		@RequestParam(value = "menuId", required = true) String menuId){
    	   	
    	boolean isOk = menuService.forbiddenMenu(menuId);
    	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }
	
	//启用板块
	@ResponseBody
    @RequestMapping(value="/usingMenu")
    public String usingMenu(Model model,
    		@RequestParam(value = "menuId", required = true) String menuId){
    	   	
    	boolean isOk = menuService.usingMenu(menuId);
    	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }
	
	
	//跳转到子菜单管理列表
    @RequestMapping(value="/toSonMenuIndex")
    public String toSonMenuIndex(Model model){
    	Page<Menu> menuList = menuService.getMenuList(1 ,5);
    	model.addAttribute("menuList", menuList);
    	return "/jsp/manager/menu/sonMenuIndex";
    }
    
	//跳转到子菜单列表
    @RequestMapping(value="/toSonMenuList")
    public String toSonMenuList(Model model,
    		@RequestParam(value = "pId", required = true) String pId){
    	Page<Menu> sonMenuList = menuService.getSonMenuList(1 ,5,pId);
    	Menu pMenu = menuService.findMenuById(pId);
    	
    	model.addAttribute("pMneu", pMenu);
    	model.addAttribute("sonMenuList", sonMenuList);
    	return "/jsp/manager/menu/sonMenuList";
    }
	
	//启用子菜单
	@ResponseBody
    @RequestMapping(value="/updateSonMenu")
    public String updateSonMenu(Model model,
    		@RequestParam(value = "parentsId", required = true) String parentsId,
    		@RequestParam(value = "name", required = true) String name,
    		@RequestParam(value = "isUse", required = true) String isUse,
    		@RequestParam(value = "url", required = true) String url,
    		@RequestParam(value = "id", required = false) String id){
    	   
		Menu menu = new Menu();
		if(id != null || id !=""){
			menu.setId(id);
		}
		
		menu.setIsUse(Integer.parseInt(isUse));
		menu.setName(name);
		menu.setParentsId(parentsId);
		menu.setUrl(url);
		
    	boolean isOk = menuService.saveOrUpdateSonMenu(menu);
  	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }
	
	//展示板块
	@ResponseBody
    @RequestMapping(value="/showMenu")
    public String showMenu(Model model,
    		@RequestParam(value = "id", required = true) String menuId){
    	   	
    	boolean isOk = menuService.showMenu(menuId);
    	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }
	
	//展示板块
	@ResponseBody
    @RequestMapping(value="/notShowMenu")
    public String notShowMenu(Model model,
    		@RequestParam(value = "id", required = true) String menuId){
    	   	
    	boolean isOk = menuService.notShowMenu(menuId);
    	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }
	
	//ajax获取二级菜单
	@ResponseBody
    @RequestMapping(value="/chooseType",produces = {"application/json;charset=UTF-8" })
    public String chooseType(Model model,
    		@RequestParam(value = "menuId", required = true) String menuId){
    	   	
    	List<Menu> menuList =  menuService.findSonMenuByPid(menuId);
    	String jsonString = JSON.toJSONString(menuList);
    	
    	return jsonString;    	
    }	
}
