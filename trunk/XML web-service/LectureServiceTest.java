
import edu.phystech.vkurse.test.*;
import edu.phystech.vkurse.model.*;
import java.util.Vector;
public  class LectureServiceTest 
{
		public Vector<Lecture> sayAnswer() throws edu.phystech.vkurse.model.TableException
    	{ 
		TableFactory tableFactory = new TestTableFactory();
    	Vector<Lecture> lectures =  tableFactory.getLecturesTable().getAll();
       	return lectures;
    	}
}
