/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.VkurseME;
import javax.microedition.lcdui.*;
import java.util.*;

/**
 *
 * @author Lex
 */

public class FormWaitPreparingSettings extends Form implements CommandListener{
    VkurseME middlet;
    StringItem si = new StringItem(null, "...", Item.HYPERLINK);
    Timer timer;
    FormWaitPreparingSettings(VkurseME middlet)
    {
        super("Подождите...");

        timer = new Timer();
        this.middlet = middlet;

        this.append("Пожалуйста, подождите. Сейчас вам будет предложено выбрать номер группы");
        this.append(si);

        TimerTask tTask = new TimerTask() {
                public void run() {
                    if(si.getText().equals("."))
                        si.setText("..");
                    else if(si.getText().equals(".."))
                        si.setText("...");
                    else if(si.getText().equals("..."))
                        si.setText(".");
                }
            };
        timer.schedule( tTask, 1000, 1000 );



        this.addCommand(new Command("Выход",Command.EXIT,0));
        this.setCommandListener(this);
    }
    public void commandAction(Command cmd, Displayable disp) {
        if(cmd.getLabel() == "Выход")
        {
            middlet.exit();
        }
    }
}