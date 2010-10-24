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
    //ArrayList<Teacher> data = new ArrayList<Teacher>();
    java.util.Vector data = new java.util.Vector();

    public TeachersTestTable()
    {
        data.addElement(new Teacher(100, "Test data - teacher", "degree"));
        data.addElement(new Teacher(0, "Аммосов Ю.П.", ""));
        data.addElement(new Teacher(10, "Половинкин Е.С.", "Профессор"));
        data.addElement(new Teacher(11, "Белоусов Ю.М.", "Профессор"));
        data.addElement(new Teacher(12, "Осипов Д.Л.", ""));
        data.addElement(new Teacher(13, "Надеждин Б.Б.", ""));
    }

    public int insert(Teacher item) throws TableException
    {
        data.addElement(item);
        return item.getID();
    }

    public boolean update(Teacher item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Teacher)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item,i);
        }
        return true;
    }

    public Teacher get(int ID) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Teacher)data.elementAt(i)).getID() == ID) return (Teacher)data.elementAt(i);
        }
        return null;
    }

    public boolean remove(int ID) throws TableException
    {
        int i;
        int r = -1;
        for (i=0; i<data.size(); ++i)
        {
            if (((Teacher)data.elementAt(i)).getID() == ID) r = i;
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
            int ID = ((Teacher)data.elementAt(i)).getID();
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
     * 
     */
}
