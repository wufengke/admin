<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"  %>
<table border="1">
<tr><th>Tag</th><th>Value</th></tr>
<tr>
<td>&lt;sec:authentication property='name' /&gt;</td><td><sec:authentication property="name"/></td>
</tr>
<tr>
<td>&lt;sec:authentication property='principal.username' /&gt;</td><td><sec:authentication property="principal.username"/></td>
</tr>
<tr>
<td>&lt;sec:authentication property='principal.enabled' /&gt;</td><td><sec:authentication property="principal.enabled"/></td>
</tr>
<tr>
<td>&lt;sec:authentication property='principal.accountNonLocked' /&gt;</td><td><sec:authentication property="principal.accountNonLocked"/></td>
</tr>
<tr>
<td></td><td><sec:accesscontrollist hasPermission="" domainObject=""></sec:accesscontrollist></td>
</tr>


</table>