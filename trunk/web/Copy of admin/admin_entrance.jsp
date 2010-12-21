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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Vkurse university schedule</title>

<link href="style1.css" rel="stylesheet" type="text/css" />
</head>

<body>
<link rel="stylesheet" type="text/css" href="style1.css">
<div id="cap" align="center"  ><img align="center" src="sketch_web_cr1.png"  ></div>
<table align="center"  border=0 bordercolor="#FFFFFF" >
<tr>
<td align="right"><img src="sketch_web4.png"    ></td>
<td>
<table align="center" border=4 bordercolor="#FFFFFF">
<tr><td>
<h1 class="color" align="center"  > Редактировать расписание:</h1>
</td></tr>
<tr><td>
<div align="center" >
<form align="right" action="edit_schedule.jsp" method="get">
	

	<select align="center" name="group">
	<%
	int i=0;
	for (i=0; i<lst.size(); i++)
        {
		    g = (Group)lst.get(i);
		    out.println("<option value=\""+g.getID()+"\">"+g.getName()+"</option>");
	    }
	%>
	</select></br>
	<input type="submit" align="center" value="Выбрать группу">
	<!-- <a href=""></a> -->
</form>
</p>
</td></tr>

<tr> <td>
<h1 class="color" align="left"  >Редактировать списки: </h1>
</td></tr>
<tr><td><a href="admin_list.jsp?subaction=2"><p align="right">Список преподавателей</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=5"><p align="right">Список предметов</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=0"><p align="right">Список групп</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=1"><p align="right">Список аудиторий</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=3"><p align="right">Список видов отчётности</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=4"><p align="right">Список видов занятий</p></a></td></tr>
</table>
</td>
<td align="right">
<img src="sketch_web_cr6.png"   >
</td>
</tr>
</table>
<div align="center"><img src="sketch_web2.png"   ></div>
</body>
</html>
</html>
