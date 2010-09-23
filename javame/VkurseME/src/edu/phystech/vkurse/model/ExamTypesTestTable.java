/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;
//import edu.phystech.vkurse.model.*;
import java.util.*;

/**
 *
 * @author Дима
 */
public class ExamTypesTestTable implements ExamTypesTable
{
    Vector data = new Vector();
    public ExamTypesTestTable()
    {
        data.addElement(new ExamType(0, "Exam"));
        data.addElement(new ExamType(1, "Test"));
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
            if (((ExamType)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item, i);
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
            if (((Group)data.elementAt(i)).getID() == ID) r = i;
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
}
