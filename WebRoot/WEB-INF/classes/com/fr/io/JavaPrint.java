package com.fr.io;    

import java.io.File;    
import java.util.HashMap; 
import com.fr.base.FRContext;      
import com.fr.base.Parameter;    
import com.fr.dav.LocalEnv;
import com.fr.main.TemplateWorkBook; 
import com.fr.print.PrintUtils;  
   
  
public class JavaPrint {    
     public static void main(String[] args) {    
         // 定义报表运行环境,才能执行报表    
         String envPath = "D:\\FineReport\\develop\\code\\build\\package\\WebReport\\WEB-INF";    
         FRContext.setCurrentEnv(new LocalEnv(envPath));    
         try {    
             TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(), "GettingStarted.cpt");    
             // 参数传值    
             Parameter[] parameters = workbook.getParameters();    
             HashMap<String, String> paraMap = new HashMap<String, String>();  
             paraMap.put(parameters[0].getName(), "华北");  
               
             // java中调用报表打印方法    
             boolean a = PrintUtils.printWorkBook("GettingStarted.cpt", paraMap, true);    
             if (a == false) {    
                 System.out.println("失败啦！返回" + a);    
             } else {    
                 System.out.println("成功！返回" + a);    
             }    
         } catch (Exception e) {    
             e.printStackTrace();    
         }    
     }    
 }