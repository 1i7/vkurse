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
//import javax.microedition.midlet.*;

public class FormWaitLectures extends Form implements CommandListener{
    VkurseME middlet;
    FormWaitLectures(VkurseME middlet)
    {
        super("Подождите...");

        this.middlet = middlet;
              
        this.append("Подождите, пожалуйста, загружаем ...");
        
        
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
