package com.wenLi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 菜单实体
 * @author xjw
 *
 */
@Entity 
@Table(name="MENU") 
public class Menu {
	private String id;//id
	private String name;//菜单名
	private String text;//菜单描述
	private String picUrl;//图片地址
	private String thumbnailUrl;//缩略图地址
	private String url;//菜单链接
	private Integer menuOrder;//菜单顺序
	private Integer isUse;//是否启用
	private Integer isShow;//是否展示在首页
	private String parentsId;//父菜单ID
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Column(name = "MENUORDER")
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	@Column(name = "NAME", length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "TEXT", length = 4000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name = "PICURL", length = 200)
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Column(name = "THUMBNAILURL", length = 200)
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
	@Column(name = "URL", length = 200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "ISUSE")
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
	@Column(name = "ISSHOW")
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	@Column(name = "PARENTSID", length = 50)
	public String getParentsId() {
		return parentsId;
	}
	public void setParentsId(String parentsId) {
		this.parentsId = parentsId;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", text=" + text + ", picUrl=" + picUrl + ", thumbnailUrl="
				+ thumbnailUrl + ", url=" + url + ", menuOrder=" + menuOrder + ", isUse=" + isUse + ", isShow=" + isShow
				+ ", parentsId=" + parentsId + "]";
	}

	



	
}
