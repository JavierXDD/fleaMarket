package com.wenLi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 交易信息实体
 * @author xjw
 *
 */
@Entity 
@Table(name="Blog") 
public class Blog {
	private String id;//id
	private String title;//标题
	private String surfacePicUrl;//封面图地址
	private String name;//品名
	private Integer price;//价格
	private String quality;//质量，成色
	private String userTelephone;//联系人电话
	private String userEmail;//联系人电子邮箱
	private String createTime;//发布时间
	private String modifyTime;//修改时间
	private String text;//详细内容
	private String userId;//联系人id
	private String userName;//联系人名
	private String menuId;//板块id
	private String menuName;//板块名
	private String typeId;//类别id
	private String typeName;//类别名
	private String tags;//标签
	private Integer isUse;//是否通过审核 0未审核 1通过 2未通过
	
	
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
	
	@Column(name = "SurfacePicUrl", length = 200)
	public String getSurfacePicUrl() {
		return surfacePicUrl;
	}
	public void setSurfacePicUrl(String surfacePicUrl) {
		this.surfacePicUrl = surfacePicUrl;
	}
	
	@Column(name = "Name", length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "Price")
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Column(name = "Quality", length = 200)
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	@Column(name = "UserTelephone", length = 200)
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	
	@Column(name = "UserEmail", length = 200)
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Column(name = "CreateTime", length = 200)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "ModifyTime", length = 200)
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "Text", length = 2000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name = "UserId", length = 200)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "UserName", length = 200)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "MenuId", length = 200)
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "MenuName", length = 200)
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Column(name = "TypeId", length = 200)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Column(name = "TypeName", length = 200)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Column(name = "Tags", length = 200)
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Column(name = "isUse")
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", surfacePicUrl=" + surfacePicUrl + ", name=" + name
				+ ", price=" + price + ", quality=" + quality + ", userTelephone=" + userTelephone + ", userEmail="
				+ userEmail + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", text=" + text
				+ ", userId=" + userId + ", userName=" + userName + ", menuId=" + menuId + ", menuName=" + menuName
				+ ", typeId=" + typeId + ", typeName=" + typeName + ", tags=" + tags + ", isUse=" + isUse + "]";
	}


	
	
	
}
