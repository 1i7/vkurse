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
    //ArrayList<Schedule> data = new ArrayList<Schedule>();
    java.util.Vector data = new java.util.Vector();

    public ScheduleTestTable()
    {
        /*
        data.addElement(new Schedule(0, 4, (byte)1, 480, 85, 0, 0, 0, "it is comment of lecture 0"));
        data.addElement(new Schedule(1, 3, (byte)2, 540, 85, 1, 1, 1, "it is comment of lecture 1"));
        data.addElement(new Schedule(2, 2, (byte)3, 600, 200, 2, 2, 2, "it is comment of lecture 2"));
        data.addElement(new Schedule(3, 1, (byte)4, 660, 85, 3, 1, 1, "it is comment of lecture 3"));
        data.addElement(new Schedule(4, 0, (byte)5, 720, 175, 2, 0, 2, "it is comment of lecture 4"));
         */
        data.addElement(new Schedule(100, 100, (byte)1, 540, 85, 100, 100, 100, 0, "comment"));
        data.addElement(new Schedule(0, 0, (byte)5, 540, 85, 10, 0, 10, 0, ""));
        data.addElement(new Schedule(1, 1, (byte)5, 540, 85, 10, 0, 10, 0, ""));
        data.addElement(new Schedule(2, 2, (byte)5, 540, 85, 10, 0, 10, 0, ""));
        data.addElement(new Schedule(3, 3, (byte)5, 540, 85, 10, 0, 10, 1, ""));
        data.addElement(new Schedule(4, 4, (byte)5, 540, 85, 15, 4, -1, 1, ""));
        data.addElement(new Schedule(5, 0, (byte)5, 645, 85, 12, 1, 11, 1, ""));
        data.addElement(new Schedule(6, 1, (byte)5, 645, 85, 12, 1, 11, 1, ""));
        data.addElement(new Schedule(7, 2, (byte)5, 645, 85, 12, 1, 11, 1, ""));
        data.addElement(new Schedule(8, 3, (byte)5, 645, 85, 12, 1, 11, 1, ""));
        data.addElement(new Schedule(9, 4, (byte)5, 645, 85, 12, 1, 11, 1, ""));
        data.addElement(new Schedule(10, 0, (byte)5, 780, 85, 11, 2, 10, 1, ""));
        data.addElement(new Schedule(11, 1, (byte)5, 780, 85, 11, 2, 10, 1, ""));
        data.addElement(new Schedule(12, 2, (byte)5, 780, 85, 11, 2, 10, 1, ""));
        data.addElement(new Schedule(13, 3, (byte)5, 780, 85, 11, 2, 10, 1, ""));
        data.addElement(new Schedule(14, 4, (byte)5, 780, 85, 13, 5, -1, 1, ""));
        data.addElement(new Schedule(15, 0, (byte)5, 835, 85, 13, 6, -1, 1, ""));
        data.addElement(new Schedule(16, 1, (byte)5, 835, 85, 13, 7, -1, 1, ""));
        data.addElement(new Schedule(17, 2, (byte)5, 835, 85, 13, 2, 12, 1, ""));
        data.addElement(new Schedule(18, 3, (byte)5, 930, 85, 13, 7, 12, 1, ""));
        data.addElement(new Schedule(19, 4, (byte)5, 930, 85, 17, -1, -1, 1, ""));
        data.addElement(new Schedule(20, 4, (byte)5, 1025, 135, 16, 8, -1, 1, ""));
        data.addElement(new Schedule(21, 0, (byte)6, 540, 85, 14, 3, 13, 1, ""));
        data.addElement(new Schedule(22, 1, (byte)6, 540, 85, 14, 3, 13, 1, ""));
        data.addElement(new Schedule(23, 2, (byte)6, 540, 85, 14, 3, 13, 1, ""));
        data.addElement(new Schedule(24, 3, (byte)6, 540, 85, 14, 3, 13, 1, ""));
        data.addElement(new Schedule(25, 4, (byte)6, 540, 85, 14, 3, 13, 1, ""));
        data.addElement(new Schedule(26, 0, (byte)6, 645, 180, 17, -1, -1, 1, ""));
        data.addElement(new Schedule(27, 1, (byte)6, 645, 180, 17, -1, -1, 1, ""));
        data.addElement(new Schedule(28, 2, (byte)6, 645, 180, 17, -1, -1, 1, ""));
        data.addElement(new Schedule(29, 3, (byte)6, 645, 180, 17, -1, -1, 1, ""));
        data.addElement(new Schedule(30, 4, (byte)6, 645, 85, 17, -1, -1, 1, ""));
        data.addElement(new Schedule(31, 0, (byte)6, 930, 260, 18, -1, -1, 1, ""));
        data.addElement(new Schedule(32, 1, (byte)6, 930, 260, 18, -1, -1, 1, ""));
        data.addElement(new Schedule(33, 2, (byte)6, 930, 260, 18, -1, -1, 1, ""));
        data.addElement(new Schedule(34, 3, (byte)6, 930, 260, 18, -1, -1, 1, ""));
        data.addElement(new Schedule(35, 4, (byte)6, 930, 260, 18, -1, -1, 1, ""));
    }

    public int insert(Schedule item) throws TableException
    {
        data.addElement(item);
        return item.getID();
    }

    public boolean update(Schedule item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Schedule)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item,i);
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

    public java.util.Vector findByGroupDay(int groupID, byte day) throws TableException
    {
        Vector r = new Vector();
        int i;
        for (i=0; i<data.size(); ++i)
        {
            Schedule s = (Schedule)data.elementAt(i);
            if ((s.getGroupID()==groupID)&&(s.getDay()==day))
                r.addElement(s);
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
            int ID = ((Schedule)data.elementAt(i)).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }

    public boolean insertWithNewID(Schedule item) throws TableException
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
