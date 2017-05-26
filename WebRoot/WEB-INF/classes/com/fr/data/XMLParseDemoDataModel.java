//解析某个目录下xml文件
package com.fr.data;  
  
import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  
import com.fr.base.FRContext; 
import com.fr.data.AbstractDataModel;  
import com.fr.general.ComparatorUtils;
import com.fr.general.data.TableDataException;
  
/** 
 * XMLParseDemoDataModel 
 *  
 * DataModel是获取数据的接口 
 *  
 * 这里通过init方法一次性取数后，构造一个二维表对象来实现DataModel的各个取数方法 
 */  
public class XMLParseDemoDataModel extends AbstractDataModel {  
    // 数据类型标识  
    public static final int COLUMN_TYPE_STRING = 0;  
    public static final int COLUMN_TYPE_INTEGER = 1;  
    public static final int COLUMN_TYPE_BOOLEAN = 2;  
  
    // 缓存取出来的数据  
    protected List row_list = null;  
  
    // 数据对应的节点路径  
    private String[] xPath;  
    // 节点路径下包含的需要取数的节点  
    private XMLColumnNameType4Demo[] columns;  
  
    private String filePath;  
  
    public XMLParseDemoDataModel(String filename, String[] xPath,  
            XMLColumnNameType4Demo[] columns) {  
        this.filePath = filename;  
        this.xPath = xPath;  
        this.columns = columns;  
    }  
  
    /** 
     * 取出列的数量 
     */  
    public int getColumnCount() throws TableDataException {  
        return columns.length;  
    }  
  
    /** 
     * 取出相应的列的名称 
     */  
    public String getColumnName(int columnIndex) throws TableDataException {  
        if (columnIndex < 0 || columnIndex >= columns.length)  
            return null;  
        String columnName = columns[columnIndex] == null ? null  
                : columns[columnIndex].getName();  
  
        return columnName;  
    }  
  
    /** 
     * 取出得到的结果集的总的行数 
     */  
    public int getRowCount() throws TableDataException {  
        this.init();  
        return row_list.size();  
    }  
  
    /** 
     * 取出相应位置的值 
     */  
    public Object getValueAt(int rowIndex, int columnIndex)  
            throws TableDataException {  
        this.init();  
        if (rowIndex < 0 || rowIndex >= row_list.size() || columnIndex < 0  
                || columnIndex >= columns.length)  
            return null;  
        return ((Object[]) row_list.get(rowIndex))[columnIndex];  
    }  
  
    /** 
     * 释放一些资源，取数结束后，调用此方法来释放资源 
     */  
    public void release() throws Exception {  
        if (this.row_list != null) {  
            this.row_list.clear();  
            this.row_list = null;  
        }  
    }  
  
    /** ************************************************** */  
    /** ***********以上是实现DataModel的方法*************** */  
    /** ************************************************** */  
  
    /** ************************************************** */  
    /** ************以下为解析XML文件的方法**************** */  
    /** ************************************************** */  
  
    // 一次性将数据取出来  
    protected void init() throws TableDataException {  
        if (this.row_list != null)  
            return;  
  
        this.row_list = new ArrayList();  
        try {  
            // 使用SAX解析XML文件， 使用方法请参见JAVA SAX解析  
            SAXParserFactory f = SAXParserFactory.newInstance();  
            SAXParser parser = f.newSAXParser();  
  
            parser.parse(new File(XMLParseDemoDataModel.this.filePath),  
                    new DemoHandler());  
        } catch (Exception e) {  
            e.printStackTrace();  
            FRContext.getLogger().error(e.getMessage(), e);  
        }  
    }  
  
    /** 
     * 基本原理就是解析器在遍历文件时 发现节点开始标记时，调用startElement方法 读取节点内部内容时，调用characters方法 
     * 发现节点结束标记时，调用endElement 
     */  
    private class DemoHandler extends DefaultHandler {  
        private List levelList = new ArrayList(); // 记录当前节点的路径  
        private Object[] values; // 缓存一条记录  
        private int recordIndex = -1; // 当前记录所对应的列的序号，-1表示不需要记录  
  
        public void startElement(String uri, String localName, String qName,  
                Attributes attributes) throws SAXException {  
            // 记录下  
            levelList.add(qName);  
  
            if (isRecordWrapTag()) {  
                // 开始一条新数据的记录  
                values = new Object[XMLParseDemoDataModel.this.columns.length];  
            } else if (needReadRecord()) {  
                // 看看其对应的列序号，下面的characters之后执行时，根据这个列序号来设置值存放的位置。  
                recordIndex = getColumnIndex(qName);  
            }  
        }  
  
        public void characters(char[] ch, int start, int length)  
                throws SAXException {  
            if (recordIndex > -1) {  
                // 读取值  
                String text = new String(ch, start, length);  
                XMLColumnNameType4Demo type = XMLParseDemoDataModel.this.columns[recordIndex];  
                Object value = null;  
                if (type.getType() == COLUMN_TYPE_STRING) {  
                    value = text;  
                }  
                if (type.getType() == COLUMN_TYPE_INTEGER) {  
                    value = new Integer(text);  
                } else if (type.getType() == COLUMN_TYPE_BOOLEAN) {  
                    value = new Boolean(text);  
                }  
  
                values[recordIndex] = value;  
            }  
        }  
  
        public void endElement(String uri, String localName, String qName)  
                throws SAXException {  
            try {  
                if (isRecordWrapTag()) {  
                    // 一条记录结束，就add进list中  
                    XMLParseDemoDataModel.this.row_list.add(values);  
                    values = null;  
                } else if (needReadRecord()) {  
                    recordIndex = -1;  
                }  
            } finally {  
                levelList.remove(levelList.size() - 1);  
            }  
        }  
  
        // 正好匹配路径，确定是记录外部的Tag  
        private boolean isRecordWrapTag() {  
            if (levelList.size() == XMLParseDemoDataModel.this.xPath.length  
                    && compareXPath()) {  
                return true;  
            }  
  
            return false;  
        }  
  
        // 需要记录一条记录  
        private boolean needReadRecord() {  
            if (levelList.size() == (XMLParseDemoDataModel.this.xPath.length + 1)  
                    && compareXPath()) {  
                return true;  
            }  
  
            return false;  
        }  
  
        // 是否匹配设定的XPath路径  
        private boolean compareXPath() {  
            String[] xPath = XMLParseDemoDataModel.this.xPath;  
            for (int i = 0; i < xPath.length; i++) {  
                if (!ComparatorUtils.equals(xPath[i], levelList.get(i))) {  
                    return false;  
                }  
            }  
  
            return true;  
        }  
  
        // 获取该字段的序号  
        private int getColumnIndex(String columnName) {  
            XMLColumnNameType4Demo[] nts = XMLParseDemoDataModel.this.columns;  
            for (int i = 0; i < nts.length; i++) {  
                if (ComparatorUtils.equals(nts[i].getName(), columnName)) {  
                    return i;  
                }  
            }  
  
            return -1;  
        }  
    }  
}