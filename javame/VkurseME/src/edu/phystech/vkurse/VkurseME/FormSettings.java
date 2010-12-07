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
        cmd_lectures = new Command("Показать лекции", Command.SCREEN, 2);
        cmd_exit = new Command("Выход", Command.SCREEN, 3);
        

        this.addCommand(cmd_reload);
        this.addCommand(cmd_lectures);
        this.addCommand(cmd_exit);
        
        this.setCommandListener(this);


        choiceGroups = new ChoiceGroup("Группа", ChoiceGroup.POPUP, sGroups, null);
        this.append(choiceGroups);

        
        //Кнопки для дней недели
        for(int i=0;i<middlet.weekdays.length;i++)
        {
            StringItem button = new StringItem(null, middlet.weekdays[i], Item.HYPERLINK);
            button.setDefaultCommand(new Command(middlet.weekdays[i], Command.ITEM, 1));
            button.setItemCommandListener(this);

            this.append(button);
            this.append("\n");
        }



        calender = new DateField("Дата", DateField.DATE, TimeZone.getDefault());
        //myChoiceGroup = new ChoiceGroup("Факультет", ChoiceGroup.POPUP, department, null);
        


        StringItem ApplyButton = new StringItem(null, "Применить", Item.BUTTON);
        ApplyButton.setDefaultCommand(new Command("Применить", Command.ITEM, 1));
        ApplyButton.setItemCommandListener(this);


        StringItem AllLectionsButton = new StringItem(null, "Доступные лекции", Item.BUTTON);
        AllLectionsButton.setDefaultCommand(new Command("Доступные лекции", Command.ITEM, 1));
        AllLectionsButton.setItemCommandListener(this);

        Date NowDate = new Date();
        calender.setDate(NowDate);

        this.append("\nКонкретный день:");

        this.append(calender);
        //this.append(myChoiceGroup);
        this.append(ApplyButton);
        this.append("\n");
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



public void commandAction(Command cmd, Item item)
    {
        if(cmd.getLabel().equals("Применить"))
        {
            //Правильно получаем ID выбранной группы
            int groupID = ((Group)groups.elementAt(choiceGroups.getSelectedIndex())).getID();

            //Получаем день недели, который выбрал пользователь
            Date dat = calender.getDate();
            Calendar d = Calendar.getInstance();
            d.setTime(dat);
            int day = d.get(Calendar.DAY_OF_WEEK)-1;


            middlet.tek_group = choiceGroups.getSelectedIndex();
            middlet.tek_day = day;


            middlet.net.request_schedule(groupID, day);
            Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));
        }


        if (cmd.getLabel().equals("Доступные лекции"))
        {
            middlet.showLecturesForm(false);
        }

        if (cmd.getLabel().equals("Выход"))
        {
            middlet.exit();
        }
        for(int i=0;i<middlet.weekdays.length;i++)
        {
           if(cmd.getLabel().equals(middlet.weekdays[i]))
           {
                //Правильно получаем ID выбранной группы
                int groupID = ((Group)groups.elementAt(choiceGroups.getSelectedIndex())).getID();
                int day = i+1;
                
                middlet.tek_group = choiceGroups.getSelectedIndex();
                middlet.tek_day = day;


                middlet.net.request_schedule(groupID, day);
                Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));
     
           }
        }
      
    }
}
