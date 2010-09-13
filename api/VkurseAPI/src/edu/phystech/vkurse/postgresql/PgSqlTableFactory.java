/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.postgresql;
import edu.phystech.vkurse.model.*;
import edu.phystech.vkurse.test.*;

/**
 * В данный момент работа с БД реализована только для LecturesTable
 */

/**
 * ToDO:  После написания (хотя бы объявления с "затычками") всех классов
 * для работы с PostgreSQL изменить фабрику, чтобы создавала именно их,
 * а не тестовые классы. Подключение пакета edu.phystech.vkurse.test.*,
 * соответственно, убрать.
 */

/**
 *
 * @author Дима
 */
public class PgSqlTableFactory implements TableFactory
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
        return new LecturesPgSqlTable();
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
