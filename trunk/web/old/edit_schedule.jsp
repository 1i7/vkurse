<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
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
		s.setTeacherID(Short.parseShort(request.getParameter("teacher")));
		s.setRoomID(Short.parseShort(request.getParameter("room")));
		s.setComment(request.getParameter("starttime"));
		gt.insertWithNewID(s);
	}
}
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
	<p align="right"><a href="admin_entrance.jsp"><img src="home.gif" align="top" border 2 width="8%"></a></p>
	<p align="center"><font size="6" color="Aqua">Расписание на неделю</font></p>

	<table border=10 align=center width="50%" bgcolor="Teal" bordercolor="Teal">
	<% 
	for(int i=1; i<8 ; i++)
    {%>
     	 <tr bgcolor="Aqua" border=10><td><a href="day.jsp?group=<%= group %>&day=<%= i %>"><%= week[i] %></a></td>
		 <td>
		 <% 
		 {
		 int temp = 1440;
		 if(lst[i].size() != 0 )
		 {
		     temp = ((Schedule)lst[i].get(0)).getStartTime();
		 }
		 
		 %>
		 <a href="edit_schedule_add.jsp?action=2&group=<%= group %>&day=<%= i %>&beginning=0&maxlen=<%= temp %>">Add</a>
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
				</td></tr>
				<tr><td>
				<a href="edit_schedule.jsp?action=1&group=<%= group%>&id=<%= ((Schedule)lst[i].get(k)).getID() %>">Снести</a>
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
			<a href="edit_schedule_add.jsp?action=2&group=<%= group %>&day=<%= i %>&beginning=<%= temp1 %>&maxlen=<%= temp2 %>">Add</a>			
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

	</body>
</html>