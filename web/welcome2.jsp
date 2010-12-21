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
		<link href="style1.css" rel="stylesheet" type="text/css" />
		<jsp:include page="favicon.jspf"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
    <div id="container2">
	<div id="up">
    	<div id="upleft">
            <div id="upleftgroup"><p id="okno">Welcome!
            </p></div>
        </div>
        <div id="uprigpt"></div>
     </div>
     <div id="middle">
     <div id="leftwelk"></div>
     <div id="content" >
		<div id="concl"><h1 id="sr" >Выберите : </h1>
		<form action = "day2.jsp" method = "get">
		<p id="sr" align="center">Группу
  		<select id="selects"  align="right" name="group">
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

		<p align="center"><input id="buttons"  name="submit"  type="submit" value="" align="right" ></p>
		</form></div>
			</div>
        
		        <div id="right_pic">
			</div>
            </div>
	    
	<div id="down">
		
	</div>
		<div id="contakt">
		<p>
		<a href="contacts-1.jsp">Vkurse Developers, 2010</a>
		<a href="http://code.google.com/p/vkurse/" > Download Mobile Clients </a>
		</p>
		
		</div>
</div>
        
	</body>
</html>