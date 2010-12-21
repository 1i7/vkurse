<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
	 
%>

<%
//Cool - our scriptlet
TableFactory Nfactory = new PgSqlTableFactory();
Group Ng = null;
GroupsTable Ngt = Nfactory.getGroupsTable();
java.util.Vector Nlst = Ngt.getAll();
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
GroupsTable ourgroup = factory.getGroupsTable();
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
int maxsizeup =0;
int maxsizedown = 0;
java.util.Vector[] lst = new java.util.Vector[8];
for(int i=1; i<7; i++)
{
    lst[i] = gt.findByGroupDay( group, (byte)i);
	java.util.Collections.sort(lst[i],comparator);
	//out.println("day "+i+"- size "+lst[i].size());
	
if((lst[i].size()>maxsizeup)&(i<3))
{
	maxsizeup=lst[i].size();
}
if((lst[i].size()>maxsizedown)&(i>=3))
{
	maxsizedown=lst[i].size();
}
}
String[] week = {"null", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};

%>




<html>
	<head>
	<title>Расписание на неделю</title>
    <link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
	<div id="container" >
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
            <p id="okno" align="left">Группу
		<select id="selects"  name="group">
        <%  
			int ii=0;
			for (ii=0; ii<Nlst.size(); ii++)
        	{
		    	Ng = (Group)Nlst.get(ii);
		    	out.println("<option value=\""+Ng.getID()+"\">"+Ng.getName()+"</option>");
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
         <div id="content">      
				<h1 id="sr" align="center">Расписание на неделю</h1>

<div id="divtable">
	
	<% 
	int max=maxsizeup;
	for(int i=1; i<7 ; i++)
    {
					 if(i==4)
			 {
				 	 %>
                     </div>
                     <div id="divtable">
					
					<%
			 }
		%>
    	<div id="dayt">
     	 <div id="dayofweek" align="center" ><h1 id="week"><a href="day2.jsp?group=<%= group %>&day=<%= i %>"><%= week[i] %></a></h1></div>
		 <%
		 for(int k=0; k<lst[i].size(); k++)
		 {

		 %>

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
            			
            		    <div id="pair">
				          <blockquote>
				            <p>
				              <%
				out.println(Integer.toString(hour)+":"+Integer.toString(min)+"-");
				%>
                                	<br/>
								<%
				out.println(Integer.toString(hour+addhour)+":"+Integer.toString(addmin)); 
				%>
				              <br/>
				              <a href="course2.jsp?lectureid=<%= lid %>">
				                <%
					    if(l==null)
						{
						    out.println("[предмет не указан]");
						}
						else
						{
						
							String[] word = l.getName().split(" ");
															%>
								<%
							for(int m=0; m<word.length;m++)
							{
								String w=word[m];
								if(w.length()<12)
								{		
									out.println(w);
									%>
                               
                                		<br/>
									<%
								}
								else
								{
									out.println(w.substring(0 , 12)+".");
										%>
                               
                                		<br/>
									<%
								}
							}
								%>
                             
								<%
						}
					%>
				                <br/>
			                  </a> 
			                </p>
			              </blockquote>
   		    </div>
            
		    
        <%		   
		 }
		 %>
         </div>
	<%
						 if(i==3)
			 {
				 	 %>
                     </div>
                     <div id="divtable">
					
					<%
			 }	 
	}
	%> </div>
  
    
    </div>
	

        
		        <div id="right_pic">
			</div>
                 	<div id="down">

	</div>
	
	</div>
 <div id="contakt"><p><a href="contacts-1.jsp">Vkurse Developers, 2010</a></p></div>
</div>

	</body>
</html>