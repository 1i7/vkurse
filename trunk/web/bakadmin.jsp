<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
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
	<td><a href="admin.jsp"><div align="center">WIP</div></a></td>
	<td><a href="admin.jsp"><div align="center">WIP</div></a></td>
	<td><a href="admin.jsp"><div align="center">WIP</div></a></td>
	<td><a href="admin.jsp"><div align="center">WIP</div></a></td>
	<td><a href="admin.jsp"><div align="center">WIP</div></a></td>
	<td><a href="admin.jsp"><div align="center">WIP</div></a></td>
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