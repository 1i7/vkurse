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
public class LectureTypesTestTable implements LectureTypesTable
{
    //ArrayList<LectureType> data = new ArrayList<LectureType>();
    java.util.Vector data = new java.util.Vector();

    public LectureTypesTestTable()
    {
        data.addElement(new LectureType(100, "Test data - lecture type"));
        data.addElement(new LectureType(0, "Лекция"));
        data.addElement(new LectureType(1, "Семинар"));
        data.addElement(new LectureType(2, "Лабораторная работа"));
    }

    public int insert(LectureType item) throws TableException
    {
        data.addElement(item);
        return item.getID();
    }

    public boolean update(LectureType item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((LectureType)data.elementAt(i)).getID() == item.getID())
                data.setElementAt(item, i);
        }
        return true;
    }

    public LectureType get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((LectureType)data.elementAt(i)).getID() == ID) return (LectureType)data.elementAt(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((LectureType)data.elementAt(i)).getID() == ID) r = i;
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

    public java.util.Vector get(int[] ids) throws TableException
    {
        Vector r = new Vector();
        int i;
        for (i=0; i<data.size(); ++i)
        {
            DbTableRecord tr = (DbTableRecord)data.elementAt(i);
            int id = tr.getID();
            boolean add = false;
            for (int j = 0; j < ids.length; j++)
            {
                if (ids[j] == id)
                {
                    add = true;
                }
            }
            if (add)
            {
                r.addElement(tr);
            }
        }
        return r;
    }

    /*
    public int findFreeID() throws TableException
    {
        int r = 0;
        int i;
        for (i = 0; i < data.size(); i++)
        {
            int ID = ((LectureType)data.elementAt(i)).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }

    public boolean insertWithNewID(LectureType item) throws TableException
    {
        boolean r = false;
        if (item != null)
        {
            item.setID(this.findFreeID());
            r = this.insert(item);
        }
        return r;
    }
     *
     */
}
