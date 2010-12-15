package edu.phystech.vkurse;

import java.util.Vector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import edu.phystech.vkurse.model.Group;

public class Welcome_Page extends Activity implements OnClickListener{
	
	static final int DIALOG_CLOSE_APPLICATION_ID = 0;
	String sGroups[];
	public static final String PREFS_NAME = "MyPrefsFile";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Groups factory = new Groups();
        
        Vector<Group> vGroups ;
        try
        {
             vGroups = factory.getAll();
             sGroups = new String[vGroups.size()];
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
           }
           catch (Exception exc)
           {
           }
        }
        
        
 			setContentView(R.layout.welcome_page);
        View forwardButton = findViewById(R.id.forward_button);
        forwardButton.setOnClickListener(this);
        
             
        
        
        if (sGroups==null)
        {
        	String[] space = {" Нет данных"};
        	Spinner spin = (Spinner)findViewById(R.id.spin);
        	ArrayAdapter adapter = new ArrayAdapter(
        		this,  android.R.layout.simple_spinner_item, space);
        	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	spin.setAdapter(adapter);
        	//showDialog(DIALOG_CLOSE_APPLICATION_ID);
        }
        else
        {
        	Spinner spin = (Spinner)findViewById(R.id.spin);
        	ArrayAdapter adapter = new ArrayAdapter(
        		this,  android.R.layout.simple_spinner_item, sGroups);
        	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	spin.setAdapter(adapter);
        }
 		
 		       
    }
    
  
    /** Called to start new activity. */
    public void onClick(View v)
    {
    	switch (v.getId()){
    	case R.id.forward_button:
	    	if(sGroups ==null){
	    		Intent i = new Intent(this, Empty.class);
	    		startActivity(i);
	    	}
	    	else{
	    
		    	Intent i = new Intent(this, Schedule_Activity.class);
		   	
		    	Spinner spin = (Spinner)findViewById(R.id.spin);
		    	i.putExtra("from_spin",spin.getSelectedItemPosition());    	
		    	
		    	startActivity(i);
	    	}
    	
    	}
    }
}