package com.wenLi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenLi.dao.MessageDao;
import com.wenLi.entity.Message;
import com.wenLi.service.MessageService;
import com.wenLi.util.entity.Page;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	@Resource
	private MessageDao  messageDao;

	@Override
	public Page<Message> findMessageByBlogId(int pc,int ps,String id) {
		
		return messageDao.findMessageByBlogId(pc,ps,id);
	}

	@Override
	public Page<Message> findAllMessage(int pc,int ps) {
		return messageDao.findAllMessage(pc,ps);
	}

	@Override
	public Message findMessageById(String id) {
		return messageDao.findMessageById(id);
	}

	@Override
	public boolean saveMessage(Message message) {
		return messageDao.saveMessage(message);
	}

	@Override
	public boolean deleteMessage(Message message) {
		return messageDao.deleteMessage(message);
	}

	@Override
	public Page<Message> findAllMessageByUserId(int pc, int ps, String id) {
		return messageDao.findAllMessageByUserId(pc,ps,id);
	}

	@Override
	public Page<Message> findReplyMessageByUserId(int pc, int ps, String id) {
		return messageDao.findReplyMessageByUserId(pc,ps,id);
	}




}
