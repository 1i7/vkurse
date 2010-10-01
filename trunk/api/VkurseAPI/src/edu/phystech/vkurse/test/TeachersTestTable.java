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
public class TeachersTestTable implements TeachersTable
{
    ArrayList<Teacher> data = new ArrayList<Teacher>();
    public TeachersTestTable()
    {
        data.add(new Teacher(100, "Test data - teacher", "degree"));
        data.add(new Teacher(0, "Аммосов Ю.П.", ""));
        data.add(new Teacher(10, "Половинкин Е.С.", "Профессор"));
        data.add(new Teacher(11, "Белоусов Ю.М.", "Профессор"));
        data.add(new Teacher(12, "Осипов Д.Л.", ""));
        data.add(new Teacher(13, "Надеждин Б.Б.", ""));
    }

    public boolean insert(Teacher item) throws TableException
    {
        data.add(item);
        return true;
    }

    public boolean update(Teacher item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (data.get(i).getID() == item.getID()) data.set(i, item);
        }
        return true;
    }

    public Teacher get(int ID) throws TableException
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

    public boolean insertWithNewID(Teacher item) throws TableException
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
