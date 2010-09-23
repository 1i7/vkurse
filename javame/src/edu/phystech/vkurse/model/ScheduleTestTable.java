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
public class ScheduleTestTable implements ScheduleTable
{
    Vector data = new Vector();
    public ScheduleTestTable()
    {
        data.addElement(new Schedule(0, (byte)1, 480, 85, 0, 0, 0, "it is comment of lecture 0"));
        data.addElement(new Schedule(1, (byte)2, 540, 85, 1, 0, 1, "it is comment of lecture 1"));
        data.addElement(new Schedule(2, (byte)3, 600, 200, 2, 0, 2, "it is comment of lecture 2"));
        data.addElement(new Schedule(3, (byte)4, 660, 85, 3, 0, 1, "it is comment of lecture 3"));
        data.addElement(new Schedule(4, (byte)5, 720, 175, 2, 0, 2, "it is comment of lecture 4"));
    }

    public boolean insert(Schedule item) throws TableException
    {
        data.addElement(item);
        return true;
    }

    public boolean update(Schedule item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Schedule)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item, i);
        }
        return true;
    }

    public Schedule get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Schedule)data.elementAt(i)).getID() == ID) return (Schedule)data.elementAt(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Schedule)data.elementAt(i)).getID() == ID) r = i;
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
