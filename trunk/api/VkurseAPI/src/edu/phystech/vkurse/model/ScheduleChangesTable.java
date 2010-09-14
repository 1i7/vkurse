/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface ScheduleChangesTable
{
    public boolean insert(ScheduleChange item) throws TableException;
    public boolean update(ScheduleChange item) throws TableException;
    public ScheduleChange get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;

    //  Выдает список изменений занятий определенной группы в определенный день
    public java.util.Vector findByGroupDay(int groupID, byte day) throws TableException;
}
