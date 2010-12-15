package edu.phystech.vkurse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import edu.phystech.vkurse.model.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        
        Groups factory = new Groups();
        //upsTable gt =  factory.getGroupsTable();
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
        if (sGroups==null)
        {
        	String[] space = {" Нет данных"};
        	Spinner spin = (Spinner)findViewById(R.id.spin2);
        	ArrayAdapter adapter = new ArrayAdapter(
        		this,  android.R.layout.simple_spinner_item, space);
        	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	spin.setAdapter(adapter);
        	//showDialog(DIALOG_CLOSE_APPLICATION_ID);
        }
        else
        {
        Spinner spin = (Spinner)findViewById(R.id.spin2);
        ArrayAdapter adapter = new ArrayAdapter(
        		this,  android.R.layout.simple_spinner_item, sGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        }
        
    }
    /** Called to start new activity. */
    public void onClick(View v)
    {
    	   Spinner spin = (Spinner)findViewById(R.id.spin2);
    	   String FILENAME = "work_file.txt";
    	   int j=spin.getSelectedItemPosition();

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
    		finish();
    }
}