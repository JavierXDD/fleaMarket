//动态修改数据
package com.fr.demo;    
     
import java.util.Map;

import com.fr.base.Env;  
import com.fr.base.FRContext;     
import com.fr.base.ModuleContext;
import com.fr.data.ArrayTableDataDemo;    
import com.fr.io.TemplateWorkBookIO;  
import com.fr.main.TemplateWorkBook;  
import com.fr.report.module.EngineModule;
import com.fr.web.core.Reportlet;  
import com.fr.web.request.ReportletRequest;
import com.fr.page.stable.ReportPageAttr;
  
public class NewDateDemo extends Reportlet {    
    public TemplateWorkBook createReport(ReportletRequest reportletrequest) {    
        TemplateWorkBook workbook = null;    
        Env oldEnv = FRContext.getCurrentEnv(); 
        ModuleContext.startModule(EngineModule.class.getName());   
        try {    
            // 创建workbook对象，将模板保存为workbook对象并返回    
            workbook = TemplateWorkBookIO.readTemplateWorkBook(oldEnv,"1.cpt");  
            ArrayTableDataDemo a = new ArrayTableDataDemo(); // 调用定义的程序数据集连接    
            workbook.putTableData("ds2", a); // 给模板赋新的数据集    
        } catch (Exception e) {    
            e.getStackTrace();    
        }    
        return workbook;    
    }

	@Override
	public void setParameterMap(Map arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTplPath(String arg0) {
		// TODO Auto-generated method stub
		
	}    
}