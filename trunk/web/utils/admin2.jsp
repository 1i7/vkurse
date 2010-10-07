<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>
<!-- Shitty utilities. Copypaste isupov -->

<%

/*
TableFactory factory = new TestTableFactory();
Lecture l = null;
LecturesTable lt = factory.getLecturesTable();

//  Эта команда выводит текст в стандарный поток вывода. Обычно это
//  консоль, но если запускать из под NetBeans 6.8, то текст выводится
//  внизу под текстом программы
System.out.println("Welcome to VkurseAPI test app");
System.out.println("");

//  Попробуем получить информацию о предмете с ID=0
System.out.println("Getting lecture with ID=0");
l = lt.get(0);
//  Если мы что-то получили, выводим информацию
if (l != null)
{
    System.out.println("  Lecture:");
    System.out.println("id:         " + l.getID());
    System.out.println("name:       '" + l.getName()+"'");
    System.out.println("examTypeID: " + l.getExamTypeID());
    System.out.println("comment:    '" + l.getComment()+"'");
}
//  Иначе - говорим, что ничего не получили
else
{
    System.out.println("No data about lecture");
}
System.out.println("");

//  Повторяем операцию уже с ID=12321, которого нет
System.out.println("Getting lecture with ID=12321");
l = lt.get(12321);
if (l != null)
{
    System.out.println("  Lecture:");
    System.out.println("id:         " + l.getID());
    System.out.println("name:       '" + l.getName()+"'");
    System.out.println("examTypeID: " + l.getExamTypeID());
    System.out.println("comment:    '" + l.getComment()+"'");
}
else
{
    System.out.println("No data about lecture");
}
System.out.println("");
System.out.println("");



int i;
System.out.println("        All:");
//  А теперь получим данные обо всех занятиях
java.util.Vector lst = lt.getAll();  //  Класс Vector находится в пакете
            // java.util, указано его полное имя, но т.к. мы подключили
            // этот пакет в самом верху, можно было объявить переменную
            // так: Vector lst = lt.getAll();

//  Выведем информацию о занятиях
for (i=0; i<lst.size(); ++i)
{
    System.out.println("  Lecture "+i+":");
    //  Т.к. класс Vector хранит в себе только тип Object, являющийся
    //  прародителем для всех классов в java, нужно сделать явное
    //  приведение типов к тому, что там на самом деле хранится
    l = (Lecture)lst.get(i);
    if (l != null)
    {
        System.out.println("id:         " + l.getID());
        System.out.println("name:       '" + l.getName()+"'");
        System.out.println("examTypeID: " + l.getExamTypeID());
        System.out.println("comment:    '" + l.getComment()+"'");
    }
    else
    {
        System.out.println("No data about lecture");
    }
}
System.out.println("");
System.out.println("");



Boolean r;
//  Попробуем удалить несуществующую запись с ID=100
r = lt.remove(100);
if (r)
{
    System.out.println("Record with ID=100 was deleted");
}
else
{
    System.out.println("Record with ID=100 was not deleted");
}



//  Теперь мы добавим новую запись
//  Для этого мы сначала создаем переменную типа Lecure, хранящую в себе
//  все информацию о лекции. Данные можно указать в конструкторе (а можно
//  и не указывать)
Lecture l2 = new Lecture(100, "New lecture", 0, "");
//  Можно изменить какую-либо информацию
l2.setComment("Comment of new lecrute");
//  Ну и в конце концов мы все-таки добавляем новую запись
r = lt.insert(l2);
if (r)
{
    System.out.println("Record with ID=100 was added");
}
else
{
    //  Но запись может и не добавиться, например, если такой ID уже есть
    System.out.println("Record with ID=100 was not added");
}
System.out.println("");


//  Выведем все записи, чтобы убедиться, что она добавилась
lst = lt.getAll();
for (i=0; i<lst.size(); ++i)
{
    System.out.println("  Lecture "+i+":");
    l = (Lecture)lst.get(i);
    if (l != null)
    {
        System.out.println("id:         " + l.getID());
        System.out.println("name:       '" + l.getName()+"'");
        System.out.println("examTypeID: " + l.getExamTypeID());
        System.out.println("comment:    '" + l.getComment()+"'");
    }
    else
    {
        System.out.println("No data about lecture");
    }
}
System.out.println("");


//  Что ж, новую запись добавлять научились - отлично! Но вот досада,
//  уже добавив запись, мы обнаружили, что допустили опечатку.
//  Нужно эту запись изменить. Сначала мы получим данные, потом
//  внесем в переменную класса Lecture все изменения, а потом уже
//  внесем эти изменения на сервер.
Lecture l3 = lt.get(100);  //  Получаем данные
if (l3 != null)
{
    //  Корректируем данные
    l3.setName("New name of lecture");
    l3.setComment("New comment");

    //  И посылаем новые данные на... на сервер =)
    r = lt.update(l3);
    //  И проверяем, получилось, али нет
    if (r)
    {
        System.out.println("Record was updated");
    }
    else
    {
        System.out.println("Record was not updated");
    }
}
else
{
    //  Ой, изменять-то нечего =( Хотя странно, кто успел удалить запись,
    //  которую мы только что добавили
    System.out.println("No data about lecture to change");
}
System.out.println("");

//  Нам же сказали, что получилось! Но, как говорится, доверяй, но
//  проверяй! Поэтому мы снова посмотрим, что там хранится в БД
//  в записи под номером 100
l = lt.get(100);
if (l != null)
{
    System.out.println("  Lecture:");
    System.out.println("id:         " + l.getID());
    System.out.println("name:       '" + l.getName()+"'");
    System.out.println("examTypeID: " + l.getExamTypeID());
    System.out.println("comment:    '" + l.getComment()+"'");
}
else
{
    System.out.println("No data about lecture");
}
System.out.println("");


//  А теперь попробуем удалить существующую запись с ID=100
r = lt.remove(100);
if (r)
{
    System.out.println("Record with ID=100 was deleted");
}
else
{
    System.out.println("Record with ID=100 was not deleted");
}
System.out.println("");


//  И снова выведем все записи
lst = lt.getAll();
for (i=0; i<lst.size(); ++i)
{
    System.out.println("  Lecture "+i+":");
    l = (Lecture)lst.get(i);
    if (l != null)
    {
        System.out.println("id:         " + l.getID());
        System.out.println("name:       '" + l.getName()+"'");
        System.out.println("examTypeID: " + l.getExamTypeID());
        System.out.println("comment:    '" + l.getComment()+"'");
    }
    else
    {
        System.out.println("No data about lecture");
    }
}
System.out.println("");
System.out.println("");


*/
%>

<%!
TableFactory factory = new TestTableFactory();
LecturesTable lect = factory.getLecturesTable();
ScheduleTable st = factory.getScheduleTable();
Lecture lectt = null;
Schedule sch = null;

public String get_sch(byte day, int time)
{
	try{
	Schedule empty_sch = null;
//	empty_sch.setComment("Empty");
	java.util.Vector lst = st.getAll();
	for (int i=0; i<lst.size(); i++)
    {
        //System.out.println("  Schedule "+i+":");
        //  Т.к. класс Vector хранит в себе только тип Object, являющийся
        //  прародителем для всех классов в java, нужно сделать явное
        //  приведение типов к тому, что там на самом деле хранится
        sch = (Schedule)lst.get(i);
        if (sch != null)
        {
            //System.out.println("id:         " + sch.getID());
            //System.out.println("comment:    '" + sch.getComment()+"'");
            if(day==sch.getDay() /*&& time==sch.getStartTime()*/)
            {
            	System.out.println("debug");
            	//return("ID="+Integer.toString(sch.getLectureID()));
            	lectt = lect.get(sch.getLectureID());
            	return lectt.getName();
            }
        }
        else
        {
            System.out.println("No data about lecture");
            System.out.println("Пусто\n");
            return "Пусто";   
        }
        
    }
	
	}
	catch(TableException e)
	{
		e.printStackTrace();
	}
	return "Пусто";
}


%>


<%
    

%>


<!-- Shitty utils end -->
<html>
	<head>
	<title>Интерфейс администратора</title>
	</head>
	<body>
	<a href="admin_entrance.jsp">НА ВХОД</a>
	<p align="center"><font size="6" color="Aqua">Расписание</font></p>

	<table border=10 align=center width="50%" bgcolor="Teal" bordercolor="Teal">
	<tr bgcolor="Aqua" border=10> 
	<td><div align="center">Time</div></td>
	<td width="15%"><div align="center">пн.</div><div>10.05</div></td>
	<td><div align="center">вт.</div><div align="center">11.05</div></td>
	<td><div align="center">ср.</div><div align="center">12.05</div></td>
	<td><div align="center">чт.</div><div align="center">13.05</div></td>
	<td><div align="center">пт.</div><div align="center">14.05</div></td>
	<td><div align="center">сб.</div><div align="center">15.05</div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">9:00</div><div align="center">10:25</div></td>
	<td><a href="admin.jsp"><div align="center"><%= get_sch((byte)1,0)%></div></a></td>
	<td><a href="admin.jsp"><div align="center"><%= get_sch((byte)2,0)%></div></a></td>
	<td><a href="admin.jsp"><div align="center"><%= get_sch((byte)3,0)%></div></a></td>
	<td><a href="admin.jsp"><div align="center"><%= get_sch((byte)4,0)%></div></a></td>
	<td><a href="admin.jsp"><div align="center"><%= get_sch((byte)5,0)%></div></a></td>
	<td><a href="admin.jsp"><div align="center"><%= get_sch((byte)6,0)%></div></a></td>
	<td><a href="admin.jsp"><div align="center"></div></a></td>
	</tr>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">10:45</div><div align="center">12:20</div></td>
	<td><a href="admin.jsp"><div align="center">Теорфиз</div></a></td>
	<<td><a href="admin.jsp"><div align="center">Матан</div></a></td>
	<td><a href="admin.jsp"><div align="center">Диффуры</div></a></td>
	<td><a href="admin.jsp"><div align="center">перерыв</a></td>
	<td><a href="admin.jsp"><div align="center">физ-ра</div></a></td>
	<td><a href="admin.jsp"><div align="center">Лабораторные работы</div></a></td>
	<td><a href="admin.jsp"><div align="center"></div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">9:00</div><div align="center">10:25</div>
	<td><a href="admin.jsp"><div align="center">Английский</div></a></td>
	<td><a href="admin.jsp"><div align="center">Метопты</div></a></td>
	<td><a href="admin.jsp"><div align="center">Информатика</div></a></td>
	<td><a href="admin.jsp"><div align="center">Физ-ра</a></td>
	<td><a href="admin.jsp"><div align="center">Химия</div></a></td>
	<td><a href="admin.jsp"><div align="center">Лабораторные работы</div></a></td>
	<td><a href="admin.jsp"><div align="center"></div></a></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">13:55</div><div align="center">15:20</div></td>
	<td><a href="admin.jsp"><div align="center">Перерыв</div></a></td>
	<td><a href="admin.jsp"><div align="center">ППС</div></a></td>
	<td><a href="admin.jsp"><div align="center">Информатика</div></a></td>
	<td><a href="admin.jsp"><div align="center">Урматы</a></td>
	<td><a href="admin.jsp"><div align="center">Физика</div></a></td>
	<td><a href="admin.jsp"><div align="center"></div> <div align="center"> работы</div></a></td>
	<td><div align="center"></div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">15:30</div><div align="center">16:55</div></td>
	<td><a href="admin.jsp"><div align="center">Теормех</div></a></td>
	<td><a href="admin.jsp"><div align="center">Метопты</div></a></td>
	<td><a href="admin.jsp"><div align="center">Теормех</div></a></td>
	<td><a href="admin.jsp"><div align="center">Теорфиз</td></a>
	<td><a href="admin.jsp"><div align="center">Функан</div></a></td>
	<td><a href="admin.jsp"><div align="center"> работы</div></a></td>
	<td><a href="admin.jsp"><div align="center"></div></a></td>
	</tr>
	</table>

	<form action="" method="post">
	<p align="center">Курс<select name="course">
	<option value="c1">1 курс</option>
	<option value="c2">2 курс</option>
	<option value="c3">3 курс</option>
	<option value="c4">4 курс</option>
	<option value="c5">5 курс</option>
	<option value="c6">6 курс</option>
	</select></p>

	<p align="center">Группа<select name="group"></p>
	<option value="g1">91</option>
	<option value="g2">92</option>
	<option value="g3">93</option>
	<option value="g4">94</option>
	<option value="g8">98</option>
	</select>
	<p align="center">День недели<select name="week"></p>
	<option value="w1">пн</option>
	<option value="w2">вт</option>
	<option value="w3">ср</option>
	<option value="w4">чт</option>
	<option value="w5">пт</option>
	<option value="w6">сб</option>
	</select>
	
	<p align="center">Название предмета<select name="name"></p>
	<option value="n1">теорфиз</option>
	<option value="n2">Функан</option>
	<option value="n3">Урматы</option>
	<option value="n4">Физика</option>
	<option value="n5">Теормех</option>
	<option value="n6">Английский</option>
	</select>

	<p align="center">Время начала<input type="text"></p>
	<p align="center">Длительность<input type="text"></p>
	<p align="center">Аудитория<input type="text"></p>
	<p align="center">Преподаватель<input type="text"></p>

 	<p align="center">Форма отчётности<select name="form"></p>
	<option value="f1">экзамен</option>
	<option value="f2">зачёт</option>
	<option value="f3">Дифф.зачёт</option>
	</select>
	<a href="admin.jsp"><div align="center"><input type="submit" value="Редактировать"></div></a>
	<a href="admin.jsp"><div align="center"><input type="submit" value="Временно изменить"></div></a>
	</form>
	</body>
</html>