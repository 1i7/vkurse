//import org.apache.axis.AxisFault;
//import org.apache.axis.MessageContext;
//import org.apache.axis.transport.http.HTTPConstants;

//import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Iterator;

import edu.phystech.vkurse.test.*;
import edu.phystech.vkurse.postgresql.*;
import edu.phystech.vkurse.model.*;
import java.util.Vector;


public class LectureService
{
    public String get(int ID)
	{
		String r = "null";
		Lecture l = null;
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			l = lt.get(ID);
			if (l != null) r = l.toStringData();
		} catch (Exception ex) { }
		return r;
	}


    public String[] getAll()
	{
		String[] r = new String[0];
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			Vector data = lt.getAll();
			r = new String[data.size()];
			int i;
			for (i=0; i<data.size(); ++i)
			{
				//r.add(data.get(i).toStringData());
				r[i] = ((Lecture)data.get(i)).toStringData();
			}
		} catch (Exception ex) { }
        return r;
	}


    public String[] get(int[] ids)
	{
		String[] r = new String[0];
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			Vector data = lt.get(ids);
			r = new String[data.size()];
			int i;
			for (i=0; i<data.size(); ++i)
			{
				//r.add(data.get(i).toStringData());
				r[i] = ((Lecture)data.get(i)).toStringData();
			}
		} catch (Exception ex) { }
        return r;
	}


/*
    public boolean insert(String lecture)
	{
		Lecture lect = new Lecture();
		lect.readData(lecture);
		boolean r = false;
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			r = lt.insert(lect);
		} catch (Exception ex) { }
		return r;
	}


    public boolean update(String lecture)
	{
		Lecture lect = new Lecture();
		lect.readData(lecture);
		boolean r = false;
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			r = lt.update(lect);
		} catch (Exception ex) { }
		return r;
	}


    public boolean remove(int ID)
	{
		boolean r = false;
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			r = lt.remove(ID);
		} catch (Exception ex) { }
		return r;
	}


	public int findFreeID()
	{
		int r = 0;
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			r = lt.findFreeID();
		} catch (Exception ex) { }
        return r;
	}


	public boolean insertWithNewID(String lecture)
	{
		Lecture lect = new Lecture();
		lect.readData(lecture);
		boolean r = false;
		try
		{
			TableFactory factory = new PgSqlTableFactory();
			LecturesTable lt = factory.getLecturesTable();
			r = lt.insertWithNewID(lect);
		} catch (Exception ex) { }
		return r;
	}
*/
}
