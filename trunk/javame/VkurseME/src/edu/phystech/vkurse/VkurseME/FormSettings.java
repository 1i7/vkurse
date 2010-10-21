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

public class FormSettings extends Form implements CommandListener, ItemCommandListener{
    VkurseME middlet;
    Command cmd_lectures,cmd_reload,cmd_exit;
    ChoiceGroup myChoiceGroup;
    ChoiceGroup choiceGroups;
    DateField calender;
    String department[]={"ФИВТ"};
    String sGroups[];// ={"191", "192", "193","194"};

    Vector groups;




    FormSettings(VkurseME middlet, Vector groups) {
        super("Настройки");
        this.middlet = middlet;
        this.groups = groups;

        sGroups = new String[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            try {
                Group g = (Group) groups.elementAt(i);
                sGroups[i] = g.getName();
            } catch (Exception exc) {
            }
        }

        cmd_reload = new Command("Обновить", Command.SCREEN, 1);
        cmd_lectures = new Command("ShowLectures", Command.SCREEN, 2);
        cmd_exit = new Command("Exit", Command.SCREEN, 3);
        

        this.addCommand(cmd_reload);
        this.addCommand(cmd_lectures);
        this.addCommand(cmd_exit);
        
        this.setCommandListener(this);



        calender = new DateField("Дата", DateField.DATE, TimeZone.getDefault());
        myChoiceGroup = new ChoiceGroup("Факультет", ChoiceGroup.POPUP, department, null);
        choiceGroups = new ChoiceGroup("Группа", ChoiceGroup.POPUP, sGroups, null);


        StringItem ApplyButton = new StringItem(null, "Применить", Item.BUTTON);
        ApplyButton.setDefaultCommand(new Command("Применить", Command.ITEM, 1));
        ApplyButton.setItemCommandListener(this);


        StringItem AllLectionsButton = new StringItem(null, "Доступные лекции", Item.BUTTON);
        AllLectionsButton.setDefaultCommand(new Command("Доступные лекции", Command.ITEM, 1));
        AllLectionsButton.setItemCommandListener(this);

        Date NowDate = new Date();
        calender.setDate(NowDate);

        this.append(calender);
        this.append(myChoiceGroup);
        this.append(choiceGroups);
        this.append(ApplyButton);
        this.append(AllLectionsButton);

    }



    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_reload)
        {
            middlet.showSettingsForm(true);
        }
        if(cmd == cmd_lectures)
        {
            middlet.showLecturesForm(false);
        }
        if(cmd == cmd_exit)
        {
            middlet.exit();
        }
    }

public void commandAction(Command cmd, Item i)
    {
        if(cmd.getLabel().equals("Применить"))
        {
            //Правильно получаем ID выбранной группы
            int groupID = ((Group)groups.elementAt(choiceGroups.getSelectedIndex())).getID();

            //Получаем день недели, который выбрал пользователь
            Date dat = calender.getDate();
            Calendar d = Calendar.getInstance();
            d.setTime(dat);
            int day = d.get(Calendar.DAY_OF_WEEK);


            middlet.tek_group = choiceGroups.getSelectedIndex();
            middlet.tek_day = day;


            middlet.net.request_schedule(groupID, day);
            Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));



            /*resForm = new Form("Рассписание");


            String sDepartment = st[myChoiceGroup.getSelectedIndex()];
            String sGroup = sGroups[choiceGroups.getSelectedIndex()];

            String sNowData;
            Date NowDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(NowDate);
            sNowData = c.get(Calendar.DAY_OF_MONTH)
                    + "." +c.get(Calendar.MONTH)
                    + "." +c.get(Calendar.YEAR)
                    + "    " + c.get(Calendar.HOUR_OF_DAY)
                    + ":" +c.get(Calendar.MINUTE)
                    + ":" + c.get(Calendar.SECOND) ;

            String sSeeData;
            Date dat = calender.getDate();
            Calendar d = Calendar.getInstance();
            d.setTime(dat);
            sSeeData = d.get(Calendar.DAY_OF_MONTH)
                    + "." +d.get(Calendar.MONTH)
                    + "." +d.get(Calendar.YEAR);

            StringItem siLabel1 = new StringItem("Факультет:", sDepartment);
            StringItem siLabel2 = new StringItem("Группа:",sGroup);
            StringItem siLabel3 = new StringItem("Текущая дата:",sNowData);
            StringItem siLabel4 = new StringItem("Просматриваеммый день:",sSeeData );
            StringItem siLabel5 = new StringItem("\n","" );





            siLabel1.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel1.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel1.setItemCommandListener(this);
            siLabel2.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel2.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel2.setItemCommandListener(this);
            siLabel3.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel3.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel3.setItemCommandListener(this);
            siLabel4.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel4.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel4.setItemCommandListener(this);


            resForm.append(siLabel1);
            resForm.append(siLabel2);
            resForm.append(siLabel3);
            resForm.append(siLabel4);
            resForm.append(siLabel5);
            resForm.setCommandListener(this);


            TableFactory factory = new TestTableFactory();
            ScheduleTable scht =  factory.getScheduleTable();
            Vector vSchedule ;
            try
            {
                vSchedule = scht.getAll();
            }
            catch (Exception exc)
            {
                vSchedule = new Vector();
            }
            for (int j = 0; j< vSchedule.size(); j++)
            {
                try
                {
                    Schedule Sc = scht.get(j);
                    if ((Sc.getGroupID() == choiceGroups.getSelectedIndex())&&(d.get(Calendar.DAY_OF_WEEK)==((Sc.getDay()+1) % 7)))
                    {
                        append_record(Sc);
                    }
                }
                catch (Exception exc)
                {
                }
            }

            Display.getDisplay(this).setCurrent(resForm
             *
             */
        }


        if (cmd.getLabel().equals("Доступные лекции"))
        {
            middlet.showLecturesForm(false);
        }

        if (cmd.getLabel().equals("Выход"))
        {
            middlet.exit();
        }

        /*
         if (cmd.getLabel().equals("Настройки")){
            Display.getDisplay(middlet).setCurrent(settingsForm);
        }
         *

        if (cmd.getLabel().equals("Подробнее"))
        {
           infoForm = new Form("Инфо о предмете");
           StringItem siLabel2 = new StringItem("","Информация о предмете" );
           siLabel2.setDefaultCommand(new Command("Назад", Command.BACK, 1));
           siLabel2.setItemCommandListener(this);
           infoForm.append(siLabel2);
           Display.getDisplay(this).setCurrent(infoForm);
        }

        if (cmd.getLabel().equals("Назад")){
           Display.getDisplay(this).setCurrent(resForm);
        }
         *
         */
    }
}
