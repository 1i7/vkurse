/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface ScheduleTable
{
    public boolean insert(Schedule item) throws TableException;
    public boolean update(Schedule item) throws TableException;
    public Schedule get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
    public int findFreeID() throws TableException;

    //  Выдает список занятий определенной группы в определенный день
    public java.util.Vector findByGroupDay(int groupID, byte day) throws TableException;
}
