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
    public int insert(ScheduleChange item) throws TableException;
    public boolean update(ScheduleChange item) throws TableException;
    public ScheduleChange get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
    //public int findFreeID() throws TableException;
    //public boolean insertWithNewID(ScheduleChange item) throws TableException;

    //  Выдает список изменений занятий определенной группы в определенный день
    public java.util.Vector findByGroupWeekDay(int groupID, int week, byte day) throws TableException;
    //  Ищет изменения конкретного элемента расписания
    public java.util.Vector findByScheduleID(int scheduleID) throws TableException;
}
