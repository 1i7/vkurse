package edu.phystech.vkurse.VkurseME;

/**
 *
 * @author Lex
 */
import edu.phystech.vkurse.model.*;
import java.util.*;
import org.ksoap.*;
import org.ksoap.transport.*;


public class Networker implements Runnable {
    VkurseME middlet;
    int action=Networker.AC_WAIT;
    final static int AC_WAIT=0, AC_REQUEST_ALL_ACTIONS=1;

    public Networker(VkurseME middlet)
    {
        this.middlet = middlet;
    }
    public void run()
    {
        switch(action)
        {
            case Networker.AC_REQUEST_ALL_ACTIONS:
                TableFactory factory = new TestTableFactory();
                LecturesTable lecturestable = factory.getLecturesTable();
                Vector lectures;
                try {
                    lectures = lecturestable.getAll();
                } catch (Exception exc) {
                    lectures = new Vector();
                }
                middlet.SetLectures(lectures);
            break;
        }
    }
    /*
     *  Запускает отдельный поток с нужной задачей
     */
    public void process(int new_action)
    {
        action = new_action;

        Thread thread = new Thread(this);
        thread.start();
    }
    public void request_all_lectures()
    {
        process(Networker.AC_REQUEST_ALL_ACTIONS);
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
