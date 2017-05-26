package com.wenLi.service;

import com.wenLi.entity.Message;
import com.wenLi.util.entity.Page;

public interface MessageService {

	//根据帖子id查留言
	Page<Message> findMessageByBlogId(int pc,int ps,String id);
	//查所有留言
	Page<Message> findAllMessage(int pc,int ps);
	//根据id查留言
	Message findMessageById(String id);
	//保存留言
	boolean saveMessage(Message message);
	//删除留言
	boolean deleteMessage(Message message);
	//根据用户id查留言
	Page<Message> findAllMessageByUserId(int pc, int ps, String id);
	//回复我的留言
	Page<Message> findReplyMessageByUserId(int pc, int ps, String id);
	
}
