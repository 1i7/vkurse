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
Group gr = null;
GroupsTable ourgroup = factory.getGroupsTable();
java.util.Vector lst2 = ourgroup.getAll();
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
	<jsp:include page="favicon.jspf"/>
           <link href="style1.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
<div id="container2">
	<div id="up">
    	<div id="upleft">
            <div id="upleftgroup"><p id="okno">Информация о курсе
            </p></div>
        </div>
        <div id="uprigpt"></div>
     </div>
     <div id="middle">
     <div id="left">
     <div id="leftconcl">
     <form action = "day2.jsp" method = "get">
		<p id="okno" align="left">Группу
  		<select id="selects"  name="group">
        <%  
			int ii=0;
			for (ii=0; ii<lst2.size(); ii++)
        	{
		    	gr = (Group)lst2.get(ii);
		    	out.println("<option value=\""+gr.getID()+"\">"+gr.getName()+"</option>");
	    	}
		%>
 		 </select>
		</p>

		<p align="center"><input id="buttons"  name="submit"  type="submit" value="" align="right" ></p>
		</form>
        </div>
        <div id="leftsee">
			<p id="okno" align="left"><a href="week2.jsp?group=<%= ((Group)lst2.get(0)).getID()%>">Рассписание<br/> на неделю</a></p>
           <p id="okno" align="left"> <a href="welcome2.jsp">На главную</a></p>
        </div>
     </div>
     <div id="content" >

	<div id="subject">
    <div id="subjname" align="center">
	  <h1 id="okno" align="center">
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
		</h1></div>
        <div id="subjinfo">
	<div id="okno" align="center">
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
	</div></div>	
	
	
	<div id="subjinfo">
    
	<div id="okno" align="center"><%
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
		    %></div></div>
	
	</div>
    </div>
        
		        <div id="right_pic">
			</div>
            </div>
	<div id="down">
	</div>
   <div id="contakt"><p><a href="contacts-1.jsp">Vkurse Developers, 2010</a></p></div>
</div>
	</body>
	</html>