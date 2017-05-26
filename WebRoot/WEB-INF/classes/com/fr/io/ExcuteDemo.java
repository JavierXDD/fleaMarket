package com.fr.io;  
  
import java.io.File;  
import java.io.FileOutputStream;  
import com.fr.base.FRContext;  
import com.fr.base.ModuleContext;  
import com.fr.dav.LocalEnv; 
import com.fr.io.exporter.ExcelExporter;
import com.fr.main.TemplateWorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.WriteActor;
   
  
public class ExcuteDemo {  
    public static void main(String[] args) {  
        try {  
            // 首先需要定义执行所在的环境，这样才能正确读取数据库信息  
            String envPath = "D:\\FineReport_7.1\\WebReport\\WEB-INF";  
            FRContext.setCurrentEnv(new LocalEnv(envPath));  
            ModuleContext.startModule(EngineModule.class.getName());  
            // 读取模板  
            TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),"\\doc\\Primary\\Parameter\\Parameter.cpt");  
            /* 
             * 生成参数map，注入参数与对应的值，用于执行报表 该模板中只有一个参数地区，给其赋值华北 
             * 若参数在发送请求时传过来，可以通过req.getParameter(name)获得 
             * 获得的参数put进map中，paraMap.put(paraname,paravalue) 
             */  
            java.util.Map paraMap = new java.util.HashMap();  
            paraMap.put("地区", "华北");  
            // 使用paraMap执行生成结果  
            ResultWorkBook result = workbook.execute(paraMap,new WriteActor());  
            // 使用结果如导出至excel  
            FileOutputStream outputStream = new FileOutputStream(new File(  
                    "D:\\Parameter.xls"));  
            ExcelExporter excelExporter = new ExcelExporter();  
            excelExporter.export(outputStream, result);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}