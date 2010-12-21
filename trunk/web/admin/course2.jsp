<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<%
//utility work
TableFactory factory = new PgSqlTableFactory();
LecturesTable lt = factory.getLecturesTable();
Lecture  l = null;
int lid = 10001;
TeachersTable tt = factory.getTeachersTable();
Teacher  t = null;
ExamTypesTable et = factory.getExamTypesTable();
ExamType e = null;
if(request.getParameter("lectureid")==null)
{
    out.println("An error occured.");
	return;
}
lid = Short.parseShort(request.getParameter("lectureid"));
l = lt.get(lid);

%>


<html>
	<head>
	<title>
	    <%
		if(l!=null)
		{
		    out.println(l.getName());
		}
		else
		{
		    out.println("Данных о занятии нет в базе");
		}
		%>
	</title>
	</head>
	<body>
	<div align="center"><img align="center" src="sketch_web_cr1.gif"></div>
	<div align="center">
	<table>
	
	<tr>
		<td><img src="sketch_web4.gif"  height="100%"  ></td>
		<td>
	<table border=10  height="70%" bgcolor="#999999"  bordercolor="#999999">
	<tr> 
	<td align="right"><a href="edit_schedule.jsp?group=<%=request.getParameter("group") %>"><img src="home.gif" align="right" width="13%"></a>
	</tr>
	<tr bgcolor="#FF9966" border=2>
	<td>
	   <div align="center">
	        <%
		    if(l!=null)
		    {
		        out.println(l.getName());
		    }
		    else
		    {
		        out.println("Данных о занятии нет в базе");
			}
		    %>
		</div>
	</td>	
	</tr>
	<tr bgcolor="#FF9966" border=2>
	<td>
	<div align="center">
	        <%
		    if(l!=null)
		    {
		        out.println("Комментарий:"+l.getComment());
		    }
		    else
		    {
		        out.println("Данных о занятии нет в базе");
			}
		    %>
	</div></td>	
	</tr>
	
	<tr bgcolor="#FF9966" border=2>
	<td><div align="center"><%
		    if(l!=null)
		    {
				e = et.get(l.getExamTypeID());
				if(e!=null)
				{
				    out.println(e.getName());
				}
				else
				{
				    out.println("Форма отчетности неизвестна");
				}
		    }
		    else
		    {
		        out.println("Данных о занятии нет в базе");
			}
		    %></div></td>
	</tr>
	</table>
	

		</td>
		<td><img src="sketch_web_cr6.jpg"  height="100%"  ></td>
	</tr>
	
	</table>
	<img align="center" src="sketch_web2.gif" height="11%" >
	</body>
	</html>