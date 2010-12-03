package edu.phystech.vkurse;

import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import edu.phystech.vkurse.model.*;

public class Rooms implements RoomsTable
{
	
	//ключевые переменные для доступа к сервисам
	 
	private static String NAMESPACE = "http://DefaultNamespace";
	private static String URL = "http://nebula.innolab.net.ru:8180/axis/RoomService.jws";
	private static String SOAP_ACTION = "";
	
	@Override
	public Vector<Room> get(int[] arg0) throws TableException 
	{
		String METHOD_NAME = "get";
		Vector<Room> exam_ts = null;
		Vector<String> res = null;
		try 
		{
			Vector<Integer> zed = new Vector<Integer>();
			for ( int i = 0; i < arg0.length; i++)
			{
				zed.add(arg0[i]);
			}
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
            request.addProperty("ids", zed);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //если пришел пустой массив 
            if (result != null )
            {                       
            	//прочитали данные
            	res = (Vector<String>)result;
            	exam_ts = new Vector<Room>();
                        
            	//инициализация объектов ExamType
            	for ( int j = 0; j < res.size(); j++ )
            	{
            		Room exam_t = new Room();
            		if ( res.elementAt(j) != null )
            		{
            			exam_t.readData( res.elementAt(j));
            		}
            		exam_ts.add(exam_t);
            	} 
            }
            
        } catch (Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return exam_ts;
	}
	
	@Override
	public Room get(int ID) throws TableException 
	{
		
		String METHOD_NAME = "get";
		Room lect = null;
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
            if (result != null )
            {
            	lect = new Room();
            	lect.readData(result.toString());
            }
 
        } catch ( Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return lect;
	}
	
	@Override
	public Vector<Room> getAll() throws TableException
	{
		String METHOD_NAME = "getAll";
		Vector<Room> exam_ts = null;
		Vector<String> res = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //если пришел пустой массив 
            if (result != null )
            {                       
            	//прочитали данные
            	res = (Vector<String>)result;
            	exam_ts = new Vector<Room>();
                        
            	//инициализация объектов ExamType
            	for ( int j = 0; j < res.size(); j++ )
            	{
            		Room exam_t = new Room();
            		if ( res.elementAt(j) != null )
            		{
            			exam_t.readData( res.elementAt(j));
            		}
            		exam_ts.add(exam_t);
            	} 
            }
            
        } catch (Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return exam_ts;
	}
	
	@Override
	public int insert(Room item) throws TableException
	{
		String METHOD_NAME = "insert";
		int res = -1;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("room", item.toStringData());
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
           
            if (result != null) 
    		{
    			res = (Integer)result;
    		}
            
        } catch ( Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
       
		return res;
	}
	
	@Override
	public boolean remove(int ID) throws TableException 
	{
		String METHOD_NAME = "remove";
		boolean res = false;
		try 
		{
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("ID", ID);
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
          
            if (result != null) 
    		{
    			res = (Boolean)result;
    		}
            
        } catch ( Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
	
		return res;
	}
	
	@Override
	public boolean update(Room item) throws TableException
	{
		String METHOD_NAME = "update";
		boolean res = false;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("room", item.toStringData());
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
           
            if (result != null) 
    		{
    			res = (Boolean)result;
    		}
            
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
   
      	return res;
	}

}
