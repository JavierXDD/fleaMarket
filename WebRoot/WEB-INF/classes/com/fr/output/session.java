package com.fr.output;

import javax.servlet.http.HttpSession;    
import java.io.IOException;    
import java.io.PrintWriter;    
import java.util.ResourceBundle;    
import java.lang.String;    
import javax.servlet.ServletException;    
import javax.servlet.http.HttpServlet;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;    

import com.fr.stable.CodeUtils;
import com.fr.web.utils.WebUtils;
    
public class session extends HttpServlet {    
    public void doGet(HttpServletRequest request,    
                      HttpServletResponse response)    
        throws IOException, ServletException    
    {    
     response.setContentType("text/html; charset=gb2312");    
    
     PrintWriter out = response.getWriter();    
     out.println("<html>");    
     out.println("<body>");    
     String urlid=request.getParameter("id");  //获取url通过ajax传递的值    
     HttpSession session=request.getSession(true);    
     if(urlid==""||urlid==null){    
     out.print("<form action=\"");    
     out.print("session\" ");    
     out.println("method=POST>");    
     out.println("set session:<input type=text name=sessionvalue>");    
     out.println("<input type=submit name=bbb value=tijiao>");    
     out.println("</form>");       
     if(request.getParameter("sessionvalue")!=null&&request.getParameter("sessionvalue")!=""){    
     session.setAttribute("sessionname", request.getParameter("sessionvalue"));    
     }    
     }    
     else{    
         urlid = CodeUtils.decodeText(urlid);
         session.setAttribute("sessionname",urlid);  //将值赋值给sessionname这个session中    
        out.println("<script language='javascript'>window.close();</script>");    
     }    
     out.println("</body>");    
     out.println("</html>");    
    }      
    public void doPost(HttpServletRequest request,    
                      HttpServletResponse response)    
        throws IOException, ServletException    
    {    
        doGet(request, response);    
    }            
}