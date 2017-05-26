package com.fr.io;    
    
import java.io.File;    
import java.io.FileInputStream;    
import java.io.FileOutputStream;    
import java.io.OutputStream;    
import java.util.Iterator;

import com.fr.main.impl.WorkBook;   
import com.fr.io.importer.ExcelReportImporter;  
import com.fr.main.TemplateWorkBook;    
import com.fr.report.cell.CellElement;
import com.fr.report.cell.cellattr.CellPageAttr;
import com.fr.report.elementcase.AbstractElementCase;
    
public class ExcelToCptpage {    
    public static void main(String[] args) throws Exception {    
        File excelFile = new File("D:\\API.xls"); // 获取EXCEL文件    
        FileInputStream a = new FileInputStream(excelFile);    
        TemplateWorkBook tpl = new ExcelReportImporter().generateWorkBookByStream(a); 
 Iterator it = tpl.getReport(0).iteratorOfElementCase();
        
        while(it.hasNext()){
			AbstractElementCase ec = (AbstractElementCase)it.next();
			Iterator cellIt = ec.cellIterator();
			while(cellIt.hasNext()) {
				CellElement obj = (CellElement)cellIt.next();
				if (matchCell(obj, Integer.parseInt("1"), Integer.parseInt("0")))
				{
					CellPageAttr cpa = new CellPageAttr();
					cpa.setPageAfterRow(true);
					obj.setCellPageAttr(cpa);
				}

			}
		}
        OutputStream outputStream = new FileOutputStream(new File("D:\\abc.cpt")); // 转换成cpt模板    
        ((WorkBook) tpl).export(outputStream);    
    }    


private static boolean matchCell (CellElement cell, int row, int col)
{
    if (cell.getRow() == row && cell.getColumn() == col)
        return true;
    return false;
}
}