<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<html>
	<head>
	<title>Изменение рассписания</title>
	<link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
    <div id="cap" align="center"  ><img align="center" src="sketch_web_cr1.png"  ></div>
<div id="container" align="center">
<div id="left" ><img src="sketch_web4.png"  > </div>
<table  align="right"><tr><td>
<%

int group=0;
if(request.getParameter("group")!=null)
{
    group = Short.parseShort(request.getParameter("group"));
}
else
{
    out.println("Error. No group selected.");
	return;
}
int day=0;
if(request.getParameter("day")!=null)
{
    day = Short.parseShort(request.getParameter("day"));
}
else
{
    out.println("Error. No day selected.");
	return;
}
int beginning=0;
if(request.getParameter("beginning")!=null)
{
    beginning = Short.parseShort(request.getParameter("beginning"));
}
else
{
    out.println("Error. No beginning selected.");
	return;
}
int maxlen=0;
if(request.getParameter("maxlen")!=null)
{
    maxlen = Short.parseShort(request.getParameter("maxlen"));
}
else
{
    out.println("Error. No maxlen selected.");
	return;
}
TableFactory factory = new PgSqlTableFactory();


%>





<%
    Schedule g = new Schedule();
    Lecture  l = null;
    Room r = null;
    Teacher t = null;
	Group k = null;
    ScheduleTable gt = factory.getScheduleTable();
    LecturesTable lt = factory.getLecturesTable();
	LectureTypesTable ltl = factory.getLectureTypesTable();//Asshole
    RoomsTable    rt = factory.getRoomsTable();
    TeachersTable tt = factory.getTeachersTable();
	GroupsTable kt = factory.getGroupsTable();
	java.util.Vector tlst;
%>	<table align="center">
    <form action="edit_schedule.jsp">
	<tr><td align="center"><h1>Добавление нового элемента расписания.</h1><br/></td></tr>
	<input type="hidden" name="action" value="2" />
	<tr><td align="center"><p>Преподаватель:</p><select name="teacher">
	<%
	tlst = tt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Teacher)tlst.get(i)).getID() + ">" + ((Teacher)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></td></tr>
	<tr><td align="center"><p>Предмет:</p><select name="lecture"> 
	<%
	tlst = lt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Lecture)tlst.get(i)).getID() + ">" + ((Lecture)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select>></td></tr>
	
	<tr><td align="center"><p>Тип занятия:</p><select name="lecturetype"> 
	<%
	tlst = ltl.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((LectureType)tlst.get(i)).getID() + ">" + ((LectureType)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></td></tr>
	
	<tr><td align="center"><p>Аудитория:</p><select name="room"> 
	<%
	tlst = rt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Room)tlst.get(i)).getID() + " >" + ((Room)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></td></tr>
	<tr><td align="center"><p>Комментарий:</p><input name="comment" type="text" /></td></tr>
	<tr><td align="center"><p>Время начала.:</p><select name="starttime"></td></tr>
	<%
	for(int i=0; i<(maxlen/5); i++)
	{
	    out.println("<option value="+(beginning+i*5)+" >" + (beginning+i*5)/60 +":"+ (beginning+i*5)%60 + "</option>");
	}
	%>
	</select>
	<tr><td  align="center"><p>Длительность:</p><select name="length"></td></tr>
	<%
	for(int i=0; i<(maxlen/5); i++)
	{
	    out.println("<option value="+(i*5)+" >" + (i*5)+ " минут </option>");
	}
	%>
	</select>
	<input type="hidden" name="group" value=<%= group %> />
	<input type="hidden" name="day" value=<%= day %> />
	<tr><td align="center"><input type="submit" value="Add" /></td></tr>
	</form>
	
	<tr><td align="center"><a href="edit_schedule.jsp?group=<%= group %>"><p>Назад</p></a></td></tr>
    </table>



</td></tr></table>
	<div id="right"><img src="sketch_web_cr6.png"  ></div>
	</div>
<div id="cap" align="center" ><img src="sketch_web2.png"   ></div>
</body>
</html>