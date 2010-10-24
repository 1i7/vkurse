/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface RoomsTable
{
    public int insert(Room item) throws TableException;
    public boolean update(Room item) throws TableException;
    public Room get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
    //public int findFreeID() throws TableException;
    //public boolean insertWithNewID(Room item) throws TableException;
}
