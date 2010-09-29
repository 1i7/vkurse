/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface LecturesTable
{
    public boolean insert(Lecture item) throws TableException;
    public boolean update(Lecture item) throws TableException;
    public Lecture get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
    public int findFreeID() throws TableException;
}
