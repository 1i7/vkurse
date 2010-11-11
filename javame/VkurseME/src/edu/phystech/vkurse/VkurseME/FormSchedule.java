/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.VkurseME;

/**
 *
 * @author Lex
 */

import javax.microedition.lcdui.*;
import java.util.*;
import edu.phystech.vkurse.model.*;

public class FormSchedule extends Form implements CommandListener, ItemCommandListener{
    VkurseME middlet;
    Command cmd_settings,cmd_reload,cmd_exit;
    Vector schedule;


    FormSchedule(VkurseME middlet,Vector schedule)
    {
        super("Рассписание");

        this.schedule = schedule;
        this.middlet = middlet;

        StringItem siLabel1 = new StringItem("Группа:",((Group)middlet.groups.elementAt(middlet.tek_group)).getName());
        StringItem siLabel2 = new StringItem("Просматриваеммый день:",middlet.weekdays[middlet.tek_day-1]);
        

        

        /*
         siLabel1.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
        siLabel1.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
        siLabel1.setItemCommandListener(this);
        siLabel2.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
        siLabel2.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
        siLabel2.setItemCommandListener(this);
         * 
         */

        this.append(siLabel1);
        this.append(siLabel2);
        this.append(new StringItem("  ",""));
        

        //append_record(Sc);
        for (int j = 0; j< schedule.size(); j++)
        {
            try
            {
                Schedule Sc = (Schedule)schedule.elementAt(j);
                append_record(Sc,j);
            }
            catch (Exception exc)
            {
            }
        }

        cmd_reload = new Command("Обновить",Command.SCREEN,0);
        cmd_settings = new Command("Настройки",Command.SCREEN,1);
        cmd_exit = new Command("Выход",Command.SCREEN,2);


        this.addCommand(cmd_settings);
        this.addCommand(cmd_reload);
        this.addCommand(cmd_exit);

        this.setCommandListener(this);

    }


    void append_record(Schedule rec, int n)
    {
        StringItem siLabel;

        int res = rec.getStartTime();
        String sTime = ((res-(res % 60)) / 60)
                + ":" +(res % 60);

        int iRoomID = rec.getRoomID();
        int iLectureID = rec.getLectureID();

    
        Room vRoom ;
        try
        {
            vRoom = middlet.get_room_by_id(iRoomID);
        }
        catch (Exception exc)
        {
            vRoom = new Room();
        }


        Lecture   vLecture ;
        try
        {
            vLecture = middlet.get_lecture_by_id(iLectureID);
        }
        catch (Exception exc)
        {
            vLecture = new Lecture();
        }

        Font MyFont = Font.getFont(Font.FACE_MONOSPACE ,Font.STYLE_BOLD, Font.SIZE_LARGE);

        String room_name = vRoom.getName();
        if(room_name == null)
            room_name = "";

        String lecture_name = vLecture.getName();
        if(lecture_name == null)
            lecture_name = "";


        siLabel = new StringItem(sTime + "   " + room_name , "" + lecture_name);
        siLabel.setFont(MyFont);
        //siLabel.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
        siLabel.setDefaultCommand(new Command("Подробнее", Command.ITEM, n));
     //   StringItem siLabel2 = new StringItem("",rec.teacher );
     //   StringItem siLabel3 = new StringItem("\n", sTime + "   " + rec.room +"\n");
        siLabel.setItemCommandListener(this);
    //    resForm.append(siLabel3);
        this.append(siLabel);
    //    resForm.append(siLabel2);
    }

    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_reload)
        {
            middlet.showScheduleForm(true);
        }
        if(cmd == cmd_settings)
        {
            middlet.showSettingsForm(false);
        }
        if(cmd == cmd_exit)
        {
            middlet.exit();
        }
    }

    public void commandAction(Command cmd, Item i) {
        if (cmd.getLabel().equals("Подробнее")) {
            //В приоритете мы храним номер выбранной записи рассписания
            int task = cmd.getPriority();

            Display.getDisplay(middlet).setCurrent(new FormSubject(middlet,(Schedule)schedule.elementAt(task)));

        }
    }
}
