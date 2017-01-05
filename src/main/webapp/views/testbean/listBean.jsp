<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User list</title>
</head>
<body>
	<h5><a href="<%=basePath %>views/home.jsp">Back to HomePage</a></h5>
  	<h3>UserList</h3>
  	<a href="<%=basePath %>views/testbean/addUser.jsp">Add User</a><br/>
	<table border="1" width="70%">
   		<tr>
   			<td>Id</td>
   			<td>Name</td>
   		</tr>
   		<c:forEach items="${beanlist}" var="bean">
   		<tr>
   			<td>${bean.id }</td>
   			<td>${bean.name }</td>
   			<td><a href="<%=basePath %>testbean/deleteBean.do?id=${bean.id }">Delete</a></td>
   			<td><a href="<%=basePath %>testbean/updateBean.do?id=${bean.id }">Update</a></td>
   		</tr>
   		</c:forEach>
   </table>
   
</body>
</html>