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
        data.addElement(new Room(0, "123 ГК"));
        data.addElement(new Room(1, "Большая физическая"));
        data.addElement(new Room(2, "115 КПМ"));
         */
        data.addElement(new Room(100, "Test data - room"));
        data.addElement(new Room(0, "202 НК"));
        data.addElement(new Room(1, "113 ГК"));
        data.addElement(new Room(2, "430 ГК"));
        data.addElement(new Room(3, "115 КПМ"));
        data.addElement(new Room(4, "110 КПМ"));
        data.addElement(new Room(5, "301 ЛК"));
        data.addElement(new Room(6, "515 ГК"));
        data.addElement(new Room(7, "417 ГК"));
        data.addElement(new Room(8, "802 КПМ"));
    }

    public int insert(Room item) throws TableException
    {
        data.addElement(item);
        return item.getID();
    }

    public boolean update(Room item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item,i);
        }
        return true;
    }

    public Room get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.elementAt(i)).getID() == ID) return (Room)data.elementAt(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.elementAt(i)).getID() == ID) r = i;
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

    /*
    public int findFreeID() throws TableException
    {
        int r = 0;
        int i;
        for (i = 0; i < data.size(); i++)
        {
            int ID = ((Room)data.elementAt(i)).getID();
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
     * 
     */
}
