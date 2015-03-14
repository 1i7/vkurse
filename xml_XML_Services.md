# Описание XML-сервисов #

  * Существует 8 сервисов:
    1. `ExamTypeService` - типы отчетности (экзамен, зачёт …)
    1. `GroupService` – группы
    1. `LectureService` – предметы
    1. `LectureTupeService` – типы занятии (лекции, семинары, лабы …)
    1. `RoomService` – аудитория
    1. `TeacherService` – преподаватели
    1. `SchduleService` – расписание
    1. `ScheduleChangeService` – изменения в расписания

Каждый из сервисов имеет стандартный набор методов:

String get(int ID); - выдаёт запрошенный элемент по ID, сериализованный в строку, либо “NULL” если такой ID не существует.

String [.md](.md) getAll (); - выдаёт все элементы из БД, сериализованные в массив строк.

String[.md](.md) get (int[.md](.md) ids); выдаёт запрошенный элемент по ID в виде массива строк. На вход получает ID элементов в виде массива int. Тип возвращаемых элементов, сериализованных в строку, зависит от сервиса и соответствует ему.

Кроме этого `SheduleService` содержит дополнительный метод.

String[.md](.md) findByGroupDay (int groupID, int day) возвращает элементы расписания определённой группы на определённый день.

И ещё `ScheduleChangeService` содержит 2 дополнительных метода:

String[.md](.md) findByGroupWeekDay (int groupID, int week, int day) – возвращает изменения в расписании определённой группой на определённый день определённой недели.

String[.md](.md) findByScheduleID (int ScheludeID) – возвращает изменения определённого элемента расписания

URI сервисов:
http://nebula.innolab.net.ru:8186(axis) ИмяСервиса.jws

WSDL описание:
http://nebula.innolab.net.ru:8186(axis) ИмяСервиса.jws?wsdl

[На главную](http://code.google.com/p/vkurse/wiki/xml)