package com.fr.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import com.fr.base.Env;
import com.fr.base.FRContext;
import com.fr.data.AbstractTableData;
import com.fr.base.Parameter;

public class ParamTableDataDemo extends AbstractTableData {
	// 列名数组，保存程序数据集所有列名
	private String[] columnNames = null;
	// 定义程序数据集的列数量
	private int columnNum = 10;
	// 保存查询表的实际列数量
	private int colNum = 0;
	// 保存查询得到列值
	private ArrayList valueList = null;

	// 构造函数，定义表结构，该表有10个数据列，列名为column#0，column#1，。。。。。。column#9
	public ParamTableDataDemo() {
		// 定义tableName参数
		setDefaultParameters(new Parameter[] { new Parameter("tableName") });
		// 定义程序数据集列名
		columnNames = new String[columnNum];
		for (int i = 0; i < columnNum; i++) {
			columnNames[i] = "column#" + String.valueOf(i);
		}
	}

	// 实现其他四个方法
	public int getColumnCount() {
		return columnNum;
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
		if (columnIndex >= colNum) {
			return null;
		}
		return ((Object[]) valueList.get(rowIndex))[columnIndex];
	}

	// 准备数据
	public void init() {
		// 确保只被执行一次
		if (valueList != null) {
			return;
		}
		// 保存得到的数据库表名
		String tableName = parameters[0].getValue().toString();
		// 构造SQL语句,并打印出来
		String sql = "select * from " + tableName + ";";
		FRContext.getLogger().info("Query SQL of ParamTableDataDemo: \n" + sql);
		// 保存得到的结果集
		valueList = new ArrayList();
		// 下面开始建立数据库连接，按照刚才的SQL语句进行查询
		Connection conn = this.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// 获得记录的详细信息，然后获得总列数
			ResultSetMetaData rsmd = rs.getMetaData();
			colNum = rsmd.getColumnCount();
			// 用对象保存数据
			Object[] objArray = null;
			while (rs.next()) {
				objArray = new Object[colNum];
				for (int i = 0; i < colNum; i++) {
					objArray[i] = rs.getObject(i + 1);
				}
				// 在valueList中加入这一行数据
				valueList.add(objArray);
			}
			// 释放数据库资源
			rs.close();
			stmt.close();
			conn.close();
			// 打印一共取到的数据行数量
			FRContext.getLogger().info(
					"Query SQL of ParamTableDataDemo: \n" + valueList.size()
							+ " rows selected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取数据库连接 driverName和 url 可以换成您需要的
	public Connection getConnection() {
		
		String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\FineReport_7.1\\WebReport\\FRDemo.mdb";
		String username = "";
		String password = "";
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
