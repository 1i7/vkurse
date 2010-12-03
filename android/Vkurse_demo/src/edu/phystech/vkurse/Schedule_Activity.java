package edu.phystech.vkurse;

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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import edu.phystech.vkurse.model.Group;
import edu.phystech.vkurse.model.Lecture;
import edu.phystech.vkurse.model.Room;
import edu.phystech.vkurse.model.Schedule;
import android.util.Log;

public class Schedule_Activity extends ListActivity{
    
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
    
    
    private ArrayList<ScheduleItem> m_orders = null;
    private ScheduleItemAdapter m_adapter;
   
    
    
    /** Called when the activity is first created. */ 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.schedule_activity);
    
        
        // get the current date
        final Calendar cl = Calendar.getInstance();
        Year = cl.get(Calendar.YEAR);
        Month = cl.get(Calendar.MONTH);
        Day = cl.get(Calendar.DAY_OF_MONTH);
        WeekDay = (byte)((cl.get(Calendar.DAY_OF_WEEK)+6)%7);

        
        DateDisplay = (TextView) findViewById(R.id.dateDisplay);     
        
        
        // display the current date (this method is below)
        updateDisplay();
        
        getScheduleAnync(WeekDay);
        
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
        if (sGroups.length==0){
        	sGroups[0]="empty";
        }
        
        
        Intent myInt = getIntent();
        int j = myInt.getIntExtra("from_spin",0);
	
       TextView GroupDisplay = (TextView) findViewById(R.id.groupDisplay);
       GroupDisplay.setText(new StringBuilder().append(sGroups[j]).append(" группа"));
	}
	private void getData(ScheduleItem[] Answer)
	{
		try{
			if(Answer.length!=0){
				for (int i=0; i<Answer.length+1;i++)
				{
					m_orders.add(Answer[i]);
				}
			}
            Log.i("ARRAY", ""+ m_orders.size());
          } catch (Exception e) { 
            Log.e("BACKGROUND_PROC", e.getMessage());
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
            vSchedule = scht.findByGroupDay(Ident[j], (byte)(5));
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
			           // if (Lect.getName() != null)
			            Room room;
		                Rooms roomTable = new Rooms();
		                room = roomTable.get(Sc.getRoomID());
		                	LectureRoom[k] = room.getName();
				            LectureName[k] = Lect.getName();
				            LectureStart[k] = Sc.getStartTime();
				            LectureLenght[k] = Sc.getLength();
				            if (LectureStart[k]%60<10 && (LectureStart[k]+LectureLenght[k])%60<10){
				            	Answer[k]= LectureStart[k]/60+":0"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":0"+(LectureStart[k]+LectureLenght[k])%60+" "+LectureName[k];
				            }
				            else if(LectureStart[k]%60<10)
				            {
				            	Answer[k]= LectureStart[k]/60+":0"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":"+(LectureStart[k]+LectureLenght[k])%60+" "+LectureName[k];
				            }
				            else if ((LectureStart[k]+LectureLenght[k])%60<10)
				            {
				            	Answer[k]= LectureStart[k]/60+":"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":0"+(LectureStart[k]+LectureLenght[k])%60+" "+LectureName[k];
				            }
				            else
				            {
				            	Answer[k]= LectureStart[k]/60+":"+LectureStart[k]%60+"-"+ (LectureStart[k]+LectureLenght[k])/60+":"+(LectureStart[k]+LectureLenght[k])%60+" "+LectureName[k];
				            }
				            if(LectureRoom[k]!=null){
				            	ScheduleItem o = new ScheduleItem();
				            	
				                o.setListRight(LectureRoom[k]);
				                o.setListLeft(Answer[k]);
				                Order[k]=o;}
				                		           
				            	            	
	            	}
        	 
                
            
        
            catch (Exception exc)
            {
            	
            }
        }}
            
        return Order;    
        
       
	}
	
	void displayScheduleInGUI(final ScheduleItem[] Answer){
		
		m_orders = new ArrayList<ScheduleItem>();
		getData(Answer);
        this.m_adapter = new ScheduleItemAdapter(this, R.layout.row, m_orders);
        setListAdapter(this.m_adapter);
        
        
	//setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Answer));
	        	getListView().setTextFilterEnabled(true);
	      
	}
	
	void getScheduleAnync(final byte weekDay) {
	    final Thread thread = new Thread() {
	        @Override
	        public void run() {
	            // получить расписание по сети (займет долгое время)
	            final ScheduleItem[] schedule = getSchedule(weekDay);
	            handler.post(new Runnable() {
	                @Override
	                public void run() {
	                    // убрать прогресс-бар из заголовка
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_OFF);
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	                    // показать информацию о расписании в интерфейсе
	                    displayScheduleInGUI(schedule);
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
	// updates the date in the TextView
    private void updateDisplay() {
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
                    TextView rt = (TextView) v.findViewById(R.id.righttext);
                    if (lt != null) {
                          lt.setText(o.getListLeft());                            }
                    if(rt != null){
                          rt.setText(o.getListRight());
                    }
            }
                return v;
        }
}
}