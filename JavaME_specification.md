# Формат, в который запаковываются классы при передаче в строке #

Все классы, наследуемые от DbTableRecord, имеют схожий вид - это просто набор параметров с методами для их получения/задания. Формат упаковывания этих классов в строку крайне прост. Каждый из параметров имеет свое название и имеет один из двух типов: строковый или целочисленный (int или byte, но при передаче в строке это не имеет значения). Строка имеет вид: "параметр1=значение1 параметр2=значение2 ... параметрN=значениеN" - т.е. пары параметр=значение, разделенные одним пробелом. При этом значение может быть заключено в одинарные кавычки (на случай, если в нем содержатся пробелы). Сами одинарные кавычки в значении заменяются тегом `"<apostrophe>"`. Имена параметров case-sensitive и строятся по следующим правилам:
  * первое слово в названии параметра пишется маленькими буквами с маленькой буквы
  * второе и последующие слова пишутся маленькими буквами, но с большой буквы
  * "ID" всегда пишется большими буквами

Чтобы не возникало никаких сомнений, выписываю используемые имена во всех классах:

> _ExamType:_<br>
ID <br>
name <br></li></ul>

<blockquote><i>Group:</i> <br>
ID <br>
name <br>
course <br></blockquote>

<blockquote><i>Lecture:</i> <br>
ID <br>
name <br>
examTypeID <br>
comment <br></blockquote>

<blockquote><i>Room:</i> <br>
ID <br>
name <br></blockquote>

<blockquote><i>Teacher:</i> <br>
ID <br>
name <br>
degree <br></blockquote>

<blockquote><i>Schedule:</i> <br>
ID <br>
groupID <br>
day <br>
startTime <br>
length <br>
lectureID <br>
roomID <br>
teacherID <br>
comment <br></blockquote>

<blockquote><i>ScheduleChange:</i> <br>
ID <br>
scheduleID <br>
week <br>
groupID <br>
day <br>
startTime <br>
length <br>
lectureID <br>
roomID <br>
teacherID <br>
comment <br></blockquote>

<b>Пример передаваемой строки для класса Group:</b> <br>
"ID=1 name='793б' course='4 курс, кафедра <code>&lt;apostrophe&gt;ABBYY&lt;apostrophe&gt;</code>'"<br>
<br>
При передаче сразу нескольких объектов (например, результат функции getAll() ) XML-сервис возвращает массив String<a href='.md'>.md</a>, каждый элемент которого - запакованный в строку класс.<br>
<br>
<a href='http://code.google.com/p/vkurse/wiki/Java'>На главную</a>