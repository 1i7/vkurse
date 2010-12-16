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
    Command cmd_reload,cmd_exit;
    ChoiceGroup myChoiceGroup;
    ChoiceGroup choiceGroups;
    String department[]={"ФИВТ"};
    String sGroups[];// ={"191", "192", "193","194"};

    Vector groups;




    FormSettings(VkurseME middlet, Vector groups) {
        super("Настройки");
        this.middlet = middlet;
        this.groups = groups;


        this.append("Выберите вашу группу:\n");

        

        sGroups = new String[groups.size()];

        /*
         * В эту переменнуж нужно получить позицию выбранной группы
         * в списке всех групп. По умолчанию - первый элемент списка
         */
        int selectedGroup = 0;
        
        for (int i = 0; i < groups.size(); i++) {
            try {
                Group g = (Group) groups.elementAt(i);
                if(g.getID() == middlet.settings.getGroup())
                    selectedGroup = i;
                sGroups[i] = g.getName();
            } catch (Exception exc) {
            }
        }

        //cmd_reload = new Command("Обновить", Command.SCREEN, 1);
        cmd_exit = new Command("Выход", Command.ITEM, 3);

        this.addCommand(cmd_exit);
        this.setCommandListener(this);


        choiceGroups = new ChoiceGroup("Группа", ChoiceGroup.POPUP, sGroups, null);
        
        choiceGroups.setSelectedIndex(selectedGroup, true);
        this.append(choiceGroups);


        StringItem SaveButton = new StringItem(null, "Сохранить", Item.BUTTON);
        SaveButton.setDefaultCommand(new Command("Сохранить", Command.SCREEN, 1));
        SaveButton.setItemCommandListener(this);

        this.append(SaveButton);
    }



    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_reload)
        {
            middlet.showSettingsForm(true);
        }
        if(cmd == cmd_exit)
        {
            middlet.exit();
        }
    }



public void commandAction(Command cmd, Item item)
{
        if (cmd.getLabel().equals("Сохранить"))
        {
            //Правильно получаем ID выбранной группы
            int groupID = ((Group)groups.elementAt(choiceGroups.getSelectedIndex())).getID();
            String groupName = ((Group)groups.elementAt(choiceGroups.getSelectedIndex())).getName();

            middlet.settings.setGroup(groupID);
            middlet.settings.setGroupName(groupName);
            middlet.settings.Save();

            middlet.groupName = middlet.settings.getGroupName();
            middlet.tek_group = middlet.settings.getGroup();
            middlet.showMainForm(true);
        }
        if (cmd.getLabel().equals("Выход"))
        {
            middlet.exit();
        }
    }
}
