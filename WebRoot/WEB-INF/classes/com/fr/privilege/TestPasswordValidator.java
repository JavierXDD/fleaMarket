package com.fr.privilege;

import com.fr.general.ComparatorUtils;
import com.fr.privilege.providers.dao.AbstractPasswordEncode;

public class TestPasswordValidator extends AbstractPasswordEncode{
	@Override
	public String encodePassword(String clinetPassword) {
		return (clinetPassword+"FR");//即获取用户输入的密码然后在后面加上FR，再与数据库密码匹配。
	}

}