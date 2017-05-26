package com.fr.demo;  

import java.util.logging.Level;  
import java.util.logging.Logger;  
import com.fr.base.FRContext;  
import com.fr.base.Utils;  
import com.fr.data.DefinedVerifyJob;  
import com.fr.data.JobValue;  
import com.fr.function.LOG;  
import com.fr.script.Calculator;  
  
public class VerifyJobDemo  extends DefinedVerifyJob{  
    /* 
     * 必须要定义此私有变量,变量名可改，表示校验状态 
     * 0  表示校验成功，默认校验状态位为0 
     * 1  表示校验失败 
     */  
    private int type = 0;   
      
    /** 
     * 当模板自定义事件增加的属性 名称与下面变量有对应时，则会自动赋值于此对应变量 
     */  
    private JobValue salenum;   // JobValue对应单元格  
    private int minnum;       // 非单元格，则对应具体类型值  
  
    public void doJob(Calculator calculator) throws Exception {  
        /* 
         * 如这边提供一个简单的判断来模拟执行过程 
         * 校验规则为销量需要大于等于最小基数：salenum >= minnum 
         * 校验不通过,提示“销量值不能小于最小基数” 
         */  
        if(salenum != null){  
            int sale = 0;  
            if(salenum.getValue() instanceof Integer){ //将单元格值转为整型以便用于比较  
                sale = (Integer)salenum.getValue();  
  
                  
            }else {  
                sale = Integer.parseInt(Utils.objectToString(salenum.getValue()));  
            }  
              
            if(sale < minnum){ //校验判断  
                type = 1;  
            }  
        }else {  
            type = 1;  
        }  
  
    }  
  
    public String getMessage() {  
        // 根据校验状态是成功还是失败，设置对应的返回信息  
        if(type == 0){  
            return "恭喜你，校验成功";  
        }else{  
            return "销量值不能小于最小基数";  
        }  
          
    }  
    public int getType() {  
        // 返回校验状态  
        return type;  
    }

	@Override
	public void doFinish(Calculator arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}  
      
}