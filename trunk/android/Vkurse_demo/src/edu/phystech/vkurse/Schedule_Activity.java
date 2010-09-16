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
    private int WeekDay;
    String sGroups[];
    String Names[] ;
    int Ident[];
    
    static final int DATE_DIALOG_ID = 0;
    
    
    TableFactory factory = new TestTableFactory();
    ScheduleTable scht =  factory.getScheduleTable();
    Vector vSchedule ;
    
    class tLecture{
    	public String name;
    	public int start;
    	public int length;
    };
    tLecture items[];
    
    
    /** Called when the activity is first created. */ 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
    
        
        // get the current date
        final Calendar cl = Calendar.getInstance();
        Year = cl.get(Calendar.YEAR);
        Month = cl.get(Calendar.MONTH);
        Day = cl.get(Calendar.DAY_OF_MONTH);
        WeekDay = cl.get(Calendar.DAY_OF_WEEK);

        
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
        
        Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
        //TableFactory factory = new TestTableFactory();
        GroupsTable gt =  factory.getGroupsTable();
        Vector vGroups;
        
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
            Names = new String[vSchedule.size()];
            items = new tLecture[vSchedule.size()];
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
                if ((Sc.getGroupID() == Ident[j])&&(cl.get(Calendar.DAY_OF_WEEK)==((Sc.getDay()+1) % 7)))
                {
                    //items[k].name = factory.getLecturesTable().get(Sc.getLectureID()).getName();
                    //items[k].start = Sc.getStartTime();
                    //items[k].length = Sc.getLength();
                   // Names[k] = factory.getLecturesTable().get(Sc.getLectureID()).getName();
                	
                	
                	Names[k]="Пусто";
                }
                else
                {
                	
                	Names[k]="Пусто";
                }
            }
            catch (Exception exc)
            {
            }
            
            
        }
       if ( Names[0]!= null )
       {
        	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Names));
        	getListView().setTextFilterEnabled(true);
        }
	
        
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id)
	{
		Intent i = new Intent(this, Class_Info.class);
		
		final Calendar c = Calendar.getInstance();
		
		Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
    	ListView list = (ListView) findViewById(android.R.id.list);
    	i.putExtra("from_list",list.getCheckedItemPosition());
    	i.putExtra("day", c.get(Calendar.DAY_OF_WEEK));
    	i.putExtra("from_spin_again",j);
    	
    	startActivity(i);
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
    }
    
    private DatePickerDialog.OnDateSetListener DateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    Year = year;
                    Month = monthOfYear;
                    Day = dayOfMonth;
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