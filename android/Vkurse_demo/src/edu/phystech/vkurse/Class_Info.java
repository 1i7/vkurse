package edu.phystech.vkurse;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
	
	TableFactory factory = new TestTableFactory();
    ScheduleTable scht =  factory.getScheduleTable();
    GroupsTable gt =  factory.getGroupsTable();
    Vector vSchedule ;
    Vector vGroups;
    
   
    
    String LectureName[];
    String LectureTeacher[];
    String LectureRoom[];
    int LectureStart[];
    int LectureLenght[];
    
    
	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.class_info);
			
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
	        Lecture Lect;
	        
	       try
	        {
	            vSchedule = scht.findByGroupDay(Ident[j], (byte)(day));
	            LectureName = new String[vSchedule.size()];
	            LectureStart = new int[vSchedule.size()];
	            LectureLenght = new int[vSchedule.size()];
	            LectureRoom = new String[vSchedule.size()];
	            LectureTeacher = new String[vSchedule.size()];
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
		        String thisLecture[]={LectureName[num], LectureRoom[num], LectureTeacher[num]};
		        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thisLecture));
		        
	       }else
	       {
	    	   String thisLecture[]={"empty"};
	    	   setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thisLecture));
	       }
	       // TextView text = (TextView)findViewById(R.id.TextView01);
	        //text.setText(new StringBuilder() .append(Lecture[0].name));
    }
}