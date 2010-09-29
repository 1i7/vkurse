package vkurseconsoleclient;

import edu.phystech.vkurse.model.*;      //  Модель
import edu.phystech.vkurse.test.*;       //  Тестовая реализация модели
import edu.phystech.vkurse.postgresql.*; //  Реализация для сервера PostgreSQL
import java.util.*;
import java.io.*;

/**
 *
 * @author Дмитрий ApX Исупов
 */
public class VkurseClient
{
    private TableFactory tableFactory = null;
    private ExamTypesTable examTypesTable;
    private GroupsTable groupsTable;
    private LecturesTable lecturesTable;
    private RoomsTable roomsTable;
    private ScheduleTable scheduleTable;
    private ScheduleChangesTable scheduleChangesTable;
    private TeachersTable teachersTable;

    //  При создании класса необходимо указать, с каким типом данных мы будем работать
    public VkurseClient(int t)
    {
        if (0 == t) tableFactory = new TestTableFactory();
        if (1 == t) tableFactory = new PgSqlTableFactory();

        if (null != tableFactory)
        {
            examTypesTable = tableFactory.getExamTypesTable();
            groupsTable = tableFactory.getGroupsTable();
            lecturesTable = tableFactory.getLecturesTable();
            roomsTable = tableFactory.getRoomsTable();
            scheduleTable = tableFactory.getScheduleTable();
            scheduleTable = tableFactory.getScheduleTable();
            scheduleChangesTable = tableFactory.getScheduleChangesTable();
            teachersTable = tableFactory.getTeachersTable();
        }
    }


    public boolean IsInited()
    {
        return (null != tableFactory);
    }


    private String StrToLen(String s, int l)
    {
        int i;
        String r = s;
        for (i=r.length(); i<l; i++) r = r + " ";
        if (r.length() > l) r = r.substring(0, l);
        return r;
    }


    private int ConsoleReadInt(int d)
    {
        BufferedReader is = new BufferedReader(
            new InputStreamReader(System.in));
        int r = d;
        try
        {
            r = Integer.parseInt(is.readLine());
        }
        catch (Exception ex) { }

        return r;
    }


    private String ConsoleReadString(String d)
    {
        BufferedReader is = new BufferedReader(
            new InputStreamReader(System.in));
        String r = d;
        try
        {
            r = is.readLine();
            if ((null == r) || (r.isEmpty())) r = d;
        }
        catch (Exception ex) { }

        return r;
    }


    public void Test()
    {
        try
        {
            //java.util.List<Lecture> l
            //System.out.println(lecturesTable.getAll().getClass().getName());
            System.out.println(tableFactory.getSourceName());
            System.out.println();
        } catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }


    private int DoMainMenu()
    {
        System.out.println("          MAIN  MENU:");
        System.out.println("      Lists management:");
        System.out.println("1:  Manage list of exam types");
        System.out.println("2:  Manage list of groups");
        System.out.println("3:  Manage list of lectures");
        System.out.println("4:  Manage list of rooms");
        System.out.println("5:  Manage list of teachers");
        System.out.println("      Schedule management:");
        System.out.println("6:  Show all schedule records");
        System.out.println("7:  Show group schedule for particular day");
        System.out.println("8:  Edit schedule entry");
        System.out.println("9:  Remove schedule entry");
        System.out.println("10: Create new schedule entry");
        System.out.println("      Schedule changes management:");
        System.out.println("11: Show all changes in schedule");
        System.out.println("12: Show changes of schedule of particalar group for particular day and week");
        System.out.println("13: Edit schedule changes entry");
        System.out.println("14: Remove schedule changes entry");
        System.out.println("15: Create new schedule changes entry");
        System.out.println("");
        System.out.println("16: Show free IDs");
        System.out.println("");
        System.out.println("0:  Exit");

        int r = ConsoleReadInt(-1);
        System.out.println("");

        return r;
    }


    private int DoManagementMenu(String caption)
    {
        System.out.println("      "+caption+" management:");
        System.out.println("1:  Show all");
        System.out.println("2:  Show particular");
        System.out.println("3:  Edit");
        System.out.println("4:  Remove");
        System.out.println("5:  Create new");
        System.out.println("6:  Get free ID");
        System.out.println("");
        System.out.println("0:  Back");

        int r = ConsoleReadInt(-1);
        System.out.println("");

        return r;
    }


    public void Run()
    {
        int mnu;
        while ((mnu = DoMainMenu()) != 0)
        {
            if (1 == mnu) ManageExamTypes();
            if (2 == mnu) ManageGroups();
            if (3 == mnu) ManageLectures();
            if (4 == mnu) ManageRooms();
            if (5 == mnu) ManageTeachers();

            if (6 == mnu) ShowAllSchedule();
            if (7 == mnu) ShowGroupDaySchedule();
            if (8 == mnu) EditScheduleEntry();
            if (9 == mnu) RemoveScheduleEntry();
            if (10 == mnu) CreateScheduleEntry();

            if (11 == mnu) ShowAllScheduleChanges();
            if (12 == mnu) ShowAllScheduleChangesGroupWeekDay();
            if (13 == mnu) EditScheduleChangeEntry();
            if (14 == mnu) RemoveScheduleChangeEntry();
            if (15 == mnu) CreateScheduleChangeEntry();

            if (16 == mnu) ShowFreeIDs();

            if (20 == mnu) Test();
        }
    }


    private void ManageExamTypes()
    {
        int mnu;
        while ((mnu = DoManagementMenu("Exam types")) != 0)
        {
            if (1 == mnu)
            {
                try
                {
                    Vector v = examTypesTable.getAll();
                    System.out.println("   List of exam types");
                    System.out.println("ID    Name");
                    System.out.println("--------------------------------------");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        ExamType d = (ExamType)v.get(i);
                        System.out.println(StrToLen(""+d.getID(), 6) + d.getName());
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (2 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    ExamType d = examTypesTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Exam type:");
                        System.out.println("ID:   " + d.getID());
                        System.out.println("Name: " + d.getName());
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (3 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    ExamType d = examTypesTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Exam type:");
                        System.out.println("ID:   " + d.getID());
                        System.out.print("Name["+d.getName()+"]: ");
                        d.setName(ConsoleReadString(d.getName()));
                        examTypesTable.update(d);
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (4 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    ExamType d = examTypesTable.get(id);
                    if (d!=null)
                    {
                        examTypesTable.remove(id);
                        System.out.println("Exam type with ID " + id + " was removed");
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (5 == mnu)
            {
                try
                {
                    System.out.println("Enter exam type data: ");
                    System.out.print("ID:   ");
                    int id = ConsoleReadInt(0);
                    System.out.print("Name: ");
                    String name = ConsoleReadString("Name");
                    ExamType d = new ExamType(id, name);
                    if (examTypesTable.insert(d))
                        System.out.println("Exam type was added");
                    else
                        System.out.println("Exam type was NOT added");
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (6 == mnu)
            {
                try
                {
                    System.out.println("Free ID:  " + examTypesTable.findFreeID());
                } catch (Exception Ex) {}
                System.out.println("");
            }
        }
    }


    private void ManageGroups()
    {
        int mnu;
        while ((mnu = DoManagementMenu("Groups")) != 0)
        {
            if (1 == mnu)
            {
                try
                {
                    Vector v = groupsTable.getAll();
                    System.out.println("   List of groups");
                    System.out.println("ID    Name                     Course");
                    System.out.println("--------------------------------------------");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Group d = (Group)v.get(i);
                        System.out.println(StrToLen(""+d.getID(), 6) + StrToLen(d.getName(),25) + d.getCourse());
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (2 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Group d = groupsTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Group:");
                        System.out.println("ID:     " + d.getID());
                        System.out.println("Name:   " + d.getName());
                        System.out.println("Course: " + d.getCourse());
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (3 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Group d = groupsTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Group:");
                        System.out.println("ID:     " + d.getID());
                        System.out.print("Name["+d.getName()+"]: ");
                        d.setName(ConsoleReadString(d.getName()));
                        System.out.print("Course["+d.getCourse()+"]: ");
                        d.setCourse(ConsoleReadString(d.getName()));
                        groupsTable.update(d);
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (4 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Group d = groupsTable.get(id);
                    if (d!=null)
                    {
                        groupsTable.remove(id);
                        System.out.println("Group with ID " + id + " was removed");
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (5 == mnu)
            {
                try
                {
                    System.out.println("Enter group data: ");
                    System.out.print("ID:     ");
                    int id = ConsoleReadInt(0);
                    System.out.print("Name:   ");
                    String name = ConsoleReadString("Name");
                    System.out.print("Course: ");
                    String course = ConsoleReadString("Course");
                    Group d = new Group(id, name, course);
                    if (groupsTable.insert(d))
                        System.out.println("Group was added");
                    else
                        System.out.println("Group was NOT added");
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (6 == mnu)
            {
                try
                {
                    System.out.println("Free ID:  " + groupsTable.findFreeID());
                } catch (Exception Ex) {}
                System.out.println("");
            }
        }
    }


    private void ManageLectures()
    {
        int mnu;
        while ((mnu = DoManagementMenu("Lectures")) != 0)
        {
            if (1 == mnu)
            {
                try
                {
                    Vector v = lecturesTable.getAll();
                    System.out.println("   List of lectures");
                    System.out.println("ID    Name                     ExamType                 Comment");
                    System.out.println("----------------------------------------------------------------------------");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Lecture d = (Lecture)v.get(i);
                        ExamType e = examTypesTable.get(d.getExamTypeID());
                        String et = "NO EXAM TYPE";
                        if (e!=null) et = e.getName();
                        System.out.println(StrToLen(""+d.getID(), 6) + StrToLen(d.getName(),25) + 
                                StrToLen("("+d.getExamTypeID()+")"+et,25) +
                                d.getComment());
                    }
                } catch (Exception Ex)
                {
                    System.out.println("Exception: " + Ex);
                }
                System.out.println("");
            }

            if (2 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Lecture d = lecturesTable.get(id);
                    if (d!=null)
                    {
                        ExamType e = examTypesTable.get(d.getExamTypeID());
                        String et = "NO EXAM TYPE";
                        if (e!=null) et = e.getName();
                        
                        System.out.println("    Lecture:");
                        System.out.println("ID:       " + d.getID());
                        System.out.println("Name:     " + d.getName());
                        System.out.println("ExamType: " + d.getExamTypeID() + " - " + et);
                        System.out.println("Comment:  " + d.getComment());
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (3 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Lecture d = lecturesTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Lecture:");
                        System.out.println("ID:     " + d.getID());
                        System.out.print("Name["+d.getName()+"]: ");
                        d.setName(ConsoleReadString(d.getName()));
                        try
                        {
                            Vector v = examTypesTable.getAll();
                            System.out.println("   Exam types");
                            int i;
                            for (i=0; i<v.size(); i++)
                            {
                                ExamType td = (ExamType)v.get(i);
                                System.out.println(""+td.getID() + ":  " + td.getName());
                            }
                        } catch (Exception Ex) {}
                        System.out.print("ExamTypeID["+d.getExamTypeID()+"]: ");
                        d.setExamTypeID(ConsoleReadInt(d.getExamTypeID()));
                        System.out.print("Coment["+d.getComment()+"]: ");
                        d.setComment(ConsoleReadString(d.getComment()));
                        lecturesTable.update(d);
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (4 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Lecture d = lecturesTable.get(id);
                    if (d!=null)
                    {
                        lecturesTable.remove(id);
                        System.out.println("Lecture with ID " + id + " was removed");
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (5 == mnu)
            {
                try
                {
                    System.out.println("Enter lecture data: ");
                    System.out.print("ID:         ");
                    int id = ConsoleReadInt(0);
                    System.out.print("Name:       ");
                    String name = ConsoleReadString("Name");
                    try
                    {
                        Vector v = examTypesTable.getAll();
                        System.out.println("   Exam types");
                        int i;
                        for (i=0; i<v.size(); i++)
                        {
                            ExamType td = (ExamType)v.get(i);
                            System.out.println(""+td.getID() + ":  " + td.getName());
                        }
                    } catch (Exception Ex) {}
                    System.out.print("ExamTypeID: ");
                    int examTypeID = ConsoleReadInt(-1);
                    System.out.print("Comment:    ");
                    String comment = ConsoleReadString("Comment");
                    Lecture d = new Lecture(id, name, examTypeID, comment);
                    if (lecturesTable.insert(d))
                        System.out.println("Lecture was added");
                    else
                        System.out.println("Lecture was NOT added");
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (6 == mnu)
            {
                try
                {
                    System.out.println("Free ID:  " + lecturesTable.findFreeID());
                } catch (Exception Ex) {}
                System.out.println("");
            }
        }
    }


    private void ManageRooms()
    {
        int mnu;
        while ((mnu = DoManagementMenu("Rooms")) != 0)
        {
            if (1 == mnu)
            {
                try
                {
                    Vector v = roomsTable.getAll();
                    System.out.println("   List of rooms");
                    System.out.println("ID    Name");
                    System.out.println("--------------------------------------");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Room d = (Room)v.get(i);
                        System.out.println(StrToLen(""+d.getID(), 6) + d.getName());
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (2 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Room d = roomsTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Room:");
                        System.out.println("ID:   " + d.getID());
                        System.out.println("Name: " + d.getName());
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (3 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Room d = roomsTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Room:");
                        System.out.println("ID:   " + d.getID());
                        System.out.print("Name["+d.getName()+"]: ");
                        d.setName(ConsoleReadString(d.getName()));
                        roomsTable.update(d);
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (4 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Room d = roomsTable.get(id);
                    if (d!=null)
                    {
                        roomsTable.remove(id);
                        System.out.println("Room with ID " + id + " was removed");
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (5 == mnu)
            {
                try
                {
                    System.out.println("Enter room data: ");
                    System.out.print("ID:   ");
                    int id = ConsoleReadInt(0);
                    System.out.print("Name: ");
                    String name = ConsoleReadString("Name");
                    Room d = new Room(id, name);
                    if (roomsTable.insert(d))
                        System.out.println("Room was added");
                    else
                        System.out.println("Room was NOT added");
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (6 == mnu)
            {
                try
                {
                    System.out.println("Free ID:  " + roomsTable.findFreeID());
                } catch (Exception Ex) {}
                System.out.println("");
            }
        }
    }


    private void ManageTeachers()
    {
        int mnu;
        while ((mnu = DoManagementMenu("Groups")) != 0)
        {
            if (1 == mnu)
            {
                try
                {
                    Vector v = teachersTable.getAll();
                    System.out.println("   List of teachers");
                    System.out.println("ID    Name                                              Degree");
                    System.out.println("---------------------------------------------------------------------");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Teacher d = (Teacher)v.get(i);
                        System.out.println(StrToLen(""+d.getID(), 6) + StrToLen(d.getName(),50) + d.getDegree());
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (2 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Teacher d = teachersTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Teacher:");
                        System.out.println("ID:     " + d.getID());
                        System.out.println("Name:   " + d.getName());
                        System.out.println("Degree: " + d.getDegree());
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (3 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Teacher d = teachersTable.get(id);
                    if (d!=null)
                    {
                        System.out.println("    Teacher:");
                        System.out.println("ID:     " + d.getID());
                        System.out.print("Name["+d.getName()+"]: ");
                        d.setName(ConsoleReadString(d.getName()));
                        System.out.print("Degree["+d.getDegree()+"]: ");
                        d.setDegree(ConsoleReadString(d.getName()));
                        teachersTable.update(d);
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (4 == mnu)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    int id = ConsoleReadInt(-1);
                    Teacher d = teachersTable.get(id);
                    if (d!=null)
                    {
                        teachersTable.remove(id);
                        System.out.println("Teacher with ID " + id + " was removed");
                    }
                    else
                    {
                        System.out.println("ID " + id + " was not found");
                    }
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (5 == mnu)
            {
                try
                {
                    System.out.println("Enter teacher data: ");
                    System.out.print("ID:     ");
                    int id = ConsoleReadInt(0);
                    System.out.print("Name:   ");
                    String name = ConsoleReadString("Name");
                    System.out.print("Degree: ");
                    String degree = ConsoleReadString("Degree");
                    Teacher d = new Teacher(id, name, degree);
                    if (teachersTable.insert(d))
                        System.out.println("Teacher was added");
                    else
                        System.out.println("Teacher was NOT added");
                } catch (Exception Ex) {}
                System.out.println("");
            }

            if (6 == mnu)
            {
                try
                {
                    System.out.println("Free ID:  " + teachersTable.findFreeID());
                } catch (Exception Ex) {}
                System.out.println("");
            }
        }
    }


    private void ShowAllSchedule()
    {
        try
        {
            Vector v = scheduleTable.getAll();
            System.out.println("   Schedule");
            System.out.println("ID    Group          Day Start  Length Lecture                  Room                     Teacher                  Comment");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            int i;
            for (i=0; i<v.size(); i++)
            {
                Schedule d = (Schedule)v.get(i);
                
                Group g = groupsTable.get(d.getGroupID());
                String gt = "NO GROUP";     if (g!=null) gt = g.getName();

                Lecture l = lecturesTable.get(d.getLectureID());
                String lt = "NO LECTURE";     if (l!=null) lt = l.getName();

                Room r = roomsTable.get(d.getRoomID());
                String rt = "NO ROOM";     if (r!=null) rt = r.getName();

                Teacher t = teachersTable.get(d.getTeacherID());
                String tt = "NO TEACHER";     if (t!=null) tt = t.getName();

                System.out.println(StrToLen(""+d.getID(), 6) +
                        StrToLen("("+d.getGroupID()+")"+gt,15) +
                        StrToLen(""+d.getDay(),4) +
                        StrToLen(""+d.getStartTime(),7) +
                        StrToLen(""+d.getLength(),7) +
                        StrToLen("("+d.getLectureID()+")"+lt,25) +
                        StrToLen("("+d.getRoomID()+")"+rt,25) +
                        StrToLen("("+d.getTeacherID()+")"+tt,25) +
                        d.getComment());
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void ShowGroupDaySchedule()
    {
        try
        {
            try
            {
                Vector v = groupsTable.getAll();
                System.out.println("   Groups");
                int i;
                for (i=0; i<v.size(); i++)
                {
                    Group td = (Group)v.get(i);
                    System.out.println(""+td.getID() + ":  " + td.getName());
                }
            } catch (Exception Ex) {}

            System.out.print("Enter group ID: ");
            int groupID = ConsoleReadInt(-1);

            System.out.print("Enter day number [1..7]: ");
            int day = ConsoleReadInt(0);

            Vector v = scheduleTable.findByGroupDay(groupID, (byte)day);
            System.out.println("   Schedule");
            System.out.println("ID    Group          Day Start  Length Lecture                  Room                     Teacher                  Comment");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            int i;
            for (i=0; i<v.size(); i++)
            {
                Schedule d = (Schedule)v.get(i);

                Group g = groupsTable.get(d.getGroupID());
                String gt = "NO GROUP";     if (g!=null) gt = g.getName();

                Lecture l = lecturesTable.get(d.getLectureID());
                String lt = "NO LECTURE";     if (l!=null) lt = l.getName();

                Room r = roomsTable.get(d.getRoomID());
                String rt = "NO ROOM";     if (r!=null) rt = r.getName();

                Teacher t = teachersTable.get(d.getTeacherID());
                String tt = "NO TEACHER";     if (t!=null) tt = t.getName();

                System.out.println(StrToLen(""+d.getID(), 6) +
                        StrToLen("("+d.getGroupID()+")"+gt,15) +
                        StrToLen(""+d.getDay(),4) +
                        StrToLen(""+d.getStartTime(),7) +
                        StrToLen(""+d.getLength(),7) +
                        StrToLen("("+d.getLectureID()+")"+lt,25) +
                        StrToLen("("+d.getRoomID()+")"+rt,25) +
                        StrToLen("("+d.getTeacherID()+")"+tt,25) +
                        d.getComment());
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void EditScheduleEntry()
    {
        try
        {
            System.out.print("Enter ID: ");
            int id = ConsoleReadInt(-1);
            
            Schedule d = scheduleTable.get(id);
            if (d!=null)
            {
                System.out.println("    Schedule entry:");
                System.out.println("ID:     " + d.getID());

                try
                {
                    Vector v = groupsTable.getAll();
                    System.out.println("   Groups");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Group td = (Group)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("getGroupID["+d.getGroupID()+"]: ");
                d.setGroupID(ConsoleReadInt(d.getGroupID()));
                System.out.print("day["+d.getDay()+"]: ");
                d.setDay((byte)ConsoleReadInt(d.getDay()));
                System.out.print("startTime["+d.getStartTime()+"]: ");
                d.setStartTime(ConsoleReadInt(d.getStartTime()));
                System.out.print("length["+d.getLength()+"]: ");
                d.setLength(ConsoleReadInt(d.getLength()));
                try
                {
                    Vector v = lecturesTable.getAll();
                    System.out.println("   Lectures");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Lecture td = (Lecture)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("LectureID["+d.getLectureID()+"]: ");
                d.setLectureID(ConsoleReadInt(d.getLectureID()));
                try
                {
                    Vector v = roomsTable.getAll();
                    System.out.println("   Rooms");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Room td = (Room)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("RoomID["+d.getRoomID()+"]: ");
                d.setRoomID(ConsoleReadInt(d.getRoomID()));
                try
                {
                    Vector v = teachersTable.getAll();
                    System.out.println("   Teachers");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Teacher td = (Teacher)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("TeacherID["+d.getTeacherID()+"]: ");
                d.setTeacherID(ConsoleReadInt(d.getTeacherID()));
                System.out.print("Comment["+d.getComment()+"]: ");
                d.setComment(ConsoleReadString(d.getComment()));
                scheduleTable.update(d);
            }
            else
            {
                System.out.println("ID " + id + " was not found");
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void RemoveScheduleEntry()
    {
        try
        {
            System.out.println("    Removing schedule entry");
            System.out.print("Enter ID: ");
            int id = ConsoleReadInt(-1);
            Schedule d = scheduleTable.get(id);
            if (d!=null)
            {
                scheduleTable.remove(id);
                System.out.println("Schedule entry with ID " + id + " was removed");
            }
            else
            {
                System.out.println("ID " + id + " was not found");
            }
        } catch (Exception Ex) {}
        System.out.println("");
    }

    
    private void CreateScheduleEntry()
    {
        try
        {
            System.out.println("Enter schedule entry data: ");
            System.out.print("ID:       ");
            int id = ConsoleReadInt(0);
            try
            {
                Vector v = groupsTable.getAll();
                System.out.println("   Groups");
                int i;
                for (i=0; i<v.size(); i++)
                {
                    Group td = (Group)v.get(i);
                    System.out.println(""+td.getID() + ":  " + td.getName());
                }
            } catch (Exception Ex) {}
            System.out.print("GroupID:  ");
            int groupID = ConsoleReadInt(0);
            
            System.out.print("Day:      ");
            byte day = (byte)ConsoleReadInt(1);
            System.out.print("startTime: ");
            int startTime = ConsoleReadInt(0);
            System.out.print("length:    ");
            int length = ConsoleReadInt(10);
            try
            {
                Vector v = lecturesTable.getAll();
                System.out.println("   Lectures");
                int i;
                for (i=0; i<v.size(); i++)
                {
                    Lecture td = (Lecture)v.get(i);
                    System.out.println(""+td.getID() + ":  " + td.getName());
                }
            } catch (Exception Ex) {}
            System.out.print("LectureID: ");
            int lectureID = ConsoleReadInt(0);
            try
            {
                Vector v = roomsTable.getAll();
                System.out.println("   Rooms");
                int i;
                for (i=0; i<v.size(); i++)
                {
                    Room td = (Room)v.get(i);
                    System.out.println(""+td.getID() + ":  " + td.getName());
                }
            } catch (Exception Ex) {}
            System.out.print("RoomID:    ");
            int roomID = ConsoleReadInt(0);
            try
            {
                Vector v = teachersTable.getAll();
                System.out.println("   Teachers");
                int i;
                for (i=0; i<v.size(); i++)
                {
                    Teacher td = (Teacher)v.get(i);
                    System.out.println(""+td.getID() + ":  " + td.getName());
                }
            } catch (Exception Ex) {}
            System.out.print("TeacherID: ");
            int teacherID = ConsoleReadInt(0);
            System.out.print("Comment:   ");
            String comment = ConsoleReadString("");

            Schedule d = new Schedule(id, groupID, day, startTime, length, lectureID, roomID, teacherID, comment);
            if (scheduleTable.insert(d))
                System.out.println("Schedule entry was added");
            else
                System.out.println("Schedule entry was NOT added");
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void ShowAllScheduleChanges()
    {
        try
        {
            Vector v = scheduleChangesTable.getAll();
            System.out.println("   Schedule changes");
            System.out.println("ID    Schedule  Week Group          Day Start  Length Lecture                  Room                     Teacher                  Comment");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            int i;
            for (i=0; i<v.size(); i++)
            {
                int scID = -1;
                {
                    ScheduleChange d = (ScheduleChange)v.get(i);
                    scID = d.getScheduleID();

                    Group g = groupsTable.get(d.getGroupID());
                    String gt = "NO GROUP";     if (g!=null) gt = g.getName();

                    Lecture l = lecturesTable.get(d.getLectureID());
                    String lt = "NO LECTURE";     if (l!=null) lt = l.getName();

                    Room r = roomsTable.get(d.getRoomID());
                    String rt = "NO ROOM";     if (r!=null) rt = r.getName();

                    Teacher t = teachersTable.get(d.getTeacherID());
                    String tt = "NO TEACHER";     if (t!=null) tt = t.getName();

                    System.out.println(StrToLen(""+d.getID(), 6) +
                            StrToLen(""+d.getScheduleID(),10) +
                            StrToLen(""+d.getWeek(),5) +
                            StrToLen("("+d.getGroupID()+")"+gt,15) +
                            StrToLen(""+d.getDay(),4) +
                            StrToLen(""+d.getStartTime(),7) +
                            StrToLen(""+d.getLength(),7) +
                            StrToLen("("+d.getLectureID()+")"+lt,25) +
                            StrToLen("("+d.getRoomID()+")"+rt,25) +
                            StrToLen("("+d.getTeacherID()+")"+tt,25) +
                            d.getComment());
                }
                {
                    Schedule d = scheduleTable.get(scID);

                    if (d != null)
                    {
                        Group g = groupsTable.get(d.getGroupID());
                        String gt = "NO GROUP";     if (g!=null) gt = g.getName();

                        Lecture l = lecturesTable.get(d.getLectureID());
                        String lt = "NO LECTURE";     if (l!=null) lt = l.getName();

                        Room r = roomsTable.get(d.getRoomID());
                        String rt = "NO ROOM";     if (r!=null) rt = r.getName();

                        Teacher t = teachersTable.get(d.getTeacherID());
                        String tt = "NO TEACHER";     if (t!=null) tt = t.getName();

                        System.out.println(StrToLen(" ORIGINAL SCHEDULE:", 21) +
                                StrToLen("("+d.getGroupID()+")"+gt,15) +
                                StrToLen(""+d.getDay(),4) +
                                StrToLen(""+d.getStartTime(),7) +
                                StrToLen(""+d.getLength(),7) +
                                StrToLen("("+d.getLectureID()+")"+lt,25) +
                                StrToLen("("+d.getRoomID()+")"+rt,25) +
                                StrToLen("("+d.getTeacherID()+")"+tt,25) +
                                d.getComment());
                    }
                    else
                    {
                        System.out.println("   NO SCHEDULE WITH ID " + scID);
                    }
                }
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void ShowAllScheduleChangesGroupWeekDay()
    {
        try
        {
            try
            {
                Vector v = groupsTable.getAll();
                System.out.println("   Groups");
                int i;
                for (i=0; i<v.size(); i++)
                {
                    Group td = (Group)v.get(i);
                    System.out.println(""+td.getID() + ":  " + td.getName());
                }
            } catch (Exception Ex) {}

            System.out.print("Enter group ID: ");
            int groupID = ConsoleReadInt(-1);

            System.out.print("Enter day number [1..7]: ");
            int day = ConsoleReadInt(0);

            System.out.print("Enter week number: ");
            int week = ConsoleReadInt(0);

            Vector v = scheduleChangesTable.findByGroupWeekDay(groupID, week, (byte)day);
            //Vector v = scheduleChangesTable.getAll();
            System.out.println("   Schedule changes");
            System.out.println("ID    Schedule  Week Group          Day Start  Length Lecture                  Room                     Teacher                  Comment");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            int i;
            for (i=0; i<v.size(); i++)
            {
                int scID = -1;
                {
                    ScheduleChange d = (ScheduleChange)v.get(i);
                    scID = d.getScheduleID();

                    Group g = groupsTable.get(d.getGroupID());
                    String gt = "NO GROUP";     if (g!=null) gt = g.getName();

                    Lecture l = lecturesTable.get(d.getLectureID());
                    String lt = "NO LECTURE";     if (l!=null) lt = l.getName();

                    Room r = roomsTable.get(d.getRoomID());
                    String rt = "NO ROOM";     if (r!=null) rt = r.getName();

                    Teacher t = teachersTable.get(d.getTeacherID());
                    String tt = "NO TEACHER";     if (t!=null) tt = t.getName();

                    System.out.println(StrToLen(""+d.getID(), 6) +
                            StrToLen(""+d.getScheduleID(),10) +
                            StrToLen(""+d.getWeek(),5) +
                            StrToLen("("+d.getGroupID()+")"+gt,15) +
                            StrToLen(""+d.getDay(),4) +
                            StrToLen(""+d.getStartTime(),7) +
                            StrToLen(""+d.getLength(),7) +
                            StrToLen("("+d.getLectureID()+")"+lt,25) +
                            StrToLen("("+d.getRoomID()+")"+rt,25) +
                            StrToLen("("+d.getTeacherID()+")"+tt,25) +
                            d.getComment());
                }
                {
                    Schedule d = scheduleTable.get(scID);

                    if (d != null)
                    {
                        Group g = groupsTable.get(d.getGroupID());
                        String gt = "NO GROUP";     if (g!=null) gt = g.getName();

                        Lecture l = lecturesTable.get(d.getLectureID());
                        String lt = "NO LECTURE";     if (l!=null) lt = l.getName();

                        Room r = roomsTable.get(d.getRoomID());
                        String rt = "NO ROOM";     if (r!=null) rt = r.getName();

                        Teacher t = teachersTable.get(d.getTeacherID());
                        String tt = "NO TEACHER";     if (t!=null) tt = t.getName();

                        System.out.println(StrToLen(" ORIGINAL SCHEDULE:", 21) +
                                StrToLen("("+d.getGroupID()+")"+gt,15) +
                                StrToLen(""+d.getDay(),4) +
                                StrToLen(""+d.getStartTime(),7) +
                                StrToLen(""+d.getLength(),7) +
                                StrToLen("("+d.getLectureID()+")"+lt,25) +
                                StrToLen("("+d.getRoomID()+")"+rt,25) +
                                StrToLen("("+d.getTeacherID()+")"+tt,25) +
                                d.getComment());
                    }
                    else
                    {
                        System.out.println("   NO SCHEDULE WITH ID " + scID);
                    }
                }
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void EditScheduleChangeEntry()
    {
        try
        {
            System.out.print("Enter ID: ");
            int id = ConsoleReadInt(-1);

            ScheduleChange d = scheduleChangesTable.get(id);
            if (d!=null)
            {
                System.out.println("    Schedule change entry:");
                System.out.println("ID:     " + d.getID());

                System.out.print("ScheduleID["+d.getScheduleID()+"]: ");
                d.setScheduleID(ConsoleReadInt(d.getScheduleID()));
                System.out.print("Week["+d.getWeek()+"]: ");
                d.setWeek(ConsoleReadInt(d.getWeek()));
                
                try
                {
                    Vector v = groupsTable.getAll();
                    System.out.println("   Groups");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Group td = (Group)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("getGroupID["+d.getGroupID()+"]: ");
                d.setGroupID(ConsoleReadInt(d.getGroupID()));
                System.out.print("day["+d.getDay()+"]: ");
                d.setDay((byte)ConsoleReadInt(d.getDay()));
                System.out.print("startTime["+d.getStartTime()+"]: ");
                d.setStartTime(ConsoleReadInt(d.getStartTime()));
                System.out.print("length["+d.getLength()+"]: ");
                d.setLength(ConsoleReadInt(d.getLength()));
                try
                {
                    Vector v = lecturesTable.getAll();
                    System.out.println("   Lectures");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Lecture td = (Lecture)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("LectureID["+d.getLectureID()+"]: ");
                d.setLectureID(ConsoleReadInt(d.getLectureID()));
                try
                {
                    Vector v = roomsTable.getAll();
                    System.out.println("   Rooms");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Room td = (Room)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("RoomID["+d.getRoomID()+"]: ");
                d.setRoomID(ConsoleReadInt(d.getRoomID()));
                try
                {
                    Vector v = teachersTable.getAll();
                    System.out.println("   Teachers");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Teacher td = (Teacher)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("TeacherID["+d.getTeacherID()+"]: ");
                d.setTeacherID(ConsoleReadInt(d.getTeacherID()));
                System.out.print("Comment["+d.getComment()+"]: ");
                d.setComment(ConsoleReadString(d.getComment()));
                scheduleChangesTable.update(d);
            }
            else
            {
                System.out.println("ID " + id + " was not found");
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void RemoveScheduleChangeEntry()
    {
        try
        {
            System.out.println("    Removing schedule change entry");
            System.out.print("Enter ID: ");
            int id = ConsoleReadInt(-1);
            ScheduleChange d = scheduleChangesTable.get(id);
            if (d!=null)
            {
                scheduleChangesTable.remove(id);
                System.out.println("Schedule change entry with ID " + id + " was removed");
            }
            else
            {
                System.out.println("ID " + id + " was not found");
            }
        } catch (Exception Ex) {}
        System.out.println("");
    }


    private void CreateScheduleChangeEntry()
    {
        try
        {
            System.out.println("Enter schedule change entry data: ");
            System.out.print("ID: ");
            int id = ConsoleReadInt(0);
            System.out.print("ScheduleID: ");
            int scheduleID = ConsoleReadInt(0);

            Schedule sc = scheduleTable.get(scheduleID);

            if (sc != null)
            {
                System.out.print("Week: ");
                int week = ConsoleReadInt(0);
                try
                {
                    Vector v = groupsTable.getAll();
                    System.out.println("   Groups");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Group td = (Group)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("GroupID["+sc.getGroupID()+"]: ");
                int groupID = ConsoleReadInt(sc.getGroupID());

                System.out.print("Day["+sc.getDay()+"]: ");
                byte day = (byte)ConsoleReadInt(sc.getDay());
                System.out.print("startTime["+sc.getStartTime()+"]: ");
                int startTime = ConsoleReadInt(sc.getStartTime());
                System.out.print("length["+sc.getLength()+"]: ");
                int length = ConsoleReadInt(sc.getLength());
                try
                {
                    Vector v = lecturesTable.getAll();
                    System.out.println("   Lectures");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Lecture td = (Lecture)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("LectureID["+sc.getLectureID()+"]: ");
                int lectureID = ConsoleReadInt(sc.getLectureID());
                try
                {
                    Vector v = roomsTable.getAll();
                    System.out.println("   Rooms");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Room td = (Room)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("RoomID["+sc.getRoomID()+"]: ");
                int roomID = ConsoleReadInt(sc.getRoomID());
                try
                {
                    Vector v = teachersTable.getAll();
                    System.out.println("   Teachers");
                    int i;
                    for (i=0; i<v.size(); i++)
                    {
                        Teacher td = (Teacher)v.get(i);
                        System.out.println(""+td.getID() + ":  " + td.getName());
                    }
                } catch (Exception Ex) {}
                System.out.print("TeacherID["+sc.getTeacherID()+"]: ");
                int teacherID = ConsoleReadInt(sc.getTeacherID());
                System.out.print("Comment: ");
                String comment = ConsoleReadString("");

                ScheduleChange d = new ScheduleChange(id, scheduleID, week, groupID, day, startTime, length, lectureID, roomID, teacherID, comment);
                if (scheduleChangesTable.insert(d))
                    System.out.println("Schedule change entry was added");
                else
                    System.out.println("Schedule change entry was NOT added");
            }
            else
            {
                System.out.println("Schedule entry with ID "+scheduleID +" was not found");
            }
        } catch (Exception Ex)
        {
            System.out.println("Exception: " + Ex);
        }
        System.out.println("");
    }


    private void ShowFreeIDs()
    {
        try
        {
            System.out.println("Free schedule ID:  " + scheduleTable.findFreeID());
            System.out.println("Free schedule changes ID:  " + scheduleChangesTable.findFreeID());
        } catch (Exception ex)
        {
            System.out.println("Exception!");
            System.out.print(ex);
        }
        System.out.println("");
    }
}
