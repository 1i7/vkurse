<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<%
TableFactory myfactory = new PgSqlTableFactory();
GroupsTable ourgroup = myfactory.getGroupsTable();
%>

<html>
	<head>
	<title>Изменение рассписания</title>
	<link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
   <div id="container2">
		<div id="up">
    	<div id="upleft">
            <div id="upleftgroup"><p id="okno" align="center">Группа 
     <%
					out.println(" "+(ourgroup.get(Short.parseShort(request.getParameter("group")))).getName());
					out.println("</br>");

				%>
                  </p></div>
        </div>
        <div id="uprigpt"></div>
     </div>
		<div id="middle">
            <div id="leftadminlist">
            
               <div id="lefttablelist"> 
				<a href="admin_list.jsp?subaction=2"><p align="left">преподавателей</p></a>
				<a href="admin_list.jsp?subaction=5"><p align="left">предметов</p></a>
				<a href="admin_list.jsp?subaction=0"><p align="left">групп</p></a>
				<a href="admin_list.jsp?subaction=1"><p align="left">аудиторий</p></a>
				<a href="admin_list.jsp?subaction=3"><p align="left">видов отчётности</p></a>
				<a href="admin_list.jsp?subaction=4"><p align="left">видов занятий</p></a>
   				 <a href="admin_entrance.jsp"><p align="center">На главную</p></a>
                 </div>
    	 
         </div>
         <div id="content">      

<table align="center">
<form action="edit_schedule.jsp">
<tr><td align="center"><div id="listname"><p id="sr">Добавление занятия в расписание.</p></div></td></tr>
<tr><td>
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
%>		
</td></tr>
    
	
	<input type="hidden" name="action" value="2" />
	<tr><td align="center"><p>Преподаватель:</p><select id="selects1" name="teacher">
	<%
	tlst = tt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Teacher)tlst.get(i)).getID() + ">" + ((Teacher)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></td></tr>
	<tr><td align="center"><p>Предмет:</p><select id="selects1" name="lecture"> 
	<%
	tlst = lt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Lecture)tlst.get(i)).getID()+">"+ ((Lecture)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select>></td></tr>
	
	<tr><td align="center"><p>Тип занятия:</p><select id="selects1" name="lecturetype"> 
	<%
	tlst = ltl.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((LectureType)tlst.get(i)).getID() + ">" + ((LectureType)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></td></tr>
	
	<tr><td align="center"><p>Аудитория:</p><select id="selects1" name="room"> 
	<%
	tlst = rt.getAll();
	for(int i=0; i<tlst.size(); i++)
	{
	    out.println("<option value=" + ((Room)tlst.get(i)).getID() + " >" + ((Room)tlst.get(i)).getName() + "</option>");
	}
	%>
	</select></td></tr>
	<tr><td align="center"><p>Комментарий:</p><input name="comment" type="text" /></td></tr>
	<tr><td align="center">Время начала:<select id="selects1" name="starttime">
	<%
	for(int i=0; i<(maxlen/5); i++)
	{
	    //beginning+=5;
	    //out.println("<option value="+(beginning+i*5)+" >" + (beginning+i*5)/60 +":"+ (beginning+i*5)%60 + "</option>");
		String starthours = Integer.toString((beginning+i*5)/60) ;
		String startmins = Integer.toString((beginning+i*5)%60);
		if(startmins.length() < 2)
		{
		    startmins = "0" + startmins;
		}
		if((beginning+i*5)/60 > 23) break;
		out.println("<option value="+(beginning+i*5)+" >" + starthours +":"+ startmins + "</option>");
		beginning+=5;
	}
	%>
	</select> </td></tr>
	<tr><td align="center" >Длительность:<select id="selects1" name="length" >
	<%
	for(int i=5; i<(maxlen/5); i++)
	{
	    String choosehours = Integer.toString((i*5)/60) ;
		String choosemins = Integer.toString((i*5)%60) ;
	    if(choosemins.length() < 2)
		{
		    choosemins = "0" + choosemins;
		}
	    if( i == 17)
		{
		    //out.println("<option value="+(i*5)+" selected=\"selected\" >" + (i*5)+ " минут </option>");
			
			  out.println("<option value="+(i*5)+" selected=\"selected\" >" + choosehours + ":" + choosemins + "</option>");
		}
		else
		{
			//out.println("<option value="+(i*5)+" >" + (i*5)+ " минут </option>");
			out.println("<option value="+(i*5)+" >" + choosehours + ":"+  choosemins + "</option>");
		}
	}
	%>
    </td></tr>
	</select>
	<input type="hidden" name="group" value=<%= group %> />
	<input type="hidden" name="day" value=<%= day %> />
	<tr><td align="center"><input id="button2" type="submit" value="" /></td></tr>
	</form>
	
	<tr><td align="center"><a href="edit_schedule.jsp?group=<%= group %>"><p>Назад</p></a></td></tr>
    </table>

        </div>
		        <div id="right_pic">
			</div>
	</div>
	<div id="down">
	</div>
</div>
</body>
</html>