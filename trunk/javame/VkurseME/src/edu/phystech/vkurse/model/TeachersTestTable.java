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
public class TeachersTestTable implements TeachersTable
{
    Vector data = new Vector();
    public TeachersTestTable()
    {
        data.addElement(new Teacher(0, "Юрий Аммосов", ""));
        data.addElement(new Teacher(1, "Сергей Коновалов", ""));
        data.addElement(new Teacher(2, "Юрий Белоусов", ""));
    }

    public boolean insert(Teacher item) throws TableException
    {
        data.addElement(item);
        return true;
    }

    public boolean update(Teacher item) throws TableException
    {
        int i;
        for (i=0; i<data.size(); ++i)
        {
            if (((Teacher)data.elementAt(i)).getID() == item.getID()) data.setElementAt(item, i);
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
}
