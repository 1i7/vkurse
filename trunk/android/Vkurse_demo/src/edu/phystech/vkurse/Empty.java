package edu.phystech.vkurse;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class Empty extends Activity implements OnClickListener{
	
	static final int DIALOG_CLOSE_APPLICATION_ID = 0;
	String sGroups[];
	public static final String PREFS_NAME = "MyPrefsFile";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty);
        
        View repButton = findViewById(R.id.rep);
        repButton.setOnClickListener(this);

    } 
  
    /** Called to start new activity. */
    public void onClick(View v)
    {
	    finish();
    }
}