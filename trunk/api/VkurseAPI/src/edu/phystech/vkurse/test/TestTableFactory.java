/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.test;
import edu.phystech.vkurse.model.*;

/**
 *
 * @author Дима
 */
public class TestTableFactory implements TableFactory
{
    public ExamTypesTable getExamTypesTable()
    {
        return new ExamTypesTestTable();
    }

    public GroupsTable getGroupsTable()
    {
        return new GroupsTestTable();
    }
    
    public LecturesTable getLecturesTable()
    {
        return new LecturesTestTable();
    }

    public RoomsTable getRoomsTable()
    {
        return new RoomsTestTable();
    }
    
    public ScheduleTable getScheduleTable()
    {
        return new ScheduleTestTable();
    }

    public ScheduleChangesTable getScheduleChangesTable()
    {
        return new ScheduleChangesTestTable();
    }

    public TeachersTable getTeachersTable()
    {
        return new TeachersTestTable();
    }
}
