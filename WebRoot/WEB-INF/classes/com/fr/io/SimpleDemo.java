//读取修改报表
package com.fr.io;  
  
import java.awt.Color;  
import java.io.File;  
import java.io.FileOutputStream;  
import com.fr.base.FRContext;
import com.fr.base.ModuleContext;  
import com.fr.base.Style;  
import com.fr.dav.LocalEnv;
import com.fr.report.cell.CellElement;
import com.fr.report.elementcase.TemplateElementCase;
import com.fr.report.module.EngineModule;
import com.fr.general.FRFont;
import com.fr.io.TemplateWorkBookIO;  
import com.fr.main.impl.WorkBook;
  
public class SimpleDemo {  
    public static void main(String[] args) {  
        // 定义报表运行环境,才能执行报表  
        String envPath = "D:\\FineReport_7.0\\WebReport\\WEB-INF";  
        FRContext.setCurrentEnv(new LocalEnv(envPath));  
        ModuleContext.startModule(EngineModule.class.getName());  
        try {  
            // 读取模板  
            WorkBook workbook = (WorkBook) TemplateWorkBookIO  
                    .readTemplateWorkBook(FRContext.getCurrentEnv(),  
                            "\\doc\\Primary\\Parameter\\Parameter.cpt");  
  
            // 获得WorkBook中的WorkSheet，进而修改A1单元格的前景色为红色  
            TemplateElementCase report = (TemplateElementCase) workbook  
                    .getReport(0);  
            // getCellElement(int column, int  
            // row),column和row都从0开始，因此A1单元格便是第0列第0行  
            CellElement cellA1 = report.getCellElement(0, 0);  
            FRFont frFont = FRFont.getInstance();  
            frFont = frFont.applyForeground(Color.red);  
            Style style = Style.getInstance();  
            style = style.deriveFRFont(frFont);  
            cellA1.setStyle(style);  
            // 保存模板  
            FileOutputStream outputStream = new FileOutputStream(new File(  
                    "D:\\newParameter1.cpt"));  
            ((WorkBook) workbook).export(outputStream);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}