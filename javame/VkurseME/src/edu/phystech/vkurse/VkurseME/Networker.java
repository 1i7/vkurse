package edu.phystech.vkurse.VkurseME;

/**
 *
 * @author Lex
 */
import javax.microedition.lcdui.*;
import edu.phystech.vkurse.model.*;
import java.util.*;
import org.ksoap.*;
import org.ksoap.transport.*;


public class Networker implements Runnable {
    VkurseME middlet;
    int action=Networker.AC_WAIT;
    final static int AC_WAIT=0, AC_REQUEST_ALL_LECTURES=1, AC_REQUEST_ALL_GROUPS=2, AC_REQUEST_SHEDULE=3;

    //Данные конкретных задач:
    //AC_REQUEST_SHEDULE
    Integer day, groupID;
    Thread networkThread;
    Timer timer;
    int timeout=16000;//Через столько миллисекунд прекращаем ждать выполнения запроса

    public Networker(VkurseME middlet)
    {
        this.middlet = middlet;
        timer = new Timer();
    }
    public void all_lectures()throws Exception
    {
            SoapObject rpc = new SoapObject
		("http://DefaultNamespace", "getAll");
            /*
             Первый параметр - пространоство имён(зависит от сервера)
             Второй параметр - имя сервиса
             Оба смотрим в WSDL-файле
             */

	    //rpc.addProperty ("symbol", "1");

	    Vector res = (Vector) new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis/LectureService.jws",
		 "getAll").call (rpc);

            Vector lectures = new Vector();
            for(int i=0;i<res.size();i++)
            {
                Lecture l = new Lecture();
                l.readData((String)(res.elementAt(i)));
                
                lectures.addElement(l);
            }



            middlet.SetLectures(lectures);
            /*
             Параметры: Url Адрес веб-сервера,
                        Имя вызываемого метода
             */
    }
    public void get_all_groups()throws Exception
    {
            SoapObject rpc = new SoapObject
		("http://DefaultNamespace", "getAll");


	    Vector res = (Vector) new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis/GroupService.jws",
		 "getAll").call (rpc);

            Vector groups = new Vector();
            for(int i=0;i<res.size();i++)
            {
                Group l = new Group();
                l.readData((String)(res.elementAt(i)));

                groups.addElement(l);
            }

            middlet.SetListGroups(groups);
    }
    

    public void get_schedule()throws Exception
    {
            SoapObject rpc = new SoapObject
		("http://DefaultNamespace", "findByGroupDay");



            rpc.addProperty ("groupID", (Object)groupID);
            rpc.addProperty ("day", (Object)day);


	    Vector res = (Vector) new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis/ScheduleService.jws",
		 "findByGroupDay").call (rpc);


            Vector schedule  = new Vector();
            for(int i=0;i<res.size();i++)
            {
                Schedule l = new Schedule();
                l.readData((String)(res.elementAt(i)));

                schedule.addElement(l);
            }

            middlet.SetSchedule(schedule);
    }

    public void get_rooms()throws Exception
    {
            SoapObject rpc = new SoapObject
		("http://DefaultNamespace", "getAll");


	    Vector res = (Vector) new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis/RoomService.jws",
		 "getAll").call (rpc);

            Vector rooms  = new Vector();
            for(int i=0;i<res.size();i++)
            {
                Room l = new Room();
                l.readData((String)(res.elementAt(i)));

                rooms.addElement(l);
            }

            middlet.SetRooms(rooms);
    }
    public void get_examtypes()throws Exception
    {
            SoapObject rpc = new SoapObject
		("http://DefaultNamespace", "getAll");

	    Vector res = (Vector) new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis/ExamTypeService.jws",
		 "getAll").call (rpc);

            Vector exams  = new Vector();
            for(int i=0;i<res.size();i++)
            {
                ExamType l = new ExamType();
                l.readData((String)(res.elementAt(i)));

                exams.addElement(l);
            }

            middlet.SetExamTypes(exams);
    }
    public void get_teachers()throws Exception
    {
            SoapObject rpc = new SoapObject
		("http://DefaultNamespace", "getAll");


	    Vector res = (Vector) new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis/TeacherService.jws",
		 "getAll").call (rpc);

            Vector teachers  = new Vector();
            for(int i=0;i<res.size();i++)
            {
                Teacher l = new Teacher();
                l.readData((String)(res.elementAt(i)));

                teachers.addElement(l);
            }

            middlet.SetTeachers(teachers);
    }

    public void run()
    {
        try{
            switch(action)
            {
                case Networker.AC_REQUEST_ALL_LECTURES:
                    all_lectures();
                    break;


                case Networker.AC_REQUEST_ALL_GROUPS:
                    get_all_groups();
                    break;

                case Networker.AC_REQUEST_SHEDULE:
                    all_lectures();
                    get_rooms();
                    //get_examtypes();
                    //get_teachers();

                    get_schedule();
                    break;

                /*
                 TableFactory factory = new TestTableFactory();
                    LecturesTable lecturestable = factory.getLecturesTable();
                    Vector lectures;
                    try {
                        lectures = lecturestable.getAll();
                    } catch (Exception exc) {
                        lectures = new Vector();
                    }
                    middlet.SetLectures(lectures);
                 *
                 */
            }
        }
        catch (Exception e) {
            String errmsg = e.toString();
            System.out.println("ERROR:"+errmsg);

            Display.getDisplay(middlet).setCurrent(new FormConnectionError(middlet,action));
	}      
        timer.cancel();
    }
    /*
     *  Запускает отдельный поток с нужной задачей
     */
    public void process(int new_action)
    {
        timer = new Timer();
        action = new_action;

        //Timeout - если долго тупит - вырубаем
        TimerTask tTask = new TimerTask() {
                public void run() {
                        timer.cancel();
                    if(networkThread.isAlive())
                    {
                        networkThread.interrupt();//не сработает, но попробовать стоит)
                        Display.getDisplay(middlet).setCurrent(new FormConnectionError(middlet,action));
                    }
                }
            };
        timer.schedule(tTask, timeout);
        
        networkThread = new Thread(this);
        networkThread.start();
    }
    public void request_all_lectures()
    {
        process(Networker.AC_REQUEST_ALL_LECTURES);
    }
    public void request_all_groups()
    {
        process(Networker.AC_REQUEST_ALL_GROUPS);
    }
    public void request_schedule(int _groupID, int _day)
    {
        groupID = new Integer(_groupID);
        day = new Integer(_day);

        process(Networker.AC_REQUEST_SHEDULE);
    }

    String Version()
    {
        try
        {
            SoapObject rpc = new SoapObject
		("http://axisversion.sample", "Version");
            /*
             Первый параметр - пространоство имён(зависит от сервера)
             Второй параметр - имя сервиса
             Оба смотрим в WSDL-файле
             */

	    rpc.addProperty ("symbol", "1");

	    String res=""+new HttpTransport
		("http://nebula.innolab.net.ru:8180/axis2/services/Version",
		 "getVersionResponse").call (rpc);
            /*
             Параметры: Url Адрес веб-сервера,
                        Имя вызываемого метода
             */

            return res;
        }
        catch (Exception e) {
	    //e.printStackTrace ();

            String errmsg = e.toString();
            System.out.println("ERROR:"+errmsg);

            return errmsg;

	    /*resultItem.setLabel ("Error:");
	    resultItem.setText (e.toString ());*/
	}
    }
}
