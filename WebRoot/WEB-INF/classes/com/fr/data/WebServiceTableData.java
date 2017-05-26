package com.fr.data;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import com.fr.data.AbstractTableData;
import com.fr.general.data.TableDataException;

public class WebServiceTableData extends AbstractTableData{
	private String[][] data;
	
	public WebServiceTableData() {
		this.data = this.getData();
	}

	//获取列数
	public int getColumnCount() throws TableDataException {
		return data[0].length;
	}

	//获取列的名称为数组中第一行的值
	public String getColumnName(int columnIndex) throws TableDataException {
		return data[0][columnIndex];
	}

	//获取行数为数据的长度-1
	public int getRowCount() throws TableDataException {
		return data.length - 1;
	}

	//获取值
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex + 1][columnIndex];
	}

	public String[][] getData() {
		try {
			String endpoint = "http://localhost:8080/axis1/TestWS2TDClient.jws";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://localhost:8080/axis1/TestWS2TDClient.jws",
					"getTD"));
			String[][] ret = (String[][])call.invoke(new Object[] {});
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[][] {};
	}
	
	public static void main(String arg[])
	{
		try {
			String endpoint = "http://localhost:8080/axis1/TestWS2TDClient.jws";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://localhost:8080/axis1/TestWS2TDClient.jws",
					"getTD"));
			String[][] ret = (String[][])call.invoke(new Object[] {});
			System.out.println(ret[0][0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}