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
        data.add(new ExamType(100, "Test data - exam type"));
        data.add(new ExamType(0, "Экзамен"));
        data.add(new ExamType(1, "Зачет"));
        data.add(new ExamType(2, "Дифференцированный зачет"));
    }

    public boolean insert(ExamType item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(ExamType item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((ExamType)data.get(i)).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }
    
    public ExamType get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((ExamType)data.get(i)).getID() == ID) return (ExamType)data.get(i);
        }
        return null;
    }
    
    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((ExamType)data.get(i)).getID() == ID) r = i;
        }
        if (r>=0)
        {
            data.remove(r);
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
            r.add(data.get(i));
        }
        return r;
    }

    public int findFreeID() throws TableException
    {
        int r = 0;
        int i;
        for (i = 0; i < data.size(); i++)
        {
            int ID = ((ExamType)data.get(i)).getID();
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
