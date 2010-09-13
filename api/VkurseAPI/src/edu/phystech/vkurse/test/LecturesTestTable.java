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
    ArrayList<Lecture> data = new ArrayList<Lecture>();
    public LecturesTestTable()
    {
        data.add(new Lecture(0, "Innovative practics", 0, ""));
        data.add(new Lecture(1, "Physics", 1, ""));
        data.add(new Lecture(2, "Math", 0, ""));
        data.add(new Lecture(3, "Programming", 1, ""));
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
            if (data.get(i).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public Lecture get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (data.get(i).getID() == ID) return data.get(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (data.get(i).getID() == ID) r = i;
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
}
