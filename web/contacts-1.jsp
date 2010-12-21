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
	<title>Vkurse Developers</title>
		<link href="style1.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
    <div id="container2">
    <div id="up">
    	<div id="upleft">
            <div id="upleftgroup"><p id="okno">Vkurse Developers
            </p></div>
        </div>
        <div id="uprigpt"></div>
     </div>
     <div id="middle">
     <div id="leftwelk"></div>
     <div id="content" >
		<div id="concl"><h1 id="sr" >Vkurse Developers: </h1>
        <p><a href="http://code.google.com/p/vkurse/">Хостинг проекта</a></p>
        <p><a href="http://community.livejournal.com/vkursemipt/">Вкурсе в LiveJournal</a></p>
        <p><a href="http://twitter.com/#!/_VKurse_">Твиттер Вкурсе</a></p>
        <p><a href="welcome2.jsp">На главную</a></p>
        </div>
			</div>
        
		        <div id="right_pic">
			</div>
            </div>
	    
	<div id="down">

	</div>
</div>
        
	</body>
</html>