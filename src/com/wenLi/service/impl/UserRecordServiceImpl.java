package com.wenLi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenLi.dao.UserRecordDao;
import com.wenLi.entity.UserRecord;
import com.wenLi.service.UserRecordService;
/**
 * 用户操作记录
 * @author think
 *
 */
@Service
@Transactional
public class UserRecordServiceImpl implements UserRecordService{

	@Resource
	private UserRecordDao userRecordDao;
	
	//禁用
	@Override
	public boolean saveRecord(String adminId,String adminNickname, String userId,String userNickname,String operation,String reason) {
		UserRecord userRecord = new UserRecord();
		userRecord.setAdminId(adminId);
		userRecord.setAdminNickname(adminNickname);
		userRecord.setUserId(userId);
		userRecord.setUserNickname(userNickname);
		userRecord.setOperation(operation);
		userRecord.setReason(reason);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String recordTime = dateFormat.format( new Date() ); 
		userRecord.setRecordTime(recordTime);
		return userRecordDao.saveRecord(userRecord);
	}

	@Override
	public List<UserRecord> findRecordByUserId(String id) {
		// TODO Auto-generated method stub
		return userRecordDao.findRecordByUserId(id);
	}

}
