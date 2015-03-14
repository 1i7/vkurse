Веб-сервис и мобильный клиент будут обмениваться XML-сообщениями по протоколу SOAP.
Со стороны веб-сервиса реализация протокола SOAP будет происходить за счет программного пакета Apache Axis. Со стороны мобильного клиента, например, ksoap2.
Клиент передает нужному веб-сервису XML-сообщение, которое содержит информацию о методе, который должен исполнить веб-сервис(например, getAll(), без параметров), и параметры;
После автоматической обработки XML-сообщения, веб-сервис делает запрос к БД.
Веб-сервисы и XML-сообщения от них до клиента после вызова метода getAll();
1)	TeacherService
<?xml version="1.0"?>


&lt;teachers-list&gt;


<edu:phystech:vkurse:model:teacher id="0001" name="Козел Станислав Миронович" degree="доктор физико-математических наук"></edu:phystech:vkurse:teacher>
…


&lt;/teachers-list&gt;


2)	LectureService
<?xml version="1.0"?>


&lt;lectures-list&gt;


<edu:phystech:vkurse:model:lecture id=”0001” name=”УРМАТЫ” examtype=”01” comment=”сложна очена ма”></edu:phystech:vkurse:model:lecture>
…


&lt;/lectures-list&gt;


3)	ExamService
<?xml version="1.0"?>


&lt;exams-list&gt;


<edu:phystech:vkurse:model:examtype id=”001” name=”УРМАТЫ”></ edu:phystech:vkurse:model:examtype >
…


&lt;/exams-list&gt;


4)	GroupService
<?xml version="1.0"?>


&lt;groups-list&gt;


< edu:phystech:vkurse:model:group id=”001” name=”794” course=”3”></ edu:phystech:vkurse:model:group>
…


&lt;/groups-list&gt;


5)	RoomService
<?xml version="1.0"?>


&lt;rooms-list&gt;


<edu:phystech:vkurse:model:room id=”001” name=”521”></edu:phystech:vkurse:model:room>
…


&lt;/rooms-list&gt;


6)	ScheduleService
<?xml version="1.0"?>


&lt;schedule-list&gt;


<edu:phystech:vkurse:model:schedule id=”0001”  name=”lecture\_name” group=”794” day=”Monday” strattime=”…” length = “…” room=”roomID” teacher=”teachers’name”></ edu:phystech:vkurse:model:schedule>
…


&lt;/schedule-list&gt;



