package com.wenLi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 首页板块实体
 * @author xjw
 *
 */
@Entity 
@Table(name="IndexPicModel") 
public class IndexPicModel {
	private String id;//id
	private String title;//一级标题
	private String secondTitle;//二级标题
	private String text;//内容介绍
	private String buttonName;//按钮名
	private String buttonLink;//按钮链接
	private String picUrl;//图片地址
	private String picLink;//图片链接
	private Integer menuOrder;//菜单顺序
	private Integer isUse;//是否启用

	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "TITLE", length = 200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "SECONDTITLE", length = 200)
	public String getSecondTitle() {
		return secondTitle;
	}
	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}
	
	@Column(name = "TEXT", length = 4000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name = "BUTTONNAME", length = 200)
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	
	@Column(name = "BUTTONLINK", length = 200)
	public String getButtonLink() {
		return buttonLink;
	}
	public void setButtonLink(String buttonLink) {
		this.buttonLink = buttonLink;
	}
	
	@Column(name = "PICURL", length = 200)
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Column(name = "PICLINK", length = 200)
	public String getPicLink() {
		return picLink;
	}
	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}
	
	@Column(name = "MENUORDER")
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	
	@Column(name = "ISUSE")
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	@Override
	public String toString() {
		return "IndexModel [id=" + id + ", title=" + title + ", secondTitle=" + secondTitle + ", text=" + text
				+ ", buttonName=" + buttonName + ", buttonLink=" + buttonLink + ", picUrl=" + picUrl + ", picLink="
				+ picLink + ", menuOrder=" + menuOrder + ", isUse=" + isUse + "]";
	}
	
	
}
