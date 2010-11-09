/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface LectureTypesTable
{
    public int insert(LectureType item) throws TableException;
    public boolean update(LectureType item) throws TableException;
    public LectureType get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
    public java.util.Vector get(int[] ids) throws TableException;
    //public int findFreeID() throws TableException;
    //public boolean insertWithNewID(LectureType item) throws TableException;
}
