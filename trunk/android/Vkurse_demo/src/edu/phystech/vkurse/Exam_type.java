package edu.phystech.vkurse;

import java.util.Vector;

import org.kobjects.base64.Base64;
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
	public ExamType get(int ID) throws TableException 
	{
		String METHOD_NAME = "get";
		ExamType exam_t = null;
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
            	exam_t = new ExamType();
            	exam_t.readData(result.toString());
            }
            
        } catch ( Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return exam_t;
	}
	
	@Override
	public Vector<ExamType> getAll() throws TableException 
	{
		String METHOD_NAME = "getAll";
		Vector<ExamType> exam_ts = null;
		Vector<String> res = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //если пришел не пустой массив 
            if (result != null )
            {
            	//прочитали данные
            	res = (Vector<String>)result;
            	exam_ts = new Vector<ExamType>();
            	
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
            }
                        
        } catch (Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return exam_ts;
		
	}
	
	@Override
	public Vector<ExamType> get(int[] arg0) throws TableException 
	{
		String METHOD_NAME = "get";
		Vector<ExamType> exam_ts = null;
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
            
            //если пришел не пустой массив 
            if (result != null )
            {
            	//прочитали данные
            	res = (Vector<String>)result;
            	exam_ts = new Vector<ExamType>();
            	
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
            }
                        
        } catch (Exception e) 
        {
        	throw new TableException("Ошибка при получений данных");
        }
		return exam_ts;
	}
	
	@Override
	public int insert(ExamType item) throws TableException 
	{
		String METHOD_NAME = "insert";
		int res = -1;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("examType", item.toStringData());
     
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
	public boolean update(ExamType item) throws TableException 
	{
		String METHOD_NAME = "update";
		boolean res = false;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("examType", item.toStringData());
     
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
