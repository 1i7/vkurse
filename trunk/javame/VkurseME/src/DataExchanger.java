import org.ksoap.*;
import org.ksoap.transport.*;

public class DataExchanger {
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
