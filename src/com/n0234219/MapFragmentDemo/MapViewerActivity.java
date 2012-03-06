package com.n0234219.MapFragmentDemo;

import com.google.android.maps.MapActivity;
import android.os.Bundle;

public class MapViewerActivity extends MapActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_view);
		
//		
//		String geoUriString = getResources().getString(R.string.map_location);  
//		Uri geoUri = Uri.parse(geoUriString);  
//		Intent mapCall = new Intent(Intent.ACTION_VIEW, geoUri);  
//		startActivity(mapCall);  
	}


	//display no route on the map
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
