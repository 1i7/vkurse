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

    public static String replace(String _text, String _searchStr, String _replacementStr)     {
        // String buffer to store str
        StringBuffer sb = new StringBuffer();

        // Search for search
        int searchStringPos = _text.indexOf(_searchStr);
        int startPos = 0;
        int searchStringLength = _searchStr.length();

        // Iterate to add string
        while (searchStringPos != -1) {
            sb.append(_text.substring(startPos, searchStringPos)).append(_replacementStr);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = _text.indexOf(_searchStr, startPos);
        }

        // Create string
        sb.append(_text.substring(startPos,_text.length()));

        return sb.toString();
    }
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
            //String c = data.substring(i, 1);
            String c = data.substring(i, i+1);
            p += c;
            if ((!inStr) && (c.equals(" ")))
            {
                readParam(p);
                p = "";
            }
            if (c.equals("'")) inStr = !inStr;
        }
        if (!p.equals("")) readParam(p);
    }

    private void readParam(String p)
    {
        String d = p;
        while (d.startsWith(" ")) d = d.substring(1, d.length());
        while (d.endsWith(" ")) d = d.substring(0, d.length()-1);
        int a = d.indexOf("=");
        if (a>0)
        {
            String l = d.substring(0, a);
            String r = d.substring(a+1, d.length());
            if (r.startsWith("'")) r = r.substring(1, r.length());
            if (r.endsWith("'")) r = r.substring(0, r.length()-1);

            r = replace(r,"<apostrophe>", "'");
            setData(l, r);
        }
    }

    abstract void setData(String n, String d);
}
