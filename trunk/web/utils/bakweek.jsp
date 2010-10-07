<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="edu.phystech.vkurse.model.*, edu.phystech.vkurse.model.*,
     edu.phystech.vkurse.test.*, edu.phystech.vkurse.postgresql.*,
     java.util.*;"
%>

<html>
	<head>
	<title>Расписание на неделю</title>
	</head>
	<body>
	<p align="right"><a href="welcome.jsp"><img src="home.gif" align="top" border 2 width="8%"></a></p>
	<p align="center"><font size="6" color="Aqua">Расписание на неделю</font></p>

	<table border=10 align=center width="50%" bgcolor="Teal" bordercolor="Teal">
	<tr bgcolor="Aqua" border=10> 
	<td><div align="center">Time</div></td>
	<td width="15%"><div align="center">пн.</div></td>
	<td><div align="center">вт.</div></td>
	<td><div align="center">ср.</div></td>
	<td><div align="center">чт.</div></td>
	<td><div align="center">пт.</div></td>
	<td><div align="center">сб.</div></td>
	<td><div align="center">вс.</div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">9:00</div><div align="center">10:25</div></td>
	<td><div align="center">Урматы</div></td>
	<td><div align="center">Функан</div></td>
	<td><div align="center">Английский</div></td>
	<td><div align="center">Инновационный</div> <div align="center">практикум</div></td>
	<td><div align="center">Астрономия</div></td>
	<td><div align="center">Физика</div></td>
	<td><div align="center"></div></td>
	</tr>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">10:45</div><div align="center">12:20</div></td>
	<td><div align="center">Теорфиз</div></td>
	<td><div align="center">Матан</div></td>
	<td><div align="center">Диффуры</div></td>
	<td><div align="center">перерыв</td>
	<td><div align="center">физ-ра</div></td>
	<td><div align="center">Лабораторные</div> <div align="center"> работы</div></td>
	<td><div align="center"></div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">9:00</div><div align="center">10:25</div></td>
	<td><div align="center">Английский</div></td>
	<td><div align="center">Метопты</div></td>
	<td><div align="center">Информатика</div></td>
	<td><div align="center">Физ-ра</td>
	<td><div align="center">Химия</div></td>
	<td><div align="center">Лабораторные</div> <div align="center"> работы</div></td>
	<td><div align="center"></div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">13:55</div><div align="center">15:20</div></td>
	<td><div align="center">Перерыв</div></td>
	<td><div align="center">ППС</div></td>
	<td><div align="center">Информатика</div></td>
	<td><div align="center">Урматы</td>
	<td><div align="center">Физика</div></td>
	<td><div align="center"></div> <div align="center"> работы</div></td>
	<td><div align="center"></div></td>
	</tr>
	<tr bgcolor="Aqua" border=10> 
	<td width="15%"><div align="center">15:30</div><div align="center">16:55</div></td>
	<td><div align="center">Теормех</div></td>
	<td><div align="center">Метопты</div></td>
	<td><div align="center">Теормех</div></td>
	<td><div align="center">Теорфиз</td>
	<td><div align="center">Функан</div></td>
	<td><div align="center"></div> <div align="center"> работы</div></td>
	<td><div align="center"></div></td>
	</tr>
	</table>

	</body>
</html>