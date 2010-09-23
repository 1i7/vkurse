/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class ExamType extends DbTableRecord
{
    protected String name;

    public ExamType() {}
    public ExamType(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public void setName(String newName)
    {
        name = newName;
    }
    public String getName()
    {
        return name;
    }

    @Override public String toString()
    {
        String r = "ID=" + id + " ";
        r += "name='" + name + "' ";
        r = r.trim();
        return r;
    }
}
