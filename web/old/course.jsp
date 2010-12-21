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
		    out.println("Unknown Lecture");
		}
		%>
	</title>
	</head>
	<body>
	<table border=2 align=center width="50%" bgcolor="Aqua" bordercolor="Aqua">
	<tr> 
	<td bgcolor="Aqua" bordercolor="Aqua" align=right><a href="welcome.jsp"><img src="home.gif" align="right" border 2 width="8%"></a>
	</tr>
	<tr bgcolor="White" border=2>
	<td>
	   <div align="center">
	        <%
		    if(l!=null)
		    {
		        out.println(l.getName());
		    }
		    else
		    {
		        out.println("Unknown Lecture");
			}
		    %>
		</div>
	</td>	
	</tr>
	<tr bgcolor="White" border=2>
	<td>
	<div align="center">
	        <%
		    if(l!=null)
		    {
		        out.println("Comment:"+l.getComment());
		    }
		    else
		    {
		        out.println("Unknown Lecture");
			}
		    %>
	</div></td>	
	</tr>
	
	<tr bgcolor="White" border=2>
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
				    out.println("Exam status unknown");
				}
		    }
		    else
		    {
		        out.println("Unknown Lecture");
			}
		    %></div></td>
	</tr>
	</table>
	</body>
</html>