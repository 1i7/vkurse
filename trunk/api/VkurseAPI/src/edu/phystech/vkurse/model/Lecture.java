/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class Lecture extends DbTableRecord
{
    protected String name;
    protected int examTypeID;
    protected String comment;

    public Lecture() {}
    public Lecture(int id, String name, int examTypeID, String comment)
    {
        this.id = id;
        this.name = name;
        this.examTypeID = examTypeID;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExamTypeID() {
        return examTypeID;
    }

    public void setExamTypeID(int examTypeID) {
        this.examTypeID = examTypeID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override public String toString()
    {
        String r = "ID=" + id + " ";
        r += "name='" + name.replace("'", "<apostrophe>") + "' ";
        r += "examTypeID=" + examTypeID + " ";
        r += "comment='" + comment.replace("'", "<apostrophe>") + "' ";
        //r = r.replace("'", "<apostrophe>");
        r = r.trim();
        return r;
    }

    void setData(String n, String d)
    {
        if (n.equals("ID")) this.id = Integer.parseInt(d);
        if (n.equals("name")) this.name = d;
        if (n.equals("examTypeID")) this.examTypeID = Integer.parseInt(d);
        if (n.equals("comment")) this.comment = d;
    }
}
