package com.n0234219.MapFragmentDemo;

import android.app.Fragment;
import android.app.ListFragment;
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
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

	// member variables for
	private static final int PHOTO_LIST_LOADER = 0x01;
	private ImageCursorAdapter adapter;
	private Cursor c;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLoaderManager().initLoader(PHOTO_LIST_LOADER, null, this);
		
		adapter = new ImageCursorAdapter(getActivity().getApplicationContext(), c);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.photo_item, container, false);		
	}
		
	
	// Loader manager methods
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { MediaStore.Images.Thumbnails._ID, MediaStore.Images.Thumbnails.DATA };
		CursorLoader cursorLoader = new CursorLoader(getActivity(),
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
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

		private LayoutInflater mLayoutInflater;
		private Context mContext;

		public ImageCursorAdapter(Context context, Cursor c) {
			super(context, c);
			mContext = context;
			mLayoutInflater = LayoutInflater.from(context);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			ImageView newView = (ImageView) view.findViewById(R.layout.grid_item);
			CharSequence info = "COLUMNS: " + cursor.getColumnCount() + " ROWS: " + cursor.getCount();
			Toast toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
			toast.show();
			String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID));
			if (imagePath != null && imagePath.length() != 0 && newView != null) {
	            newView.setVisibility(ImageView.VISIBLE);
	            newView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + cursor.getPosition()));
			}
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
		    View v = mLayoutInflater.inflate(R.layout.grid_item, parent, false);
	        return v;
		}
		
		
	}
		
}
