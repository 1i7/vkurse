/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.test;
import edu.phystech.vkurse.model.*;
import java.util.*;

/**
 *
 * @author Дима
 */
public class ExamTypesTestTable implements ExamTypesTable
{
    //ArrayList<ExamType> data = new ArrayList<ExamType>();
    java.util.Vector data = new java.util.Vector();

    public ExamTypesTestTable()
    {
        data.addElement(new ExamType(100, "Test data - exam type"));
        data.addElement(new ExamType(0, "Экзамен"));
        data.addElement(new ExamType(1, "Зачет"));
        data.addElement(new ExamType(2, "Дифференцированный зачет"));
    }

    public boolean insert(ExamType item) throws TableException
    {
        data.addElement(item);
        return true;
    }

    public boolean update(ExamType item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((ExamType)data.elementAt(i)).getID() == item.getID())
                data.setElementAt(item, i);
        }
        return true;
    }
    
    public ExamType get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((ExamType)data.elementAt(i)).getID() == ID) return (ExamType)data.elementAt(i);
        }
        return null;
    }
    
    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((ExamType)data.elementAt(i)).getID() == ID) r = i;
        }
        if (r>=0)
        {
            data.removeElementAt(r);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public java.util.Vector getAll() throws TableException
    {
        Vector r = new Vector();
        int i;
        for (i=0; i<data.size(); ++i)
        {
            r.addElement(data.elementAt(i));
        }
        return r;
    }

    public int findFreeID() throws TableException
    {
        int r = 0;
        int i;
        for (i = 0; i < data.size(); i++)
        {
            int ID = ((ExamType)data.elementAt(i)).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }
    
    public boolean insertWithNewID(ExamType item) throws TableException
    {
        boolean r = false;
        if (item != null)
        {
            item.setID(this.findFreeID());
            r = this.insert(item);
        }
        return r;
    }
}
