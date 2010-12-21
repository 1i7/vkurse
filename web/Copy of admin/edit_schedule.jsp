<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<%
    //Cool - our scriptlet
    TableFactory factorys = new PgSqlTableFactory();
    Group gg = null;
    GroupsTable gtq = factorys.getGroupsTable(); 
    java.util.Vector lst1 = gtq.getAll();
%>
<%

//deleting-adding
int action=0;
if(request.getParameter("action")!=null)
{
    action = Short.parseShort(request.getParameter("action"));
}
switch(action)
{
    case 1: //deleting
	{
	    if(request.getParameter("id")==null)
	    {
	        out.println("error");
			return;
		}
	    int id = Short.parseShort(request.getParameter("id"));
	    TableFactory factory = new PgSqlTableFactory();
	    ScheduleTable gt = factory.getScheduleTable();
	    gt.remove(id);
	}
	break;
	case 2:  //adding
	{
	    TableFactory factory = new PgSqlTableFactory();
	    ScheduleTable gt = factory.getScheduleTable();
	    Schedule s = new Schedule();
		s.setGroupID((Short.parseShort(request.getParameter("group"))));
		s.setDay((byte)Short.parseShort(request.getParameter("day")));
		s.setStartTime(Short.parseShort(request.getParameter("starttime")));
		s.setLength(Short.parseShort(request.getParameter("length")));
		s.setLectureID(Short.parseShort(request.getParameter("lecture")));
		s.setLectureTypeID(Short.parseShort(request.getParameter("lecturetype")));
		s.setTeacherID(Short.parseShort(request.getParameter("teacher")));
		s.setRoomID(Short.parseShort(request.getParameter("room")));
		//s.setComment(request.getParameter("starttime"));
		s.setComment("");
		gt.insert(s);
	}
}
%>



<%
//utilities
int group=0;
if(request.getParameter("group")!=null)
{
    if(request.getParameter("group").equals("null"))
	{
	    group=793;//todo
	}
	else
	{
        group = Short.parseShort(request.getParameter("group"));
	}
}
else
{
    out.println("Error. No group selected.");
	return;
}
TableFactory factory = new PgSqlTableFactory();
Schedule g = null;
Lecture  l = null;
LectureType ll= null;
Room r = null;
Teacher t = null;
ScheduleTable gt = factory.getScheduleTable();
LecturesTable lt = factory.getLecturesTable();
LectureTypesTable ltl = factory.getLectureTypesTable();
RoomsTable    rt = factory.getRoomsTable();
TeachersTable tt = factory.getTeachersTable();
final class StartCmp implements java.util.Comparator
{
    public int compare(Object obj1, Object obj2)
	{
	    Schedule one = (Schedule)obj1;
		Schedule two = (Schedule)obj2;
		if(one.getStartTime() < two.getStartTime())
		{
		    return(-1);
		}
        if(one.getStartTime() > two.getStartTime())
		{
		    return(1);
		}
        return(0);
	}
}
StartCmp comparator = new StartCmp();

java.util.Vector[] lst = new java.util.Vector[8];
for(int i=1; i<8; i++)
{
    lst[i] = gt.findByGroupDay( group, (byte)i);
	java.util.Collections.sort(lst[i],comparator);
	//out.println("day "+i+"- size "+lst[i].size());
}
String[] week = {"null", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};

%>


<%
//Cool - our scriptlet
TableFactory Nfactory = new PgSqlTableFactory();
Group Ng = null;
GroupsTable Ngt = Nfactory.getGroupsTable();
java.util.Vector Nlst = Ngt.getAll();
%>

<%
//Код меню. двигать целиком
%>






<html>
	<head>
	<title>Расписание на неделю</title>
    <link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
	<!--<p><a href="admin_entrance.jsp">К спискам</a></p>-->
    <div id="cap" align="center"  ><img align="center" src="sketch_web_cr1.png"  ></div>

	<!--<a href="admin_entrance.jsp">На главную</a>-->
	<div id="container" align="center">
		
        
<table align="left"  bordercolor="#FFFFFF">
<tr><td>
<p class="color" align="center"  > Редактировать расписание:</p>
</td></tr>
<tr><td>
<form align="right" action="edit_schedule.jsp" method="get">
	

	<select align="center" name="group">
    	<%
	int ii=0;
	for (ii=0; ii<lst1.size(); ii++)
        {
		    gg = (Group)lst1.get(ii);
		    out.println("<option value=\""+gg.getID()+"\">"+gg.getName()+"</option>");
	    }
	%>
	</select></br>
	<input type="submit" align="center" value="Выбрать группу">
	<!-- <a href=""></a> -->
</form>
</p>
</td></tr>

<tr> <td>
<p class="color" align="left"  >Редактировать списки: </p>
</td></tr>
<tr><td><a href="admin_list.jsp?subaction=2"><p align="right">Список преподавателей</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=5"><p align="right">Список предметов</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=0"><p align="right">Список групп</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=1"><p align="right">Список аудиторий</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=3"><p align="right">Список видов отчётности</p></a></td></tr>
<tr><td><a href="admin_list.jsp?subaction=4"><p align="right">Список видов занятий</p></a></td></tr>
</table>
        
		<div id="left" ><img src="sketch_web4.png"  > </div>
        <table>
        <tr><td>
	<p align="center"><font size="6" color="Black">Расписание на неделю</font></p>

	<table border=10 align="center" width="50%" bgcolor="#999999" bordercolor="#999999">
<% 
	for(int i=1; i<8 ; i++)
    {%>
     	 <tr bgcolor="#FF8455" border=10><td><a href="../day.jsp?group=<%= group %>&day=<%= i %>"><p><%= week[i] %></p></a></td>
		 <td>
		 <% 
		 {
		 int temp = 1440;
		 if(lst[i].size() != 0 )
		 {
		     temp = ((Schedule)lst[i].get(0)).getStartTime();
		 }
		 
		 %>
		 <a href="edit_schedule_add.jsp?action=2&group=<%= group %>&day=<%= i %>&beginning=0&maxlen=<%= temp %>">+</a>
		 </td>
		 <%
		 }
		 for(int k=0; k<lst[i].size(); k++)
		 {
		 %>
		    <td>
		    <div align="center">
			<%
			    int lid = ((Schedule)lst[i].get(k)).getLectureID();
				l = lt.get(lid);
				int hour = ((Schedule)lst[i].get(k)).getStartTime()/60;
				int min =  ((Schedule)lst[i].get(k)).getStartTime()%60;
				//out.println(Integer.toString(hour)+":"+Integer.toString(min)); 
				int addhour = ((Schedule)lst[i].get(k)).getLength()/60;
				int addmin =  ((Schedule)lst[i].get(k)).getLength()%60;
				if(addmin+min > 59)
				{
				    addhour+=(addmin+min)/60;
					addmin=(addmin+min)%60;
				}
				
			%>
			    <table><tr><td>
				<%
				out.println(Integer.toString(hour)+":"+Integer.toString(min)+" - ");
				out.println(Integer.toString(hour+addhour)+":"+Integer.toString(addmin)); 
				%>
				</td></tr>
				<tr><td>
			    <a href="../course.jsp?lectureid=<%= lid %>">
				    <%
					    if(l==null)
						{
						    out.println("неизвестно");
						}
						else
						{
						    out.println(l.getName());
						}
					%>
					<br/>
				</a>
				</td></tr>
				</table>  
			</div>
		    </td>
            <td>
			<%
			{
			int temp1 = ((Schedule)lst[i].get(k)).getStartTime() + ((Schedule)lst[i].get(k)).getLength();
			int temp2 = 1440;
			if( k != lst[i].size()-1 )
			{
			    temp2 = ((Schedule)lst[i].get(k+1)).getStartTime();
			}
			%>
			<a href="edit_schedule_add.jsp?action=2&group=<%= group %>&day=<%= i %>&beginning=<%= temp1 %>&maxlen=<%= temp2 %>">+</a>
            <a href="edit_schedule.jsp?action=1&group=<%= group%>&id=<%= ((Schedule)lst[i].get(k)).getID() %>">-</a>			
			</td>
        <%	
            }		
		 }
		 %>
		 </tr>
	<%	 
	}
	%>
	
	
	</table>
    </td></tr></table>
    		<div id="right"><img src="sketch_web_cr6.png"  ></div>
	</div>
<div id="cap" align="center" ><img src="sketch_web2.png"   ></div>

	</body>
</html>