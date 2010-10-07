<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
	 
<% 
try{
	//connect to database
	java.io.PrintWriter sout = response.getWriter();
    TableFactory factory = new PgSqlTableFactory();
    Lecture l = null;
    LecturesTable lt = factory.getLecturesTable();
    //finish utility
	//print head
    sout.println("<html>");
	sout.println("<head>");
	sout.println("<title>Интерфейс администратора</title>");
	sout.println("</head>");
	sout.println("<body>");
	//head printed
	//if we want to add smth - print it
	if(request.getParameter("add")!=null && Short.parseShort(request.getParameter("add"))==1) 
    {
	sout.println("<form action=lectures_add_edit.jsp method=get>");
	sout.println("Name : <input type=text name=name /></br>");
	sout.println("Exam : <select name=type>");
	sout.println("<option value=0>Зачет</option>");
	sout.println("<option value=1>Экзамен</option>");
	sout.println("</select></br>");
	sout.println("Comment : <input type=text name=comment /></br>");
	sout.println("<input type=submit value=process name=really_add>");
	sout.println("</form>");
    }
	//adding form printed
	//deleting a record
	if(request.getParameter("delete")!=null) 
    {
		boolean r;
		r = lt.remove(Short.parseShort(request.getParameter("delete")));
        if (r)
        {
            out.println("Record was deleted</br>");
        }
        else
        {
            out.println("Record was not deleted</br>");
        }
        sout.println("");

    } 
	//finished deleting
	if(request.getParameter("really_add")!=null)
	{
		//searching for empty ID(bottomly)
		int id=0;
		for(id=0;lt.get(id)!=null;id++)
			;//нашли пустой id. метод ужасный
		//search ended
		Lecture l2 = new Lecture(id, request.getParameter("name"),
									Short.parseShort(request.getParameter("type")),
									request.getParameter("comment"));
        //  Можно изменить какую-либо информацию
        //l2.setComment("Comment of new lecture");
        //  Ну и в конце концов мы все-таки добавляем новую запись
        boolean r = lt.insert(l2);
        if (r)
        {
            sout.println("Record was added</br>");
        }
        else
        {
            //  Но запись может и не добавиться, например, если такой ID уже есть
            sout.println("Record was not added</br>");
        }
        

	}
	

	if(request.getParameter("add")==null)
	{
        //This code prints table
        l = lt.get(0);
        java.util.Vector lst = lt.getAll();
        int i=0;
        for (i=0; i<lst.size(); ++i)
        {
            sout.println("  Lecture "+i+":");
    		//  Т.к. класс Vector хранит в себе только тип Object, являющийся
    		//  прародителем для всех классов в java, нужно сделать явное
    		//  приведение типов к тому, что там на самом деле хранится
    		l = (Lecture)lst.get(i);
    		if (l != null)
    		{
        	sout.println("id:         " + l.getID()+ "<br/>");
        	sout.println("name:       '" + l.getName()+"'"+ "<br/>");
        	sout.println("examTypeID: " + l.getExamTypeID()+ "<br/>");
        	sout.println("comment:    '" + l.getComment()+"'"+ "<br/>");
        	sout.println("<a href=lectures_add_edit.jsp?edit="+ l.getID() + ">Изменить</a>"+ "<br/>");
        	sout.println("<a href=lectures_add_edit.jsp?delete="+ l.getID() + ">Снести</a>"+ "<br/>");
        	sout.println("<br/>");
    		}
    		else
    		{	
        	sout.println("No data about lecture");
    		}
    		sout.println("<br/>");sout.println("<br/>");
    		//debug
    		//sout.println("ADD=="+request.getParameter("add"));
		}

    	sout.println("<a href=lectures_add_edit.jsp?add=1>Новая Запись</a>");
	}
    	//end page
    	sout.println("</body>");
    	sout.println("</html>");
}

catch (TableException e) 
{
	// TODO Auto-generated catch block
	e.printStackTrace();
}

%>

	