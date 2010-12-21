<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>

<%
//So, first we gonna get some parameters.
//In particular, group name and current date
//this is day.jsp - so we only need group as parameter
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
/*this is buggy, actually - we don't check lousy parameters*/
//Now the day
java.util.Calendar today = java.util.Calendar.getInstance();
int day = today.get(Calendar.DAY_OF_WEEK);
if(day>1) day--;
else day=7;
//out.println("Debug:: group="+group);
if(request.getParameter("day")!=null)
{
    day = Short.parseShort(request.getParameter("day"));
}

//Now we connect to db
TableFactory factory = new PgSqlTableFactory();
Schedule g = null;
Lecture  l = null;
Room r = null;
Teacher t = null;
ScheduleTable gt = factory.getScheduleTable();
LecturesTable lt = factory.getLecturesTable();
RoomsTable    rt = factory.getRoomsTable();
TeachersTable tt = factory.getTeachersTable();

//java.util.Vector tsl=lt.getAll();
//day = 5;//friday - for debugging purposes
java.util.Vector lst =gt.findByGroupDay( group, (byte)day);

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
java.util.Collections.sort(lst,comparator);

/*for(int i=0; i<lst.size(); i++)
{
    out.println(lst.get(i));
	out.println("<br/>");
}*/

%>


<html>
	<head>
	<title>Day schedule</title>
	</head>
	<body>
	<table border=10 align=center width="50%" bgcolor="Teal" bordercolor="Teal">
	<tr>
	<th height="20%" align=left>
	

	</th>
		
	<th height="20%" align=right>
	<%=  java.util.Calendar.getInstance().getTime() %>
	<a href="welcome.jsp"><img src="home.gif" align="top" border 2 width="8%"></a>
	</th>
	</tr>
<%	
	for(int i=0; i<lst.size(); i++)
	{
%>
	<tr bgcolor="Aqua" border=10> 
	    <td>
	        <div align="center">
		        <%
				    int hour = ((Schedule)lst.get(i)).getStartTime()/60;
					int min =  ((Schedule)lst.get(i)).getStartTime()%60;
				    out.println(Integer.toString(hour)+":"+Integer.toString(min)); 
				%>
		    </div>
		    <div align="center">
		        <%
				    int addhour = ((Schedule)lst.get(i)).getLength()/60;
					int addmin =  ((Schedule)lst.get(i)).getLength()%60;
					if(addmin+min > 59)
					{
					    addhour+=(addmin+min)/60;
						addmin=(addmin+min)%60;
				    }
					out.println(Integer.toString(hour+addhour)+":"+Integer.toString(addmin)); 
				%>
		    </div>
		</td>
		<td>
		    <div align="center">
			<%
			    int lid = ((Schedule)lst.get(i)).getLectureID();
				l = lt.get(lid);
				int rid = ((Schedule)lst.get(i)).getRoomID();
				r = rt.get(rid);
				int tid = ((Schedule)lst.get(i)).getTeacherID();
				t = tt.get(tid);
			%>
			
			    <a href="course.jsp?lectureid=<%= lid %>">
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
					<br/>
				</a>
				
				    <%
					if(r!=null)
					{
					    out.println(r.getName()); 
					}
					else
					{
					    out.println("Unknown Room");
					}
					%>
					<br/>
			
				    <%
					if(t!=null)
					{
					    out.println(t.getDegree());
					    out.println(t.getName()); 
					}
					else
					{
					    out.println("Unknown Teacher");
					}
					%> 
				
				</a>
			</div>
		</td>
	</tr>
<%
    }
%>
	
	<tr>
	<td height="20%" align=left>
	<a href="changes.jsp">Changes</a>
	</td>
		
	<td height="20%" align=right>
	<a href="week.jsp?group=<%= group %>">Weekly schedule</a>
	</td>
	</table>
	</body>
</html>