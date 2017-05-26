package com.wenLi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wenLi.entity.IndexPicModel;
import com.wenLi.entity.Menu;
import com.wenLi.entity.User;
import com.wenLi.entity.UserRecord;
import com.wenLi.service.IndexPicModelService;
import com.wenLi.service.MenuService;
import com.wenLi.service.UserRecordService;
import com.wenLi.service.UserService;
import com.wenLi.util.entity.Page;

/**
 * 用户控制
 * @author think
 *
 */
@Controller
@SessionAttributes("isLogin")
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private IndexPicModelService indexPicModelService;
	@Resource
	private MenuService menuService;
	@Resource
	private UserRecordService userRecordService;
	
	
	//跳转到登录
    @RequestMapping(value="/toLogin")
    public String toLogin(Model model){
    	
    	return "/jsp/login";
    }
    
    //跳转到首页
    @RequestMapping(value="/toIndexDoor")
    public String toIndexDoor(Model model){
    	//首页图
    	List<IndexPicModel> indexPicList = indexPicModelService.getUsingIndexPicList();
    	model.addAttribute("indexPicList", indexPicList);
    	
    	//板块
    	List<Menu> menuList = menuService.findUsingMenu();
    	model.addAttribute("menuList", menuList);
    	return "/jsp/indexDoor";
    } 
    
    //登录
    @ResponseBody
    @RequestMapping(value="/login")
    public String userLogin(Model model,
    		@RequestParam(value = "nickname", required = true) String nickname,
    		@RequestParam(value = "password", required = true) String password){
    	User user = userService.userLogin( nickname, password);
    	if(user != null){
    		if(user.getIsUse() == 1){
    			return "notUse";
    		}else{
        		model.addAttribute("isLogin", user);
        		return "success";    			
    		}
    	}else {
    		return "defeat";
    	}
    	
    }   
    

    //注册
    @RequestMapping(value="/register")
    public String userRegister(@ModelAttribute("user") User user,Model model){
    	
    	boolean isok = userService.saveUser(user);
    	if(isok){
    		model.addAttribute("isLogin", user);
    	}
    	
    	return this.toIndexDoor(model);
    }   
    
    //检查注册名是否被使用
    @ResponseBody
    @RequestMapping(value="/nicknameCan")
    public boolean nicknameCan(String nickname){
    	
    	User user = userService.findUserByNickname(nickname);
    	if(user != null){
    		return false;
    	}else{
    		return true;
    	}
    	
    }
    
    
    //注销
    @RequestMapping(value="/loginOut")
    public String loginOut(@ModelAttribute("isLogin")User user,Model model){
    	model.addAttribute("isLogin", "");
    	return this.toIndexDoor(model);    	
    }
    

    
    //跳转到管理员列表
    @RequestMapping(value = "/toAdminList")
    public String toAdminList(Model model){
    	Page<User> adminList = userService.getAdmin(1 ,5);
    	model.addAttribute("adminList", adminList);
    	return "/jsp/manager/user/adminList";    	
    }   
    
    //获取管理员列表
	@RequestMapping(value="/getAdminList")
	public String getAdminList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode) {
		int pc = Integer.parseInt(pageCode);
		Page<User> adminList = userService.getAdmin(pc ,5);
		model.addAttribute("adminList", adminList);
		return "/jsp/manager/user/adminList";
	}
	
	//跳转到用户详情页
	@RequestMapping(value="/toUserDetail")
	public String toUserDetail(Model model,
			@RequestParam(value = "id", required = true) String id) {
		User admin = userService.getUserById( id);
		List<UserRecord> recordList = userRecordService.findRecordByUserId(id);
		model.addAttribute("recordList", recordList);
		model.addAttribute("admin", admin);
		return "/jsp/manager/user/userDetail";
	}
	
	//跳转到用户编辑、新建页
	@RequestMapping(value="/toUserEdit")
	public String toUserEdit(Model model,
			@RequestParam(value = "id", required = false) String id) {
		if(id != null){
			User admin = userService.getUserById(id);		
			model.addAttribute("admin", admin);
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String createTime = dateFormat.format( new Date() ); 
			
			User user = new User();
			user.setCreateTime(createTime);
			model.addAttribute("admin", user);
		}

		return "/jsp/manager/user/userEdit";
	}	
	
	//新建、更新用户
	@RequestMapping(value="/saveOrUpdateUser")
	public String saveOrUpdateUser(Model model,
			@ModelAttribute("user") User user) {
		boolean isok = userService.saveOrUpdateUser(user);
		if(isok){
			model.addAttribute("msg", "保存成功");
		}else{
			model.addAttribute("msg", "保存失败");
		}
		if(user.getIsManager() == 1){
			return this.toAdminList(model);
		}else{
			return this.toUserList(model);
		}
		//return "/jsp/manager/user/userEdit";
	}
	
    //跳转到用户列表
    @RequestMapping(value = "/toUserList")
    public String toUserList(Model model){
    	Page<User> userList = userService.getAllUsers(1 ,5);
    	model.addAttribute("userList", userList);
    	return "/jsp/manager/user/userList";    	
    }   
    
    //获取用户列表
	@RequestMapping(value="/getUserList")
	public String getUserList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode) {
		int pc = Integer.parseInt(pageCode);
		Page<User> userList = userService.getAllUsers(pc ,5);
		model.addAttribute("userList", userList);
		return "/jsp/manager/user/userList";
	}
	
	//跳转到个人中心-我的信息
	@RequestMapping(value="/toPersonInfo")
	public String toPersonInfo(Model model,
			@RequestParam(value = "id", required = true) String id) {

		User user = userService.getUserById(id);
		List<UserRecord> recordList = userRecordService.findRecordByUserId(id);
		model.addAttribute("recordList", recordList);
		model.addAttribute("user", user);
		return "/jsp/personInfo/personInfo";
	}	
	
	//跳转到个人中心-编辑我的信息
	@RequestMapping(value="/toPersonEdit")
	public String toPersonEdit(Model model,
			@RequestParam(value = "id", required = true) String id) {

		User user = userService.getUserById(id);
		model.addAttribute("person", user);
		return "/jsp/personInfo/personEdit";
	}
	
	//跳转到个人中心-编辑我的信息
	@RequestMapping(value="/saveOrUpdatePerson")
	public String saveOrUpdatePerson(Model model,
			@ModelAttribute("user") User user) {

		boolean isok = userService.saveOrUpdateUser(user);
		if(isok){
			model.addAttribute("msg", "保存成功");
		}else{
			model.addAttribute("msg", "保存失败");
		}		
		return this.toPersonInfo(model, user.getId());
	}
	
	
    //改密码
    @ResponseBody
    @RequestMapping(value="/changePassword")
    public String changePassword(@RequestParam(value = "userId", required = true) String userId,
    		@RequestParam(value = "oldPassword", required = true) String oldPassword,
    		@RequestParam(value = "newPassword", required = true) String newPassword){
    	
    	User user = userService.getUserById(userId);
    	if(user.getPassword().equals(oldPassword)){
    		user.setPassword(newPassword);
    		if(userService.saveOrUpdateUser(user)){
    			return "success";
    		}else{
    			return "error";
    		}
    	}else{
    		return "oldPasswordError";
    	}

    }
}
