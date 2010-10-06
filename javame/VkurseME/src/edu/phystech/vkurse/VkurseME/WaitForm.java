/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.VkurseME;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Font;
import java.util.*;

/**
 *
 * @author Lex
 */
import javax.microedition.midlet.*;

public class WaitForm extends Form implements CommandListener{
    VkurseME middlet;
    WaitForm(VkurseME middlet)
    {
        super("Подождите...");

        this.middlet = middlet;
              
        this.append("Подождите, пожалуйста, программа немножко думает ...");
        
        
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
