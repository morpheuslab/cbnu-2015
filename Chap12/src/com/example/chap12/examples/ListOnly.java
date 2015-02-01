package com.example.chap12.examples;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ListOnly extends ListActivity {
	ArrayList<String> arGeneral;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		arGeneral = new ArrayList<String>();
		arGeneral.add("김유신");
		arGeneral.add("이순신");
		arGeneral.add("강감찬");
		arGeneral.add("을지문덕");

		ArrayAdapter<String> Adapter;
		Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				arGeneral);

		setListAdapter(Adapter);
	}

	public void onListItemClick(ListView list, View view, int position, long id) {
		String mes;
		mes = "Select Item = " + arGeneral.get(position);
		Toast.makeText(ListOnly.this,mes,Toast.LENGTH_SHORT).show();
	}
}