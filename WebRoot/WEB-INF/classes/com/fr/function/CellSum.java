// 自定义函数中获取公式所在单元格 
package com.fr.function;  
  
import com.fr.base.Utils;
import com.fr.script.AbstractFunction;  

public class CellSum extends AbstractFunction {  
    public Object run(Object[] args) {  
        String sum = Utils.objectToNumber(new SUM().run(args), false)  
                .toString(); // 直接调用FR内部的SUM方法  
        String result = "所在单元格为：" + this.getCalculator().getCurrentColumnRow()  
                + "；总和为：" + sum; // 获取当前单元格拼出最终结果  
        return result;  
    }  
}