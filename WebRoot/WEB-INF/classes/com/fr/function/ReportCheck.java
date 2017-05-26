// 自定义函数实现表间校验 
package com.fr.function;  
  
import java.io.File;  
import java.util.HashMap; 
import com.fr.base.Env;
import com.fr.base.FRContext;
import com.fr.io.TemplateWorkBookIO;
import com.fr.json.JSONArray;
import com.fr.json.JSONObject;
import com.fr.main.TemplateWorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.cell.CellElement;
import com.fr.report.elementcase.TemplateElementCase; 
import com.fr.script.AbstractFunction;  
import com.fr.stable.WriteActor;
  
public class ReportCheck extends AbstractFunction {  
    private static HashMap wMap = new HashMap();  
  
    public Object run(Object[] args) {  
        // 获取公式中的参数  
        String cptname = args[0].toString(); // 获取报表名称  
        int colnumber = Integer.parseInt(args[2].toString()); // 所取单元格所在列  
        int rownumber = Integer.parseInt(args[3].toString()); // 所取单元格所在行  
        // 定义返回的值  
        Object returnValue = null;  
        // 定义报表运行环境，才能运行读取的报表  
        Env oldEnv = FRContext.getCurrentEnv();  
        String envPath = oldEnv.getPath();  
        try {  
            ResultWorkBook rworkbook = null;  
            // 读取模板  
            TemplateWorkBook workbook = TemplateWorkBookIO  
                    .readTemplateWorkBook(oldEnv, envPath + "\\reportlets\\" 
                            + cptname); 
            // 获取需要传递给报表的参数名与参数值，格式如[{"name":para1name,"value":para1value},{"name":para2name,"value":para2value},......] 
            JSONArray parasArray = new JSONArray(args[1].toString()); 
            // 需要判断是否是5秒内执行过的 
            // 取出保存的resultworkbook; 
            Object tempWObj = wMap.get(cptname + parasArray.toString()); 
            if (tempWObj != null) { 
                // 取出hashmap里面保存的TpObj; 
                TpObj curTpObj = (TpObj) tempWObj; 
 
                if ((System.currentTimeMillis() - curTpObj.getExeTime()) < 8000) { 
                    rworkbook = curTpObj.getRworkbook(); 
                } else { 
                    wMap.remove(cptname + parasArray.toString()); 
                } 
            } 
            // 如果没有设置，需要生成 
            if (rworkbook == null) { 
                JSONObject jo = new JSONObject(); 
                // 定义报表执行时使用的paraMap,保存参数名与值 
                java.util.Map parameterMap = new java.util.HashMap(); 
                if (parasArray.length() > 0) { 
                    for (int i = 0; i < parasArray.length(); i++) { 
                        jo = parasArray.getJSONObject(i); 
                        parameterMap.put(jo.get("name"), jo.get("value"));  
                    }  
                }  
                // 执行报表  
                rworkbook = workbook.execute(parameterMap, new WriteActor());  
                // 保存下来  
                wMap.put(cptname + parasArray.toString(), new TpObj(rworkbook,  
                        System.currentTimeMillis()));  
            }  
            // 获取报表结果中对应Cell的值  
            TemplateElementCase rreport = (TemplateElementCase) rworkbook  
                    .getResultReport(0);  
            CellElement ccell = rreport.getCellElement(colnumber, rownumber);  
            returnValue = ccell.getValue();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return returnValue;  
    }  
  
    class TpObj {  
        private ResultWorkBook rworkbook = null;  
        private long exeTime = System.currentTimeMillis();  
  
        public TpObj(ResultWorkBook rworkbook, long exeTime) {  
            this.setRworkbook(rworkbook);  
            this.setExeTime(exeTime);  
        }  
  
        public ResultWorkBook getRworkbook() {  
            return rworkbook;  
        }  
  
        public void setRworkbook(ResultWorkBook rworkbook) {  
            this.rworkbook = rworkbook;  
        }  
  
        public long getExeTime() {  
            return exeTime;  
        }  
  
        public void setExeTime(long exeTime) {  
            this.exeTime = exeTime;  
        }  
    }  
}