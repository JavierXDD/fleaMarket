package com.fr.io;   
  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.OutputStream;  
import com.fr.base.FRContext;    
import com.fr.base.ModuleContext;  
import com.fr.base.Parameter;      
import com.fr.dav.LocalEnv;  
import com.fr.io.exporter.PageExcelExporter;  
import com.fr.main.TemplateWorkBook;       
import com.fr.main.workbook.PageWorkBook;
import com.fr.report.core.ReportUtils;  
import com.fr.report.module.EngineModule;  
import com.fr.report.report.PageReport;
import com.fr.stable.PageActor;
    
public class ExportReports {    
    public static void main(String[] args) {    
        // 定义报表运行环境,才能执行报表    
        String envpath = "D:\\FineReport\\develop\\code\\build\\package\\WebReport\\WEB-INF";    
        FRContext.setCurrentEnv(new LocalEnv(envpath));    
        ModuleContext.startModule(EngineModule.class.getName());    
        // 进行程序的一些必要初始化    
        try {    
            // 未执行模板工作薄    
            TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),    
                    "Gettingstarted.cpt");    
            // 参数值为China计算结果，将结果保存至rworkbook    
            Parameter[] parameters = workbook.getParameters();    
            java.util.Map parameterMap = new java.util.HashMap();    
            for (int i = 0; i < parameters.length; i++) {    
                parameterMap.put(parameters[i].getName(), "华东");    
            }    
            PageWorkBook rworkbook = (PageWorkBook)workbook.execute(parameterMap,new PageActor());    
            rworkbook.setReportName(0, "华东");    
            // 清空parametermap，将参数值改为华北,计算后获得ResultReport    
            parameterMap.clear();    
            for (int i = 0; i < parameters.length; i++) {    
                parameterMap.put(parameters[i].getName(), "华北");    
            }    
            PageWorkBook rworkbook2 = (PageWorkBook)workbook.execute(parameterMap,new PageActor());    
            PageReport rreport2 = rworkbook2.getPageReport(0);    
            rworkbook.addReport("华北", rreport2);    
            // 将结果工作薄导出为Excel文件    
            OutputStream outputStream = new FileOutputStream(new File("D:\\ExcelExport1.xls"));    
            PageExcelExporter excelExport = new PageExcelExporter(ReportUtils.getPaperSettingListFromWorkBook(rworkbook));      
            excelExport.export(outputStream, rworkbook); 
            outputStream.close();  
            ModuleContext.stopModules();
              
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }    
} 