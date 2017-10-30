 package org.xk.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Snippet  extends HttpServlet{
     public void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
    
           this.doPost(req, resp);
        }
    
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         resp.setContentType("text/html; charset=UTF-8");
            resp.setContentType("image/jpeg");    //设置图片格式格式，这里可以忽略
            
            FileInputStream fis = new FileInputStream("D:\\DownLoad\\original.jpg");
            OutputStream os = resp.getOutputStream();
            
            try {
                int count = 0;
                byte[] buffer = new byte[1024*1024];
                while ( (count = fis.read(buffer)) != -1 )
                    os.write(buffer, 0, count);
            } catch (IOException e){  
               e.printStackTrace();  
    
             }finally {
    
       if(os!=null)
    
                os.close();
    
                if(fis != null)
    
       fis.close();
            }
     }
    
}