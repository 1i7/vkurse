<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<%


//out.println("testing");
/*biz.source_code.base64Coder.Base64Coder nccclass = new biz.source_code.base64Coder.Base64Coder;
String TEST = new String("testing");
out.println(TEST);
TEST = Base64Coder.encodeString(TEST);
out.println(TEST);
TEST = Base64Coder.decodeString(TEST);
out.println(TEST);
*/
%>

<html>
	<head>
	<title>Списки</title>
	<link href="style1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
	<a href="admin_entrance.jsp">На главную</a>
	
	<div align="center"><img src="sketch_web_cr1.png" ></div>
	<table align="center">
	<tr>
		
		<td><img src="sketch_web4.png"  > </td>
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
	%><div align="center"><h1>Список преподавателей</h1></div><%
	%> <table align="center"> <%
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
	%><form action="admin_add_edit.jsp" method="get">
	  Имя:<input type="text" name="name"/>
	  Ученая степень: <input type="text" name="degree"/>
	  <input type="hidden" name="action" value="6">  
	  <input type="hidden" name="subaction" value="2">
	  <input type="submit" value="Создать" />
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
	%><div align="center"><h1>Список видов отчетности</h1></div><%
	%> <table align="center"> <%
	for(int i=0; i<elst.size(); i++)
	{
	    %><tr><td width="200"><%
		out.println((i+1)+". "+" "+((ExamType)elst.get(i)).getName() + " |" + ((ExamType)elst.get(i)).getID() +"|");
		
		%> </td>
		<td><a href="admin_add_edit.jsp?action=5&subaction=3&id=<%=((ExamType)elst.get(i)).getID()%> ">Удалить</a></td>
		<td><a href="admin_add_edit.jsp?action=7&subaction=3&id=<%=((ExamType)elst.get(i)).getID()%> ">Править</a></td>
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
<input type="hidden" name="subaction" value="3">	  
	  <input type="submit" value="Создать" />
	  </form>
	</div>
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
			out.println( le.getName() );
			
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
		</td>
		<td><img src="sketch_web_cr6.png"  ></td>
	</tr>
	</table>
<div align="center"><img src="sketch_web2.png"   ></div>

</body>
</html>