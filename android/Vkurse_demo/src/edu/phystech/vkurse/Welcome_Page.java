package edu.phystech.vkurse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import edu.phystech.vkurse.model.Group;

public class Welcome_Page extends Activity implements OnClickListener{
	
	String sGroups[];
	public static final String PREFS_NAME = "MyPrefsFile";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        
        View forwardButton = findViewById(R.id.forward_button);
        forwardButton.setOnClickListener(this);
        
                
        Groups factory = new Groups();
       
        Vector<Group> vGroups ;
        try
        {
             vGroups = factory.getAll();
             sGroups = new String[vGroups.size()];
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
           }
           catch (Exception exc)
           {
           }
        }
        
        
        Spinner spin = (Spinner)findViewById(R.id.spin);
        ArrayAdapter adapter = new ArrayAdapter(
        		this,  android.R.layout.simple_spinner_item, sGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        
    }
    
  
    /** Called to start new activity. */
    public void onClick(View v)
    {
    	Intent i = new Intent(this, Schedule_Activity.class);
   	
    	Spinner spin = (Spinner)findViewById(R.id.spin);
    	i.putExtra("from_spin",spin.getSelectedItemPosition());    	
    	
    	startActivity(i);
    }
}