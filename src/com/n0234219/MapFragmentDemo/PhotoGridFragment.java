package com.n0234219.MapFragmentDemo;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PhotoGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

	// member variables for
	private static final int PHOTO_LIST_LOADER = 0x01;
	private GridView gridView;
	private ImageCursorAdapter adapter;
	private Cursor c;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLoaderManager().initLoader(PHOTO_LIST_LOADER, null, this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.photo_item, container, false);		
	}
		
	@Override
	public void onStart() {
		super.onStart();
		adapter = new ImageCursorAdapter(getActivity().getApplicationContext(), c, 0);
		gridView = (GridView) getView();
	    gridView.setAdapter(adapter);
	}
	
	// Loader manager methods
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { MediaStore.Images.Thumbnails._ID };
		CursorLoader cursorLoader = new CursorLoader(getActivity(),
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection,
				null, null, null);
		return cursorLoader;
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);

	}

	public void onLoaderReset(Loader<Cursor> cursor) {
		adapter.swapCursor(null);
	}
	
	
	private class ImageCursorAdapter extends CursorAdapter {

		private Context mContext;

		public ImageCursorAdapter(Context context, Cursor c, int flags) {
			super(context, c);
			mContext = context;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			ImageView iv = (ImageView) view;
			String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
			iv.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, ""+id));		
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			ImageView view = new ImageView(mContext);
	        return view;
		}
		
		
	}
		
}
