package com.fr.data;

import com.fr.base.FRContext;
import com.fr.base.Parameter;
import com.fr.function.ConnectSAPServer;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class ParamSAPDataTest extends AbstractTableData
{
  private String[] columnNames = null;

  private int columnNum = 3;


  private String[][] rowData;
  private static JCoDestination jCoDestination;

  public ParamSAPDataTest()
  {
    this.parameters = new Parameter[] { new Parameter("LIFNR"), 
      new Parameter("NAME1") };

    this.columnNames = new String[this.columnNum];
    this.columnNames[0] = "供应商编码";
    this.columnNames[1] = "供应商名称";
    this.columnNames[2] = "供应商地址";
  }

  public int getColumnCount() {
    return this.columnNum;
  }

  public String getColumnName(int columnIndex) {
    return this.columnNames[columnIndex];
  }

  public int getRowCount() {
    try {
      init();
    }
    catch (JCoException e) {
      FRContext.getLogger().info("失败");
    }
    return this.rowData.length;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    try {
      init();
    }
    catch (JCoException e) {
      FRContext.getLogger().info("失败");
    }
    if (columnIndex >= this.columnNum) {
      return null;
    }
    return this.rowData[rowIndex][columnIndex];
  }

  public void init() throws JCoException {
    if (this.rowData != null) {
      return;
    }
    try
    {
    jCoDestination = ConnectSAPServer.Connect();
    }catch (Exception e) {
        FRContext.getLogger().info("失败");
      }
    JCoFunction function = jCoDestination.getRepository().getFunction("Z_LFA3_QUERY");
    if (function == null)
      throw new RuntimeException(
        "Function not found in SAP.");
    function.getImportParameterList().setValue("LIFNR", "%"+this.parameters[0].getValue().toString().toUpperCase().trim()+"%");
    function.getImportParameterList().setValue("NAME1", "%"+this.parameters[1].getValue().toString().toUpperCase().trim()+"%");
    function.execute(jCoDestination);
    JCoTable returnTable = function.getTableParameterList().getTable(
      "ZLFA1S3");
    rowData=new String[20][3];
    if (returnTable.getNumRows() > 0) {
      returnTable.firstRow();
      for (int i = 0; i < 20; )
      {
        String[] objArray = new String[this.columnNum];
        objArray[0] = returnTable.getString("LIFNR");
        objArray[1] = returnTable.getString("NAME1");
        objArray[2] = returnTable.getString("STRAS");
        this.rowData[i]=objArray;

        i++; returnTable
          .nextRow();
      }

      FRContext.getLogger().info(
        "Query SQL of ParamSAPDataTest: \n" + this.rowData.length + 
        " rows selected");
    }
  }

  public void release() throws Exception {
    super.release();
    this.rowData = null;
  }
}
