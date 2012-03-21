package com.n0234219.MapFragmentDemo;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MapViewerFragment extends LocalActivityManagerFragment {
	
	 private TabHost mTabHost;
	 private MapView mapView;
	 private List<Overlay> mapOverlays; 
	    
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
	        View view = inflater.inflate(R.layout.mapview_fragment, container, false);
	        mTabHost = (TabHost)view.findViewById(android.R.id.tabhost);
	        mapView = (MapView)view.findViewById(R.id.mapview);
	        mTabHost.setup(getLocalActivityManager());

	        TabSpec tab = mTabHost.newTabSpec("map")
	                              .setIndicator("map")
	                              .setContent(new Intent(getActivity(), MapViewerActivity.class));
	        mTabHost.addTab(tab);     
	        mapView = (MapView) mTabHost.getCurrentView().findViewById(R.id.mapview);
	        mapOverlays = mapView.getOverlays();

	        return view;
	        
	    }
	    
	    @Override
	    public void onAttach(Activity activity) {
	    	super.onAttach(activity);

	    }
	    
	    @Override
	    public void onStop() {
	    	super.onStop();
	    	mapView.getOverlays().clear();	    	
	    }
	    
	    
		public void updateMapPosition(String latitude, String longitude) {
			
			float lat = convertToDegree(latitude);
			float lng = convertToDegree(longitude);
			
			GeoPoint p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
			MapController mc = mapView.getController();
			OverlayItem overlayitem = new OverlayItem(p, "Photo Location", "is here");
			Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		    MapOverlay itemizedoverlay = new MapOverlay(drawable, this.getActivity());
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
			mc.animateTo(p);
			mc.setZoom(17);
			mapView.invalidate();
			
		}
		
		private Float convertToDegree(String toConvert){
			 Float result = null;
			 String[] DMS = toConvert.split(",", 3);

			 String[] stringD = DMS[0].split("/", 2);
			    Double D0 = new Double(stringD[0]);
			    Double D1 = new Double(stringD[1]);
			    Double FloatD = D0/D1;

			 String[] stringM = DMS[1].split("/", 2);
			 Double M0 = new Double(stringM[0]);
			 Double M1 = new Double(stringM[1]);
			 Double FloatM = M0/M1;
			  
			 String[] stringS = DMS[2].split("/", 2);
			 Double S0 = new Double(stringS[0]);
			 Double S1 = new Double(stringS[1]);
			 Double FloatS = S0/S1;
			  
			 result = new Float(FloatD + (FloatM/60) + (FloatS/3600));
			  
			 return result;


			}
}
