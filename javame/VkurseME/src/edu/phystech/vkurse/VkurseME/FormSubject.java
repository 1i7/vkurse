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

public class FormSubject extends Form implements CommandListener{
    VkurseME middlet;
    Command cmd_back,cmd_exit;

    FormSubject(VkurseME _middlet,Schedule task)
    {
        super("Информация о предмете");
        middlet = _middlet;

        cmd_back = new Command("Назад",Command.SCREEN,1);
        cmd_exit = new Command("Выход",Command.SCREEN,2);

        this.addCommand(cmd_back);
        //this.addCommand(cmd_exit);
        this.setCommandListener(this);


        StringItem siLabel1 = new StringItem("Группа:",((Group)middlet.groups.elementAt(middlet.tek_group)).getName());
        StringItem siLabel2 = new StringItem("День:",middlet.weekdays[middlet.tek_day-1]);

        String lecture_name = middlet.get_lecture_by_id(task.getLectureID()).getName();
        if(lecture_name == null)
            lecture_name = "";
        StringItem siLabel3 = new StringItem("Предмет:", lecture_name);

        /*(String examtype_name = middlet.get_examtype_by_id(task.getExamTypeID()).getName();
        if(examtype_name == null)
            examtype_name = "";
        StringItem siLabel4 = new StringItem("Форма отчетности::", examtype_name);*/

        /*String teacher_name = middlet.get_teacher_by_id(task.getTeacherID()).getName();
        if(teacher_name == null)
            teacher_name = "";
        StringItem siLabel4 = new StringItem("Преподаватель:", teacher_name);*/
        

        
       /* String teacher_name = middlet.get_teacher_by_id(task.getTeacherID()).getName();
        StringItem siLabel4 = new StringItem("Препод:",teacher_name);*/
        
         

        String room_name = middlet.get_room_by_id(task.getRoomID()).getName();
        if(room_name == null)
            room_name = "";
        StringItem siLabel5 = new StringItem("Аудитория:",room_name);

        String comment =  task.getComment();
        if(comment == null)
            comment = "";
        StringItem siLabel6 = new StringItem("Комментарий:",comment);



        this.append(siLabel1);
        this.append(siLabel2);
        this.append(siLabel3);
       // this.append(siLabel4);
        this.append(siLabel5);
        this.append(siLabel6);
    }

    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_back)
        {
            middlet.showScheduleForm(false);
        }
        if(cmd == cmd_exit)
        {
            middlet.exit();
        }
    }
}
