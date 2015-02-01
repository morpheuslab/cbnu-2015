package com.example.chap12.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chap12.R;

public class SpinnerTest extends Activity {
	ArrayAdapter<CharSequence> adspin;
	boolean mInitSpinner;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinnertest);

		Spinner spin = (Spinner)findViewById(R.id.myspinner);
		spin.setPrompt("과일을 고르세요.");

		adspin = ArrayAdapter.createFromResource(this, R.array.fruits, 
				android.R.layout.simple_spinner_item);
		adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adspin);

		spin.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, 
					int position, long id) {
				/* 초기화시의 선택 제외시
				if (mInitSpinner == false) {
					mInitSpinner = true;
					return;
				}
				//*/
				Toast.makeText(SpinnerTest.this,adspin.getItem(position) + "는 맛있다.",
						Toast.LENGTH_SHORT).show();
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}
}
