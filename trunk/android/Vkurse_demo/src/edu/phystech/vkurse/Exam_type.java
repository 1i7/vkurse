package edu.phystech.vkurse;

import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import edu.phystech.vkurse.model.*;

public class Exam_type implements ExamTypesTable
{
	//ключевые переменные для доступа к сервисам
	 
	private static String NAMESPACE = "http://DefaultNamespace";
	private static String URL = "http://nebula.innolab.net.ru:8180/axis/ExamTypeService.jws";
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
	public boolean insertWithNewID(ExamType item) throws TableException 
	{
		String METHOD_NAME = "insertWithNewID";
		Object result = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("examType", item.toStringData());
     
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
	public ExamType get(int ID) throws TableException 
	{
		String METHOD_NAME = "get";
		ExamType exam_t = new ExamType();
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
            
            exam_t.readData(result.toString());
           
        } catch ( Exception e) 
        {
           e.printStackTrace();
        }
		return exam_t;
	}
	
	@Override
	public Vector<ExamType> getAll() throws TableException 
	{
		String METHOD_NAME = "getAll";
		Vector<ExamType> exam_ts = new Vector<ExamType>();
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
                        
            //инициализация объектов ExamType
            for ( int j = 0; j < res.size(); j++ )
            {
            	ExamType exam_t = new ExamType();
            	if ( res.elementAt(j) != null )
            	{
            		exam_t.readData( res.elementAt(j));
            	}
            	exam_ts.add(exam_t);
            } 
            
        } catch (Exception e) 
        {
           e.printStackTrace();
        }
		return exam_ts;
		
	}
	
	@Override
	public boolean insert(ExamType item) throws TableException 
	{
		String METHOD_NAME = "insert";
		Object result = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("examType", item.toStringData());
     
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
	public boolean update(ExamType item) throws TableException 
	{
		String METHOD_NAME = "update";
		Object result = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("examType", item.toStringData());
     
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
