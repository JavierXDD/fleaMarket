package com.fr.io;    
    
import java.io.File;    
import java.io.FileInputStream;    
import java.io.FileOutputStream;    
import java.io.OutputStream;    
import com.fr.main.impl.WorkBook;   
import com.fr.base.ModuleContext;  
import com.fr.io.importer.ExcelReportImporter;  
import com.fr.main.TemplateWorkBook;    
import com.fr.page.stable.ReportPageAttr;  
import com.fr.report.module.EngineModule;  
    
public class ExcelToCpt {    
    public static void main(String[] args) throws Exception {    
        File excelFile = new File("D:\\API.xls"); // 获取EXCEL文件    
        FileInputStream a = new FileInputStream(excelFile);    
        ModuleContext.startModule(EngineModule.class.getName());  
        TemplateWorkBook tpl = new ExcelReportImporter().generateWorkBookByStream(a);    
        OutputStream outputStream = new FileOutputStream(new File("D:\\abc.cpt")); // 转换成cpt模板    
        ((WorkBook) tpl).export(outputStream);  
        outputStream.close();  
        ModuleContext.stopModules();
    }    
}