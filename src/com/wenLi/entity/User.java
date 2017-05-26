package com.wenLi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户实体
 * @author xjw
 *
 */
@Entity 
@Table(name="USER") 
public class User {
	//id
	private String id;
	//是否管理员	0游客  1管理员 2超级管理员
	private Integer isManager ;
	//用户名
	private String nickname;
	//姓名
	private String name;
	//密码
	private String password;
	//性别
	private String sex;
	//电话
	private String telephone;
	//email
	private String email;
	//创建日期
	private String createTime;
	//修改日期
	private Date updateTime;
	
	//是否启用
	private Integer isUse;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "IS_MANAGER")
	public Integer getIsManager() {
		return isManager;
	}
	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}
	
	@Column(name = "NICKNAME", length = 200)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name = "NAME", length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "PASSWORD", length = 200)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "SEX", length = 200)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "TELEPHONE", length = 200)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "CREATETIME",length = 100)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "IS_USE")
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", isManager=" + isManager + ", nickname=" + nickname + ", name=" + name
				+ ", password=" + password + ", sex=" + sex + ", telephone=" + telephone + ", email=" + email
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", isUse=" + isUse + "]";
	}
	



	


	
	
}
