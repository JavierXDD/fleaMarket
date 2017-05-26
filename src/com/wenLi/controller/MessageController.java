package com.wenLi.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenLi.entity.Message;
import com.wenLi.service.MessageService;
import com.wenLi.util.entity.Page;

/**
 * 留言控制
 * @author xjw
 *
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
	@Resource
	private MessageService messageService;
	
	@ResponseBody
    @RequestMapping(value="/publishMessage")
	public String saveOrUpdateMyBlog(Model model,
			@ModelAttribute("message") Message message) {
		//回复
		if(message.getParentId() !="" && message.getParentId() !=null){
			Message pMessage = messageService.findMessageById(message.getParentId());
			if(pMessage != null){
				String oldText = message.getText();
				String newText = oldText + "<hr><div class='alert green'>  原文——@"+pMessage.getSenderName()+":"+pMessage.getText()+"</div>";
				message.setText(newText);
			}
		}
		boolean isok = messageService.saveMessage(message);
		if(isok){			
			return "success";
		}else{			
			return "error";
		}
	}
	
	//所有评论列表-管理员
    @RequestMapping(value="/toManagerMessageList")
	public String toManagerMessageList(Model model) {
    	Page<Message> messageList = messageService.findAllMessage(1, 10);
    	model.addAttribute("messageList", messageList);
    	return "/jsp/manager/blog/messageList";
	}
    
	//分页获取评论列表-管理员
    @RequestMapping(value="/getManagerMessageList")
	public String getManagerMessageList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode) {
    	int pc = Integer.parseInt(pageCode);
    	Page<Message> messageList = messageService.findAllMessage(pc, 10);
    	model.addAttribute("messageList", messageList);
    	return "/jsp/manager/blog/messageList";
	}
    
	//管理员删除评论
    @RequestMapping(value="/managerDeleteMessage")
	public String managerDeleteMessage(Model model,
			@RequestParam(value = "id", required = true) String id) {
    	Message message = messageService.findMessageById(id);
    	boolean isok = messageService.deleteMessage(message);
    	if(isok){
    		return this.toManagerMessageList(model);
    	}else{
    		return "";
    	}
	}  
    
	//所有评论列表-自己
    @RequestMapping(value="/toMyMessageList")
	public String toMyMessageList(Model model,
			@RequestParam(value = "id", required = true) String id) {
    	Page<Message> messageList = messageService.findAllMessageByUserId(1,10,id);
    	model.addAttribute("messageList", messageList);
    	return "/jsp/personInfo/myMessageList";
	}
	//分页获取评论列表-自己
    @RequestMapping(value="/getMyMessageList")
	public String getMyMessageList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode,
			@RequestParam(value = "id", required = true) String id) {
    	int pc = Integer.parseInt(pageCode);
    	Page<Message> messageList = messageService.findAllMessageByUserId(pc, 10,id);
    	model.addAttribute("messageList", messageList);
    	return "/jsp/personInfo/myMessageList";
	}    
	//自己删除评论
    @RequestMapping(value="/deleteMyMessage")
	public String deleteMyMessage(Model model,
			@RequestParam(value = "id", required = true) String id) {
    	Message message = messageService.findMessageById(id);
    	String userId = message.getSenderId();
    	boolean isok = messageService.deleteMessage(message);
    	if(isok){
    		return this.toMyMessageList(model, userId);
    	}else{
    		return "";
    	}
	} 
    
	//删除帖子中评论
	@ResponseBody
    @RequestMapping(value="/deleteBlogMessage")
	public String deleteBlogMessage(Model model,
			@RequestParam(value = "messageId", required = true) String messageId) {
    	Message message = messageService.findMessageById(messageId);
    	boolean isok = messageService.deleteMessage(message);
    	if(isok){
    		return "success";
    	}else{
    		return "error";
    	}
	} 
	
	//模态框中展示评论信息
	@ResponseBody
    @RequestMapping(value="/viewMessageDetail",produces = {"text/html;charset=UTF-8" })
	public String viewMessageDetail(Model model,
			@RequestParam(value = "id", required = true) String id) {
    	Message message = messageService.findMessageById(id);    	
    	if(message != null){
    		return message.getText();
    	}else{
    		return "error";
    	}
	}  
	
	//回复我的-自己
    @RequestMapping(value="/toReplyMyMessageList")
	public String toReplyMyMessageList(Model model,
			@RequestParam(value = "id", required = true) String id) {
    	Page<Message> messageList = messageService.findReplyMessageByUserId(1,10,id);
    	model.addAttribute("messageList", messageList);
    	return "/jsp/personInfo/replyMyMessageList";
	}
	//分页获取回复我的列表-自己
    @RequestMapping(value="/getReplyMyMessageList")
	public String getReplyMyMessageList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode,
			@RequestParam(value = "id", required = true) String id) {
    	int pc = Integer.parseInt(pageCode);
    	Page<Message> messageList = messageService.findReplyMessageByUserId(pc, 10,id);
    	model.addAttribute("messageList", messageList);
    	return "/jsp/personInfo/myMessageList";
	} 
}


