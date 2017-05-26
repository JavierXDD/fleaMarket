//解析数据库内xml文件
package com.fr.data;  
  
import java.io.InputStream;   
import java.io.StringBufferInputStream;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
import java.util.ArrayList;    
import com.fr.data.AbstractTableData;  
  
public class XMLRead extends AbstractTableData {  
    // 列名数组，保存程序数据集所有列名  
    private String[] columnNames = { "id", "name", "MemoryFreeSize",  
            "MemoryTotalSize", "MemoryUsage" };  
    // 保存表数据  
    private ArrayList valueList = null;  
  
    public int getColumnCount() {  
        return 5;  
    }  
  
    public String getColumnName(int columnIndex) {  
        return columnNames[columnIndex];  
    }  
  
    public int getRowCount() {  
        init();  
        return valueList.size();  
    }  
  
    public Object getValueAt(int rowIndex, int columnIndex) {  
        init();  
        return ((Object[]) valueList.get(rowIndex))[columnIndex];  
    }  
  
    private void init() {  
        // 确保只被执行一次  
        if (valueList != null) {  
            return;  
        }  
        valueList = new ArrayList();  
        String sql = "select * from xmltest";  
        String[] name = { "MemoryFreeSize", "MemoryTotalSize", "MemoryUsage" };  
        Connection conn = this.getConncetion();  
        try {  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery(sql);  
            // 用对象保存数据  
            Object[] objArray = null;  
            while (rs.next()) {  
                objArray = new Object[5];  
                String[] xmldata = null;  
                objArray[0] = rs.getObject(1);  
                objArray[1] = rs.getObject(2);  
                InputStream in = new StringBufferInputStream("<demo>"  
                        + rs.getObject(3).toString() + "</demo>");  
                GetXmlDate getxmldata = new GetXmlDate();  
                // 对xml流进行解析，返回的为name对应的value值数组  
                xmldata = getxmldata.readerXMLSource(in, name);  
                // 将解析后的值存于最终结果ArrayList中  
                objArray[2] = xmldata[0];  
                objArray[3] = xmldata[1];  
                objArray[4] = xmldata[2];  
                valueList.add(objArray);  
            }  
            // 释放数据源  
            rs.close();  
            stmt.close();  
            conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public Connection getConncetion() {  
        String driverName = "oracle.jdbc.driver.OracleDriver";  
        String url = "jdbc:oracle:thin:@192.168.100.169:1521:orcl10g";  
        String username = "temp";  
        String password = "temp123";  
        Connection con = null;  
  
        try {  
            Class.forName(driverName);  
            con = DriverManager.getConnection(url, username, password);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
        return con;  
  
    }  
  
    // 释放一些资源，因为可能会有重复调用，所以需释放valueList，将上次查询的结果释放掉  
    public void release() throws Exception {  
        super.release();  
        this.valueList = null;  
    }  
}