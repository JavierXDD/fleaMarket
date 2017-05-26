package com.fr.io;  
  
import java.io.File;  
import java.io.FileOutputStream;  
import com.fr.base.FRContext; 
import com.fr.base.ModuleContext;
import com.fr.base.Parameter;
import com.fr.dav.LocalEnv;
import com.fr.io.exporter.CSVExporter;
import com.fr.io.exporter.EmbeddedTableDataExporter;
import com.fr.io.exporter.Excel2007Exporter;
import com.fr.io.exporter.ExcelExporter;
import com.fr.io.exporter.PDFExporter;
import com.fr.io.exporter.TextExporter;
import com.fr.io.exporter.WordExporter;
import com.fr.main.impl.WorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.WriteActor;

  
public class ExportApi {  
    public static void main(String[] args) {  
        // 定义报表运行环境,才能执行报表  
        String envpath = "D:\\FineReport_7.1\\WebReport\\WEB-INF";  
        FRContext.setCurrentEnv(new LocalEnv(envpath));  
        ModuleContext.startModule(EngineModule.class.getName()); 
        ResultWorkBook rworkbook = null;  
        try {  
            // 未执行模板工作薄  
            WorkBook workbook = (WorkBook) TemplateWorkBookIO  
                    .readTemplateWorkBook(FRContext.getCurrentEnv(),  
                            "\\doc\\Primary\\Parameter\\Parameter.cpt");  
            // 获取报表参数并设置值，导出内置数据集时数据集会根据参数值查询出结果从而转为内置数据集  
            Parameter[] parameters = workbook.getParameters();  
            parameters[0].setValue("华东");  
            // 定义parametermap用于执行报表，将执行后的结果工作薄保存为rworkBook  
            java.util.Map parameterMap = new java.util.HashMap();  
            for (int i = 0; i < parameters.length; i++) {  
                parameterMap.put(parameters[i].getName(), parameters[i]  
                        .getValue());  
            }  
            // 定义输出流  
            FileOutputStream outputStream;  
            // 将未执行模板工作薄导出为内置数据集模板  
            outputStream = new FileOutputStream(new File("D:\\EmbExport.cpt"));  
            EmbeddedTableDataExporter templateExporter = new EmbeddedTableDataExporter();  
            templateExporter.export(outputStream, workbook);  
            // 将模板工作薄导出模板文件，在导出前您可以编辑导入的模板工作薄，可参考报表调用章节  
            outputStream = new FileOutputStream(new File("D:\\TmpExport.cpt"));  
            ((WorkBook) workbook).export(outputStream);
            // 将结果工作薄导出为2003Excel文件  
            outputStream = new FileOutputStream(new File("D:\\ExcelExport.xls"));  
            ExcelExporter ExcelExport = new ExcelExporter();  
            ExcelExport.export(outputStream, workbook.execute(parameterMap,new WriteActor()));  
            // 将结果工作薄导出为2007Excel文件  
            outputStream = new FileOutputStream(new File("D:\\ExcelExport.xls"));  
            Excel2007Exporter ExcelExport1 = new Excel2007Exporter();  
            ExcelExport.export(outputStream, workbook.execute(parameterMap,new WriteActor()));  
            // 将结果工作薄导出为Word文件  
            outputStream = new FileOutputStream(new File("D:\\WordExport.doc"));  
            WordExporter WordExport = new WordExporter();  
            WordExport.export(outputStream, workbook.execute(parameterMap,new WriteActor()));  
            // 将结果工作薄导出为Pdf文件  
            outputStream = new FileOutputStream(new File("D:\\PdfExport.pdf"));  
            PDFExporter PdfExport = new PDFExporter();  
            PdfExport.export(outputStream, workbook.execute(parameterMap,new WriteActor()));  
            // 将结果工作薄导出为Txt文件（txt文件本身不支持表格、图表等，被导出模板一般为明细表）  
            outputStream = new FileOutputStream(new File("D:\\TxtExport.txt"));  
            TextExporter TxtExport = new TextExporter();  
            TxtExport.export(outputStream, workbook.execute(parameterMap,new WriteActor()));  
            // 将结果工作薄导出为Csv文件  
            outputStream = new FileOutputStream(new File("D:\\CsvExport.csv"));  
            CSVExporter CsvExport = new CSVExporter();  
            CsvExport.export(outputStream, workbook.execute(parameterMap,new WriteActor()));
            outputStream.close();  
            ModuleContext.stopModules();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}