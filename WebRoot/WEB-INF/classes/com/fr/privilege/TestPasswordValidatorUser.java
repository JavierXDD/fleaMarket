package com.fr.privilege;

import com.fr.general.ComparatorUtils;
import com.fr.privilege.providers.dao.AbstractPasswordEncode;

public class TestPasswordValidatorUser extends AbstractPasswordEncode {

    /**
     * 三个参数的密码加密算法：满足数据库密码=FR+用户名+密码+RF，返回true
     * @param localPassword 存储在数据库中的密码
     * @param clientPassword 用户输入的密码
     * @param clientUsername 用户名
     * @return 是否验证成功
     */
    public String encodePassword(String clientPassword, String clientUsername) {
       return "FR" + clientUsername + clientPassword + "RF";
     
    }

    /**
     * 验证密码时是否要忽略用户名
     */
    public boolean shouldIgnoreUsername() {
        return false;
    }

	@Override
	public String encodePassword(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * 2个参数的密码验证方法，直接return false
     */
    }