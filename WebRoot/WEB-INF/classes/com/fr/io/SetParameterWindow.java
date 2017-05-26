// 报表参数界面API 
package com.fr.io;  
  
import java.awt.Color;
import java.io.File;  
import java.io.FileOutputStream;  
import com.fr.base.FRContext;  
import com.fr.base.ModuleContext;  
import com.fr.base.background.ColorBackground;
import com.fr.dav.LocalEnv;
import com.fr.general.Background;
import com.fr.io.TemplateWorkBookIO;    
import com.fr.io.exporter.EmbeddedTableDataExporter;
import com.fr.main.impl.WorkBook;
import com.fr.main.parameter.ReportParameterAttr;
import com.fr.report.module.EngineModule;
  
public class SetParameterWindow {  
    public static void main(String[] args) {  
        try {  
            // 定义报表运行环境,用于执行报表  
            String envPath = "D:\\FineReport\\develop\\code\\build\\package\\WebReport\\WEB-INF";  
            FRContext.setCurrentEnv(new LocalEnv(envPath));  
            ModuleContext.startModule(EngineModule.class.getName());  
            // 读取模板保存为WorkBook对象  
            WorkBook workbook = (WorkBook) TemplateWorkBookIO  
                    .readTemplateWorkBook(FRContext.getCurrentEnv(),  
                            "\\doc\\Primary\\Parameter\\Parameter.cpt");  
            // 获取WorkBook工作薄的参数属性ReportParameterAttr  
            ReportParameterAttr paraAttr = workbook.getReportParameterAttr();  
            /* 参数界面的布局
            * 0 : 靠左
            * 1 ：居中
            * 2 ： 靠右
            */
            paraAttr.setAlign(1);
            /*
            * 设置参数界面背景
            * ColorBackground ：颜色背景
            * GradientBackground ：渐变色背景
            * ImageBackground ：图片背景
            * PatternBackground ：图案背景
            * TextureBackground ：纹理背景
            */
            Background background = ColorBackground.getInstance(new Color(0,255,255));
            paraAttr.setBackground(background);
            // 重新设置参数属性,导出最终结果  
            workbook.setReportParameterAttr(paraAttr);  
            FileOutputStream outputStream = new FileOutputStream(new File(  
                    "D:\\newParameter.cpt"));  
            EmbeddedTableDataExporter templateExporter = new EmbeddedTableDataExporter();  
            templateExporter.export(outputStream, workbook);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}