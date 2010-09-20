/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.postgresql;
import edu.phystech.vkurse.model.*;

/**
 *
 * @author Дима
 */
public class PgSqlTableFactory implements TableFactory
{
    public String getSourceName()
    {
        return "SQL server. " +
                "jdbcDriverClass='" + PgSqlSettings.getJdbcDriverClass() + "' " +
                "url='" + PgSqlSettings.getUrl() + "' " +
                "dbName='" + PgSqlSettings.getDbName() + "'";
    }

    public ExamTypesTable getExamTypesTable()
    {
        return new ExamTypesPgSqlTable();
    }

    public GroupsTable getGroupsTable()
    {
        return new GroupsPgSqlTable();
    }

    public LecturesTable getLecturesTable()
    {
        return new LecturesPgSqlTable();
    }

    public RoomsTable getRoomsTable()
    {
        return new RoomsPgSqlTable();
    }

    public ScheduleTable getScheduleTable()
    {
        return new SchedulePgSqlTable();
    }

    public ScheduleChangesTable getScheduleChangesTable()
    {
        return new ScheduleChangesPgSqlTable();
    }

    public TeachersTable getTeachersTable()
    {
        return new TeachersPgSqlTable();
    }
}
