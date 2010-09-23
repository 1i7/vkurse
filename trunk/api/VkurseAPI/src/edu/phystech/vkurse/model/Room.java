/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class Room extends DbTableRecord
{
    protected String name;

    public Room() {}
    public Room(int id, String name)
    {
        this.id = id;
        this.name = name;
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

    @Override public String toString()
    {
        String r = "ID=" + id + " ";
        r += "name='" + name + "' ";
        r = r.trim();
        return r;
    }
}
