<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<%
//utilities
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
TableFactory factory = new PgSqlTableFactory();
Schedule g = null;
Lecture  l = null;
Room r = null;
Teacher t = null;
ScheduleTable gt = factory.getScheduleTable();
LecturesTable lt = factory.getLecturesTable();
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
String[] week = {"null", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};

%>




<html>
	<head>
	<title>Расписание на неделю</title>
	</head>
	<body>
	<p align="right"><a href="welcome.jsp"><img src="home.gif" align="top" border 2 width="8%"></a></p>
	<p align="center"><font size="6" color="Aqua">Расписание на неделю</font></p>

	<table border=10 align=center width="50%" bgcolor="Teal" bordercolor="Teal">
	<% 
	for(int i=1; i<8 ; i++)
    {%>
     	 <tr bgcolor="Aqua" border=10><td><a href="day.jsp?group=<%= group %>&day=<%= i %>"><%= week[i] %></a></td>
		 <%
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
			    <a href="course.jsp?lectureid=<%= lid %>">
				    <%
					    if(l==null)
						{
						    out.println("Unknown Lecture");
						}
						else
						{
						    out.println(l.getName());
						}
					%>
					<br/>
				</a>
				</td></tr></table>  
			</div>
		    </td> 
        <%		   
		 }
		 %>
		 </tr>
	<%	 
	}
	%>
	
	
	</table>

	</body>
</html>