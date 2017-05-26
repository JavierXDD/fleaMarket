//单元格格式设置
package com.fr.demo;  
  
import java.awt.Color;  
import java.awt.Font;
import java.util.Map;

import com.fr.base.Style;  
import com.fr.base.background.ColorBackground;  
import com.fr.general.FRFont;
import com.fr.report.cell.DefaultTemplateCellElement;
import com.fr.report.cell.TemplateCellElement;
import com.fr.report.worksheet.WorkSheet;
import com.fr.stable.Constants;
import com.fr.stable.unit.OLDPIX;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest;
import com.fr.main.TemplateWorkBook;  
import com.fr.main.impl.WorkBook;
  
  
public class SetCellElementStyle extends Reportlet {  
    public TemplateWorkBook createReport(ReportletRequest arg0) {  
        // 新建报表  
        WorkBook workbook = new WorkBook();  
        WorkSheet worksheet = new WorkSheet();  
        // 新建一个单元格，位置为(1,1),列占2单元格，行占2单元格，文本值为 "FineReport"  
        TemplateCellElement cellElement = new DefaultTemplateCellElement(1, 1,  
                2, 2, "FineReport");  
        // 设置列宽为300px，设置行高为30px  
        worksheet.setColumnWidth(1, new OLDPIX(300));  
        worksheet.setRowHeight(1, new OLDPIX(30));  
        // 得到CellElement的样式，如果没有新建默认样式  
        Style style = cellElement.getStyle();  
        if (style == null) {  
            style = Style.getInstance();  
        }  
        // 设置字体和前景的颜色  
        FRFont frFont = FRFont.getInstance("Dialog", Font.BOLD, 16);  
        frFont = frFont.applyForeground(new Color(21, 76, 160));  
        style = style.deriveFRFont(frFont);  
        // 设置背景  
        ColorBackground background = ColorBackground.getInstance(new Color(255,  
                255, 177));  
        style = style.deriveBackground(background);  
        // 设置水平居中  
        style = style.deriveHorizontalAlignment(Constants.CENTER);  
        // 设置边框  
        style = style.deriveBorder(Constants.LINE_DASH, Color.red,  
                Constants.LINE_DOT, Color.gray, Constants.LINE_DASH_DOT,  
                Color.BLUE, Constants.LINE_DOUBLE, Color.CYAN);  
        // 改变单元格的样式  
        cellElement.setStyle(style);  
        // 将单元格添加到报表中  
        worksheet.addCellElement(cellElement);  
        workbook.addReport(worksheet);  
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