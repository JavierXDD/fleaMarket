package com.wenLi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户操作记录类
 * @author xjw
 *
 */
@Entity 
@Table(name="USER_RECORD") 
public class UserRecord {

	//id
	private String id;
	//操作内容
	private String operation;
	//操作原因
	private String reason;
	//操作时间
	private String recordTime;
	//操作人id
	private String adminId;
	//操作人名
	private String adminNickname;
	//关联用户id
	private String userId;
	//关联用户名
	private String userNickname;
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "OPERATION", length = 200)
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Column(name = "REASON", length = 200)
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name = "RECORDTIME")
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
	@Column(name = "ADMINID", length = 30)
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	

	@Column(name = "ADMIN_NICKNAME", length = 200)
	public String getAdminNickname() {
		return adminNickname;
	}
	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}
	
	@Column(name = "USERID", length = 30)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "USER_NICKNAME", length = 200)
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	@Override
	public String toString() {
		return "UserRecord [id=" + id + ", operation=" + operation + ", reason=" + reason + ", recordTime=" + recordTime
				+ ", adminId=" + adminId + ", adminNickname=" + adminNickname + ", userId=" + userId + ", userNickname="
				+ userNickname + "]";
	}


	
	
}
