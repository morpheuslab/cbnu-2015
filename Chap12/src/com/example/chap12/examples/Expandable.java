package com.example.chap12.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.example.chap12.R;

public class Expandable extends Activity {
	ExpandableListView mList;
	String[] arProv = new String[] {
			"충청도", "경기도", "강원도"
	};
	String[][] arCity = new String[][] {
			{"논산", "당진", "부여"},
			{"수원", "용인"},
			{"춘천", "원주", "홍천", "강릉"},
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable);

		mList = (ExpandableListView)findViewById(R.id.list);
		List<Map<String, String>> provData = new ArrayList<Map<String, String>>();
		List<List<Map<String, String>>> cityData = 
			new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < arProv.length; i++) {
			Map<String, String> Prov = new HashMap<String, String>();
			Prov.put("prov", arProv[i]);
			provData.add(Prov);

			List<Map<String, String>> children = new ArrayList<Map<String, String>>();
			for (int j = 0; j < arCity[i].length; j++) {
				Map<String, String> City = new HashMap<String, String>();
				City.put("city", arCity[i][j]);
				children.add(City);
			}
			cityData.add(children);
		}

		ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this,
				provData,
				android.R.layout.simple_expandable_list_item_1,
				new String[] { "prov" },
				new int[] { android.R.id.text1 },
				cityData,
				android.R.layout.simple_expandable_list_item_1,
				new String[] { "city" },
				new int[] { android.R.id.text1 }
		);
		mList.setAdapter(adapter);
	}
}
