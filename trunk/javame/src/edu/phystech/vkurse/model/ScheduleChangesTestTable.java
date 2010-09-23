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
public class ScheduleChangesTestTable implements ScheduleChangesTable
{
    Vector data = new Vector();
    public ScheduleChangesTestTable()
    {
        data.addElement(new ScheduleChange(0, 0, 1, (byte)1, 540, 85, 0, 0, 0, "it is comment of change"));
    }

    public boolean insert(ScheduleChange item) throws TableException
    {
        data.addElement(item);
        return true;
    }

    public boolean update(ScheduleChange item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((ScheduleChange)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item, i);
        }
        return true;
    }

    public ScheduleChange get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((ScheduleChange)data.elementAt(i)).getID() == ID) return (ScheduleChange)data.elementAt(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((ScheduleChange)data.elementAt(i)).getID() == ID) r = i;
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
