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
        r += "examTypeID=" + examTypeID + " ";
        r += "comment='" + comment + "' ";
        r = r.trim();
        return r;
    }
}
