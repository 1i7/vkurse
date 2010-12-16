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

public class FormMain extends Form implements CommandListener, ItemCommandListener{
    VkurseME middlet;
    int selectedGroup;
    String selectedGroupName="";
    Command cmd_lectures, cmd_settings,cmd_exit;
    Settings settings;
    DateField calender;
    

    FormMain(VkurseME middlet, Settings settings) {
        super("VKurse");
        this.middlet = middlet;
        this.settings = settings;
        
        this.selectedGroup = settings.getGroup();
        this.selectedGroupName = settings.getGroupName();

        cmd_lectures = new Command("Показать лекции", Command.SCREEN, 2);
        cmd_exit = new Command("Выход", Command.SCREEN, 3);
        cmd_settings = new Command("Настройки",Command.SCREEN,1);


        this.addCommand(cmd_lectures);
        this.addCommand(cmd_exit);
        this.addCommand(cmd_settings);

        this.setCommandListener(this);

        this.append("Расписание группы " + settings.getGroupName() + "\n");
        

        StringItem button = new StringItem(null, "Сегодня", Item.HYPERLINK);
        button.setDefaultCommand(new Command("Сегодня", Command.ITEM, 1));
        button.setItemCommandListener(this);
        this.append(button);
        this.append("\n");
        
        this.append("День недели:\n");
        //Кнопки для дней недели
        for(int i=0;i<middlet.weekdays.length;i++)
        {
            button = new StringItem(null, middlet.weekdays[i], Item.HYPERLINK);
            button.setDefaultCommand(new Command(middlet.weekdays[i], Command.ITEM, 1));
            button.setItemCommandListener(this);

            this.append(button);
            this.append("\n");
        }

        calender = new DateField("Дата", DateField.DATE, TimeZone.getDefault());
        Date NowDate = new Date();
        calender.setDate(NowDate);
        this.append("\nИли конкретный день:");
        this.append(calender);

        StringItem ApplyButton = new StringItem(null, "Показать", Item.BUTTON);
        ApplyButton.setDefaultCommand(new Command("Показать", Command.ITEM, 1));
        ApplyButton.setItemCommandListener(this);
        this.append(ApplyButton);

    }

    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_settings)
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

    public void commandAction(Command cmd, Item item)
    {
        if(cmd.getLabel().equals("Показать"))
        {
            //Правильно получаем ID выбранной группы
            int groupID = selectedGroup;


            //Получаем день недели, который выбрал пользователь
            Date dat = calender.getDate();
            Calendar d = Calendar.getInstance();
            d.setTime(dat);
            int day = d.get(Calendar.DAY_OF_WEEK)-1;

            middlet.tek_day = day;
            middlet.net.request_schedule(groupID, day);
            Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));
        }

/*
        if (cmd.getLabel().equals("Доступные лекции"))
        {
            middlet.showLecturesForm(false);
        }

        if (cmd.getLabel().equals("Выход"))
        {
            middlet.exit();
        }
     */
        if(cmd.getLabel().equals("Сегодня"))
        {
            int groupID = selectedGroup;

            //Получаем сегодняшний день недели
            Date dat = new Date();
            Calendar d = Calendar.getInstance();
            d.setTime(dat);
            int day = d.get(Calendar.DAY_OF_WEEK)-1;

            middlet.tek_day = day;
            middlet.net.request_schedule(groupID, day);
            Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));

        }
        for(int i=0;i<middlet.weekdays.length;i++)
        {
           if(cmd.getLabel().equals(middlet.weekdays[i]))
           {
                //Правильно получаем ID выбранной группы
                int groupID = selectedGroup;
                int day = i+1;


                middlet.tek_day = day;


                middlet.net.request_schedule(groupID, day);
                Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));

           }
        }
    }
}
