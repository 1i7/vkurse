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
//debug!!!!
//day = 6;
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
GroupsTable ourgroup = factory.getGroupsTable();
Group gr = null;
java.util.Vector lst2 = ourgroup.getAll();
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
	<title>Дневное расписание</title>
		<jsp:include page="favicon.jspf"/>
		<link href="style1.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
    <div id="container2">
	<div id="up">
    	<div id="upleft">
            <div id="upleftgroup"><p id="okno">Группа
                 <%
					out.println(" "+(ourgroup.get(group)).getName());
					out.println("</br>");

				 %>
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
			<p id="okno" align="left"><a href="week2.jsp?group=<%= group %>">Рассписание<br/> на неделю</a></p>
           <p id="okno" align="left"> <a href="welcome2.jsp">На главную</a></p>
        </div>
     </div>
     <div id="content" >
     <div id="date" align="center">
     	<div id="dateleft"></div>
        <div id="datemiddle">
		<%=  java.util.Calendar.getInstance().getTime() %>
		</div>
		 <div id="dateright" ></div>
      </div>
      <div  id="subjecttable" align="center">
<%	
	for(int i=0; i<lst.size(); i++)
	{
%>
	
    	<div id="daydate">
	        <div id="tine" align="center" >
            <div id="info" align="center">
		        <%
				    int hour = ((Schedule)lst.get(i)).getStartTime()/60;
					int min =  ((Schedule)lst.get(i)).getStartTime()%60;
					{
					String starthours = Integer.toString(((Schedule)lst.get(i)).getStartTime()/60) ;
					String startmins = Integer.toString(((Schedule)lst.get(i)).getStartTime()%60);
					if(startmins.length() < 2)
					{
						startmins = "0" + startmins;
					}
				    out.println(starthours+":"+startmins); 
					}
				%>
                </div>
		    </div>
         
		    <div id="info" align="center">
		       <%
				    int addhour = ((Schedule)lst.get(i)).getLength()/60;
					int addmin =  ((Schedule)lst.get(i)).getLength()%60;
					if(addmin+min > 59)
					{
					    addhour+=(addmin+min)/60;
						addmin=(addmin+min)%60;
				    }
					{
					String starthours = Integer.toString(hour+addhour) ;
					String startmins = Integer.toString(addmin) ;
					if(startmins.length() < 2)
					{
						startmins = "0" + startmins;
					}
					out.println(starthours+":"+startmins); 
					}
					//out.println(Integer.toString(hour+addhour)+":"+Integer.toString(addmin)); 
				%>
		    </div></div>
		
		
		<div id="daysubkect">
			<div id="info" align="center">
			<%
			    int lid = ((Schedule)lst.get(i)).getLectureID();
				l = lt.get(lid);
				int rid = ((Schedule)lst.get(i)).getRoomID();
				r = rt.get(rid);
				int tid = ((Schedule)lst.get(i)).getTeacherID();
				t = tt.get(tid);
			%>
			
			    <a href="course2.jsp?lectureid=<%= lid %>">
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
			</div></div>
<%
    }
%>
	
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