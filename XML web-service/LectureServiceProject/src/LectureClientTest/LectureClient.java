package LectureClientTest;

import edu.phystech.vkurse.model.*;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import java.util.*;

import javax.xml.namespace.QName;

import java.net.URL;

public class LectureClient {
	public static void main(String[] args) 
	{
		try {
	    String endpoint = "http://localhost:8080/axis/LectureServiceTest.jws";
	    Service service = new Service();
	    Call call = (Call) service.createCall();
	    call.setTargetEndpointAddress(new URL(endpoint));
	    call.setOperationName(new QName("http://localhost:8080/axis/LectureServiceTest.jws", "sayAnswer" ));
  	    Object[] params = new Object[]{};
  	    Vector response = (Vector)call.invoke("sayAnswer", params );
  	    System.out.println("All done!");
	} catch (Exception exception) {
		System.err.println("Caught an exception: " + exception);
	}
	}
}
