//SubSection函数-Oracle查询参数个数限制
package com.fr.function;  
  
import com.fr.general.FArray;
import com.fr.script.AbstractFunction;   
  
public class SubSection extends AbstractFunction {  
    public Object run(Object[] args) {  
        // 获取第一个对象，即取得传入的参数  
        Object para = args[0];  
        String parastr = para.toString();  
        // 由于是复选参数，因此要去掉前后的"("和")"  
        if (parastr.startsWith("(") && parastr.endsWith(")")) {  
            parastr = parastr.substring(1, parastr.length() - 1);  
        }  
        // 将字符串转为","分割的数组  
        String test[] = parastr.split(",");  
        int len = test.length;  
        int loopnum = len / 500;  
        if (len % 500 != 0) {  
            loopnum += 1;  
        }  
        ;  
        // 返回的值是数组，需要定义成我们内部的类型FArray  
        FArray result = new FArray();  
        String str = "";  
        int k = 1;  
        for (int i = 0; i < loopnum; i++) {  
            for (int j = 500 * i; j < 500 * (i + 1) && j < len; j++) {  
                if (k != 500 && j != (len - 1)) {  
                    str += test[j] + ",";  
                } else {  
                    str += test[j];  
                }  
                k++;  
            }  
            // 每500个形成一组并在每组外部加上"("和")"  
            str = "(" + str + ")";  
            result.add(str);  
            str = "";  
            k = 1;  
        }  
        return result;  
    }  
}