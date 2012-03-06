package com.n0234219.MapFragmentDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

	   @Override    
	    protected void onCreate(Bundle bundle) {        
	        super.onCreate(bundle);
	        setContentView(R.layout.menu_layout);        
	        final Button startButton = (Button) findViewById(R.id.button1);
	        startButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 Intent myIntent = new Intent(MenuActivity.this, MainFragmentActivity.class);
	            	 MenuActivity.this.startActivity(myIntent);
	             }
	         });
	    }
}
