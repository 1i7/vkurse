/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class Teacher extends DbTableRecord
{
    protected String name;
    protected String degree;

    public Teacher() {}
    public Teacher(int id, String name, String degree)
    {
        this.id = id;
        this.name = name;
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override public String toString()
    {
        String r = "ID=" + id + " ";
        r += "name='" + name + "' ";
        r += "degree='" + degree + "' ";
        r = r.replace("'", "<apostrophe>");
        r = r.trim();
        return r;
    }

    void setData(String n, String d)
    {
        if (n.equals("ID")) this.id = Integer.parseInt(d);
        if (n.equals("name")) this.name = d;
        if (n.equals("degree")) this.degree = d;
    }
}
