using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace VkurseClient.edu.phystech.vkurse.model
{

    public interface ExamTypesTable
    {
        bool insert(ExamType item);
        bool update(ExamType item);
        ExamType get(int ID);
        bool remove(int ID);
        List<ExamType> getAll();
        int findFreeID();
        bool insertWithNewID(ExamType item);
    }


    public interface GroupsTable
    {
        bool insert(Group item);
        bool update(Group item);
        Group get(int ID);
        bool remove(int ID);
        List<Group> getAll();
        int findFreeID();
        bool insertWithNewID(Group item);
    }


    public interface LecturesTable
    {
        bool insert(Lecture item);
        bool update(Lecture item);
        Lecture get(int ID);
        bool remove(int ID);
        List<Lecture> getAll();
        int findFreeID();
        bool insertWithNewID(Lecture item);
    }


    public interface RoomsTable
    {
        bool insert(Room item);
        bool update(Room item);
        Room get(int ID);
        bool remove(int ID);
        List<Room> getAll();
        int findFreeID();
        bool insertWithNewID(Room item);
    }


    public interface ScheduleTable
    {
        bool insert(Schedule item);
        bool update(Schedule item);
        Schedule get(int ID);
        bool remove(int ID);
        List<Schedule> getAll();
        int findFreeID();
        bool insertWithNewID(Schedule item);

        //  Выдает список занятий определенной группы в определенный день
        List<Schedule> findByGroupDay(int groupID, byte day);
    }


    public interface ScheduleChangesTable
    {
        bool insert(ScheduleChange item);
        bool update(ScheduleChange item);
        ScheduleChange get(int ID);
        bool remove(int ID);
        List<ScheduleChange> getAll();
        int findFreeID();
        bool insertWithNewID(ScheduleChange item);

        //  Выдает список изменений занятий определенной группы в определенный день
        List<ScheduleChange> findByGroupWeekDay(int groupID, int week, byte day);
        //  Ищет изменения конкретного элемента расписания
        List<ScheduleChange> findByScheduleID(int scheduleID);
    }


    public interface TeachersTable
    {
        bool insert(Teacher item);
        bool update(Teacher item);
        Teacher get(int ID);
        bool remove(int ID);
        List<Teacher> getAll();
        int findFreeID();
        bool insertWithNewID(Teacher item);
    }


    public interface TableFactory
    {
        ExamTypesTable getExamTypesTable();
        GroupsTable getGroupsTable();
        LecturesTable getLecturesTable();
        RoomsTable getRoomsTable();
        ScheduleTable getScheduleTable();
        ScheduleChangesTable getScheduleChangesTable();
        TeachersTable getTeachersTable();
        string getSourceName();
    }

}
