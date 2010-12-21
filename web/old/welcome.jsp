<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>

<%
//Cool - our scriptlet
TableFactory factory = new PgSqlTableFactory();
Group g = null;
GroupsTable gt = factory.getGroupsTable();
java.util.Vector lst = gt.getAll();
%>


<html>
	<head>
	<title>Welcome!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
	<link rel="stylesheet" type="text/css" href="style1.css">
        <img src="sketch_web_cr1.gif" width="100%" height="25%">
        
        
        
	<form action="day.jsp" method="get">
	
	<p align="center">выберите курс
	<select>
	    <option>4 курс</option>
	</select></p>

	<p align="center">
	выберите группу
	<select name="group">
	<%
	int i=0;
	for (i=0; i<lst.size(); i++)
        {
		    g = (Group)lst.get(i);
		    out.println("<option value=\""+g.getID()+"\">"+g.getName()+"</option>");
	    }
	%>
	</select>
	</p>
	<div align="center"><input type="submit" value="Вход"></div>
	</form>
	
	
	
	
	
	</body>
</html>