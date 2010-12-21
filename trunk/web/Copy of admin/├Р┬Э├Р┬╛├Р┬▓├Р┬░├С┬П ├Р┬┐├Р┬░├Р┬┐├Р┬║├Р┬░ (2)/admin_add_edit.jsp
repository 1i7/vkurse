<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>


<html>
<body>
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>	
	
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
String text = null;
String encoding = null;
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
		    if(request.getParameter("confirm")==null)
			{
			    out.println("<p>Точно хотите удалить?</p>");
				%> 
				   
				   <a href="admin_add_edit.jsp?action=5&subaction=<%= request.getParameter("subaction") %>&confirm=1&id=<%= request.getParameter("id") %>">Да</a>
				   </br>
				   <a href="admin_list.jsp?subaction=<%= request.getParameter("subaction") %>">Нет</a></br>
				   
				   
				   
			    <%
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
			case 4:
		    LectureTypesTable ltremovetable= factory.getLectureTypesTable();
			ltremovetable.remove(Short.parseShort(request.getParameter("id")));
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
			response.sendRedirect("admin_list.jsp?subaction="+request.getParameter("subaction"));
			out.println("DAMN");
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
				 text = request.getParameter("name");
				  
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 gaaa.setName(text);
				 text = request.getParameter("course");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 gaaa.setCourse("0");
				 gaddtable.insert(gaaa);
				 break;
				case 1:
				 RoomsTable raddtable= factory.getRoomsTable();  
				 Room raaa = new Room();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 raaa.setName(text);
				 raddtable.insert(raaa);
				 break;
				case 2:
				 TeachersTable taddtable= factory.getTeachersTable();  
				 Teacher taaa = new Teacher();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 taaa.setName(text);	
				 text = request.getParameter("degree");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 taaa.setDegree(text);
				 taddtable.insert(taaa);
				 break;
				case 3:
				 ExamTypesTable eaddtable= factory.getExamTypesTable();  
				 ExamType eaaa = new ExamType();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) 
				 {
				 encoding = "UTF-8";
				 }				 
				 text = new String(text.getBytes(), encoding);
				 eaaa.setName(text);
				 eaddtable.insert(eaaa);
				 break;
				case 4:
				 LectureTypesTable leaddtable= factory.getLectureTypesTable();  
				 LectureType leaaa = new LectureType();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) 
				 {
				 encoding = "UTF-8";
				 }				 
				 text = new String(text.getBytes(), encoding);
				 leaaa.setName(text);
				 leaddtable.insert(leaaa);
				 break;
				case 5:
				 LecturesTable laddtable= factory.getLecturesTable();  
				 Lecture laaa = new Lecture();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}
				 text = new String(text.getBytes(), encoding);
				 laaa.setName(text);
				 laaa.setExamTypeID(Short.parseShort(request.getParameter("examtype")));
				 laaa.setComment("");
				 laddtable.insert(laaa);
				 break;
				}
				response.sendRedirect("admin_list.jsp?subaction="+request.getParameter("subaction"));
		    }
		}
		break;
	case 7:
		int id = Short.parseShort(request.getParameter("id"));
	    if(request.getParameter("confirm")==null)
		{
		    //out.println("CONFIRM=NULL");
			switch(Short.parseShort(request.getParameter("subaction")))
	        {
			case 0:
				//edit groups
				GroupsTable gt = factory.getGroupsTable();
				Group g = null;
				g = gt.get(id);
	
				%><form action="admin_add_edit.jsp" method="get">
				Группа:<input type="text" name="name" value="<%= g.getName()%>"/>
				<% 
				//Курс: <input type="text" name="course" value=""/> 
				%>
				<input type="hidden" name="action" value="7"/>  
				<input type="hidden" name="subaction" value="0"/> 
				<input type="hidden" name="confirm" value="1"/>
				<input type="hidden" name="id" value="<%= id %>"/>
				</br>
				<input type="submit" value="Сохранить" />
				</form>
				
				<form action="admin_list.jsp">
				<input type="hidden" name="subaction" value="0"/>
				<input type="submit" value="Отмена" />
				</form>
				<%
				break;
			case 1:
				//edit Rooms
				RoomsTable rt = factory.getRoomsTable();
				Room r = null;
				r = rt.get(id);
				%>
				<form action="admin_add_edit.jsp" method="get">
				Аудитория:<input type="text" name="name" value="<%= r.getName()%>" />
				<input type="hidden" name="action" value="7">  
				<input type="hidden" name="subaction" value="1">
				<input type="hidden" name="confirm" value="1"/>
				<input type="hidden" name="id" value="<%= id %>"/>
						</br>
				<input type="submit" value="Сохранить" />
				</form>
		
				<form action="admin_list.jsp">
				<input type="hidden" name="subaction" value="1"/>
				<input type="submit" value="Отмена" />
				</form>
				<%
				break;
				case 2:
				//edit Teachers
				TeachersTable tt = factory.getTeachersTable();
				Teacher t = null;
				t = tt.get(id);
				%><form action="admin_add_edit.jsp" method="get">
				Имя:<input type="text" name="name" value="<%= t.getName()%>"/>
				Ученая степень: <input type="text" name="degree" value="<%= t.getDegree()%>"/>
				<input type="hidden" name="action" value="7">  
				<input type="hidden" name="subaction" value="2">
				<input type="hidden" name="confirm" value="1"/>
				<input type="hidden" name="id" value="<%= id %>"/>
					</br>
				<input type="submit" value="Сохранить" />
				</form>
			
				<form action="admin_list.jsp">
				<input type="hidden" name="subaction" value="2"/>
				<input type="submit" value="Отмена" />
				</form>
				<%
				break;
				case 3:
				//edit examtypes
				ExamTypesTable et = factory.getExamTypesTable();
				ExamType e = null;
				e = et.get(id);
				%>
				<form action="admin_add_edit.jsp" method="get">
				Экзамен:<input type="text" name="name" value="<%= e.getName() %>" />
				<input type="hidden" name="action" value="7"> 
				<input type="hidden" name="subaction" value="3">	  
				<input type="hidden" name="confirm" value="1"/>
				<input type="hidden" name="id" value="<%= id %>"/>
					</br>
				<input type="submit" value="Сохранить" />
				</form>
			
				<form action="admin_list.jsp">
				<input type="hidden" name="subaction" value="3"/>
				<input type="submit" value="Отмена" />
				</form>
				<%
				break;
			case 4:
	
				break;
			case 5:
				//edit groups
				LecturesTable lt = factory.getLecturesTable();
				ExamTypesTable lte = factory.getExamTypesTable();
				Lecture l = null;
				ExamType le = null;
				java.util.Vector lelst = lte.getAll();
				l = lt.get(id);
    
				%><form action="admin_add_edit.jsp" method="get">
				Предмет:<input type="text" name="name" value="<%= l.getName() %>"/>
				Отчетность:   <select name="examtype" value="<%= l.getExamTypeID() %>" >
				<%
				for(int k=0; k<lelst.size(); k++)
				{
					out.println( "<option value=\""+ ((ExamType)lelst.get(k)).getID() +"\">"+ ((ExamType)lelst.get(k)).getName() +"</option>"); 
				}
				%>
				</select>
				<input type="hidden" name="action" value="7">  
				<input type="hidden" name="subaction" value="5"> 
				<input type="hidden" name="confirm" value="1"/>
				<input type="hidden" name="id" value="<%= id %>"/>
					</br>
				<input type="submit" value="Сохранить" />
				</form>
			
				<form action="admin_list.jsp">
				<input type="hidden" name="subaction" value="5"/>
				<input type="submit" value="Отмена" />
				</form>
				<%
				break;
			}
		}
		else
		{
			{
				switch(Short.parseShort(request.getParameter("subaction")))
				{
				case 0:
			     GroupsTable gaddtable= factory.getGroupsTable();  
				 Group gaaa = new Group();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}			
				 text = new String(text.getBytes(), encoding);
				 gaaa.setName(text);
				 //gaaa.setCourse(request.getParameter("course"));
				 gaaa.setCourse("0");
				 gaaa.setID(id);
				 gaddtable.update(gaaa);
				 //gaddtable.insert(gaaa);
				 break;
				case 1:
				 RoomsTable raddtable= factory.getRoomsTable();  
				 Room raaa = new Room();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 raaa.setName(text);
				 raaa.setID(id);
				 //raddtable.insert(raaa);
				 raddtable.update(raaa);
				 break;
				case 2:
				 TeachersTable taddtable= factory.getTeachersTable();  
				 Teacher taaa = new Teacher();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 taaa.setName(text);
				 text = request.getParameter("degree");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}			
				 text = new String(text.getBytes(), encoding);
				 taaa.setDegree(text);
				 //taddtable.insert(taaa);
				 taaa.setID(id);
				 taddtable.update(taaa);
				 break;
				case 3:
				 ExamTypesTable eaddtable= factory.getExamTypesTable();  
				 ExamType eaaa = new ExamType();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}			
				 text = new String(text.getBytes(), encoding);
				 eaaa.setName(text);
				 eaaa.setID(id);
				 out.println(id);
				 eaddtable.update(eaaa);
				 //eaddtable.insert(eaaa);
				 break;
				case 5:
				 LecturesTable laddtable= factory.getLecturesTable();  
				 Lecture laaa = new Lecture();
				 text = request.getParameter("name");
				  
				 encoding = request.getCharacterEncoding();
				 if (encoding == null) {encoding = "UTF-8";}				
				 text = new String(text.getBytes(), encoding);
				 laaa.setName(text);
				 laaa.setExamTypeID(Short.parseShort(request.getParameter("examtype")));
				 laaa.setComment("");
				 //laddtable.insert(laaa);
				 laaa.setID(id);
				 laddtable.update(laaa);
				 break;
				}
			
			
			}
		    response.sendRedirect("admin_list.jsp?subaction="+request.getParameter("subaction"));
		}
		break;
}
	
	
%>
</body>		
</html>