package edu.phystech.vkurse;

import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import edu.phystech.vkurse.model.*;

public class Schedules implements ScheduleTable
{
	//ключевые переменные для доступа к сервисам
	 
	private static String NAMESPACE = "http://DefaultNamespace";
	private static String URL = "http://nebula.innolab.net.ru:8180/axis/ScheduleService.jws";
	private static String SOAP_ACTION = "";
	

	@Override
	public Vector<Schedule> get(int[] arg0) throws TableException 
	{
		String METHOD_NAME = "get";
		Vector<Schedule> lects = null;
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
            	lects = new Vector<Schedule>();
                        
            	//инициализация объектов Schedule
            	for ( int j = 0; j < res.size(); j++ )
            	{
            		Schedule lect = new Schedule();
            		if ( res.elementAt(j) != null )
            		{
            			lect.readData( res.elementAt(j));
          	 		}
            		lects.add(lect);
            	} 
            }
        } catch (Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return lects;
	}
	
	@Override
	public Schedule get(int ID) throws TableException 
	{
		String METHOD_NAME = "get";
		Schedule lect = null;
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
            	lect = new Schedule();
            	lect.readData(result.toString());
            }
      
        } catch ( Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return lect;
	}
	
	@Override
	public Vector<Schedule> getAll() throws TableException 
	{
		String METHOD_NAME = "getAll";
		Vector<Schedule> lects = null;
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
            	lects = new Vector<Schedule>();
                        
            	//инициализация объектов Schedule
            	for ( int j = 0; j < res.size(); j++ )
            	{
            		Schedule lect = new Schedule();
            		if ( res.elementAt(j) != null )
            		{
            			lect.readData( res.elementAt(j));
          	 		}
            		lects.add(lect);
            	} 
            }
        } catch (Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return lects;
	}
	
	@Override
	public int insert(Schedule item) throws TableException 
	{
		String METHOD_NAME = "insert";
		int res = -1;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("schedule", item.toStringData());
     
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
	public boolean update(Schedule item) throws TableException 
	{
		String METHOD_NAME = "update";
		boolean res = false;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("schedule", item.toStringData());
     
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
	
	@Override
	public Vector<Schedule> findByGroupDay(int groupID, byte day) throws TableException 
	{
		String METHOD_NAME = "findByGroupDay";
		Vector<Schedule> lects = new Vector<Schedule>();
		Vector<String> res;
		try 
		{
			
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("groupID", groupID);
            request.addProperty("day",(int)day);
            
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
                        
            //инициализация объектов Schedule
            for ( int j = 0; j < res.size(); j++ )
            {
            	Schedule lect = new Schedule();
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
	
}
