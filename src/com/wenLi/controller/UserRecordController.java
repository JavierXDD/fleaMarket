package com.wenLi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wenLi.entity.User;
import com.wenLi.service.UserRecordService;
import com.wenLi.service.UserService;

/**
 * 用户操作记录控制
 * @author think
 *
 */
@Controller
@SessionAttributes("isLogin")
@RequestMapping(value = "/userRecord")
public class UserRecordController {
	
	@Resource
	private UserRecordService userRecordService;
	@Resource
	private UserService userService;
	
	//禁用用户
	@ResponseBody
    @RequestMapping(value="/forbiddenUser")
    public String forbiddenUser(Model model,
    		@RequestParam(value = "userId", required = true) String userId,
    		@RequestParam(value = "reason", required = false) String reason,
    		@RequestParam(value = "adminId", required = true) String adminId,
    		@RequestParam(value = "adminNickname", required = true) String adminNickname,
    		@RequestParam(value = "userNickname", required = true) String userNickname){
    	
    	String operation = "禁用";
    	boolean isOk = userRecordService.saveRecord(adminId,adminNickname,userId,userNickname,operation,reason);
    	User user =  userService.forbiddenUser(userId);
    	if(isOk && user != null){
    		if(user.getIsManager() == 0){
    			return "success_user";
    		}else{
    			return "success_admin";
    		}
    		
    	}else{
    		return "error";
    	}
    }
	
	//启用用户
	@ResponseBody
    @RequestMapping(value="/usingUser")
    public String usingUser(Model model,
    		@RequestParam(value = "adminId", required = true) String adminId,
    		@RequestParam(value = "adminNickname", required = true) String adminNickname,
    		@RequestParam(value = "userId", required = true) String userId,
    		@RequestParam(value = "userNickname", required = true) String userNickname){
    	
    	String operation = "启用";
    	String reason = "";
    	boolean isOk = userRecordService.saveRecord(adminId,adminNickname,userId,userNickname,operation,reason);
    	User user =  userService.usingUser(userId);
    	if(isOk && user != null){
    		if(user.getIsManager() == 0){
    			return "success_user";
    		}else{
    			return "success_admin";
    		}
    		
    	}else{
    		return "error";
    	}
    }
}
