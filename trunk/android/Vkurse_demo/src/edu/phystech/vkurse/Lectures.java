package edu.phystech.vkurse;
// класс для работы с лекциями.
// Запрашивает данные от сервера. 
// get(ID) - возвращает объект Lecture по ID
// getAll() - возвращает все объекты типа Lecture
// remove() - позволяет удалить запись по ID
// insert() - можно вставить свою запись, главное: Id должен быть новым
// update() - обновляет запись. Например, можно скачать Lecture = get(ID), потом изменить имя LEcture.SetName() и послать это на сервер
// пока не понятно зачем пользователю последние три метода :)

import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import edu.phystech.vkurse.model.*;

//import edu.phystech.vkurse.model.TableException;
//import edu.phystech.vkurse.model.Lecture;

public class Lectures implements LecturesTable
{
	//ключевые переменные для доступа к сервисам
	 
	private static String NAMESPACE = "http://DefaultNamespace";
	private static String URL = "http://nebula.innolab.net.ru:8180/axis/LectureService.jws";
	private static String SOAP_ACTION = "";
	
	@Override
	public int findFreeID() throws TableException 
	{
		String METHOD_NAME = "findFreeID";
		int res_int = 0;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //если пршила пустая строка
            if (result == null )
            {
            	
            }
                     
            res_int = ((Integer)result).intValue();
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
		return res_int;
	}
	
	@Override
	public boolean insertWithNewID(Lecture item) throws TableException 
	{
		String METHOD_NAME = "insertWithNewID";
		Object result = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("lecture", item.toStringData());
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            result = envelope.getResponse();
           
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
        if (result == null) 
		{
			return false;
		}
		else
		{
			return ((Boolean) result).booleanValue();
		}
	}
	
	@Override
	public Lecture get(int ID) throws TableException 
	{
		String METHOD_NAME = "get";
		Lecture lect = new Lecture();
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("ID", ID);
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //если пршила пустая строка
            if (result == null )
            {
            	
            }
            
            lect.readData(result.toString());
           
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
		return lect;
	}
	
	@Override
	public Vector<Lecture> getAll() throws TableException 
	{
		String METHOD_NAME = "getAll";
		Vector<Lecture> lects = new Vector<Lecture>();
		Vector<String> res;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //если пришел пустой массив 
            if (result == null )
            {
            	TableException table = new TableException("An empty string");
            	table.printStackTrace();
            }
            
            //прочитали данные
            res = (Vector<String>)result;
                        
            //инициализация объектов Lecture
            for ( int j = 0; j < res.size(); j++ )
            {
            	Lecture lect = new Lecture();
            	if ( res.elementAt(j) != null )
            	{
            		lect.readData( res.elementAt(j));
            	}
            	lects.add(lect);
            } 
            
        } catch (Exception e) 
        {
           e.printStackTrace();
        }
		return lects;
	}
	
	@Override
	public boolean remove(int ID) throws TableException 
	{
		String METHOD_NAME = "remove";
		Object result = null;
		try 
		{
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("ID", ID);
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            result = envelope.getResponse();
          
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
		if (result == null) 
		{
			return false;
		}
		else
		{
			return ((Boolean) result).booleanValue();
		}
	}
	
	@Override
	public boolean insert(Lecture item) throws TableException 
	{
		String METHOD_NAME = "insert";
		Object result = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("lecture", item.toStringData());
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            result = envelope.getResponse();
           
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
        if (result == null) 
		{
			return false;
		}
		else
		{
			return ((Boolean) result).booleanValue();
		}
	}
	
	@Override
	public boolean update(Lecture item) throws TableException 
	{
		String METHOD_NAME = "update";
		Object result = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("lecture", item.toStringData());
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            result = envelope.getResponse();
           
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
        if (result == null) 
		{
			return false;
		}
		else
		{
			return ((Boolean) result).booleanValue();
		}
		
	}
	
}
