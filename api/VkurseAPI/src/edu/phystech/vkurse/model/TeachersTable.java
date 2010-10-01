/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface TeachersTable
{
    public boolean insert(Teacher item) throws TableException;
    public boolean update(Teacher item) throws TableException;
    public Teacher get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
    public int findFreeID() throws TableException;
    public boolean insertWithNewID(Teacher item) throws TableException;
}
