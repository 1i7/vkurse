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
public class ScheduleChangesTestTable implements ScheduleChangesTable
{
    ArrayList<ScheduleChange> data = new ArrayList<ScheduleChange>();
    public ScheduleChangesTestTable()
    {
        data.add(new ScheduleChange(100, 100, 100, 100, (byte)2, 540, 85, 100, 100, 100, "comment of change"));
        data.add(new ScheduleChange(0, 0, 1, 0, (byte)1, 540, 85, 0, 0, 0, "it is comment of change"));
    }

    public boolean insert(ScheduleChange item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(ScheduleChange item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (data.get(i).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public ScheduleChange get(int ID) throws TableException
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

    public java.util.Vector findByGroupWeekDay(int groupID, int week, byte day) throws TableException
    {
        Vector r = new Vector();
        int i;
        for (i=0; i<data.size(); ++i)
        {
            ScheduleChange s = data.get(i);
            if ((s.getGroupID()==groupID) && (s.getDay()==day) && (s.getWeek()==week))
                r.add(s);
        }
        return r;
    }

    public java.util.Vector findByScheduleID(int scheduleID) throws TableException
    {
        Vector r = new Vector();
        int i;
        for (i=0; i<data.size(); ++i)
        {
            ScheduleChange s = data.get(i);
            if ((s.getScheduleID()==scheduleID))
                r.add(s);
        }
        return r;
    }

    public int findFreeID() throws TableException
    {
        int r = 0;
        int i;
        for (i = 0; i < data.size(); i++)
        {
            int ID = data.get(i).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }

    public boolean insertWithNewID(ScheduleChange item) throws TableException
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
