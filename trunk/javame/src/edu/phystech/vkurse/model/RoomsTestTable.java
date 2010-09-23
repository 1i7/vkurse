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
public class RoomsTestTable implements RoomsTable
{
    Vector data = new Vector();
    public RoomsTestTable()
    {
        data.addElement(new Room(0, "123 ГК"));
        data.addElement(new Room(1, "Большая физическая"));
        data.addElement(new Room(2, "115 КПМ"));
    }

    public boolean insert(Room item) throws TableException
    {
        data.addElement(item);
        return true;
    }

    public boolean update(Room item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Room)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item, i);
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
}
