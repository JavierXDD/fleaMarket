//解析某个目录下xml文件
package com.fr.data;  
  
public class XMLColumnNameType4Demo {  
    private int type = -1;  
    private String name = null;  
      
    public XMLColumnNameType4Demo(String name, int type) {  
        this.name = name;  
        this.type = type;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    public int getType() {  
        return type;  
    }  
  
    public void setType(int type) {  
        this.type = type;  
    }  
}