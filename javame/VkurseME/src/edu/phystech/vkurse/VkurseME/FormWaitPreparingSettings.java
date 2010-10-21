/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.VkurseME;
import javax.microedition.lcdui.*;

/**
 *
 * @author Lex
 */

public class FormWaitPreparingSettings extends Form implements CommandListener{
    VkurseME middlet;
    FormWaitPreparingSettings(VkurseME middlet)
    {
        super("Подождите...");

        this.middlet = middlet;

        this.append("Пожалуйста, подождите...Сейчас вам будет предложено выбрать факультет и номер группы");


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