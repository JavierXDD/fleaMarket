package com.wenLi.service;

import java.util.List;

import com.wenLi.entity.UserRecord;

//用户操作记录
public interface UserRecordService {

	//禁用
	boolean saveRecord(String adminId,String adminNickname, String userId,String userNickname,String operation,String reason);

	List<UserRecord> findRecordByUserId(String id);

}
