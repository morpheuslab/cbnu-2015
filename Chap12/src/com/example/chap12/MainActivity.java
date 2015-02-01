package com.example.chap12;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.chap12.examples.*;

public class MainActivity extends Activity {

	class Example {
		Class<?> activityClass;
		String name;
		String desc;

		public Example(Class<?> activityClass, String name, String desc) {
			this.activityClass = activityClass;
			this.name = name;
			this.desc = desc;
		}

		public Example(Class<?> activityClass, String desc) {
			this.activityClass = activityClass;
			this.name = activityClass.getSimpleName();
			this.desc = desc;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private Example[] examples = {
				new Example(ListViewTest.class, "리스트뷰에 문자열 항목 표시"),
				new Example(ListFromArray.class, "리스트뷰에 XML 리소스 배열 표시"),
				new Example(ListAttr.class, "리스트뷰의 구분선 속성"),
				new Example(ListItemSelect.class, "리스트뷰의 항목 선택"),
				new Example(ListAddDel.class, "실행 중에 항목 삽입 및 삭제"),
				new Example(ListAddDelMulti.class, "여러 개의 항목 한꺼번에 삭제하기"),
				new Example(ListAddDelMulti2.class, "라디오 버튼으로 여러 항목 삭제"),
				new Example(ListAddDelMulti3.class, "체크 박스로 여러 항목 삭제"),
				new Example(ListIconText.class, "아이콘과 텍스트로 항목 뷰 구성하기"),
				new Example(ListOfViews.class, "여러 종류의 항목 뷰 섞어서 표시"),
				new Example(ManyItem.class, "대용량 항목 뷰 표시와 리스트 뷰의 동작 연구"),
				new Example(Expandable.class, "2단계의 확장 리스트 뷰"),
				new Example(ListOnly.class, "ListActivity 상속"),
				new Example(OverScroll.class, "리스트뷰의 오버 스크롤"),
				new Example(SpinnerTest.class, "스피너로 과일 이름 선택하기"),
				new Example(GridViewTest.class, "그리드뷰로 이미지 선택하기"),
				new Example(GalleryTest.class, "갤러리로 이미지 선택하기")
			};
	private ListView exampleListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		exampleListView = (ListView) findViewById(R.id.example_list);
		ExampleListAdapter adapter = new ExampleListAdapter(this);
		exampleListView.setAdapter(adapter);

		exampleListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
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
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
				convertView = inflater.inflate(R.layout.example_list_item,
						parent, false);
			}

			TextView examName = (TextView) convertView
					.findViewById(R.id.exam_item_name);
			TextView examDesc = (TextView) convertView
					.findViewById(R.id.exam_item_desc);
			examName.setText(examples[position].name);
			examDesc.setText(examples[position].desc);

			return convertView;
		}

	}

}
