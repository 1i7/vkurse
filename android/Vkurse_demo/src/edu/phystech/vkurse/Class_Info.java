package edu.phystech.vkurse;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.app.ListActivity;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



import edu.phystech.vkurse.model.*;

import java.util.*;

import edu.phystech.vkurse.test.*;

public class Class_Info  extends ListActivity {
	
	
	String sGroups[];
	int Ident[];
	
	Handler handler = new Handler();
	TableFactory factory = new TestTableFactory();
    ScheduleTable scht =  factory.getScheduleTable();
    GroupsTable gt =  factory.getGroupsTable();
    Vector vSchedule ;
    Vector vGroups;
    
   
    
    String LectureName[];
    String LectureTeacher[];
    String LectureRoom[];
    String Form[];
    String TypeOfLecture[];
    int LectureStart[];
    int LectureLenght[];
    
    
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_PROGRESS);
			setContentView(R.layout.class_info);
			
			Intent myInt = getIntent();
			int j = myInt.getIntExtra("from_spin_again", 0);
	        //int num = myInt.getIntExtra("from_list",0);
	        byte day = myInt.getByteExtra("day", (byte)0);
	        
	        getScheduleAnync();
	        
	        Groups factory = new Groups();
	        Vector<Group> vGroups ;
	        
	        try
	        {
	             vGroups = factory.getAll();
	             sGroups = new String[vGroups.size()];
	             Ident = new int[vGroups.size()];
	             
	        } 
	        catch (Exception exc)
	        {
	            vGroups = new Vector();
	        }
	        for (int i = 0; i< vGroups.size()+ 1; i++)
	        {
	           try
	           {
	        	   Group g = vGroups.elementAt(i);
		           sGroups[i] = g.getName();
		           Ident[i] = g.getID();
	           }
	           catch (Exception exc)
	           {
	           }
	        }
	        
	        String days[]={"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" };
	       	TextView text1 = (TextView)findViewById(R.id.text1);
	        text1.setText(new StringBuilder() .append(days[day]));
	        TextView text2 = (TextView)findViewById(R.id.text2);
	        text2.setText(new StringBuilder() .append(sGroups[j]+" группа"));
	}
	   
	void getScheduleAnync() {
	    final Thread thread = new Thread() {
	        @Override
	        public void run() {
	            // получить расписание по сети (займет долгое время)
	            final String[] schedule = getInformation();
	            handler.post(new Runnable() {
	                @Override
	                public void run() {
	                    // убрать прогресс-бар из заголовка
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_OFF);
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	                    // показать информацию о расписании в интерфейсе
	                    displayInfoInGUI(schedule);
	                }
	            });
	        }
	    };

	    // показать прогресс-бар в заголовке:
	    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
	    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	    // запустить поток
	    thread.start();
	}
	void displayInfoInGUI(String[] schedule){
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule));
	}
	private String[] getInformation()
	{ 
			Intent myInt = getIntent();
			int j = myInt.getIntExtra("from_spin_again", 0);
			int num = myInt.getIntExtra("from_list",0);
			byte day = myInt.getByteExtra("day", (byte)0);
	        Groups factory = new Groups();
	        Vector<Group> vGroups ;
	        
	        try
	        {
	             vGroups = factory.getAll();
	             sGroups = new String[vGroups.size()];
	             Ident = new int[vGroups.size()];
	             
	        } 
	        catch (Exception exc)
	        {
	            vGroups = new Vector();
	        }
	        for (int i = 0; i< vGroups.size()+ 1; i++)
	        {
	           try
	           {
	        	   Group g = vGroups.elementAt(i);
		           sGroups[i] = g.getName();
		           Ident[i] = g.getID();
	           }
	           catch (Exception exc)
	           {
	           }
	        }
	        
	        Schedules scht = new Schedules();
	        Vector<Schedule> vSchedule;
	        Lectures lct = new Lectures();
	        ExamType Exam = new ExamType();
	        Vector<ExamType> Ex;
	        Lecture Lect;
	        
	       try
	        {
	            vSchedule = scht.findByGroupDay(Ident[j], (byte)(day));
	            LectureName = new String[vSchedule.size()];
	            LectureStart = new int[vSchedule.size()];
	            LectureLenght = new int[vSchedule.size()];
	            LectureRoom = new String[vSchedule.size()];
	            LectureTeacher = new String[vSchedule.size()];
	            Form = new String[vSchedule.size()];
	            TypeOfLecture = new String[vSchedule.size()];
	        }
	        catch (Exception exc)
	        {
	            vSchedule = new Vector();
	        }
	        for (int k = 0; k< vSchedule.size()+ 1; k++)
	        {
	            try
	            {
	            	Schedule Sc = vSchedule.elementAt(k);
	            	Lect = lct.get(Sc.getLectureID());
	            	
	            	if (Lect.getName() != null)
	            	{
	            		LectureName[k] = Lect.getName();
	            		LectureStart[k] = Sc.getStartTime();
	            		LectureLenght[k] = Sc.getLength();
	            		//TypeOfLecture[k] = 
	            	}
	            	else
	                {
	            		LectureName[k] = "emtpy";
	            		LectureStart[k] = 00;
	            		LectureLenght[k] = 00;
	                }
	                Teacher teach ;
	                Teachers TeacherTable = new Teachers();
	                teach = TeacherTable.get(Sc.getTeacherID());
	                if (teach.getName() != null)
	                {
	                	LectureTeacher[k] = teach.getName();
	                }
	                else
	                {
	                	LectureTeacher[k] = "emtpy";
	                }
	                Room room;
	                Rooms roomTable = new Rooms();
	                room = roomTable.get(Sc.getRoomID());
	                if(room.getName() != null)
	                {
	                	LectureRoom[k] = room.getName();
	                }
	                else
	                {
	                	LectureRoom[k] = "emtpy";
	                }
	               
	            }
	                
	              
	            catch (Exception exc)
	            {
	            }
	            
	            
	        }
	        
	       if(LectureName.length != 0)
	       {
	    	   
	            if (LectureStart[num]%60<10 && (LectureStart[num]+LectureLenght[num])%60<10){
	            	String thisLecture[]={LectureStart[num]/60+":0"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":0"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num]};
	            	return thisLecture;
	            }
	            else if(LectureStart[num]%60<10)
	            {
	            	String thisLecture[]={LectureStart[num]/60+":0"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num]};
	            	return thisLecture;
	            }
	            else if ((LectureStart[num]+LectureLenght[num])%60<10)
	            {
	            	String thisLecture[]={LectureStart[num]/60+":"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":0"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num]};
	            	return thisLecture;
	            }
	            else
	            {
	            	String thisLecture[]={LectureStart[num]/60+":"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num]};
	            	return thisLecture;
	            }
		        
	       }else
	       {
	    	   String thisLecture[]={"empty"};
	    	  // setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thisLecture));
	    	   return thisLecture;
	       }
	       
	  } 	
    
}