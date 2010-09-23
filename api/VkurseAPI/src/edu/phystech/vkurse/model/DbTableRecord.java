/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public abstract class DbTableRecord
{
    protected int id;

    public void setID(int newID)
    {
        id = newID;
    }
    
    public int getID()
    {
        return id;
    }

    
    public void readData(String data)
    {
        String p = "";
        boolean inStr = false;
        int i;
        for (i=0; i<data.length(); i++)
        {
            String c = data.substring(i, 1);
            p += c;
            if ((!inStr) && (c.equals(" ")))
            {
                readParam(p);
                p = "";
            }
            if (c.equals("'")) inStr = !inStr;
        }
    }

    private void readParam(String p)
    {
        String d = p.trim();
        int a = d.indexOf("=");
        String l = d.substring(0, a);
        String r = d.substring(a+1, d.length()-a-1);
        if (r.startsWith("'")) r = r.substring(1, r.length()-1);
        if (r.endsWith("'")) r = r.substring(0, r.length()-1);
        setData(l, r);
    }

    abstract void setData(String n, String d);
}
