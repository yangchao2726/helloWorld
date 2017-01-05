<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	数据源切换实例
</h1>
  <ul>
    <li><a href="<%=basePath %>testbean/listBean.do">testbean</a></li>
    <li><a href="<%=basePath %>muser/listUser.do">user</a></li>
  </ul>
</body>
</html>
