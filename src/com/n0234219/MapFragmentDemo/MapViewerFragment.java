package com.n0234219.MapFragmentDemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MapViewerFragment extends LocalActivityManagerFragment {
	
	 private TabHost mTabHost;
	    
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
	        View view = inflater.inflate(R.layout.mapview_fragment, container, false);
	        mTabHost = (TabHost)view.findViewById(android.R.id.tabhost);
	        mTabHost.setup(getLocalActivityManager());
	        
	        TabSpec tab = mTabHost.newTabSpec("map")
	                              .setIndicator("map")
	                              .setContent(new Intent(getActivity(), MapViewerActivity.class));
	        mTabHost.addTab(tab);        
	        return view;
	    }
}
