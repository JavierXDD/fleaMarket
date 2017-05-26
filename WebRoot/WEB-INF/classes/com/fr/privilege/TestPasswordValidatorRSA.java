package com.fr.privilege;  

import com.fr.privilege.providers.dao.AbstractPasswordValidator;  
public class TestPasswordValidatorRSA extends AbstractPasswordValidator{  
    //@Override
    public String encodePassword( String clinetPassword) {
    	try {
    		//对密码进行翻转如输入ab翻转后为ba
    		StringBuffer sb = new StringBuffer();  
	        sb.append(new String(clinetPassword));
	        String bb = sb.reverse().toString();
    		//进行加密
    		byte[] en_test = RSAUtil.encrypt(RSAUtil.getKeyPair().getPublic(),bb.getBytes());     		
    		//进行解密，如果数据库里面保存的是加密码，则此处不需要进行解密
    		byte[] de_test = RSAUtil.decrypt(RSAUtil.getKeyPair().getPrivate(),en_test);  
    		//返回加密密码
    		clinetPassword=new String(de_test);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clinetPassword; //即获取加密密码再与数据库密码匹配。  
    }

	@Override
	public boolean validatePassword(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}


} 