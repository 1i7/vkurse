package edu.phystech.vkurse;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.app.ListActivity;
import android.widget.TextView;
import android.widget.ArrayAdapter;


import edu.phystech.vkurse.model.*;

import java.util.*;

import edu.phystech.vkurse.test.*;

public class Class_Info  extends ListActivity {
	
	
	String sGroups[];
	int Ident[];
	private ArrayList<String[]> m_orders = null;
	private ScheduleItemAdapter m_adapter;
	Handler handler = new Handler();
	TableFactory factory = new TestTableFactory();
    ScheduleTable scht =  factory.getScheduleTable();
    GroupsTable gt =  factory.getGroupsTable();
    Vector<Schedule> vSchedule ;
    Vector<Group> vGroups;
    Vector<ExamType> Exam ;
   
    
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
	        
	        String days[]={"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"  };
	       	TextView text1 = (TextView)findViewById(R.id.text1);
	        text1.setText(new StringBuilder() .append(days[day]));
	        TextView text2 = (TextView)findViewById(R.id.text2);
	        text2.setText(new StringBuilder() .append(sGroups[j]+" группа"));
	}
	   
	void getScheduleAnync() {
	    final Thread thread = new Thread() {
	        @Override
	        public void run() {
	           
	            final String[] schedule = getInformation();
	            handler.post(new Runnable() {
	                @Override
	                public void run() {
	                    
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_OFF);
	                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	                    
	                    displayInfoInGUI(schedule);
	                }
	            });
	        }
	    };

	    
	    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
	    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_INDETERMINATE_ON);

	    
	    thread.start();
	}
	private void getData(String[] Answer)
	{
        m_orders.add(Answer);
    }
	void displayInfoInGUI(String[] schedule){
		m_orders = new ArrayList<String[]>();
		getData(schedule);
        this.m_adapter = new ScheduleItemAdapter(this, R.layout.list, m_orders);
        setListAdapter(this.m_adapter);
        
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
	        LectureTypes lt = new LectureTypes();
	        Exam_type ex = new Exam_type();
	        
	        ExamType exm;
	        Lecture Lect;
	        LectureType lectType;
	       try
	        {
	            vSchedule = scht.findByGroupDay(Ident[j], (byte)(day));
	            //Exam = ex.getAll();
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
	            vSchedule = new Vector<Schedule>();
	        }
	        for (int k = 0; k< vSchedule.size()+ 1; k++)
	        {
	            try
	            {
	            	Schedule Sc = vSchedule.elementAt(k);
	            	Lect = lct.get(Sc.getLectureID());
	            	lectType = lt.get(Sc.getLectureTypeID());
	            	exm = ex.get(Lect.getExamTypeID());
	            	if (Lect.getName() != null)
	            	{
	            		LectureName[k] = Lect.getName();
	            		LectureStart[k] = Sc.getStartTime();
	            		LectureLenght[k] = Sc.getLength();
	            		Form[k]= exm.getName(); 
	            		//ExamType e = Exam.get(Sc.getLectureTypeID());
	            		TypeOfLecture[k]=lectType.getName(); 
	            	}
	            	else
	                {
	            		LectureName[k] = "-";
	            		LectureStart[k] = 0;
	            		LectureLenght[k] = 0;
	            		TypeOfLecture[k] = "-";
	            		Form[k]="-";
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
	                	LectureTeacher[k] = "-";
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
	                	LectureRoom[k] = "-";
	                }
	              
	                
	            }
	                
	              
	            catch (Exception exc)
	            {
	            }
	            
	            
	        }
	        
	       if(LectureName.length != 0)
	       {
	    	   
	            if (LectureStart[num]%60<10 && (LectureStart[num]+LectureLenght[num])%60<10){
	            	String thisLecture[]={LectureStart[num]/60+":0"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":0"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num],TypeOfLecture[num],Form[num]};
	            	return thisLecture;
	            }
	            else if(LectureStart[num]%60<10)
	            {
	            	String thisLecture[]={LectureStart[num]/60+":0"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num],TypeOfLecture[num],Form[num]};
	            	return thisLecture;
	            }
	            else if ((LectureStart[num]+LectureLenght[num])%60<10)
	            {
	            	String thisLecture[]={LectureStart[num]/60+":"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":0"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num],TypeOfLecture[num],Form[num]};
	            	return thisLecture;
	            }
	            else
	            {
	            	String thisLecture[]={LectureStart[num]/60+":"+LectureStart[num]%60+"-"+ (LectureStart[num]+LectureLenght[num])/60+":"+(LectureStart[num]+LectureLenght[num])%60,LectureName[num], LectureRoom[num], LectureTeacher[num],TypeOfLecture[num],Form[num]};
	            	return thisLecture;
	            }
		        
	       }else
	       {
	    	   String thisLecture[]={"empty"};
	    	  // setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thisLecture));
	    	   return thisLecture;
	       }
	       
	  } 
	private class ScheduleItemAdapter extends ArrayAdapter<String[]> {

        private ArrayList<String[]> items;

        public ScheduleItemAdapter(Context context, int textViewResourceId, ArrayList<String[]> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list, null);
                }
                String[] o = items.get(position);
                if (o != null) {
                    TextView t = (TextView) v.findViewById(R.id.time);
                    TextView s = (TextView) v.findViewById(R.id.subj);
                    TextView r = (TextView) v.findViewById(R.id.room);
                    TextView l = (TextView) v.findViewById(R.id.lector);
                    TextView tp = (TextView) v.findViewById(R.id.type);
                    TextView ex = (TextView) v.findViewById(R.id.exam);
                    if (t != null) {
                          t.setText(o[0]);                            }
                    if (s!= null) {
                    	s.setText(o[1]);
                    	}
                    if(r != null){
                          r.setText(o[2]);
                    }
                    if(l != null){
                        l.setText(o[3]);
                    }
                    if(tp != null){
                        tp.setText(o[4]);
                    }
                    if(ex != null){
                        ex.setText(o[5]);
                    }
            }
                return v;
        }
	}
    
}