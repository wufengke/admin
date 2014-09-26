package com.cyou.base.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * @author wangqian_js
 *
 */
public class CurrencyApi {
	private final static Logger logger = Logger.getLogger(CurrencyApi.class);
	static String loginUrl = "";
	static HttpClient httpClient = new HttpClient();
	static{
		loginUrl=PropertyUtil.getProperty("realtime_currency_agent_url");
	}

	public static String appAnnieCookie(String tocurrency) {
		String resultrate = "";
		GetMethod getMethod = null;
		try {
		   getMethod = getmethod(loginUrl.replace("{tocurrency}", tocurrency));
		   System.out.println(loginUrl.replace("{tocurrency}", tocurrency));
           getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 50000);
           getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
           byte[] responseBody = null;
           String responseString="";
           int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "+ getMethod.getStatusLine());
			}
			/* 5 处理 HTTP 响应内容 */
			responseBody = getMethod.getResponseBody();
			responseString = new String(responseBody, "utf-8");
			if(responseString.contains("double")){
				int pos = responseString.indexOf("<double");
		        int endpos = responseString.indexOf("</double>");
		        resultrate = responseString.substring(pos+44, endpos);
			}else {
				resultrate = "0";
			}
			
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	resultrate = "0";
        }finally{
        	getMethod.releaseConnection();
        }
        double result = Double.valueOf(resultrate);
	    if (result > 0) {
		   resultrate = String.format("%.8f", 1*1.0/result);
	    }
        return resultrate;
	}
	
	public static GetMethod getmethod (String url) {
		GetMethod method = new GetMethod(url);
		method.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
		method.setRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		method.setRequestHeader("Accept-Language", "en-us,en;q=0.5");
		method.setRequestHeader("Accept-Charset","utf-8;q=0.7,*;q=0.7");
		method.setRequestHeader("Keep-Alive", "300");
		method.setRequestHeader("Proxy-Connection", "keep-alive");
		method.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		return method;
	}
}
