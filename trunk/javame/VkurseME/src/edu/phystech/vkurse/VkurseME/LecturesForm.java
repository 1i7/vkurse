/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.VkurseME;
import javax.microedition.lcdui.*;
import java.util.*;
import edu.phystech.vkurse.model.*;

/**
 *
 * @author Lex
 */
public class LecturesForm extends Form implements CommandListener{
    VkurseME middlet;
    Command cmd_back,cmd_reload,cmd_exit;


    LecturesForm(VkurseME middlet,Vector lectures)
    {
        super("Список лекций");

        this.middlet = middlet;

        cmd_reload = new Command("Обновить",Command.SCREEN,0);
        cmd_back = new Command("Назад",Command.SCREEN,1);
        cmd_exit = new Command("Выход",Command.SCREEN,2);


        this.addCommand(cmd_back);
        this.addCommand(cmd_reload);
        this.addCommand(cmd_exit);

        this.setCommandListener(this);

        //Выводим на экран лекции
        for (int j = 0; j< lectures.size()+ 1; j++)
        {
            try
            {
                Lecture Sc = (Lecture)lectures.elementAt(j);
                StringItem siLabel1 = new StringItem("",Sc.getName());
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
            middlet.net.request_all_lectures();
            Display.getDisplay(middlet).setCurrent(new WaitForm(middlet));
        }
        if(cmd == cmd_back)
        {
            Display.getDisplay(middlet).setCurrent(middlet.settingsForm);
        }
        if(cmd == cmd_exit)
        {
            middlet.exit();
        }
    }
}
