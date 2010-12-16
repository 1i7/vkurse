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

public class FormConnectionError extends Form implements CommandListener, ItemCommandListener{
    Command cmd_exit;
    VkurseME middlet;
    int action;
    StringItem retryButton;
            
    FormConnectionError(VkurseME _middlet, int _action)
    {
        super("Ошибка подключения");
        middlet = _middlet;
        action = _action;

        this.append("Ошибка подключения\n");
        this.append("Не могу подключиться. Похоже, нет сети, или деньги на телефоне кончились.\n");

        
        cmd_exit = new Command("Выход", Command.ITEM, 0);
        this.addCommand(cmd_exit);
        this.setCommandListener(this);

        
        retryButton = new StringItem(null, "Попробовать снова", Item.BUTTON);
        retryButton.setDefaultCommand(new Command("Попробовать снова", Command.ITEM, 1));
        retryButton.setItemCommandListener(this);

        this.append(retryButton);
    }

    public void commandAction(Command cmd, Displayable disp) {
        if(cmd == cmd_exit)
        {
            middlet.exit();
        }
    }

    public void commandAction(Command cmd, Item item)
    {
        if(item == retryButton)
        {
            middlet.net.process(action);
            //С Networker-ом то проблем нет...
            //А какую форму теперь отобразить? Жопа...
            //Надеюсь, кто-нибудь потом перепишет покрасивее)
            switch(action)
            {
                case Networker.AC_REQUEST_ALL_LECTURES:
                    Display.getDisplay(middlet).setCurrent(new FormWaitLectures(middlet));
                    break;
                case Networker.AC_REQUEST_ALL_GROUPS:
                    Display.getDisplay(middlet).setCurrent(new FormWaitPreparingSettings(middlet));
                    break;
                case Networker.AC_REQUEST_SHEDULE:
                    Display.getDisplay(middlet).setCurrent(new FormWaitSchedule(middlet));
                    break;
            }
        }
    }
}
