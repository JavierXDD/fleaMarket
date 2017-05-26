package com.fr.demo;  
  
import java.io.InputStream;  
import java.sql.Blob;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;
import java.util.Map;

import com.fr.base.Env;
import com.fr.base.FRContext;
import com.fr.main.TemplateWorkBook;
import com.fr.main.impl.WorkBook;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest;

  
public class ReadFromDatabase extends Reportlet {  
    public TemplateWorkBook createReport(ReportletRequest reportletRequest) {  
    	Env oldEnv = FRContext.getCurrentEnv();   
        WorkBook workbook = new WorkBook();  
        String name = reportletRequest.getParameter("cptname").toString();  
        try {  
            // 定义数据连接  
            String driver = "com.mysql.jdbc.Driver";  
            String url = "jdbc:mysql://192.168.100.252:3306/test?useUnicode=true&characterEncoding=gbk";  
            String user = "root";  
            String pass = "mysql252";  
            Class.forName(driver);  
            Connection conn = DriverManager.getConnection(url, user, pass);  
            // 从数据库中读模板  
            String sql = "select cpt from test.report1 where cptname = '" + name  
                    + "'";  
            Statement smt = conn.createStatement();  
            ResultSet rs = smt.executeQuery(sql);  
            while (rs.next()) {  
                Blob blob = rs.getBlob(1); // 取第一列的值，即cpt列  
                FRContext.getLogger().info(blob.toString());
                InputStream ins = blob.getBinaryStream();  
                workbook.readStream(ins);  
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