//自定义函数把阳历转换成阴历
package com.fr.function;  

import com.fr.script.AbstractFunction;
   
public class Lunar extends AbstractFunction {  
    public Object run(Object[] args) {  
        String result = "";  
        int y = Integer.parseInt(args[0].toString());  
        int m = Integer.parseInt(args[1].toString());  
        int d = Integer.parseInt(args[2].toString());  
        result = SolarToLunar.today(y, m, d);  
        return result;  
    }  
}