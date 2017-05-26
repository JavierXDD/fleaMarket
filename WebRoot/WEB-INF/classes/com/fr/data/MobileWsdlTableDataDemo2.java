package com.fr.data;

import java.util.logging.Logger;
import moblie.MobileCodeWSStub;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import com.fr.general.data.TableDataException;
import com.fr.third.javax.xml.namespace.QName;

public class MobileWsdlTableDataDemo2 extends AbstractTableData{
	private String[][] data;  
    
    public MobileWsdlTableDataDemo2() {  
  //      this.data = this.getData();  
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
 public static void main(String agrs[]){
//	public String[][] getData() {
		 try {
		      String url = "http://www.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
		      MobileCodeWSStub stub = new MobileCodeWSStub(url);
//		      MobileCodeWSStub.GetMobileCodeInfo aa = new MobileCodeWSStub.GetMobileCodeInfo();
		      MobileCodeWSStub.GetDatabaseInfo bb = new MobileCodeWSStub.GetDatabaseInfo();
//		      aa.setMobileCode("18795842832");
//		      String rs=stub.getMobileCodeInfo(aa).getGetMobileCodeInfoResult();
		     String[] p=stub.getDatabaseInfo(bb).getGetDatabaseInfoResult().getString();
		     String result[][] = new String[p.length][3];
		     String b1,b2,b3;
		     for(int i = 0;i<p.length;i++)
		     {		       
		    	 if(p[i].length()!=0)
		    	 {
		       b1 = p[i].substring(0, p[i].indexOf(" "));
		       b2 = p[i].substring(p[i].indexOf(" ")+1).substring(0,p[i].substring(p[i].indexOf(" ")+1).indexOf(" "));
		       b3 = p[i].substring(p[i].indexOf(" ")+1).substring(p[i].substring(p[i].indexOf(" ")+1).indexOf(" ")+1);
		       result[i][0] = b1;
		       result[i][1] = b2;
		       result[i][2] = b3;
		//       System.out.println(b1);
			}			
		   }
	//	     return result;
		} catch (org.apache.axis2.AxisFault e) {
			e.printStackTrace();
		} catch (java.rmi.RemoteException e) {
			e.printStackTrace();
		}
	//	return new String[][] { {} };
	}
 
    }

