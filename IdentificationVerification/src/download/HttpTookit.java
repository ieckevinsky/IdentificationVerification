package download;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpTookit 
{
	/***
	 * 下载二进制内容，如图片
	 * @param url
	 * @param refer
	 * @return
	 */
	public static byte[] doGetBinary(String url,String refer,String proxyIP,String proxyPort,String cookie){
    	Logger lg = Logger.getLogger("HLDrools");
    	byte[] returnData = null;
    	HttpClient httpclient = new DefaultHttpClient();
         try 
         {
        	 httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
        	 httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
             HttpGet httpGet = new HttpGet(url);
             if(refer == null || refer.length() == 0){
            	 refer = url;
             }

        	 //设置代理服务器的ip地址和端口
        	 if(proxyIP != null && proxyPort != null){
	        	 HttpHost proxy = new HttpHost(proxyIP,Integer.valueOf(proxyPort).intValue());
	        	 httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
        	 }
        	 
             //設置httpGet的头部參數信息
             httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
             //httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");    
             //httpGet.setHeader("Accept-Encoding", "gzip,deflate");         
             //httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
             httpGet.setHeader("Connection", "keep-alive");   
             if(cookie != null)
            	 httpGet.setHeader("Cookie", cookie);
             //httpGet.setHeader("Host", "www.cnblogs.com");
             httpGet.setHeader("refer", refer);
             httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
             
             
             HttpResponse response = httpclient.execute(httpGet);
             int nCode = response.getStatusLine().getStatusCode();
             if(nCode == 200)
             {
            	 HttpEntity entity = response.getEntity();
	             if (entity != null) 
	             {
	            	 returnData = EntityUtils.toByteArray(entity);
	             }
             }else{
            	 System.out.println(url+" resCode:"+ nCode);
             }
         }
         catch (ClientProtocolException e) 
         {
			if (lg != null)
				lg.error(e.toString());
			e.printStackTrace();
		}
        catch (IOException e) 
		{
			if (lg != null)
				lg.error(e.toString());
			e.printStackTrace();
		}
        catch (Exception e) 
        {
			if (lg != null)
				lg.error(e.toString());
			e.printStackTrace();
		}
        
        finally 
        {
        	httpclient.getConnectionManager().shutdown();
        }
		return returnData;
    }
	
    public static String doGet(String url) 
    { 
    	Logger lg = Logger.getLogger("HLDrools");
    	String responseBody="";
    	boolean bOk = false; 
    	HttpClient httpclient = new DefaultHttpClient();
         try 
         {
        	 httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
        	 httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
             HttpGet httpget = new HttpGet(url);
             HttpResponse response = httpclient.execute(httpget);
             int nCode = response.getStatusLine().getStatusCode();
             if(nCode == 200)
             {
            	 HttpEntity entity = response.getEntity();
	             if (entity != null) 
	             {
	            	 //responseBody = EntityUtils.toString(entity);
	            	 responseBody = new String(EntityUtils.toByteArray(entity),"UTF-8");
	                 bOk = true;
	             }
             }
         }
         catch (ClientProtocolException e) 
         {
			if (lg != null)
				lg.error(e.toString());
		}
        catch (IOException e) 
		{
			if (lg != null)
				lg.error(e.toString());
		}
        catch (Exception e) 
        {
			if (lg != null)
				lg.error(e.toString());
		}
        
        finally 
        {
        	httpclient.getConnectionManager().shutdown();
        }

        return responseBody; 
       
    }
    
    public static boolean doDownload(String url,String strFile) 
    { 
    	Logger lg = Logger.getLogger("HLDrools");
    	boolean bOk = false; 
    	HttpClient httpclient = new DefaultHttpClient();
         try 
         {
        	 httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
        	 httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
        	 
             HttpGet httpget = new HttpGet(url);
             HttpResponse response = httpclient.execute(httpget);
             int nCode = response.getStatusLine().getStatusCode();
             if(nCode == 200)
             {
            	 HttpEntity entity = response.getEntity();
	             if (entity != null) 
	             {
	                 byte[] bytes = EntityUtils.toByteArray(entity);
	                 File storeFile = new File(strFile);  
	                 FileOutputStream output = new FileOutputStream(storeFile);  
	      
	                 output.write(bytes);  
	                 output.close();
	                 bOk = true;
	             }
             }
         }
         catch (ClientProtocolException e) 
         {
        	 if (lg != null)
 				lg.error(e.toString());
		}
        catch (IOException e) 
		{
        	if (lg != null)
				lg.error(e.toString());
		}
        catch (Exception e) 
        {
			if (lg != null)
				lg.error(e.toString());
		}
        finally 
        {
        	httpclient.getConnectionManager().shutdown();
        }

        return bOk; 
    }
     
    public static String doGetLastModify(String url) 
    { 
    	Logger lg = Logger.getLogger("HLDrools");
    	boolean bOk = false;
    	String strLastModify="";
    	HttpClient httpclient = new DefaultHttpClient();
         try 
         {
        	 httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
        	 httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
             HttpGet httpget = new HttpGet(url);
             HttpResponse response = httpclient.execute(httpget);
             int nCode = response.getStatusLine().getStatusCode();

             if(nCode == 200)
             {
                 Header[] aHeaders = response.getHeaders(HttpHeaders.LAST_MODIFIED);
                 if (aHeaders.length > 0)
                 {
    				strLastModify = aHeaders[0].getValue();
                 }
             }
         }
         catch (ClientProtocolException e) 
         {
			if (lg != null)
				lg.error(e.toString());
		}
        catch (IOException e) 
		{
			if (lg != null)
				lg.error(e.toString());
		}
        catch (Exception e) 
        {
			if (lg != null)
				lg.error(e.toString());
		}
        
        finally 
        {
        	httpclient.getConnectionManager().shutdown();
        }

        return strLastModify; 
    }
    
    public static boolean isModify(String url,String strLastModifyTime) 
    { 
    	Logger lg = Logger.getLogger("HLDrools");
    	boolean bOk = false; 
    	HttpClient httpclient = new DefaultHttpClient();
         try 
         {
        	 httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
        	 httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
             HttpHead httphead = new HttpHead(url);
             httphead.addHeader(HttpHeaders.IF_MODIFIED_SINCE, strLastModifyTime);
             HttpResponse response = httpclient.execute(httphead);
             int nCode = response.getStatusLine().getStatusCode();
             if(nCode == 304)
             {
            	 bOk = true;
             }
         }
         catch (ClientProtocolException e) 
         {
			if (lg != null)
				lg.error(e.toString());
		}
        catch (IOException e) 
		{
			if (lg != null)
				lg.error(e.toString());
		}
        catch (Exception e) 
        {
			if (lg != null)
				lg.error(e.toString());
		}
        
        finally 
        {
        	httpclient.getConnectionManager().shutdown();
        }

        return bOk; 
       
    }
    public static void main(String[] args) throws IOException, InterruptedException {
//    	for(int i=0;i<10000;i++){
//    		System.out.println("第"+i+"张");
//    	doDownload("http://tjcredit.gov.cn/verifycode?date=1468473466610","D:/验证码训练数据/tianjin/"+i+".jpg");
//    	Thread.sleep(3000);
//    	}
    	for(int i=0;i<10000;i++){
    		System.out.println("第"+i+"张");
    	//doDownload("http://qyxy.baic.gov.cn/CheckCodeCaptcha?currentTimeMillis=1469432007798","D:/验证码训练数据/beijing/zimu/"+i+".jpg");
    	Thread.sleep(2000);
    	}
    }
}
