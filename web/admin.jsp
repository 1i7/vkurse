<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>


<html>
	<head>
	<title>Admin</title>
	</head>
	<body>
	<a href="admin_entrance.jsp">Admin Entrance</a>
<%


TableFactory factory = new PgSqlTableFactory();
//first we parse parameters
if(request.getParameter("subaction")==null)
{
    out.println("No class selected");
	return;
}
int action = 0;
if(request.getParameter("action")!=null)
{
    action = Short.parseShort(request.getParameter("action"));
}
switch(action)
{
    case 5:
	    //delete something
		if(action==5)
		{
	    if(request.getParameter("id")==null)
	    {
	        out.println("error");
		}
		else
		{
		    switch(Short.parseShort(request.getParameter("subaction")))
			{
			case 0:
		    GroupsTable gremovetable= factory.getGroupsTable();
			gremovetable.remove(Short.parseShort(request.getParameter("id")));
			break;
			case 1:
		    RoomsTable rremovetable= factory.getRoomsTable();
			rremovetable.remove(Short.parseShort(request.getParameter("id")));
			break;
			case 2:
		    TeachersTable tremovetable= factory.getTeachersTable();
			tremovetable.remove(Short.parseShort(request.getParameter("id")));
			break;
			case 3:
		    ExamTypesTable eremovetable= factory.getExamTypesTable();
			eremovetable.remove(Short.parseShort(request.getParameter("id")));
			break;
			case 5:
		    LecturesTable lremovetable= factory.getLecturesTable();
			lremovetable.remove(Short.parseShort(request.getParameter("id")));
			break;
			}
		}
		}
	case 6:
	//add something
	    if(action==6)
		{
		    if(request.getParameter("name")==null)
	        {
	            out.println("error");
		    }
			else
			{
			    switch(Short.parseShort(request.getParameter("subaction")))
				{
				case 0:
			     GroupsTable gaddtable= factory.getGroupsTable();  
				 Group gaaa = new Group();
				 gaaa.setName(request.getParameter("name"));
				 gaaa.setCourse(request.getParameter("course"));
				 gaddtable.insertWithNewID(gaaa);
				 break;
				case 1:
				 RoomsTable raddtable= factory.getRoomsTable();  
				 Room raaa = new Room();
				 raaa.setName(request.getParameter("name"));
				 raddtable.insertWithNewID(raaa);
				 break;
				case 2:
				 TeachersTable taddtable= factory.getTeachersTable();  
				 Teacher taaa = new Teacher();
				 taaa.setName(request.getParameter("name"));
				 taaa.setDegree(request.getParameter("degree"));
				 taddtable.insertWithNewID(taaa);
				 break;
				case 3:
				 ExamTypesTable eaddtable= factory.getExamTypesTable();  
				 ExamType eaaa = new ExamType();
				 eaaa.setName(request.getParameter("name"));
				 eaddtable.insertWithNewID(eaaa);
				 break;
				case 5:
				 LecturesTable laddtable= factory.getLecturesTable();  
				 Lecture laaa = new Lecture();
				 laaa.setName(request.getParameter("name"));
				 laaa.setExamTypeID(Short.parseShort(request.getParameter("examtype")));
				 laddtable.insertWithNewID(laaa);
				 break;
				}
		    }
		}
}
	//list something
	switch(Short.parseShort(request.getParameter("subaction")))
	{
    case 0:
	//list groups
	final class NameCmp implements java.util.Comparator
    {
        public int compare(Object obj1, Object obj2)
	    {
	        Group one = (Group)obj1;
		    Group two = (Group)obj2;
		    return(one.getName().compareTo(two.getName()));
	    }
    }
	//edit groups
	GroupsTable gt = factory.getGroupsTable();
	Group g = null;
    java.util.Vector glst = gt.getAll();
    NameCmp gcomparator = new NameCmp();
	for(int i=0; i<glst.size(); i++)
	{
	  //  out.println((Group)glst.get(i));
	//	out.println("\n");
	//	out.println("");
	}
    java.util.Collections.sort(glst,gcomparator);
	%> <table> <%
	for(int i=0; i<glst.size(); i++)
	{
	    %><tr><td><%
		out.println("ID="+((Group)glst.get(i)).getID()+" Name="+((Group)glst.get(i)).getName()+" Course="+((Group)glst.get(i)).getCourse());
		
		%> </td><td><a href="admin.jsp?action=5&subaction=0&id=<%=((Group)glst.get(i)).getID()%> ">Снести</a></td></tr> <%
	}
	%> </table> <%
	%><form action="admin.jsp" method="get">
	  New Group:<input type="text" name="name"/>
	  Course: <input type="text" name="course"/>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="0"> 
	  <input type="submit" value="Create" />
	  </form>
	<%
	break;
    case 1:
	//list Rooms
	final class NameCmp implements java.util.Comparator
    {
        public int compare(Object obj1, Object obj2)
	    {
	        Room one = (Room)obj1;
		    Room two = (Room)obj2;
		    return(one.getName().compareTo(two.getName()));
	    }
    }
	//edit Rooms
	RoomsTable rt = factory.getRoomsTable();
	Room r = null;
    java.util.Vector rlst = rt.getAll();
    NameCmp rcomparator = new NameCmp();
	for(int i=0; i<rlst.size(); i++)
	{
	  //  out.println((Room)rlst.get(i));
	//	out.println("\n");
	//	out.println("");
	}
    java.util.Collections.sort(rlst,rcomparator);
	%> <table> <%
	for(int i=0; i<rlst.size(); i++)
	{
	    %><tr><td><%
		out.println("ID="+((Room)rlst.get(i)).getID()+" Name="+((Room)rlst.get(i)).getName());
		
		%> </td><td><a href="admin.jsp?action=5&subaction=1&id=<%=((Room)rlst.get(i)).getID()%> ">Снести</a></td></tr> <%
	}
	%> </table> <%
	%><form action="admin.jsp" method="get">
	  New Room:<input type="text" name="name"/>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="1">
	  <input type="submit" value="Create" />
	  </form>
	<%
	break;
	case 2:
	//list teachers
	final class NameCmp implements java.util.Comparator
    {
        public int compare(Object obj1, Object obj2)
	    {
	        Teacher one = (Teacher)obj1;
		    Teacher two = (Teacher)obj2;
		    return(one.getName().compareTo(two.getName()));
	    }
    }
	//edit Teachers
	TeachersTable tt = factory.getTeachersTable();
	Teacher t = null;
    java.util.Vector tlst = tt.getAll();
    NameCmp tcomparator = new NameCmp();
	for(int i=0; i<tlst.size(); i++)
	{
	  //  out.println((Teacher)tlst.get(i));
	//	out.println("\n");
	//	out.println("");
	}
    java.util.Collections.sort(tlst,tcomparator);
	%> <table> <%
	for(int i=0; i<tlst.size(); i++)
	{
	    %><tr><td><%
		out.println("ID="+((Teacher)tlst.get(i)).getID()+" Name="+((Teacher)tlst.get(i)).getName()+" Degree="+((Teacher)tlst.get(i)).getDegree());
		
		%> </td><td><a href="admin.jsp?action=5&subaction=2&id=<%=((Teacher)tlst.get(i)).getID()%> ">Снести</a></td></tr> <%
	}
	%> </table> <%
	%><form action="admin.jsp" method="get">
	  New Teacher:<input type="text" name="name"/>
	  Degree: <input type="text" name="degree"/>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="2">
	  <input type="submit" value="Create" />
	  </form>
	<%
	break;
	case 3:
	//list ExamsTypes
	final class NameCmp implements java.util.Comparator
    {
        public int compare(Object obj1, Object obj2)
	    {
	        ExamType one = (ExamType)obj1;
		    ExamType two = (ExamType)obj2;
		    return(one.getName().compareTo(two.getName()));
	    }
    }
	//edit examtypes
	ExamTypesTable et = factory.getExamTypesTable();
	ExamType e = null;
    java.util.Vector elst = et.getAll();
    NameCmp ecomparator = new NameCmp();
	for(int i=0; i<elst.size(); i++)
	{
	  //  out.println((ExamType)elst.get(i));
	//	out.println("\n");
	//	out.println("");
	}
    java.util.Collections.sort(elst,ecomparator);
	%> <table> <%
	for(int i=0; i<elst.size(); i++)
	{
	    %><tr><td><%
		out.println("ID="+((ExamType)elst.get(i)).getID()+" Name="+((ExamType)elst.get(i)).getName());
		
		%> </td><td><a href="admin.jsp?action=5&subaction=3&id=<%=((ExamType)elst.get(i)).getID()%> ">Снести</a></td></tr> <%
	}
	%> </table> <%
	%><form action="admin.jsp" method="get">
	  New ExamType:<input type="text" name="name"/>
	  <input type="hidden" name="action" value="6"> 
<input type="hidden" name="subaction" value="3">	  
	  <input type="submit" value="Create" />
	  </form>
	<%
	break;
	case 4:
	
	break;
	case 5:
    //list lectures
	final class NameCmp implements java.util.Comparator
    {
        public int compare(Object obj1, Object obj2)
	    {
	        Lecture one = (Lecture)obj1;
		    Lecture two = (Lecture)obj2;
		    return(one.getName().compareTo(two.getName()));
	    }
    }
	//edit groups
	LecturesTable lt = factory.getLecturesTable();
	ExamTypesTable lte = factory.getExamTypesTable();
	Lecture l = null;
	ExamType le = null;
    java.util.Vector llst = lt.getAll();
	java.util.Vector lelst = lte.getAll();
    NameCmp lcomparator = new NameCmp();
	for(int i=0; i<llst.size(); i++)
	{
	  //  out.println((lecture)llst.get(i));
	//	out.println("\n");
	//	out.println("");
	}
    java.util.Collections.sort(llst,lcomparator);
	%> <table> <%
	for(int i=0; i<llst.size(); i++)
	{
	    %><tr><td><%
		le=(ExamType)lte.get( ((Lecture)llst.get(i)).getExamTypeID() );
		out.println("ID="+((Lecture)llst.get(i)).getID()+" Name="+((Lecture)llst.get(i)).getName()+" Exam="+ le.getName() );
		
		%> </td><td><a href="admin.jsp?action=5&subaction=5&id=<%=((Lecture)llst.get(i)).getID()%> ">Снести</a></td></tr> <%
	}
	%> </table> <%
	%><form action="admin.jsp" method="get">
	  New Group:<input type="text" name="name"/>
	  Exam:   <select name="examtype">
	          <%
			  for(int k=0; k<lelst.size(); k++)
			  {
			      out.println( "<option value="+ ((ExamType)lelst.get(k)).getID() +">"+ ((ExamType)lelst.get(k)).getName() +"</option>"); 
			  }
			  %>
			  </select>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="5"> 
	  <input type="submit" value="Create" />
	  </form>
	<%
	break;
}	
	
%>