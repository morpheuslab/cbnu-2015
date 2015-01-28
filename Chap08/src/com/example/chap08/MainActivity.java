package com.example.chap08;

import com.example.chap08.examples.*;

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
			new Example(HandleEvent.class, "HandleEvent", "여러 가지 이벤트 처리 방식"),
			new Example(HandlerOrder.class, "HandlerOrder", "핸들러의 우선 순위"),
			new Example(HandlerOrder2.class, "HandlerOrder2", "모든 핸들러 순차적 호출"),
			new Example(HandlerAccess.class, "HandlerAccess", "핸들러에서 외부 변수 액세스"),
			new Example(FreeLine.class, "FreeLine", "터치 이벤트로 자유 곡선 그리기"),
			new Example(MoveCircle.class, "MoveCircle", "키보드로 원 움직이기"),
			new Example(Fruit.class, "Fruit", "위젯의 이벤트 처리 및 핸들러 통합"),
			new Example(Fruit2.class, "Fruit2", "onClick 속성으로 클릭 이벤트 처리"),
			new Example(LongClick.class, "LongClick", "위젯의 롱클릭 처리"),
			new Example(FocusTest.class, "FocusTest", "포커스 이동 (기본)"),
			new Example(FocusTest2.class, "FocusTest2", "포커스 이동 (순환)"),
			new Example(TimerTest.class, "TimerTest", "핸들러를 이용한 타이머")
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
