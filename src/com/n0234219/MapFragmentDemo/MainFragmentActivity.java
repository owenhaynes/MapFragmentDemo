package com.n0234219.MapFragmentDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainFragmentActivity extends Activity implements PhotoGridFragment.OnImageSelectedListener {

	@Override    
	protected void onCreate(Bundle bundle) {        
		super.onCreate(bundle);
		setContentView(R.layout.main_layout);        
	}

	public void onImageSelected(String lat, String lng) {
		MapViewerFragment viewer = (MapViewerFragment) getFragmentManager()
				.findFragmentById(R.id.mapview_fragment);

		if (viewer == null || !viewer.isInLayout()) {
			Intent showContent = new Intent(getApplicationContext(),
					MapViewerActivity.class);
			showContent.putExtra("latitude", lat);
			showContent.putExtra("longitude", lng);
			startActivity(showContent);	

		} else {
			viewer.updateMapPosition(lat, lng);
		}


	}
}
