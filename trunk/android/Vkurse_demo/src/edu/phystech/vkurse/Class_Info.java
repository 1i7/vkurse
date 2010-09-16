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
	
	/*String test[] ={"lorem", "ipsum", "dolor", "sit", "amet",
			"consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis",
			"etiam", "vel", "erat", "placerat", "ante",
			"porttitor", "sodales", "pellentesque", "augue", "purus"};*/
	String sGroups[];
	int Ident[];
	
	TableFactory factory = new TestTableFactory();
    ScheduleTable scht =  factory.getScheduleTable();
    GroupsTable gt =  factory.getGroupsTable();
    Vector vSchedule ;
    Vector vGroups;
    
    class tLecture{
    	public String name;
    	public int start;
    	public int length;
    };
    tLecture items[];
    
    class infoLecture{
    	public String name ;
    	public String room;
    	public String teacher;
    	public int start;
    	public int length;
    };
    infoLecture Lecture[];
    
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
	            Lecture = new infoLecture[vSchedule.size()];
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
	                	Lecture[k].name = factory.getLecturesTable().get(Sc.getLectureID()).getName();
	 	                Lecture[k].start = Sc.getStartTime();
	 	                Lecture[k].length = Sc.getLength();
	 	                Lecture[k].room = factory.getRoomsTable().get(Sc.getRoomID()).getName();
	 	                Lecture[k].teacher = factory.getTeachersTable().get(Sc.getLectureID()).getName();
	                }
	            
	            }
	            catch (Exception exc)
	            {
	            }
	        } 
	        
	        String thisLecture[]={Lecture[num].name, Lecture[num].room, Lecture[num].teacher};
	        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thisLecture));
	        
	       // TextView text = (TextView)findViewById(R.id.TextView01);
	        //text.setText(new StringBuilder() .append(Lecture[0].name));
    }
}