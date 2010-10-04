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
        data.add(new Lecture(0, "Innovative practics", 0, ""));
        data.add(new Lecture(1, "Physics", 1, ""));
        data.add(new Lecture(2, "Math", 0, ""));
        data.add(new Lecture(3, "Programming", 1, ""));
        */
        data.add(new Lecture(100, "Test data - lecture", 100, "comment"));
        data.add(new Lecture(0, "Innovative practics", 0, "Comment"));
        data.add(new Lecture(11, "Convex analysis (seminar)", 0, " "));
        data.add(new Lecture(12, "Theoretical physics (lecture)", 0, "  "));
        data.add(new Lecture(13, "Theoretical physics (seminar)", 0, "  "));
        data.add(new Lecture(14, "Jurisprudence", 2, " "));
        data.add(new Lecture(10, "Convex analysis (lecture", 0, "  "));
        data.add(new Lecture(15, "Computational math (lecture)", 0, " "));
        data.add(new Lecture(16, "Computational math (seminar)", 0, "  "));
        data.add(new Lecture(17, "Foreign language", 1, " "));
        data.add(new Lecture(18, "Military training", 0, " "));
    }

    public boolean insert(Lecture item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(Lecture item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.get(i)).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public Lecture get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.get(i)).getID() == ID) return (Lecture)data.get(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.get(i)).getID() == ID) r = i;
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
            int ID = ((Lecture)data.get(i)).getID();
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
}
