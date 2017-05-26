// 自定义函数Unicode编码转化为中文 
package com.fr.function;  

import com.fr.script.AbstractFunction;  
  
public class Ubm extends AbstractFunction {  
    public Object run(Object[] args) {  
        String str = args[0].toString();  
        String st = "";  
        StringBuffer buffer = new StringBuffer();  
        while (str.length() > 0) {  
            if (str.startsWith("%u")) {  
                st = str.substring(2, 6);  
                char ch = (char) Integer.parseInt(String.valueOf(st), 16);  
                buffer.append(new Character(ch).toString());  
                str = str.substring(6);  
            } else {  
                st = str.substring(0, str.indexOf("%u"));  
                buffer.append(st);  
                str = str.substring(st.length());  
            }  
        }  
        return buffer.toString();  
    }  
} 