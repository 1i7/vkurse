package edu.phystech.vkurse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import edu.phystech.vkurse.model.*;

import java.util.*;

import edu.phystech.vkurse.test.*;

public class Change_Group extends Activity implements OnClickListener{
	
	String sGroups[];
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_group);
        
        View forwardButton = findViewById(R.id.ok_button);
        forwardButton.setOnClickListener(this);
        
        TableFactory factory = new TestTableFactory();
        GroupsTable gt =  factory.getGroupsTable();
        Vector vGroups ;
        try
        {
             vGroups = gt.getAll();
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
           Group g = gt.get(i);
           sGroups[i] = g.getName();
           }
           catch (Exception exc)
           {
           }
        }
        
        Spinner spin = (Spinner)findViewById(R.id.spin2);
        ArrayAdapter adapter = new ArrayAdapter(
        		this,  android.R.layout.simple_spinner_item, sGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        
    }
    /** Called to start new activity. */
    public void onClick(View v)
    {
    	Intent i = new Intent(this, Schedule_Activity.class);
    	
    	Spinner spin = (Spinner)findViewById(R.id.spin2);
    	i.putExtra("from_spin",spin.getSelectedItemPosition());
    	
    	startActivity(i);
    }
}