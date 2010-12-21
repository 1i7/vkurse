<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>



<html>
	<head>
	<title>Интерфейс администратора</title>
	</head>
	<body>
	
	<form action="admin.jsp" method="get">
      Select action: 
      <select name="subaction">
	      <option value="0">Edit groups.</option>
		  <option value="1">Edit rooms.</option>
		  <option value="2">Edit teachers.</option>
		  <option value="3">Edit exams.</option>
		  <option value="5">Edit lectures.</option>
      </select>
      <input type="submit" value="Submit" />
    </form>
	
	<br/><br/><br/>
	
	<%
    //Cool - our scriptlet
    TableFactory factory = new PgSqlTableFactory();
    Group g = null;
    GroupsTable gt = factory.getGroupsTable();
    java.util.Vector lst = gt.getAll();
    %>
	<form action="edit_schedule.jsp" method="get">
	выберите курс
	<select>
	    <option>4 курс</option>
	</select>

	
	выберите группу
	<select name="group">
	<%
	int i=0;
	for (i=0; i<lst.size(); i++)
        {
		    g = (Group)lst.get(i);
		    out.println("<option value=\""+g.getID()+"\">"+g.getName()+"</option>");
	    }
	%>
	</select>
	<input type="submit" value="Редактировать расписание">
	<!-- <a href=""></a> -->
	</form>
   
	
	
	
	</body>
</html>