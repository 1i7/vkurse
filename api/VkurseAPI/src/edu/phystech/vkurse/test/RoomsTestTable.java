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
public class RoomsTestTable implements RoomsTable
{
    //ArrayList<Room> data = new ArrayList<Room>();
    java.util.Vector data = new java.util.Vector();

    public RoomsTestTable()
    {
        /*
        data.add(new Room(0, "123 ГК"));
        data.add(new Room(1, "Большая физическая"));
        data.add(new Room(2, "115 КПМ"));
         */
        data.add(new Room(100, "Test data - room"));
        data.add(new Room(0, "202 НК"));
        data.add(new Room(1, "113 ГК"));
        data.add(new Room(2, "430 ГК"));
        data.add(new Room(3, "115 КПМ"));
        data.add(new Room(4, "110 КПМ"));
        data.add(new Room(5, "301 ЛК"));
        data.add(new Room(6, "515 ГК"));
        data.add(new Room(7, "417 ГК"));
        data.add(new Room(8, "802 КПМ"));
    }

    public boolean insert(Room item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(Room item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.get(i)).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public Room get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.get(i)).getID() == ID) return (Room)data.get(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.get(i)).getID() == ID) r = i;
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
            int ID = ((Room)data.get(i)).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }

    public boolean insertWithNewID(Room item) throws TableException
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
