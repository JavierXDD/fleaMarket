//创建程序报表
package com.fr.demo;  

import com.fr.base.Style;  
import com.fr.general.FRFont;  
import com.fr.main.TemplateWorkBook; 
import com.fr.main.impl.WorkBook;
import com.fr.report.cell.DefaultTemplateCellElement;  
import com.fr.report.cell.TemplateCellElement; 
import com.fr.report.worksheet.WorkSheet;
import com.fr.stable.unit.OLDPIX;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest; 
import java.awt.Color;  
import java.util.Map;
  
public class CreateReportletDemo extends Reportlet  
{  
  public TemplateWorkBook createReport(ReportletRequest arg0)  
  {  
	//创建一个WorkBook工作薄，在工作薄中插入一个WorkSheet	
	WorkBook workbook = new WorkBook();  
    WorkSheet sheet1 = new WorkSheet();  
  
    TemplateCellElement CellA1 = new DefaultTemplateCellElement(0, 0,   
      "FineReport");  
    Style style = Style.getInstance();  
  
    FRFont frfont = FRFont.getInstance("Arial", 1, 20.0F, Color.red);  
    style = style.deriveFRFont(frfont);  
    CellA1.setStyle(style);  
    sheet1.addCellElement(CellA1);  
  
    sheet1.setColumnWidth(0, new OLDPIX(150.0F));  
    sheet1.setRowHeight(1, new OLDPIX(35.0F));  
    workbook.addReport(sheet1);  
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