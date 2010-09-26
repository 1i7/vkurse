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
	        int day = myInt.getIntExtra("day", 0);
	        
	        
	        try
	        {
	             vGroups = gt.getAll();
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
		           Group g = gt.get(i);
		           sGroups[i] = g.getName();
		           Ident[i] = g.getID();
	           }
	           catch (Exception exc)
	           {
	           }
	        }
	        
	        
	       try
	        {
	            vSchedule = scht.getAll();
	            LectureName = new String[vSchedule.size()];
	            LectureTeacher = new String[vSchedule.size()];
	            LectureRoom = new String[vSchedule.size()];
	            LectureStart = new int[vSchedule.size()];
	            LectureLenght = new int[vSchedule.size()];
	        }
	        catch (Exception exc)
	        {
	            vSchedule = new Vector();
	        }
	        for (int k = 0; k< vSchedule.size()+ 1; k++)
	        {
	            try
	            {
	                Schedule Sc = scht.get(k);
	                if ((Sc.getGroupID() == Ident[j])&&(day==((Sc.getDay()+1) % 7)))
	                {
	                	LectureName[k] = factory.getLecturesTable().get(Sc.getLectureID()).getName();
	 	                LectureStart[k] = Sc.getStartTime();
	 	                LectureLenght[k] = Sc.getLength();
	 	                LectureRoom[k] = factory.getRoomsTable().get(Sc.getRoomID()).getName();
	 	                LectureTeacher[k] = factory.getTeachersTable().get(Sc.getLectureID()).getName();
	                }
	            
	            }
	            catch (Exception exc)
	            {
	            }
	        } 
	        
	       // LectureName[num] = "Empty";
	       // LectureRoom[num] = "Empty";
	       // LectureTeacher[num] = "Empty";
	        String thisLecture[]={LectureName[num], LectureRoom[num], LectureTeacher[num]};
	        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thisLecture));
	        
	       // TextView text = (TextView)findViewById(R.id.TextView01);
	        //text.setText(new StringBuilder() .append(Lecture[0].name));
    }
}