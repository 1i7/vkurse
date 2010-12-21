<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<%
/*
TableFactory lufactory = new PgSqlTableFactory();
LectureTypesTable lonnl = lufactory.getLectureTypesTable();
LectureType lon = new LectureType();
lon.setName("Test");
int rarra = lonnl.insert(lon);
out.println("%d"+ rarra);
*/
%>
<%
    //Cool - our scriptlet
    TableFactory factorys = new PgSqlTableFactory();
    Group gg = null;
    GroupsTable gtq = factorys.getGroupsTable(); 
    java.util.Vector lst = gtq.getAll();
%>

<html>
	<head>
	<title>Списки</title>
	<link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div id="cap" align="center"  ><img align="center" src="sketch_web_cr1.png"  ></div>
	<div id="container" align="center">
		<table align="left"  bordercolor="#FFFFFF">
		<tr>
			<td>
				<p class="color" align="center"  > Редактировать расписание:</p>
			</td>
		</tr>
		<tr>
			<td>
				<form align="right" action="edit_schedule.jsp" method="get">
				<select align="center" name="group">
    	<%
	int ii=0;
	for (ii=0; ii<lst.size(); ii++)
        {
		    gg = (Group)lst.get(ii);
		    out.println("<option value=\""+gg.getID()+"\">"+gg.getName()+"</option>");
	    }
	%>
				</select></br>
				<input type="submit" align="center" value="Выбрать группу">
				</form>
				</p>
			</td>
		</tr>
		<tr> 
			<td>
				<p class="color" align="center"  >Редактировать списки: </p>
			</td>
		</tr>
		<tr>
			<td><a href="admin_list.jsp?subaction=2"><p align="right">Список преподавателей</p></a></td></tr>
			<tr><td><a href="admin_list.jsp?subaction=5"><p align="right">Список предметов</p></a></td></tr>
			<tr><td><a href="admin_list.jsp?subaction=0"><p align="right">Список групп</p></a></td></tr>
			<tr><td><a href="admin_list.jsp?subaction=1"><p align="right">Список аудиторий</p></a></td></tr>
			<tr><td><a href="admin_list.jsp?subaction=3"><p align="right">Список видов отчётности</p></a></td></tr>
			<tr><td><a href="admin_list.jsp?subaction=4"><p align="right">Список видов занятий</p></a></td>
		</tr>
		</table>
        <div id="left" ><img src="sketch_web4.png"  > </div>
        <table>
			<tr>
				<td>
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
	%><div align="center"><h1>Список групп</h1></div><%
	%> <table align="center"> <%
	for(int i=0; i<glst.size(); i++)
	{
	    %><tr><td width="200"><%
		out.println((i+1)+". "+" Группа "+((Group)glst.get(i)).getName());
		//out.println("ID="+((Group)glst.get(i)).getID()+" Name="+((Group)glst.get(i)).getName());
		%> </td>
		<td><a href="admin_add_edit.jsp?action=5&subaction=0&id=<%=((Group)glst.get(i)).getID()%> ">Удалить </a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=0&id=<%=((Group)glst.get(i)).getID()%> ">Править</a></td>
		
		</tr> <%
	}
	%> </table> <%
	%><form action="admin_add_edit.jsp" method="get">
	  Новая Группа:<input type="text" name="name"/>
	  <!-- Курс: <input type="text" name="course"/> -->
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="0"> 
	  <input type="submit" value="Создать" />
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
	%><div align="center"><h1>Список аудиторий</h1></div><%
	%> <table align="center"> <%
	for(int i=0; i<rlst.size(); i++)
	{
	    %><tr><td width="200"><%
		out.println((i+1)+". "+" Аудитория "+((Room)rlst.get(i)).getName());
		
		%> </td>
		<td><a href="admin_add_edit.jsp?action=5&subaction=1&id=<%=((Room)rlst.get(i)).getID()%> ">Удалить</a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=1&id=<%=((Room)rlst.get(i)).getID()%> ">Править</a></td>
		</tr> <%
	}
	%> </table> <%
	%><form action="admin_add_edit.jsp" method="get">
	  Новая Аудитория:<input type="text" name="name"/>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="1">
	  <input type="submit" value="Создать" />
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
	%><div ><h1>Список преподавателей</h1></div><%
	%>
				<div id="container">
		<table align="left"> 
		 <%
	for(int i=0; i<tlst.size(); i++)
	{
	    %><tr>
		<td>
		<%
		//out.println((i+1)+". "+((Teacher)tlst.get(i)).getName()+" "+((Teacher)tlst.get(i)).getDegree());
		out.println((i+1));
		%> 
		</td>
		<td>
		<%
		out.println(((Teacher)tlst.get(i)).getName());
		%>
		</td>
		<td>
		<%
		out.println(((Teacher)tlst.get(i)).getDegree());
		%>
		</td>
		
		
		<td><a href="admin_add_edit.jsp?action=5&subaction=2&id=<%=((Teacher)tlst.get(i)).getID()%> ">Удалить</a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=2&id=<%=((Teacher)tlst.get(i)).getID()%> ">Править</a></td>
		</tr> <%
	}
	%> </table> <%
	%>
		<div id="container"  align="left">
        <form action="admin_add_edit.jsp" method="get">
		<div id="left"  align="center">Имя:<input type="text" name="name"/>
	  <div id="left"  align="center">Ученая степень: <input type="text" name="degree"/>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="2">
	  <input type="submit" value="Создать" /></div></div>
	  </form></div></div>
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
	%><div align="center"><h1>Список видов отчетности</h1></div><%
	%> <table align="center"> <%
	for(int i=0; i<elst.size(); i++)
	{
	    %><tr><td width="200"><%
		out.println((i+1)+". "+" "+((ExamType)elst.get(i)).getName() );
		
		%> </td>
		<td><a href="admin_add_edit.jsp?action=5&subaction=3&id=<%=((ExamType)elst.get(i)).getID()%> ">Удалить</a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=3&id=<%=((ExamType)elst.get(i)).getID()%> ">Править</a></td>
		</tr> <%
	}
	%> 
	</table> 
	
	<div align="center" >
	<form action="admin_add_edit.jsp" method="get">
	  Новый:<input type="text" name="name"/>
	  <input type="hidden" name="action" value="6"> 
<input type="hidden" name="subaction" value="3">	  
	  <input type="submit" value="Создать" />
	  </form>
	</div>
	<%
	break;
	case 4:
	//list LectureTypes
	final class NameCmp implements java.util.Comparator
    {
        public int compare(Object obj1, Object obj2)
	    {
	        LectureType one = (LectureType)obj1;
		    LectureType two = (LectureType)obj2;
		    return(one.getName().compareTo(two.getName()));
	    }
    }
	//edit examtypes
	LectureTypesTable etl = factory.getLectureTypesTable();
	LectureType el = null;
    java.util.Vector elstl = etl.getAll();
    NameCmp ecomparatorl = new NameCmp();
	for(int i=0; i<elstl.size(); i++)
	{
	  //  out.println((LectureType)elstl.get(i));
	//	out.println("\n");
	//	out.println("");
	}
    java.util.Collections.sort(elstl,ecomparatorl);
	%><div align="center"><h1>Список видов занятий</h1></div><%
	%> <table align="center"> <%
	for(int i=0; i<elstl.size(); i++)
	{
	    %><tr><td width="200"><%
		out.println((i+1)+". "+" "+((LectureType)elstl.get(i)).getName() );
		
		%> </td>
		<td><a href="admin_add_edit.jsp?action=5&subaction=4&id=<%=((LectureType)elstl.get(i)).getID()%> ">Удалить</a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=4&id=<%=((LectureType)elstl.get(i)).getID()%> ">Править</a></td>
		</tr> <%
	}
	%> 
	</table> 
	<%
	%>
	<div align="center" >
	<form action="admin_add_edit.jsp" method="get">
	  Новый:<input type="text" name="name"/>
	  <input type="hidden" name="action" value="6"> 
<input type="hidden" name="subaction" value="4">	  
	  <input type="submit" value="Создать" />
	  </form>
	</div>
	<%
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
	%><div align="center"><h1>Список предметов</h1></div><%
	%> <table align="center"> <%
	for(int i=0; i<llst.size(); i++)
	{
		le=(ExamType)lte.get( ((Lecture)llst.get(i)).getExamTypeID() );
	    %><tr>
		<td>
			<%
			out.println((i+1) );
			//out.println((i+1)+". "+((Lecture)llst.get(i)).getName()+" "+ le.getName() );
		    //out.println((i+1) + " " + ((Lecture)llst.get(i)).getName() + " " + ((Lecture)llst.get(i)).getExamTypeID() );
			%> 
		</td>
		<td>
			<%
			out.println(((Lecture)llst.get(i)).getName() );
			
			%>
		</td>
		<td>
			<%
			//out.println( le.getName() );
			out.println( ((Lecture)llst.get(i)).getExamTypeID() );
			%>
		</td>
		<td><a href="admin_add_edit.jsp?action=5&subaction=5&id=<%=((Lecture)llst.get(i)).getID()%> ">Удалить</a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=5&id=<%=((Lecture)llst.get(i)).getID()%> ">Править</a></td>
		</tr> <%
	}
	%> </table> <%
	%><form action="admin_add_edit.jsp" method="get">
	  Новый Предмет:<input type="text" name="name"/>
	  Отчетность:   <select name="examtype">
	          <%
			  for(int k=0; k<lelst.size(); k++)
			  {
			      out.println( "<option value=\""+ ((ExamType)lelst.get(k)).getID() +"\">"+ ((ExamType)lelst.get(k)).getName() +"</option>"); 
			  }
			  %>
			  </select>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="5"> 
	  <input type="submit" value="Создать" />
	  </form>
	<%
	break;
	
	
	
    }	
	
%>
</td></tr>
		</table>
		<div id="right"><img src="sketch_web_cr6.png"  ></div>
	</div>
<div id="cap" align="center" ><img src="sketch_web2.png"   ></div>

</body>
</html>