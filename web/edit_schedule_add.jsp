<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>

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
    RoomsTable    rt = factory.getRoomsTable();
    TeachersTable tt = factory.getTeachersTable();
	GroupsTable kt = factory.getGroupsTable();
	java.util.Vector tlst;
%>
    <form action="edit_schedule.jsp">
	Adding new schedule entry.<br/>
	<input type="hidden" name="action" value="2" />
	Teacher:<select name="teacher">
	<%
	tlst = tt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Teacher)tlst.get(i)).getID() + ">" + ((Teacher)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></br>
	Lecture:<select name="lecture"> 
	<%
	tlst = lt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Lecture)tlst.get(i)).getID() + ">" + ((Lecture)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></br>
	Room:<select name="room"> 
	<%
	tlst = rt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Room)tlst.get(i)).getID() + " >" + ((Room)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></br>
	Comment:<input name="comment" type="text" /></br>
	StartTime:<select name="starttime">
	<%
	for(int i=0; i<(maxlen/5); i++)
	{
	    out.println("<option value="+(beginning+i*5)+" >" + (beginning+i*5)/60 +":"+ (beginning+i*5)%60 + "</option>");
	}
	%>
	</select></br>
	Length:<select name="length">
	<%
	for(int i=0; i<(maxlen/5); i++)
	{
	    out.println("<option value="+(i*5)+" >" + (i*5)+ " минут </option>");
	}
	%>
	</select></br>
	<input type="hidden" name="group" value=<%= group %> />
	<input type="hidden" name="day" value=<%= day %> />
	<input type="submit" value="Add" />
	</form>
	
	<a href="edit_schedule.jsp?group=<%= group %>">Назад</a>

<html>
<head>
</head>
<body>


</body>
</html>