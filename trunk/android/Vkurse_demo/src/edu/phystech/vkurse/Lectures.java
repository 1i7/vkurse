package edu.phystech.vkurse;
// ����� ��� ������ � ��������.
// ����������� ������ �� �������. 
// get(ID) - ���������� ������ Lecture �� ID
// getAll() - ���������� ��� ������� ���� Lecture
// remove() - ��������� ������� ������ �� ID
// insert() - ����� �������� ���� ������, �������: Id ������ ���� �����
// update() - ��������� ������. ��������, ����� ������� Lecture = get(ID), ����� �������� ��� LEcture.SetName() � ������� ��� �� ������
// ���� �� ������� ����� ������������ ��������� ��� ������ :)

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
	//�������� ���������� ��� ������� � ��������
	 
	private static String NAMESPACE = "http://DefaultNamespace";
	private static String URL = "http://nebula.innolab.net.ru:8180/axis/LectureService.jws";
	private static String SOAP_ACTION = "";
	
	@Override
	public Vector<Lecture> get(int[] arg0) throws TableException 
	{
		String METHOD_NAME = "get";
		Vector<Lecture> lects = null;
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
            
            //���� ������ ������ ������ 
            if (result != null )
            {      
            	//��������� ������
            	res = (Vector<String>)result;
            	lects = new Vector<Lecture>();
                        
            	//������������� �������� Lecture
            	for ( int j = 0; j < res.size(); j++ )
            	{
            		Lecture lect = new Lecture();
            		if ( res.elementAt(j) != null )
            		{
            			lect.readData( res.elementAt(j));
            		}
            		lects.add(lect);
            	} 
            }
            
        } catch (Exception e) 
        {
        	throw new TableException("������ ��� ��������� ������");
        }
		return lects;
	}
	
	@Override
	public Lecture get(int ID) throws TableException 
	{
		String METHOD_NAME = "get";
		Lecture lect = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("ID", ID);
     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //���� ������ ������ ������
            if (result != null )
            {
            	lect = new Lecture();
            	lect.readData(result.toString());
            }
            
        } catch ( Exception e) 
        {
        	throw new TableException("������ ��� ��������� ������");
        }
		return lect;
	}
	
	@Override
	public Vector<Lecture> getAll() throws TableException 
	{
		String METHOD_NAME = "getAll";
		Vector<Lecture> lects = null;
		Vector<String> res = null;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);     
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = envelope.getResponse();
            
            //���� ������ ������ ������ 
            if (result != null )
            {      
            	//��������� ������
            	res = (Vector<String>)result;
            	lects = new Vector<Lecture>();
                        
            	//������������� �������� Lecture
            	for ( int j = 0; j < res.size(); j++ )
            	{
            		Lecture lect = new Lecture();
            		if ( res.elementAt(j) != null )
            		{
            			lect.readData( res.elementAt(j));
            		}
            		lects.add(lect);
            	} 
            }
            
        } catch (Exception e) 
        {
        	throw new TableException("������ ��� ��������� ������");
        }
		return lects;
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
        	throw new TableException("������ ��� ��������� ������");
        }
	
		return res;
	}
	
	@Override
	public int insert(Lecture item) throws TableException 
	{
		String METHOD_NAME = "insert";
		int res = -1;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("lecture", item.toStringData());
     
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
        	throw new TableException("������ ��� ��������� ������");
        }
       
		return res;
	}
	
	@Override
	public boolean update(Lecture item) throws TableException 
	{
		String METHOD_NAME = "update";
		boolean res = false;
		try 
		{
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("lecture", item.toStringData());
     
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
