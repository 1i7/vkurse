package edu.phystech.vkurse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import edu.phystech.vkurse.model.Group;
import edu.phystech.vkurse.model.Lecture;
import edu.phystech.vkurse.model.Room;
import edu.phystech.vkurse.model.Schedule;


public class Schedule_Activity extends ListActivity implements OnClickListener{
    
	private TextView DateDisplay;
    private int Year;
    private int Month;
    private int Day;
    private byte WeekDay;
    String sGroups[];
    String Answer[];
    String LectureName[];
    int LectureStart[];
    int LectureLenght[];
    String LectureRoom[];
    ScheduleItem[] Order;
    int Ident[];
    Handler handler = new Handler();
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID1 = 1;
    
    int gr;
    int dt=-1;
    int year;
    int mnth;
    int day;
    
    
    private ArrayList<ScheduleItem> m_orders = null;
    private ScheduleItemAdapter m_adapter;
   
    View groupButton;
    View renewButton;
    
    
    
    /** Called when the activity is first created. */ 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        
     // getScheduleAnync(WeekDay);
        final Calendar cl = Calendar.getInstance();
	        Year = cl.get(Calendar.YEAR);
	        Month = cl.get(Calendar.MONTH);
	        Day = cl.get(Calendar.DAY_OF_MONTH);
	        WeekDay = (byte)((cl.get(Calendar.DAY_OF_WEEK)+6)%7);
	        
	        dt = WeekDay;
	        day = Day;
	        mnth = Month;
	        year = Year;
 	  
	}
	private void getData(ScheduleItem[] Answer)
	{
		if(Answer != null)
		{
    for (int i = 0; i < Answer.length; i++) {
        m_orders.add(Answer[i]);}
    }
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
	private ScheduleItem[] getSchedule(byte weekDay){
	
		Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
        int date = myInt.getIntExtra("date",-1);
        
        if(date!=-1)
        	weekDay=(byte)date;
        gr = j;
        String FILENAME = "work_file.txt";
       // String string = "hello world!";

        FileOutputStream fos;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			try {
				fos.write(j);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
            vGroups = new Vector<Group>();
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
        if(sGroups==null)
        {
        	return null;
        }
        else
        {
        Schedules scht = new Schedules();
        Vector<Schedule> vSchedule;
        Lectures lct = new Lectures();
        Lecture Lect;
        if(scht== null){
        	Answer = new String[1];
	    	   Answer[0]="!!!";
        }
        else{
       try
        {
            vSchedule = scht.findByGroupDay(Ident[j], (byte)(weekDay));
            LectureRoom = new String[vSchedule.size()];
            Order = new ScheduleItem[vSchedule.size()];
            Answer = new String[vSchedule.size()];
            LectureName = new String[vSchedule.size()];
            LectureStart = new int[vSchedule.size()];
            LectureLenght = new int[vSchedule.size()];
        }
        catch (Exception exc)
        {
            vSchedule = new Vector<Schedule>();
        }
        for (int k = 0; k< vSchedule.size()+ 1; k++)
        {
	            try
	            {
	            		
	                    Schedule Sc = vSchedule.elementAt(k);
			            Lect = lct.get(Sc.getLectureID());
			           
			            Room room;
		                Rooms roomTable = new Rooms();
		                room = roomTable.get(Sc.getRoomID());
		                	LectureRoom[k] = room.getName();
				            LectureName[k] = Lect.getName();
				            LectureStart[k] = Sc.getStartTime();
				            LectureLenght[k] = Sc.getLength();
				            if (LectureStart[k]%60<10 && (LectureStart[k]+LectureLenght[k])%60<10){
				            	Answer[k]= LectureStart[k]/60+":0"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":0"+(LectureStart[k]+LectureLenght[k])%60;
				            }
				            else if(LectureStart[k]%60<10)
				            {
				            	Answer[k]= LectureStart[k]/60+":0"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":"+(LectureStart[k]+LectureLenght[k])%60;
				            }
				            else if ((LectureStart[k]+LectureLenght[k])%60<10)
				            {
				            	Answer[k]= LectureStart[k]/60+":"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":0"+(LectureStart[k]+LectureLenght[k])%60;
				            }
				            else
				            {
				            	Answer[k]= LectureStart[k]/60+":"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":"+(LectureStart[k]+LectureLenght[k])%60;
				            }
				            if(LectureRoom[k]!=null){
				            	ScheduleItem o = new ScheduleItem();
				            	
				                o.setListRight(LectureRoom[k]);
				                o.setListLeft(Answer[k]);
				                o.setListLeft1(LectureName[k]);
				                Order[k]=o;}
				                		           
				            	            	
	            	}
        	 
                
            
        
            catch (Exception exc)
            {
            	
            }
        }}
            
        return Order;    
        }
       
	}
	
	void displayScheduleInGUI(final ScheduleItem[] Answer){
		
		m_orders = new ArrayList<ScheduleItem>();
		if(Answer != null)
		{
		getData(Answer);
        this.m_adapter = new ScheduleItemAdapter(this, R.layout.row, m_orders);
        setListAdapter(this.m_adapter);
        
        
	//setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Answer));
	        	getListView().setTextFilterEnabled(true);
		}
	      
	}
	
	void getScheduleAnync(final byte weekDay) {
	    final Thread thread = new Thread() {
	        @Override
	        public void run() {
	            
	            final ScheduleItem[] schedule = getSchedule(weekDay);
	            handler.post(new Runnable() {
	                @Override
	                public void run() {
	                    
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_OFF);
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	                    
	                    displayScheduleInGUI(schedule);
	                }
	            });
	        }
	    };

	    
	    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
	    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	    
	    thread.start();
	}
	// updates the date in the TextView
    private void updateDisplay() {
    	Intent myInt = getIntent();
    	int d = myInt.getIntExtra("day_",-1);
        int m = myInt.getIntExtra("mnth",-1);
        int y = myInt.getIntExtra("year",-1);
        int dow = myInt.getIntExtra("date",-1);
        if(d!=-1)
        	Day = d;
        if(m!=-1)
        	Month = m;
        if(y!=-1)
        	Year = y;
        if(dow!=-1)
        	WeekDay = (byte)dow;
    	String days[]={"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" };
        DateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(Day).append("-")
                    .append(Month + 1).append("-")
                    .append(Year).append(", ")
                    .append(days[WeekDay]));
        getScheduleAnync((byte)(WeekDay));
    }
    
    private void updateDisplay_() {
    	Intent myInt = getIntent();
    	int d = myInt.getIntExtra("day_",-1);
        int m = myInt.getIntExtra("mnth",-1);
        int y = myInt.getIntExtra("year",-1);
        int dow = myInt.getIntExtra("date",-1);
        if(d!=-1)
        	Day = d;
        if(m!=-1)
        	Month = m;
        if(y!=-1)
        	Year = y;
        if(dow!=-1)
        	WeekDay = (byte)dow;
    	String days[]={"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" };
        DateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(Day).append("-")
                    .append(Month + 1).append("-")
                    .append(Year).append(", ")
                    .append(days[WeekDay]));
        
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
                    dt = WeekDay;
                    day=Day;
                    mnth= Month;
                    year = Year;
                    updateDisplay();
                    
                    
                }
            };
            private DatePickerDialog.OnDateSetListener DateSetListener1 =
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, 
                                          int monthOfYear, int dayOfMonth) {
                        Year = year;
                        Month = monthOfYear;
                        Day = dayOfMonth;
                        final Calendar cl = Calendar.getInstance();
                        cl.set(year, monthOfYear, dayOfMonth);
                        WeekDay = (byte)((cl.get(Calendar.DAY_OF_WEEK)+6)%7);
                        dt = WeekDay;
                        day=Day;
                        mnth= Month;
                        year = Year;
                        updateDisplay_();
                        
                        
                    }
                };
           
  	   
    @Override
    protected Dialog onCreateDialog(int id) {
          switch (id) {
              case DATE_DIALOG_ID:
                   return new DatePickerDialog(this,
                                DateSetListener,
                                Year, Month, Day);
              case DATE_DIALOG_ID1:
                  return new DatePickerDialog(this,
                               DateSetListener1,
                               Year, Month, Day);
              	            	  
          }
					  	 
          return null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.icon_menu, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.changeGroup:
        	Intent i = new Intent(this, Change_Group.class);
        	startActivity(i);
            return true;
        case R.id.changeDate:
        	showDialog(DATE_DIALOG_ID);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private class ScheduleItemAdapter extends ArrayAdapter<ScheduleItem> {

        private ArrayList<ScheduleItem> items;

        public ScheduleItemAdapter(Context context, int textViewResourceId, ArrayList<ScheduleItem> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.row, null);
                }
                ScheduleItem o = items.get(position);
                if (o != null) {
                    TextView lt = (TextView) v.findViewById(R.id.lefttext);
                    TextView lt1 = (TextView) v.findViewById(R.id.lefttext1);
                    TextView rt = (TextView) v.findViewById(R.id.righttext);
                    if (lt != null) {
                          lt.setText(o.getListLeft());                            }
                    if (lt1!= null) {
                    	lt1.setText(o.getListLeft1());
                    	}
                    if(rt != null){
                          rt.setText(o.getListRight());
                    }
            }
                return v;
        }
}
    public void onClick(View v)
    {
    	if (v==groupButton)
    	{
    		Intent i = new Intent(this, Change_Group.class);
			startActivity(i);
    	}
    	if(v==renewButton)
		{
			Intent j = new Intent(this, Schedule_Activity.class);
			j.putExtra("from_spin",gr);
			j.putExtra("date", dt);
			j.putExtra("year", year);
			j.putExtra("mnth", mnth);
			j.putExtra("day_", day);
			startActivity(j);
		}
    	
    }
   protected void onResume()
    {
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
           vGroups = new Vector<Group>();
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
       
       
       
    	String FILENAME = "work_file.txt";
        int data=-1;

         FileInputStream fos;
 		try {
 			fos = openFileInput(FILENAME);
 			try {
 				data = fos.read();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	        try {
 				fos.close();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		} catch (FileNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
 		if (sGroups==null)
 		{
 			setContentView(R.layout.schedule_activity);
 		    
 			TextView text = (TextView) findViewById(R.id.info);
            text.setText(R.string.info1);
 	        groupButton = findViewById(R.id.gr);
 	        groupButton.setOnClickListener(this);
 	    
 	        View dateButton = findViewById(R.id.dt);
 	        dateButton.setOnClickListener(this);
 	        
 	        dateButton.setOnClickListener(new View.OnClickListener() {
 	            public void onClick(View v) {
 	                showDialog(DATE_DIALOG_ID1);
 	            }
 	        });
 	        
 	        renewButton = findViewById(R.id.but);
 	        renewButton.setOnClickListener(this);
 	        
 	        
 	        // get the current date
 	        final Calendar cl = Calendar.getInstance();
 	        Year = cl.get(Calendar.YEAR);
 	        Month = cl.get(Calendar.MONTH);
 	        Day = cl.get(Calendar.DAY_OF_MONTH);
 	        WeekDay = (byte)((cl.get(Calendar.DAY_OF_WEEK)+6)%7);
 	        
 	        dt = WeekDay;
 	        day = Day;
 	        mnth = Month;
 	        year = Year;

 	        
 	        DateDisplay = (TextView) findViewById(R.id.dateDisplay);     
 	        
 	        
 	        
 	       updateDisplay_();
	        
 	       
 	      
 		}
 		else
 		{
	 		if(data == -1)
	 		{
	 			Intent i = new Intent(this, Welcome_Page.class);
			   
		    	startActivity(i);
	 		}
	 			
	 		else
	 		{
	 			setContentView(R.layout.schedule_activity);
	 		    
	 			TextView text = (TextView) findViewById(R.id.info);
	            text.setText(R.string.info);
	 	        groupButton = findViewById(R.id.gr);
	 	        groupButton.setOnClickListener(this);
	 	    
	 	        View dateButton = findViewById(R.id.dt);
	 	        dateButton.setOnClickListener(this);
	 	        
	 	        dateButton.setOnClickListener(new View.OnClickListener() {
	 	            public void onClick(View v) {
	 	                showDialog(DATE_DIALOG_ID);
	 	            }
	 	        });
	 	        
	 	        renewButton = findViewById(R.id.but);
	 	        renewButton.setOnClickListener(this);
	 	        
	 	        
	 	        // get the current date
	 	       

	 	        
	 	        DateDisplay = (TextView) findViewById(R.id.dateDisplay);     
	 	        
	 	        
	 	        
	 	        // display the current date (this method is below)
	 	        updateDisplay();
	 	        
	 	        getScheduleAnync(WeekDay);
	 	        
	 	       
	 	        
	 	        
	 	        Intent myInt = getIntent();
	 	        int j = myInt.getIntExtra("from_spin",0);
	 		
	 	       TextView GroupDisplay = (TextView) findViewById(R.id.groupDisplay);
	 	       GroupDisplay.setText(new StringBuilder().append(sGroups[j]).append(" группа"));
	 		}
 		}
 		super.onResume();
    }
}