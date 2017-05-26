//解析某个目录下xml文件
package com.fr.data;    

import java.io.BufferedInputStream;  
import java.io.ByteArrayInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.InputStream;  
import java.io.Reader;  
import java.util.*; 
import javax.xml.stream.XMLEventReader;  
import javax.xml.stream.XMLInputFactory;  
import javax.xml.stream.XMLStreamException;  
import javax.xml.stream.events.XMLEvent; 
import com.fr.base.Parameter;  
import com.fr.data.AbstractParameterTableData;
import com.fr.general.data.DataModel;
import com.fr.script.Calculator;    
import com.fr.stable.StringUtils;
    
/**  
 *  XMLDemoTableData  
 *   
 *  这是一个按参数来解析不同地址XML文件的demo  
 *   
 *  AbstractParameterTableData 包装了有参数数据集的基本实现  
 */    
public class XMLDemoTableData extends AbstractParameterTableData {    
        
    // 构造函数    
    public XMLDemoTableData() {    
        // 定义需要的参数，这里定义一个参数，参数名为filename，给其一个默认值"Northwind.xml"    
        this.parameters = new Parameter[1];    
        this.parameters[0] = new Parameter("filename", "Northwind");    
    }    
    
    /**  
     * 返回获取数据的执行对象  
     * 系统取数时，调用此方法来返回一个获取数据的执行对象  
     * 注意！ 当数据集需要根据不同参数来多次取数时，此方法在一个计算过程中会被多次调用。  
     */    
    @SuppressWarnings("unchecked")  
    public DataModel createDataModel(Calculator calculator) {    
        // 获取传进来的参数    
        Parameter[] params = super.processParameters(calculator);    
            
        // 根据传进来的参数，等到文件的路径    
        String filename = null;    
        for (int i = 0; i < params.length; i++) {    
            if (params[i] == null) continue;    
                
            if ("filename".equals(params[i].getName())) {    
                filename = (String)params[i].getValue();    
            }    
        }    
            
        String filePath;    
        if (StringUtils.isBlank(filename)) {    
            filePath = "D://DefaultFile.xml";    
        } else {    
            filePath = "D://" + filename + ".xml";    
        }    
            
        // 定义需要解析的数据列，机器    
//        XMLColumnNameType4Demo[] columns = new XMLColumnNameType4Demo[7];    
//        columns[0] = new XMLColumnNameType4Demo("CustomerID", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
//        columns[1] = new XMLColumnNameType4Demo("CompanyName", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
//        columns[2] = new XMLColumnNameType4Demo("ContactName", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
//        columns[3] = new XMLColumnNameType4Demo("ContactTitle", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
//        columns[4] = new XMLColumnNameType4Demo("Address", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
//        columns[5] = new XMLColumnNameType4Demo("City", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
//        columns[6] = new XMLColumnNameType4Demo("Phone", XMLParseDemoDataModel.COLUMN_TYPE_STRING);    
            
        List list=new ArrayList();  
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();  
        InputStream in;  
        try {  
            in = new BufferedInputStream(new FileInputStream(new File(filePath)));  
            XMLEventReader reader = inputFactory.createXMLEventReader(in);  
            readCol(reader,list);  
            in.close();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        XMLColumnNameType4Demo[] columns=(XMLColumnNameType4Demo[])list.toArray(new XMLColumnNameType4Demo[0]);  
          
          
        // 定义解析的数据在xml文件结构中的位置    
        String[] xpath = new String[2];    
        xpath[0] = "Northwind";    
        xpath[1] = "Customers";    
        /*  
         * 说明  
         * 提供的样例xml文件的格式是：  
         * <Notrhwind>  
         *     <Customers>  
         *         <CustomerID>ALFKI</CustomerID>  
         *         <CompanyName>Alfreds Futterkiste</CompanyName>  
         *         <ContactName>Maria Anders</ContactName>  
         *         <ContactTitle>Sales Representative</ContactTitle>  
         *         <Address>Obere Str. 57</Address>  
         *         <City>Berlin</City>  
         *         <PostalCode>12209</PostalCode>  
         *         <Country>Germany</Country>  
         *         <Phone>030-0074321</Phone>  
         *         <Fax>030-0076545</Fax>  
         *     </Customers>  
         * </Northwind>  
         *   
         * 上面定义的意义就是  
         * /Northwind/Customers路径所表示的一个Customers节点为一条数据，它包含的节点中的CustomerID...等等是需要获取的列值  
         */    
            
        // 构造一个实际去取值的执行对象    
        return new XMLParseDemoDataModel(filePath, xpath, columns);    
    }  
    private int deep=0;  
    private static final int COL_DEEP=3;  
    private boolean flag=false;  
    private  void readCol(XMLEventReader reader,List list)  
            throws XMLStreamException {  
        while (reader.hasNext()) {  
            XMLEvent event = reader.nextEvent();  
            if (event.isStartElement()) {  
                //deep是控制层数的，只把xml中对应的层的加入到列名中  
                deep++;  
                //表示已经进入到了列名那一层  
                if(deep==COL_DEEP){  
                    flag=true;  
                }  
                //如果在高层，并且已经进入到了col层，则退出  
                if(deep<COL_DEEP&&flag){  
                    return;  
                }  
                if(deep!=COL_DEEP){  
                    continue;  
                }  
//              println("name: " + event.asStartElement().getName());  
                XMLColumnNameType4Demo column=new XMLColumnNameType4Demo(event.asStartElement().getName().toString(), XMLParseDemoDataModel.COLUMN_TYPE_STRING);  
                list.add(column);  
                readCol(reader,list);  
            } else if (event.isCharacters()) {  
                //对数据值不做处理  
            } else if (event.isEndElement()) {  
                deep--;  
                return;  
            }  
        }  
    }  
      
    private  void readCol0(XMLEventReader reader)  
            throws XMLStreamException {  
        while (reader.hasNext()) {  
            XMLEvent event = reader.nextEvent();  
            if (event.isStartElement()) {  
                //deep是控制层数的，只把xml中对应的层的加入到列名中  
                deep++;  
                //表示已经进入到了列名那一层  
                if(deep==COL_DEEP){  
                    flag=true;  
                }  
                //如果在高层，并且已经进入到了col层，则退出  
                if(deep<COL_DEEP&&flag){  
                    return;  
                }  
                if(deep!=COL_DEEP){  
                    continue;  
                }  
                System.out.println("name: " + event.asStartElement().getName());  
                readCol0(reader);  
            } else if (event.isCharacters()) {  
                //对数据值不做处理  
            } else if (event.isEndElement()) {  
                deep--;  
                return;  
            }  
        }  
    }  
    public static void main(String[] args){  
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();  
//      in = new FileReader(new File(filePath));  
//      XMLEventReader reader = inputFactory.createXMLEventReader(in);  
//      readCol(reader,list);  
        BufferedInputStream in;  
        try {  
            in = new BufferedInputStream(new FileInputStream(new File("D:/tmp/f.xml")));  
            byte[] ba=new byte[3];  
            in.read(ba,0,3);  
//      System.out.println(in)  
        XMLEventReader reader = inputFactory.createXMLEventReader(in);  
        new XMLDemoTableData().readCol0(reader);  
        } catch (Exception e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
    }  
}