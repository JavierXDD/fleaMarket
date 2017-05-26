package com.wenLi.dao;

import com.wenLi.entity.Message;
import com.wenLi.util.entity.Page;

public interface MessageDao {

	Page<Message> findMessageByBlogId(int pc,int ps,String id);

	Page<Message> findAllMessage(int pc,int ps);

	Message findMessageById(String id);

	boolean saveMessage(Message message);

	boolean deleteMessage(Message message);

	Page<Message> findAllMessageByUserId(int pc, int ps, String id);

	Page<Message> findReplyMessageByUserId(int pc, int ps, String id);

}
