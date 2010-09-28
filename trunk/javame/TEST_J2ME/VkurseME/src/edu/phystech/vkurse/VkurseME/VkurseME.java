package edu.phystech.vkurse.VkurseME;
import edu.phystech.vkurse.model.*;      //  Модель
//import edu.phystech.vkurse.test.TestTableFactory;       //  Тестовая реализация модели

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Font;
import java.util.*;

public class VkurseME extends MIDlet implements CommandListener, ItemCommandListener{
    private Form settingsForm, resForm, infoForm;
    ChoiceGroup myChoiceGroup;
    ChoiceGroup choiceGroups;
    DateField calender;
    String st[]={"ФИВТ"};
    String sGroups[];// ={"191", "192", "193","194"};

    Networker net;


    private void prepare_settings_form()
    {
        settingsForm = new Form("Настройки");

        /*TableFactory factory = new TestTableFactory();
        GroupsTable gt =  factory.getGroupsTable();
        Vector vGroups;
        try
        {
             vGroups = gt.getAll();
             sGroups = new String[vGroups.size()];
        }
        catch (Exception exc)
        {
            vGroups = new Vector();
        }
        for (int i = 0; i< vGroups.size()+ 1; i++)
        {
           try
           {
           Group g = gt.get(i);
           sGroups[i] = g.getName();
           }
           catch (Exception exc)
           {
           }
        }

        settingsForm.addCommand(new Command("ShowLectures",Command.SCREEN,2));
        settingsForm.addCommand(new Command("Exit",Command.SCREEN,3));
        
        



        calender = new DateField("Дата", DateField.DATE , TimeZone.getDefault());
        myChoiceGroup = new ChoiceGroup("Факультет",ChoiceGroup.POPUP,st,null);
        choiceGroups = new ChoiceGroup("Группа",ChoiceGroup.POPUP,sGroups,null);

        StringItem ApplyButton = new StringItem(null,"Применить", Item.BUTTON);
        ApplyButton.setDefaultCommand(new Command("Применить", Command.ITEM, 1));
        ApplyButton.setItemCommandListener(this);

        StringItem AllLectionsButton = new StringItem(null,"Доступные лекции", Item.BUTTON);
        AllLectionsButton.setDefaultCommand(new Command("Доступные лекции", Command.ITEM, 1));
        AllLectionsButton.setItemCommandListener(this);

        Date NowDate = new Date();
        calender.setDate(NowDate);

        settingsForm.append(calender);
        settingsForm.append(myChoiceGroup);
        settingsForm.append(choiceGroups);
        settingsForm.append(ApplyButton);
        settingsForm.append(AllLectionsButton);
         *
         *
         */
    }

    public VkurseME() {
        net = new Networker(this);
        prepare_settings_form();
    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(settingsForm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command cmd, Displayable disp) {

    }
/*
    void append_record(Schedule rec)
    {
        StringItem siLabel;

        int res = rec.getStartTime();
        String sTime = ((res-(res % 60)) / 60)
                + ":" +(res % 60);

        int iRoomID = rec.getRoomID();
        int iLectureID = rec.getLectureID();

        TableFactory factory = new TestTableFactory();
        RoomsTable rt =  factory.getRoomsTable();
        Room vRoom ;
        try
        {
            vRoom = rt.get(iRoomID);
        }
        catch (Exception exc)
        {
            vRoom = new Room();
        }
        LecturesTable lt =  factory.getLecturesTable();
        Lecture   vLecture ;
        try
        {
            vLecture = lt.get(iLectureID);
        }
        catch (Exception exc)
        {
            vLecture = new Lecture();
        }

        Font MyFont = Font.getFont(Font.FACE_MONOSPACE ,Font.STYLE_BOLD, Font.SIZE_LARGE);
        siLabel = new StringItem(sTime + "   " + vRoom.getName() , "" + vLecture.getName());
        siLabel.setFont(MyFont);
        //siLabel.setDefaultCommand(new Command("Выход", Command.EXIT, 2));
        siLabel.setDefaultCommand(new Command("Подробнее", Command.ITEM, 1));
     //   StringItem siLabel2 = new StringItem("",rec.teacher );
     //   StringItem siLabel3 = new StringItem("\n", sTime + "   " + rec.room +"\n");
        siLabel.setItemCommandListener(this);
    //    resForm.append(siLabel3);
        resForm.append(siLabel);
    //    resForm.append(siLabel2);
    }
*/
    
    public void SetLectures(Vector lectures)
    {
        Form LecturesForm = new Form("Лекции");
        for (int j = 0; j< lectures.size()+ 1; j++)
        {
            try
            {
                Lecture Sc = (Lecture)lectures.elementAt(j);

                LecturesForm.append(Sc.getName()+"\n");
            }
            catch (Exception exc)
            {
            }
        }

        LecturesForm.append(net.Version());
        Display.getDisplay(this).setCurrent(LecturesForm);
    }

    public void commandAction(Command cmd, Item i)
    {
        if(cmd.getLabel().equals("Применить"))
        {
            resForm = new Form("Рассписание");
          
            
            String sDepartment = st[myChoiceGroup.getSelectedIndex()];
            String sGroup = sGroups[choiceGroups.getSelectedIndex()];

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
            StringItem siLabel4 = new StringItem("Просматриваеммый день:",sSeeData );
            StringItem siLabel5 = new StringItem("\n","" );




            
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
            

            resForm.append(siLabel1);
            resForm.append(siLabel2);
            resForm.append(siLabel3);
            resForm.append(siLabel4);
            resForm.append(siLabel5);
            resForm.setCommandListener(this);

            /*
            TableFactory factory = new TestTableFactory();
            ScheduleTable scht =  factory.getScheduleTable();
            Vector vSchedule ;
            try
            {
                vSchedule = scht.getAll();
            }
            catch (Exception exc)
            {
                vSchedule = new Vector();
            }
            for (int j = 0; j< vSchedule.size()+ 1; j++)
            {
                try
                {
                    Schedule Sc = scht.get(j);
                    if ((Sc.getGroupID() == choiceGroups.getSelectedIndex())&&(d.get(Calendar.DAY_OF_WEEK)==((Sc.getDay()+1) % 7)))
                    {
                        append_record(Sc);
                    }
                }
                catch (Exception exc)
                {
                }
            }
             * 
             */
            
            Display.getDisplay(this).setCurrent(resForm);
        }


        if (cmd.getLabel().equals("Доступные лекции"))
        {
            //запускаем класс Networker

            net.request_all_lectures();

        
        }

        if (cmd.getLabel().equals("Выход"))
        {
            destroyApp(false);
            notifyDestroyed();
        }

        if (cmd.getLabel().equals("Настройки")){
            Display.getDisplay(this).setCurrent(settingsForm);
        }
        if (cmd.getLabel().equals("Подробнее"))
        {
           infoForm = new Form("Инфо о предмете");
           StringItem siLabel2 = new StringItem("","Информация о предмете" );
           siLabel2.setDefaultCommand(new Command("Назад", Command.BACK, 1));
           siLabel2.setItemCommandListener(this);
           infoForm.append(siLabel2);
           Display.getDisplay(this).setCurrent(infoForm);
        }

        if (cmd.getLabel().equals("Назад")){
           Display.getDisplay(this).setCurrent(resForm);
        }
    }
}
