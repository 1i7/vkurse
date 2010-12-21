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

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Vkurse university schedule</title>
	<link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
	
	<div id="container2">
	<div id="up">
    	<div id="upleft">
            <div id="upleftgroup"><p id="okno">Главная
            </p></div>
        </div>
        <div id="uprigpt"></div>
     </div>
     <div id="middle">
     <div id="leftwelk"></div>
     <div id="content" >
<h1 id="sr" align="center"  > Редактировать расписание:</h1>
<form align="right" action="edit_schedule.jsp" method="get">
	

	<select id="selects" align="center" name="group">
	<%
	int i=0;
	for (i=0; i<lst.size(); i++)
        {
		    g = (Group)lst.get(i);
		    out.println("<option value=\""+g.getID()+"\">"+g.getName()+"</option>");
	    }
	%>
	</select>
	<input id="button1" type="submit" align="center" value="Выбрать группу" >
	<!-- <a href=""></a> -->
</form>
</p>



<h1 id="sr" align="center"  >Редактировать списки: </h1>
<div align="center">
<a href="admin_list.jsp?subaction=2"><p align="center">Список преподавателей</p></a>
<a href="admin_list.jsp?subaction=5"><p align="center">Список предметов</p></a>
<a href="admin_list.jsp?subaction=0"><p align="center">Список групп</p></a>
<a href="admin_list.jsp?subaction=1"><p align="center">Список аудиторий</p></a>
<a href="admin_list.jsp?subaction=3"><p align="center">Список видов отчётности</p></a>
<a href="admin_list.jsp?subaction=4"><p align="center">Список видов занятий</p></a>
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
</html>
