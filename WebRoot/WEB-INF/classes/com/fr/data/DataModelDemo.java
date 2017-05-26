package com.fr.data;
import javax.naming.*;
import javax.ejb.*;
import java.rmi.*;
import java.util.*;
import com.fr.data.AbstractTableData;
import examples.ejb.ejb20.basic.beanManaged.*;
public class DataModelDemo extends AbstractTableData {
	private String[] columnNames;
	private ArrayList valueList = null;
	public DataModelDemo() {
		String[] columnNames = { "Name", "Score" };
		this.columnNames = columnNames;
	}
	// 实现其他四个方法
	public int getColumnCount() {
		return columnNames.length;
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
	// 准备数据
	public void init() {
		// 确保只被执行一次
		if (valueList != null) {
			return;
		}
		// 保存得到的结果集
		valueList = new ArrayList();
		Context ctx = null;
		Account ac = null;
		AccountHome home = null;
		try {
			// Contact the AccountBean container (the "AccountHome") through
			// JNDI.
			ctx = new InitialContext();
			home = (AccountHome) ctx
					.lookup("java:/comp/env/BeanManagedAccountEJB");
			double balanceGreaterThan = 100;
			Collection col = home.findBigAccounts(balanceGreaterThan);
			if (col != null) {
				// 用对象保存数据
				Object[] objArray = null;
				Iterator iter = col.iterator();
				while (iter.hasNext()) {
					Account bigAccount = (Account) iter.next();
					objArray = new Object[2];
					objArray[0] = bigAccount.getPrimaryKey();
					objArray[1] = new Double(bigAccount.balance());
					// 在valueList中加入这一行数据
					valueList.add(objArray);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}