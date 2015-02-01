package com.example.chap12.examples;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chap12.R;

public class ListViewTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewtest);

		//* 데이터 원본 준비
		ArrayList<String> arGeneral = new ArrayList<String>();
		arGeneral.add("김유신");
		arGeneral.add("이순신");
		arGeneral.add("강감찬");
		arGeneral.add("을지문덕");
		//*/
		
		/* 배열로 준비
		String[] arGeneral = {"김유신", "이순신", "강감찬", "을지문덕"};
		//*/

		// 어댑터 준비
		ArrayAdapter<String> Adapter;
		Adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, arGeneral);

		// 어댑터 연결
		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(Adapter);
	}
}
