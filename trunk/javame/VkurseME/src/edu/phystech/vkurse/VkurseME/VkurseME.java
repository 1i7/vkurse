package edu.phystech.vkurse.VkurseME;
import edu.phystech.vkurse.model.*;      //  Модель
import edu.phystech.vkurse.test.*;       //  Тестовая реализация модели

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Font;
import java.util.*;

public class VkurseME extends MIDlet /*implements CommandListener, ItemCommandListener*/{
    Networker net;
    final static int AC_WAIT=0, AC_SHOW_ALL_LECTURES=1,AC_SHOW_SETTINGS=2;
    int action=AC_WAIT;

    Vector all_lectures=null;
    Vector groups=null;
    Vector rooms=null;
    Vector examtypes=null;
    Vector teachers=null;

    int tek_group=0;
    int tek_day=0;

    FormSettings form_settings=null;
    FormLectures form_lectures=null;
    FormSchedule form_schedule=null;

    String weekdays[]={"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};

    



    public VkurseME() {
   
    }


    public void startApp() {
        net = new Networker(this);

        //Запускаем скачивание списка групп с сервера
        action = AC_SHOW_SETTINGS;
        net.request_all_groups();

        Display.getDisplay(this).setCurrent(new FormWaitPreparingSettings(this));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }



    void showSettingsForm(boolean reload)
    {
        //нужно загрузить данные заного
        if(form_settings == null || reload)
        {
            //Запускаем скачивание списка групп с сервера
            action = AC_SHOW_SETTINGS;
            net.request_all_groups();

            Display.getDisplay(this).setCurrent(new FormWaitPreparingSettings(this));
        }else
        {
            Display.getDisplay(this).setCurrent(form_settings);
        }
    }
    void showLecturesForm(boolean reload)
    {
        //нужно загрузить данные заного
        if(form_lectures == null || reload)
        {
            action = AC_SHOW_ALL_LECTURES;
            net.request_all_lectures();

            Display.getDisplay(this).setCurrent(new FormWaitLectures(this));
        }else
        {
            Display.getDisplay(this).setCurrent(form_lectures);
        }
    }
    void showScheduleForm(boolean reload)
    {
        //нужно загрузить данные заного
        if(form_schedule == null || reload)
        {
            net.request_schedule(((Group)groups.elementAt(tek_group)).getID(),tek_day);

            Display.getDisplay(this).setCurrent(new FormWaitSchedule(this));
        }else
        {
            Display.getDisplay(this).setCurrent(form_schedule);
        }
    }


    Room get_room_by_id(int id)
    {
        for(int i=0;i<rooms.size();i++)
        {
            Room tek = (Room)rooms.elementAt(i);
            if(tek.getID() == id)
                return tek;
        }
        return new Room();//Хак. Некрасиво(
    }
    Lecture get_lecture_by_id(int id)
    {
        for(int i=0;i<all_lectures.size();i++)
        {
            Lecture tek = (Lecture)all_lectures.elementAt(i);
            if(tek.getID() == id)
                return tek;
        }
        return new Lecture();//Хак. Некрасиво(
    }
    Teacher get_teacher_by_id(int id)
    {
        for(int i=0;i<teachers.size();i++)
        {
            Teacher tek = (Teacher)teachers.elementAt(i);
            if(tek.getID() == id)
                return tek;
        }
        return new Teacher();//Хак. Некрасиво(
    }

    public void SetLectures(Vector lectures)
    {
        all_lectures = lectures;
        if(action == AC_SHOW_ALL_LECTURES)
        {
            action = AC_WAIT;
            form_lectures = new FormLectures(this,lectures);
            Display.getDisplay(this).setCurrent(form_lectures);
        }
    }

    public void SetListGroups(Vector groups)
    {
        this.groups = groups;
        if(action == AC_SHOW_SETTINGS)
        {
            action = AC_WAIT;
            form_settings = new FormSettings(this,groups);
            Display.getDisplay(this).setCurrent(form_settings);
        }
    }
    public void SetSchedule(Vector schedule)
    {
        form_schedule = new FormSchedule(this,schedule);
        Display.getDisplay(this).setCurrent(form_schedule);
    }
    public void SetRooms(Vector _rooms)
    {
        rooms = _rooms;
    }

    public void SetExamTypes(Vector _examtypes)
    {
        examtypes = _examtypes;
    }

    public void SetTeachers(Vector _teachers)
    {
        teachers = _teachers;
    }

    public void exit()
    {
        destroyApp(false);
        notifyDestroyed();
    }

}
