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
public class LecturesTestTable implements LecturesTable
{
    //ArrayList<Lecture> data = new ArrayList<Lecture>();
    java.util.Vector data = new java.util.Vector();

    public LecturesTestTable()
    {
        /*
        data.addElement(new Lecture(0, "Innovative practics", 0, ""));
        data.addElement(new Lecture(1, "Physics", 1, ""));
        data.addElement(new Lecture(2, "Math", 0, ""));
        data.addElement(new Lecture(3, "Programming", 1, ""));
        */
        data.addElement(new Lecture(100, "Test data - lecture", 100, "comment"));
        data.addElement(new Lecture(0, "Инновационный практикум", 0, ""));
        data.addElement(new Lecture(11, "Выпуклый анализ (семинар)", 0, ""));
        data.addElement(new Lecture(12, "Theoretical physics (лекция)", 0, ""));
        data.addElement(new Lecture(13, "Theoretical physics (семинар)", 0, ""));
        data.addElement(new Lecture(14, "Jurisprudence", 2, " "));
        data.addElement(new Lecture(10, "Convex analysis (лекция)", 0, ""));
        data.addElement(new Lecture(15, "Computational math (лекция)", 0, ""));
        data.addElement(new Lecture(16, "Computational math (семинар)", 0, ""));
        data.addElement(new Lecture(17, "Foreign language", 1, ""));
        data.addElement(new Lecture(18, "Military training", 0, ""));
    }

    public int insert(Lecture item) throws TableException
    {
        data.addElement(item);
        return item.getID();
    }

    public boolean update(Lecture item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item,i);
        }
        return true;
    }

    public Lecture get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.elementAt(i)).getID() == ID) return (Lecture)data.elementAt(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.elementAt(i)).getID() == ID) r = i;
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
            int ID = ((Lecture)data.elementAt(i)).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }

    public boolean insertWithNewID(Lecture item) throws TableException
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
