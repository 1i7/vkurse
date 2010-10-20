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
import android.widget.DatePicker;
import android.widget.Spinner;
import android.app.Dialog;
import android.app.DatePickerDialog;
import java.util.Calendar;
import java.util.Vector;

import edu.phystech.vkurse.model.*;

import java.util.*;
import edu.phystech.vkurse.test.*;

public class Schedule_Activity extends ListActivity implements OnClickListener{
    
	private TextView DateDisplay;
    private Button PickDate;
    private View ChangeGroup;
    private int Year;
    private int Month;
    private int Day;
    private byte WeekDay;
    String sGroups[];
    
    String Answer[];
    String LectureName[];
    int LectureStart[];
    int LectureLenght[];
    
    int Ident[];
    
    static final int DATE_DIALOG_ID = 0;
    
    
   
    
    /** Called when the activity is first created. */ 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
    
        
        // get the current date
        final Calendar cl = Calendar.getInstance();
        Year = cl.get(Calendar.YEAR);
        Month = cl.get(Calendar.MONTH);
        Day = cl.get(Calendar.DAY_OF_MONTH);
        WeekDay = (byte)((cl.get(Calendar.DAY_OF_WEEK)+6)%7);

        
        DateDisplay = (TextView) findViewById(R.id.dateDisplay);
        PickDate = (Button) findViewById(R.id.pickDate);
        ChangeGroup = findViewById(R.id.changeGroup);
        
           
        // add a click listener to the button
        ChangeGroup.setOnClickListener(this);
        PickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
        
        // display the current date (this method is below)
        updateDisplay();
        
        getSchedule(WeekDay);
        
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
        
        
        
        Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
	
       TextView GroupDisplay = (TextView) findViewById(R.id.groupDisplay);
       GroupDisplay.setText(new StringBuilder().append(sGroups[j]).append(" group"));
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id)
	{
		Intent i = new Intent(this, Class_Info.class);
		
		
		
		Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
    	ListView list = (ListView) findViewById(android.R.id.list);
    	i.putExtra("from_list",list.getCheckedItemPosition());
    	i.putExtra("day", (byte)(WeekDay));
    	i.putExtra("from_spin_again",j);
    	
    	startActivity(i);
	}
	//get information from DB and load ListView
	private void getSchedule(byte weekDay){
		Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
        
        
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
            vSchedule = scht.findByGroupDay(Ident[j], (byte)(weekDay));
           
            Answer = new String[vSchedule.size()];
            LectureName = new String[vSchedule.size()];
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
	            	
	            	if (vSchedule.isEmpty())
	            	{
	            		
	                    Answer[k]= "empty";
	            	}
	            	else
	            	{
	            		
	                    Schedule Sc = vSchedule.elementAt(k);
			            Lect = lct.get(Sc.getLectureID());
			            if (Lect.getName() != null)
			            {
				            LectureName[k] = Lect.getName();
				            LectureStart[k] = Sc.getStartTime();
				            LectureLenght[k] = Sc.getLength();
				            Answer[k]= LectureStart[k] +" "+LectureName[k];
		            	}
		            	else
		                {
		                    LectureStart[k] = 00;
		                    LectureName[k] = " Empty";
		                    	
		                    Answer[k]= LectureStart[k] +LectureName[k];
		                }
	            	}
	            }
        	 
                
            
        
            catch (Exception exc)
            {
            	
            }
        }
            
            
        
       if ( Answer.length!=0 )
       {
        	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Answer));
        	getListView().setTextFilterEnabled(true);
        }
       else
       {
    	   Answer = new String[4];
    	   for(int n=0; n<4; n++)
    	   {
    		   Answer[n]="empty";
    	   }
    	   setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Answer));
       		getListView().setTextFilterEnabled(true);
       }
	}
	
	// updates the date in the TextView
    private void updateDisplay() {
        DateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(Day).append("-")
                    .append(Month + 1).append("-")
                    .append(Year).append(", ")
                    .append(WeekDay));
        getSchedule((byte)(WeekDay));
    }
    
    private DatePickerDialog.OnDateSetListener DateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    Year = year;
                    Month = monthOfYear;
                    Day = dayOfMonth;
                    final Calendar cl = Calendar.getInstance();
                    cl.set(year, monthOfYear, dayOfMonth);
                    WeekDay = (byte)((cl.get(Calendar.DAY_OF_WEEK)+6)%7);
                    updateDisplay();
                    
                }
            };
           
  	   
    @Override
    protected Dialog onCreateDialog(int id) {
          switch (id) {
              case DATE_DIALOG_ID:
                   return new DatePickerDialog(this,
                                DateSetListener,
                                Year, Month, Day);
              	            	  
          }
					  	 
          return null;
    }
    
    public void onClick (View v)
    {  	
    	if(v == ChangeGroup)
    	{Intent i = new Intent(this, Change_Group.class);

    	startActivity(i);}
    }
}