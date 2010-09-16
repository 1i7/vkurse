import edu.phystech.vkurse.test.*;
import edu.phystech.vkurse.model.*;
import java.util.Vector;
public  class LectureService 
{
		public Lecture get(int id)throws edu.phystech.vkurse.model.TableException
		{
			TableFactory tableFactory = new TestTableFactory();
			Lecture lecture = tableFactory.getLecturesTable().get(id);
			return lecture;
		}
	
		public Vector getAll() throws edu.phystech.vkurse.model.TableException
    	{ 
			TableFactory tableFactory = new TestTableFactory();
			Vector lectures =  tableFactory.getLecturesTable().getAll();
			return lectures;
    	}
}
