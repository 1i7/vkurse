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
public class ScheduleTestTable implements ScheduleTable
{
    ArrayList<Schedule> data = new ArrayList<Schedule>();
    public ScheduleTestTable()
    {
        data.add(new Schedule(0, (byte)1, 480, 85, 0, 0, 0, "it is comment of lecture 0"));
        data.add(new Schedule(1, (byte)2, 540, 85, 1, 0, 1, "it is comment of lecture 1"));
        data.add(new Schedule(2, (byte)3, 600, 200, 2, 0, 2, "it is comment of lecture 2"));
        data.add(new Schedule(3, (byte)4, 660, 85, 3, 0, 1, "it is comment of lecture 3"));
        data.add(new Schedule(4, (byte)5, 720, 175, 2, 0, 2, "it is comment of lecture 4"));
    }

    public boolean insert(Schedule item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(Schedule item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (data.get(i).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public Schedule get(int ID) throws TableException
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
