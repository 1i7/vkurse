/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class Group extends DbTableRecord
{
    protected String name;
    protected String course;

    public Group() {}
    public Group(int id, String name, String course)
    {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    //@Override public String toString()
    public String toStringData()
    {
        String r = "ID=" + id + " ";
        r += "name='" + replace(name,"'", "<apostrophe>") + "' ";
        r += "course='" + replace(course,"'", "<apostrophe>") + "' ";
        r = r.trim();
        return r;
    }

    void setData(String n, String d)
    {
        if (n.equals("ID")) this.id = Integer.parseInt(d);
        if (n.equals("name")) this.name = d;
        if (n.equals("course")) this.course = d;
    }
}
