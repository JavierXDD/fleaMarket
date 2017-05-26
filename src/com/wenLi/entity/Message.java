package com.wenLi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 评论信息实体
 * @author xjw
 *
 */
@Entity 
@Table(name="Message") 
public class Message {
	private String id;//id
	private String senderId;//发送者id
	private String senderName;//发送者名
	private String receiverId;//接收者id
	private String receiverName;//接受者名
	private String text;//内容
	private String blogId;//关联博客id
	private String blogName;//关联博客名
	private String sendTime;//发送时间
	private String parentId;//父消息id
	private Integer messageType;//消息类型
	private Integer isUse;//消息状态
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	@Column(name = "Text", length = 4000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", senderId=" + senderId + ", senderName=" + senderName + ", receiverId="
				+ receiverId + ", receiverName=" + receiverName + ", text=" + text + ", blogId=" + blogId
				+ ", blogName=" + blogName + ", sendTime=" + sendTime + ", parentId=" + parentId + ", messageType="
				+ messageType + ", isUse=" + isUse + "]";
	}
	
	
	
	
}
