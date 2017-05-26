package com.wenLi.dao;

import java.util.List;

import com.wenLi.entity.UserRecord;

/**
 * 用户操作记录
 * @author xjw
 *
 */
public interface UserRecordDao {

	//禁用
	boolean saveRecord(UserRecord userRecord);

	List<UserRecord> findRecordByUserId(String id);

}
