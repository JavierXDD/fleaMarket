//遍历单元格
package com.fr.demo;  
  
import java.util.Map;

import com.fr.base.Env;
import com.fr.base.FRContext;   
import com.fr.report.cell.TemplateCellElement;  
import com.fr.report.elementcase.TemplateElementCase;  
import com.fr.report.worksheet.WorkSheet;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest;
import com.fr.main.TemplateWorkBook;  
import com.fr.io.TemplateWorkBookIO;  

  
public class ChangeRowAndCol extends Reportlet {  
    public TemplateWorkBook createReport(ReportletRequest reportletrequest) {  
        // 定义最终需要返回的WorkBook对象  
        TemplateWorkBook workbook = null;  
        Env oldEnv = FRContext.getCurrentEnv();  
        WorkSheet newworksheet = new WorkSheet();  
        String change = "0";  
        try {  
            // 读取模板保存为WorkBook对象  
            workbook = TemplateWorkBookIO.readTemplateWorkBook(oldEnv,  
                    "\\doc\\Primary\\GroupReport\\Group.cpt");  
            // 读取请求中的参数判断是否需要切换行列显示，0表示不切换，1表示切换  
            if (reportletrequest.getParameter("change") != null) {  
                change = reportletrequest.getParameter("change").toString();  
            }  
            if (change.equals("1")) {  
                // 获得单元格需要首先获得单元格所在的报表  
                TemplateElementCase report = (TemplateElementCase) workbook  
                        .getTemplateReport(0);  
                // 遍历单元格  
                int col = 0, row = 0;  
                byte direction = 0;  
                java.util.Iterator it = report.cellIterator();  
                while (it.hasNext()) {  
                    TemplateCellElement cell = (TemplateCellElement) it.next();  
                    // 获取单元格的行号与列号并互换  
                    col = cell.getColumn();  
                    row = cell.getRow();  
                    cell.setColumn(row);  
                    cell.setRow(col);  
                    // 获取原单元格的扩展方向，0表示纵向扩展，1表示横向扩展  
                    direction = cell.getCellExpandAttr().getDirection();  
                    if (direction == 0) {  
                        cell.getCellExpandAttr().setDirection((byte) 1);  
                    } else if (direction == 1) {  
                        cell.getCellExpandAttr().setDirection((byte) 0);  
                    }  
                    // 将改变后的单元格添加进新的WorkSheet中  
                    newworksheet.addCellElement(cell);  
                }  
                // 替换原sheet  
                workbook.setReport(0, newworksheet);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
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