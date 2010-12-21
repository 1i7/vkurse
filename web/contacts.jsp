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
		<jsp:include page="favicon.jspf"/>
		<link href="style1.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
    <div id="container2">
	<div id="up">
    	<div id="upleft">
        	<div id="upleftup"></div>
            <div id="upleftmiddle"><div><p id="okno">Welcome!</p></div></div>
            <div id="upleftdown"></div>
        </div>
		<div id="upmidlle"></div>
        <div id="uprigpt"></div>
     </div>
     <div id="middle">
     <div id="left"></div>
     <div id="content" >
		<div class="top:50px;"><p id="sr" >Выберите : </p>
		<form action = "day2.jsp" method = "get">
		<p id="sr" align="center">Группу
  		<select align="right" name="group">
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

		<p align="center"><input name="submit"  type="submit" value="         OK         " align="right" ></p></td></tr>
		</form></div>
			</div>
        
		        <div id="right_pic">
			</div>
            </div>
	    
	<div id="down">

	</div>
		<a href="http://code.google.com/p/vkurse/">Vkurse Developers, 2010</a>
</div>
        
	</body>
</html>