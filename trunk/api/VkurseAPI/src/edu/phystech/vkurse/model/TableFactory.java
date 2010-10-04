/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface TableFactory
{
    public ExamTypesTable getExamTypesTable();
    public GroupsTable getGroupsTable();
    public LecturesTable getLecturesTable();
    public RoomsTable getRoomsTable();
    public ScheduleTable getScheduleTable();
    public ScheduleChangesTable getScheduleChangesTable();
    public TeachersTable getTeachersTable();
    public String getSourceName();
}
