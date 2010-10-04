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
public class GroupsTestTable implements GroupsTable
{
    //ArrayList<Group> data = new ArrayList<Group>();
    java.util.Vector data = new java.util.Vector();

    public GroupsTestTable()
    {
        data.add(new Group(100, "Test data - group", "1"));
        data.add(new Group(0, "791", "4"));
        data.add(new Group(1, "792", "4"));
        data.add(new Group(2, "793", "4"));
        data.add(new Group(3, "794", "4"));
        data.add(new Group(4, "798", "4"));
    }

    public boolean insert(Group item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(Group item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Group)data.get(i)).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public Group get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Group)data.get(i)).getID() == ID) return (Group)data.get(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Group)data.get(i)).getID() == ID) r = i;
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
            int ID = ((Group)data.get(i)).getID();
            if (ID > r) r = ID;
        }
        r++;
        return r;
    }

    public boolean insertWithNewID(Group item) throws TableException
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
