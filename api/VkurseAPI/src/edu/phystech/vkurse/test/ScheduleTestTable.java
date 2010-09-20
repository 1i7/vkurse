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
        /*
        data.add(new Schedule(0, 4, (byte)1, 480, 85, 0, 0, 0, "it is comment of lecture 0"));
        data.add(new Schedule(1, 3, (byte)2, 540, 85, 1, 1, 1, "it is comment of lecture 1"));
        data.add(new Schedule(2, 2, (byte)3, 600, 200, 2, 2, 2, "it is comment of lecture 2"));
        data.add(new Schedule(3, 1, (byte)4, 660, 85, 3, 1, 1, "it is comment of lecture 3"));
        data.add(new Schedule(4, 0, (byte)5, 720, 175, 2, 0, 2, "it is comment of lecture 4"));
         */
        data.add(new Schedule(100, 100, (byte)1, 540, 85, 100, 100, 100, "comment"));
        data.add(new Schedule(0, 0, (byte)5, 540, 85, 10, 0, 10, ""));
        data.add(new Schedule(1, 1, (byte)5, 540, 85, 10, 0, 10, ""));
        data.add(new Schedule(2, 2, (byte)5, 540, 85, 10, 0, 10, ""));
        data.add(new Schedule(3, 3, (byte)5, 540, 85, 10, 0, 10, ""));
        data.add(new Schedule(4, 4, (byte)5, 540, 85, 15, 4, -1, ""));
        data.add(new Schedule(5, 0, (byte)5, 645, 85, 12, 1, 11, ""));
        data.add(new Schedule(6, 1, (byte)5, 645, 85, 12, 1, 11, ""));
        data.add(new Schedule(7, 2, (byte)5, 645, 85, 12, 1, 11, ""));
        data.add(new Schedule(8, 3, (byte)5, 645, 85, 12, 1, 11, ""));
        data.add(new Schedule(9, 4, (byte)5, 645, 85, 12, 1, 11, ""));
        data.add(new Schedule(10, 0, (byte)5, 780, 85, 11, 2, 10, ""));
        data.add(new Schedule(11, 1, (byte)5, 780, 85, 11, 2, 10, ""));
        data.add(new Schedule(12, 2, (byte)5, 780, 85, 11, 2, 10, ""));
        data.add(new Schedule(13, 3, (byte)5, 780, 85, 11, 2, 10, ""));
        data.add(new Schedule(14, 4, (byte)5, 780, 85, 13, 5, -1, ""));
        data.add(new Schedule(15, 0, (byte)5, 835, 85, 13, 6, -1, ""));
        data.add(new Schedule(16, 1, (byte)5, 835, 85, 13, 7, -1, ""));
        data.add(new Schedule(17, 2, (byte)5, 835, 85, 13, 2, 12, ""));
        data.add(new Schedule(18, 3, (byte)5, 930, 85, 13, 7, 12, ""));
        data.add(new Schedule(19, 4, (byte)5, 930, 85, 17, -1, -1, ""));
        data.add(new Schedule(20, 4, (byte)5, 1025, 135, 16, 8, -1, ""));
        data.add(new Schedule(21, 0, (byte)6, 540, 85, 14, 3, 13, ""));
        data.add(new Schedule(22, 1, (byte)6, 540, 85, 14, 3, 13, ""));
        data.add(new Schedule(23, 2, (byte)6, 540, 85, 14, 3, 13, ""));
        data.add(new Schedule(24, 3, (byte)6, 540, 85, 14, 3, 13, ""));
        data.add(new Schedule(25, 4, (byte)6, 540, 85, 14, 3, 13, ""));
        data.add(new Schedule(26, 0, (byte)6, 645, 180, 17, -1, -1, ""));
        data.add(new Schedule(27, 1, (byte)6, 645, 180, 17, -1, -1, ""));
        data.add(new Schedule(28, 2, (byte)6, 645, 180, 17, -1, -1, ""));
        data.add(new Schedule(29, 3, (byte)6, 645, 180, 17, -1, -1, ""));
        data.add(new Schedule(30, 4, (byte)6, 645, 85, 17, -1, -1, ""));
        data.add(new Schedule(31, 0, (byte)6, 930, 260, 18, -1, -1, ""));
        data.add(new Schedule(32, 1, (byte)6, 930, 260, 18, -1, -1, ""));
        data.add(new Schedule(33, 2, (byte)6, 930, 260, 18, -1, -1, ""));
        data.add(new Schedule(34, 3, (byte)6, 930, 260, 18, -1, -1, ""));
        data.add(new Schedule(35, 4, (byte)6, 930, 260, 18, -1, -1, ""));
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

    public java.util.Vector findByGroupDay(int groupID, byte day) throws TableException
    {
        Vector r = new Vector();
        int i;
        for (i=0; i<data.size(); ++i)
        {
            Schedule s = data.get(i);
            if ((s.getGroupID()==groupID)&&(s.getDay()==day))
                r.add(s);
        }
        return r;
    }
}
