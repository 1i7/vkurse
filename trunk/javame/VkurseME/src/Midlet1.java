/**
 * @author Администратор
 *
 * 
 */
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;
import org.ksoap.*;


public class Midlet1 extends MIDlet implements CommandListener, ItemCommandListener{
    private Form myForm, resForm, myForm1;
    ChoiceGroup myChoiceGroup;
    ChoiceGroup choiceGroups;
    DateField calender;
    String st[]={"ФИВТ", "ФОПВ", "ФНТИ"};
    String sGroups[]={"791", "792", "793","794"};
    class SheduleRecord
    {
        String subject;
        String teacher;
        String room;
        Date StartTime;
        String info;
    }

    public Midlet1() {
        myForm = new Form("Настройки");

        calender = new DateField("Дата", DateField.DATE , TimeZone.getDefault());
        myChoiceGroup = new ChoiceGroup("Факультет",ChoiceGroup.POPUP,st,null);
        choiceGroups = new ChoiceGroup("Группа",ChoiceGroup.POPUP,sGroups,null);
        StringItem siButton = new StringItem(null,"Применить", Item.BUTTON);

        siButton.setDefaultCommand(new Command("Применить", Command.ITEM, 1));
        siButton.setItemCommandListener(this);

        Date NowDate = new Date();
        calender.setDate(NowDate);

        myForm.append(calender);
        myForm.append(myChoiceGroup);
        myForm.append(choiceGroups);
        myForm.append(siButton);

        DataExchanger de = new DataExchanger();
        myForm.append(de.Version());
    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(myForm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command cmd, Displayable disp) {
    }
    void  append(SheduleRecord rec)
    {
        StringItem siLabel;
        Calendar c = Calendar.getInstance();
        c.setTime(rec.StartTime);
        String sTime = c.get(Calendar.HOUR_OF_DAY)
                + ":" +c.get(Calendar.MINUTE);
        siLabel = new StringItem("", rec.subject +"\n");
        siLabel.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
        siLabel.setDefaultCommand(new Command("Подробнее", Command.ITEM, 3));
        StringItem siLabel2 = new StringItem("",rec.teacher );
        StringItem siLabel3 = new StringItem("\n", sTime + "   " + rec.room +"\n");
        siLabel.setItemCommandListener(this);
        resForm.append(siLabel3);
        resForm.append(siLabel);
        resForm.append(siLabel2);
    }
    public void commandAction(Command cmd, Item i)
    {
        if(cmd.getLabel().equals("Применить"))
        {
            resForm = new Form("Рассписание");

            String sDepartment = st[myChoiceGroup.getSelectedIndex()];
            String sGroup = sGroups[myChoiceGroup.getSelectedIndex()];

            String sNowData;
            Date NowDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(NowDate);
            sNowData = c.get(Calendar.DAY_OF_MONTH)
                    + "." +c.get(Calendar.MONTH)
                    + "." +c.get(Calendar.YEAR)
                    + "    " + c.get(Calendar.HOUR_OF_DAY)
                    + ":" +c.get(Calendar.MINUTE)
                    + ":" + c.get(Calendar.SECOND) ;

            String sSeeData;
            Date dat = calender.getDate();
            Calendar d = Calendar.getInstance();
            d.setTime(dat);
            sSeeData = d.get(Calendar.DAY_OF_MONTH)
                    + "." +d.get(Calendar.MONTH)
                    + "." +d.get(Calendar.YEAR);

            StringItem siLabel1 = new StringItem("Факультет:", sDepartment);
            StringItem siLabel2 = new StringItem("Группа:",sGroup);
            StringItem siLabel3 = new StringItem("Текущая дата:",sNowData);
            StringItem siLabel4 = new StringItem("Просматриваеммый день:",sSeeData);

            siLabel1.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel1.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel1.setItemCommandListener(this);
            siLabel2.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel2.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel2.setItemCommandListener(this);
            siLabel3.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel3.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel3.setItemCommandListener(this);
            siLabel4.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
            siLabel4.setDefaultCommand(new Command("Настройки", Command.ITEM, 1));
            siLabel4.setItemCommandListener(this);

            SheduleRecord rec = new SheduleRecord();
            rec.subject = "Опты";
            rec.room = "239 нк";
            rec.teacher = "sdifjsoif";
            rec.info = "asdfausdfhsudfhiusdhfusidhfius";
            rec.StartTime = dat;

            SheduleRecord rec2 = new SheduleRecord();
            rec2.subject = "СуперОпты";
            rec2.room = "239 нк";
            rec2.teacher = "sdifjsoif";
            rec2.info = "asdfausdfhsudfhiusdhfusidhfiusыа";
            rec2.StartTime = dat;

            SheduleRecord rec3 = new SheduleRecord();
            rec3.subject = "НеОпты";
            rec3.room = "239 нк";
            rec3.teacher = "sdifjsoif";
            rec3.info = "asdfausdfhsudfhiusdhfusidhfiusпрапрапр";
            rec3.StartTime = dat;
           
            resForm.append(siLabel1);
            resForm.append(siLabel2);
            resForm.append(siLabel3);
            resForm.append(siLabel4);
            resForm.setCommandListener(this);
            
            append(rec);
            append(rec2);
            append(rec3);

            Display.getDisplay(this).setCurrent(resForm);
        }

        if (cmd.getLabel().equals("Выход"))
        {
        destroyApp(false);
        notifyDestroyed();
        }

        if (cmd.getLabel().equals("Настройки")){
            Display.getDisplay(this).setCurrent(myForm);
        }

        if (cmd.getLabel().equals("Подробнее"))
        {
           myForm1 = new Form("Инфо о предмете");
           StringItem siLabel2 = new StringItem("","Информация о предмете" );
           siLabel2.setDefaultCommand(new Command("Назад", Command.BACK, 1));
           siLabel2.setItemCommandListener(this);
           myForm1.append(siLabel2);
           Display.getDisplay(this).setCurrent(myForm1);
        }

        if (cmd.getLabel().equals("Назад")){
           Display.getDisplay(this).setCurrent(resForm);
        }
    }
}
