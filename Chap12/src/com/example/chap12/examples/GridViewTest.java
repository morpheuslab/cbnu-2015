package com.example.chap12.examples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chap12.R;

public class GridViewTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridviewtest);

		GridView grid = (GridView)findViewById(R.id.grid);
		ImageAdapter Adapter = new ImageAdapter(this);
		grid.setAdapter(Adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(GridViewTest.this, position + "번째 그림 선택",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}

class ImageAdapter extends BaseAdapter {
	private Context mContext;

	int[] picture = { 
			R.drawable.ccdam,
			R.drawable.soyang2,
			R.drawable.ududong,
			R.drawable.zipdarigol,
			R.drawable.dongul
	};

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return 100;
	}

	public Object getItem(int position) {
		return picture[position % 5];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(80, 60));
			imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(picture[position % 5]);

		return imageView;
	}
}
