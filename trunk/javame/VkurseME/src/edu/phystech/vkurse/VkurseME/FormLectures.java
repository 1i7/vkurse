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

public class FormLectures extends Form implements CommandListener{
    VkurseME middlet;
    Command cmd_settings,cmd_reload,cmd_exit;


    FormLectures(VkurseME middlet,Vector lectures)
    {
        super("Список лекций");

        this.middlet = middlet;

        cmd_reload = new Command("Обновить",Command.SCREEN,0);
        cmd_settings = new Command("Настройки",Command.SCREEN,1);
        cmd_exit = new Command("Выход",Command.SCREEN,2);


        this.addCommand(cmd_settings);
        this.addCommand(cmd_reload);
        this.addCommand(cmd_exit);

        this.setCommandListener(this);

        //Выводим на экран лекции
        for (int j = 0; j< lectures.size()+ 1; j++)
        {
            try
            {
                Lecture Sc = (Lecture)lectures.elementAt(j);
                StringItem siLabel1 = new StringItem(Sc.getName(),"");
                this.append(siLabel1);
            }
            catch (Exception exc)
            {
            }
        }
    }
    
    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_reload)
        {
            middlet.showLecturesForm(true);
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
}
