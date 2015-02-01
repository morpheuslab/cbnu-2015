package com.example.chap13;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chap13.examples.*;

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
				new Example(ProgressBarTest.class, "프로그레스 바"),
				new Example(ProgressTitle.class, "타이틀바의 프로그레스 바"),
				new Example(ProgressTitle2.class, "타이틀바의 원형 프로그레스"),
				new Example(SeekBarTest.class, "SeekBar"),
				new Example(RatingBarTest.class, "RatingBar"),
				new Example(DateTimeTest.class, "날짜 및 시간 조사 방법"),
				new Example(SpeedTest.class, "덧셈 5억번 수행 시간 측정"),
				new Example(ClockTest.class, "디지털, 아날로그 시계 위젯"),
				new Example(DatePickerTest.class, " 날짜 선택 위젯"),
				new Example(TimePickerTest.class, "시간 선택 위젯"),
//				new Example(DateTimePickerTest.class, "날짜, 시간 선택기"),
				new Example(PickerDialogTest.class, "날짜, 시간 선택 다이얼로그"),
				new Example(ChronometerTest.class, "스톱워치 위젯"),
				new Example(StopWatch.class, "핸들러로 구현한 스톱워치"),
				new Example(AutoComplete.class, "자동 완성 에디트"),
				new Example(MultiAuto.class, "자동 완성 멀티 에디트"),
				new Example(SlidingDrawerTest.class, "화면 아래쪽에 숨겨진 서랍"),
				new Example(ScrollViewTest.class, "스크롤 뷰 (색상 뷰 수직 스크롤)"),
				new Example(HScrollView.class, "수평 스크롤 뷰"),
				new Example(WebViewTest.class, "웹 뷰 (웹 페이지 및 리소스의 HTML 보기)"),
				new Example(SportsScore.class, "스포츠 경기의 점수 매기기"),
				new Example(SwitchTest.class, "스위치 위젯"),
				new Example(SpaceTest.class, "Space 위젯으로 여백 띄우기"),
				new Example(NumberPickerTest.class, "숫자 선택기 (Holo 테마)"),
				new Example(NumberPickerTest2.class, "숫자 선택기 (Theme 테마)"),
				new Example(CalendarViewTest.class, "달력 위젯"),
				new Example(ListPopupWindowTest.class, "팝업 목록")
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
