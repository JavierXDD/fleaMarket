package com.fr.data;


import com.fr.data.AbstractTableData;
import com.fr.general.data.TableDataException;

public class CustomTableData extends AbstractTableData {
    public CustomTableData() {
        
    }

    /**
     * 获取数据集的列数
     * @return 数据集的列
     * @throws TableDataException
     */
    public int getColumnCount() throws TableDataException {
        return 0;
    }

    /**
     * 获取数据集指定列的列名
     * @param columnIndex 指定列的索引
     * @return 指定列的列名
     * @throws TableDataException
     */
    public String getColumnName(int columnIndex) throws TableDataException {
        return null;
    }

    /**
     * 获取数据集的行数
     * @return 数据集数据行数
     * @throws TableDataException
     */
    public int getRowCount() throws TableDataException {
        return 0;
    }

    /**
     * 获取数据集指定位置上的值
     * @param rowIndex 指定的行索引
     * @param columnIndex  指定的列索引
     * @return  指定位置的值
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
