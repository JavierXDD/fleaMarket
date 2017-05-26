// 程序网络报表中获取request中的值 
package com.fr.demo;  

import java.util.Map;
import java.util.logging.Level;

import com.fr.base.FRContext;    
import com.fr.base.ModuleContext;
import com.fr.main.TemplateWorkBook;    
import com.fr.report.module.EngineModule;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest;
import com.fr.io.TemplateWorkBookIO;    
import com.fr.base.Parameter;    
import com.fr.dav.LocalEnv;
import com.fr.page.stable.ReportPageAttr;
    
    
public class URLParameterDemo extends Reportlet {    
    public TemplateWorkBook createReport(ReportletRequest reportletRequest) {   
          
         String envPath="D:\\FineReport_7.1\\WebReport\\WEB-INF";    
         FRContext.setCurrentEnv(new LocalEnv(envPath));    
         ModuleContext.startModule(EngineModule.class.getName());   
        // 获取外部传来的参数    
        TemplateWorkBook wbTpl = null;  
        String countryValue = reportletRequest.getParameter("地区").toString(); 
        try {    
            wbTpl = TemplateWorkBookIO.readTemplateWorkBook(    
                    FRContext.getCurrentEnv(), "\\doc\\Primary\\Parameter\\Parameter.cpt");    
            // 提取报表参数组，由于原模板只有country一个参数，因此直接取index为0的参数，并将外部传入的值赋给该参数    
            Parameter[] ps = wbTpl.getParameters();    
            ps[0].setValue(countryValue);    
            // 原模板定义有参数界面，参数已经从外部获得，去掉参数页面    
            // 若您想保留参数界面，则将模板设置为不延迟报表展示，再传入参数后直接根据参数值显示结果，否则还需要再次点击查询按钮    
            wbTpl.getReportParameterAttr().setParameterUI(null);    
        } catch (Exception e) {    
            e.printStackTrace();    
            return null;    
        }    
        return wbTpl;   
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