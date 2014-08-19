package com.cyou.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * <p>
 * HTTP公用类
 *  所需包:Commons-httpclient.jar,commons-codec-1.3.jar
 * 学习参见网址: https://www.ibm.com/developerworks/cn/opensource/os-cn-crawler/
 * </p>
 */
public class HttpClientUtils {
	private static Logger log = Logger.getLogger(HttpClientUtils.class);
	/**
	 * <p>httpClient的get请求方式2</p>
	 * @param url = "https://www.99bill.com/webapp/receiveDrawbackAction.do";
	 * @param charset = ="utf-8";
	 * @return
	 * @throws Exception
	 */
	public static String getDoGetURL(String url, String charset,String logSig) throws HttpException,IOException{
		log.info(logSig + url);
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
		
		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 50 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 50000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		
		byte[] responseBody = null;
		String responseString="";
		/* 3 执行 HTTP GET 请求 */
		int statusCode = httpClient.executeMethod(getMethod);
		/* 4 判断访问的状态码 */
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: "+ getMethod.getStatusLine());
		}
		/* 5 处理 HTTP 响应内容 */
		responseBody = getMethod.getResponseBody();
		responseString = new String(responseBody, charset);
		//System.out.println("responseString:"+responseString);
		/* 6 .释放连接 */
		getMethod.releaseConnection();
		
		return responseString;
	}
	
	/**
	 * <p>httpClient的get请求方式2</p>
	 * @param url = "https://www.99bill.com/webapp/receiveDrawbackAction.do";
	 * @param charset = ="utf-8";
	 * @return
	 * @throws Exception
	 */
	public static byte[] getDoGetURL2(String url, String charset,String logSig) throws HttpException,IOException{
		log.info(logSig + url);
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 50 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 50000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		
		byte[] responseBody = null;
		/* 3 执行 HTTP GET 请求 */
		int statusCode = httpClient.executeMethod(getMethod);
		/* 4 判断访问的状态码 */
		if (statusCode != HttpStatus.SC_OK) {
			log.error("httpclient error:" + statusCode);
		}
		/* 5 处理 HTTP 响应内容 */
		responseBody = getMethod.getResponseBody();// 读取为字节数组
		/* 6 .释放连接 */
		getMethod.releaseConnection();
		
		return responseBody;
	}
	
    /** 
     * <p>执行一个HTTP POST请求，返回请求响应的HTML</p> 
     * 
     * @param url       请求的URL地址 
     * @param params    请求的查询参数,可以为null 
     * @param charset 	字符集 
     * @param pretty    是否美化 
     * @return 			返回请求响应的HTML 
     * @throws IOException 
     * @throws HttpException 
     */ 
    public static String getDoPostResponseDataByURL(String url, Map<String, String> params, String charset, boolean pretty,String logSig) throws HttpException, IOException {
		
    	StringBuffer response = new StringBuffer();
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair[] npa = null;
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append("&"+entry.getKey() + "=" + entry.getValue());
				NameValuePair np = new NameValuePair(entry.getKey(),entry.getValue());
				list.add(np);
			}
			log.info(logSig + url + sb.toString().replaceFirst("&", "?"));
			npa = new NameValuePair[list.size()];
			list.toArray(npa);
		}
		post.setRequestBody(npa);
		client.executeMethod(post);
		if (post.getStatusCode() == HttpStatus.SC_OK) {
			//读取为 InputStream，在网页内容数据量大时候推荐使用
			BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(),charset));
			String line;
			while ((line = reader.readLine()) != null) {
				if (pretty)
					response.append(line).append(System.getProperty("line.separator"));
				else
					response.append(line);
			}
			reader.close();
		}
		post.releaseConnection();
		
		return response.toString();
	} 
    public static void main(String[] args) {
    	try {
    		String urlString= "https://graph.facebook.com/305866922876745?access_token="+URLEncoder.encode("114390885402571|y1L0BIlMJUkoqmsx3k-zQE_hDIU", "utf-8");
			byte[] result = HttpClientUtils.getDoGetURL2(urlString, "utf-8","logSig");
			System.out.println(new String(result));
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}