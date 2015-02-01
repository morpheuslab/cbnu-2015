package com.example.chap11;

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

import com.example.chap11.examples.AdjustKey1;
import com.example.chap11.examples.AdjustKey2;
import com.example.chap11.examples.ArrowButton;
import com.example.chap11.examples.ButtonDrawable;
import com.example.chap11.examples.ButtonSelector;
import com.example.chap11.examples.EditLimit;
import com.example.chap11.examples.EditSelect;
import com.example.chap11.examples.EditableTest;
import com.example.chap11.examples.FilterTouch;
import com.example.chap11.examples.GramPrice;
import com.example.chap11.examples.ImageButtonTest;
import com.example.chap11.examples.ImageViewAttr;
import com.example.chap11.examples.InputTypeTest;
import com.example.chap11.examples.NoNinePatch;
import com.example.chap11.examples.RadioCheck;
import com.example.chap11.examples.ReadResource;
import com.example.chap11.examples.ReadResource2;
import com.example.chap11.examples.ScaleTypeTest;
import com.example.chap11.examples.ShowHideKey;
import com.example.chap11.examples.SpannableTest;
import com.example.chap11.examples.SpannableTest2;
import com.example.chap11.examples.StyleTest;
import com.example.chap11.examples.StyleTest2;
import com.example.chap11.examples.SystemTheme;
import com.example.chap11.examples.TextChange;
import com.example.chap11.examples.TextViewAttr;
import com.example.chap11.examples.TextViewHtml;
import com.example.chap11.examples.ThemeTest;
import com.example.chap11.examples.YesNinePatch;

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
				new Example(ReadResource.class, "리소스에서 문자열, 색상, 크기 읽기"),
				new Example(ReadResource2.class, "레이아웃에서 리소스 읽기"),
				new Example(StyleTest.class, "스타일 계층 정의 및 사용"),
				new Example(StyleTest2.class, "부모 스타일 변경"),
				new Example(ThemeTest.class, "액티비티에 타이틀바 없음 테마 적용"),
				new Example(SystemTheme.class, "액티비티에 대화상자 테마 적용"),
				new Example(TextViewAttr.class, "텍스트 뷰의 속성"),
				new Example(SpannableTest.class, "Spannable 버퍼 타입 (서식 미지정)"),
				new Example(SpannableTest2.class, "Spannable 버퍼 타입 (서식 문자열 출력)"),
				new Example(TextViewHtml.class, "HTML 태그로 서식 문자열 출력"),
				new Example(EditableTest.class, "Editable 버퍼 타입으로 문자열 직접 수정"),
				new Example(TextChange.class, "텍스트 변경 이벤트"),
				new Example(GramPrice.class, "그램당 상품 가격 계산"),
				new Example(EditLimit.class, "텍스트 입력 길이 제한"),
				new Example(EditSelect.class, "선택 영역 관리"),
				new Example(InputTypeTest.class, "inputType 속성으로 키보드 종류 선택"),
				new Example(ShowHideKey.class, "Java 코드로 키보드 보이기 / 숨기기"),
				new Example(AdjustKey1.class, "키보드 Panning 모드"),
				new Example(AdjustKey2.class, "키보드 Resize 모드"),
				new Example(NoNinePatch.class, "보통 이미지 배경 사용"),
				new Example(YesNinePatch.class, "나인패치 이미지 배경 사용"),
				new Example(ArrowButton.class, "상태에 따라 색상이 변하는 화살표 버튼"),
				new Example(RadioCheck.class, "라디오 버튼, 체크 박스"),
				new Example(FilterTouch.class, "버튼의 터치 필터링 속성"),
				new Example(ScaleTypeTest.class, "이미지 뷰의 확대 모드"),
				new Example(ImageViewAttr.class, "이미지 뷰의 속성"),
				new Example(ImageButtonTest.class, "이미지 버튼 테스트"),
				new Example(ButtonDrawable.class, "버튼에 drawable 같이 표시하기"),
				new Example(ButtonSelector.class, "버튼에 셀렉터 적용하여 상태 표시하기")
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
