package com.example.chap10;

import com.example.chap10.examples.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	class Example {
		Class<?> activityClass;
		String name;
		String desc;
		
		public Example(Class<?> activityClass, String name, String desc) {
			this.activityClass = activityClass;
			this.name = name;
			this.desc = desc;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private Example[] examples = {
				new Example(MemoryPower.class, "MemoryPower", "기억력 게임 (도형)"),
				new Example(MemoryPowerBaby.class, "MemoryPowerBaby", "기억력 게임 (이미지)"),
				new Example(NumPang.class, "NumPang", "같은 숫자 타일 3개 이상 일직선으로 만들어 제거하는 게임"),
				new Example(BabyPang.class, "BabyPang", "같은 사진 타일 3개 이상 일직선으로 만들어 제거하는 게임"),
				new Example(MemoryPower2.class, "MemoryPower2", "디버깅 실습용"),
				new Example(LogTest.class, "LogTest", "로그 기록 연습")
			};
	private ListView exampleListView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		exampleListView = (ListView) findViewById(R.id.example_list);
		ExampleListAdapter adapter = new ExampleListAdapter(this);
		exampleListView.setAdapter(adapter);
		
		exampleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Class<?> cls = examples[position].activityClass;
				if (cls != null) {
					Intent intent = new Intent(MainActivity.this, cls);
					startActivity(intent);
				}
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("onKeyDown", event.toString());
		return super.onKeyDown(keyCode, event);
	}



	class ExampleListAdapter extends BaseAdapter {
		
		Context context;
		LayoutInflater inflater;
		
		public ExampleListAdapter(Context context) {
			this.context = context;
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public int getCount() {
			return examples.length;
		}

		@Override
		public Object getItem(int position) {
			return examples[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.example_list_item, parent, false);
			}
			
			TextView examName = (TextView) convertView.findViewById(R.id.exam_item_name);
			TextView examDesc = (TextView) convertView.findViewById(R.id.exam_item_desc);
			examName.setText(examples[position].name);
			examDesc.setText(examples[position].desc);
			
			return convertView;
		}
		
	}
	
}
