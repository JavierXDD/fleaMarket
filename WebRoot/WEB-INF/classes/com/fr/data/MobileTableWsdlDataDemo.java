package com.fr.data;  
  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import org.apache.axiom.om.OMAbstractFactory;  
import org.apache.axiom.om.OMElement;  
import org.apache.axiom.om.OMFactory;  
import org.apache.axiom.om.OMNamespace;  
import org.apache.axis2.addressing.EndpointReference;  
import org.apache.axis2.client.Options;  
import org.apache.axis2.client.ServiceClient;  
import com.fr.general.data.TableDataException;  
  
public class MobileTableWsdlDataDemo extends AbstractTableData{  
    private String[][] data;    
      
    public MobileTableWsdlDataDemo() {    
        this.data = this.getData();    
    }  
      
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
    
    public static String[][] getResults(OMElement element) {  
        if (element == null) {  
            return null;  
        }  
        Iterator iterator = element.getChildElements();  
        Iterator innerItr;  
        List<String> list = new ArrayList<String>();  
        OMElement result = null;  
        while (iterator.hasNext()) {  
            result = (OMElement) iterator.next();  
            innerItr = result.getChildElements();  
            while (innerItr.hasNext()) {   
                OMElement elem = (OMElement)innerItr.next();    
               list.add(elem.getText());  
            }  
        }  
        String[] result1 = list.toArray(new String[list.size()]);  
        String results[][] = new String[result1.length][3];  
        String b1, b2, b3;  
        for (int i = 0; i < result1.length; i++) {  
            if (result1[i].length() != 0) {  
                b1 = result1[i].substring(0, result1[i].indexOf(" "));  
                b2 = result1[i].substring(result1[i].indexOf(" ") + 1).substring(0,result1[i].substring(result1[i].indexOf(" ") + 1).indexOf(" "));  
                b3 = result1[i].substring(result1[i].indexOf(" ") + 1).substring(result1[i].substring(result1[i].indexOf(" ") + 1).indexOf(" ") + 1);  
                results[i][0] = b1;  
                results[i][1] = b2;  
                results[i][2] = b3;  
            }  
        }  
        return results;  
}  
  
   
    public String[][] getData() {  
        try {  
            String url = "http://www.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";  
              EndpointReference targetEPR = new EndpointReference(url);             
              //创建一个OMFactory，下面的namespace、方法与参数均需由它创建    
              OMFactory fac = OMAbstractFactory.getOMFactory();  
              // 命名空间  
              OMNamespace omNs = fac.createOMNamespace("http://WebXml.com.cn", "a");  
              //下面创建的是参数对数  
             /*  
              *OMElement symbol = fac.createOMElement("mobileCode", omNs); 
              symbol.addChild(fac.createOMText(symbol, "18795842")); 
              */  
              //下面创建一个method对象  ,方法  
              OMElement method = fac.createOMElement("getDatabaseInfo", omNs);  
              // method.addChild(symbol);               
              Options options = new Options();              
              options.setTo(targetEPR);  
              options.setAction("http://WebXml.com.cn/getDatabaseInfo");  
              ServiceClient sender = new ServiceClient();  
              sender.setOptions(options);      
              OMElement result1 = sender.sendReceive(method);  
              String[][] result = getResults(result1);  
             return result;  
        } catch (org.apache.axis2.AxisFault e) {  
            e.printStackTrace();  
        } catch (java.rmi.RemoteException e) {  
            e.printStackTrace();  
        }  
        return new String[][] { {} };  
    }  
   
    } 