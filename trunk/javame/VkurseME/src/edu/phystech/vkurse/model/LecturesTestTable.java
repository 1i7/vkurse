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
public class LecturesTestTable implements LecturesTable
{
    Vector data = new Vector();
    public LecturesTestTable()
    {
        data.addElement(new Lecture(0, "Innovative practics", 0, ""));
        data.addElement(new Lecture(1, "Physics", 1, ""));
        data.addElement(new Lecture(2, "Math", 0, ""));
        data.addElement(new Lecture(3, "Programming", 1, ""));
    }

    public boolean insert(Lecture item) throws TableException
    {
        data.addElement(item);
        return true;
    }

    public boolean update(Lecture item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item, i);
        }
        return true;
    }

    public Lecture get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Lecture)data.elementAt(i)).getID()== ID) return (Lecture)data.elementAt(i);
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
}
