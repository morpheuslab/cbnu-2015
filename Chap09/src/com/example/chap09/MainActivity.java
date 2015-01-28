package com.example.chap09;

import com.example.chap09.examples.*;

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
			new Example(OptionMenu.class, "OptionMenu", "옵션 메뉴, 서브 메뉴"),
			new Example(OptionMenuXml.class, "OptionMenuXml", "XML로 메뉴 정의"),
			new Example(MenuOnClick.class, "MenuOnClick", "onClick 으로 메뉴 선택하기"),
			new Example(ContextMenuTest.class, "ContextMenuTest", "컨텍스트 메뉴"),
			new Example(PopupMenuTest.class, "PopupMenuTest", "팝업 메뉴"),
			new Example(MenuHistory.class, "MenuHistory", "버전별 메뉴 모양 및 기능"),
			new Example(MenuCheck.class, "MenuCheck", "메뉴로 체크/라디오 옵션 사용"),
			new Example(ChangeMenu.class, "ChangeMenu", "실행중 메뉴 교체")
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
