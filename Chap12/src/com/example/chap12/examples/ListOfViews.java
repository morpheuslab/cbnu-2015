package com.example.chap12.examples;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chap12.R;

public class ListOfViews extends Activity {
	ArrayList<ListItem> arItem;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewtest);
		
		arItem = new ArrayList<ListItem>();
		arItem.add(new ListItem(0,"제목","입력내용","",0));
		arItem.add(new ListItem(1,"","","push",R.drawable.ic_launcher));
		arItem.add(new ListItem(0,"이름을 입력하시오.","니 이름","",0));
		arItem.add(new ListItem(0,"나이도 입력하시오.","몇살이니","",0));
		arItem.add(new ListItem(1,"","","누르시오",R.drawable.ic_launcher));
		arItem.add(new ListItem(1,"","","업로드",R.drawable.ic_launcher));
		arItem.add(new ListItem(0,"주소","어디 사니?","",0));

		MultiAdapter MyAdapter = new MultiAdapter(this, arItem);
		
		ListView MyList;
		MyList=(ListView)findViewById(R.id.list);
		MyList.setAdapter(MyAdapter);
	}
}

class ListItem {
	ListItem(int aType, String aText, String aEditText, String aBtnText, int aIconRes) {
		Type = aType;
		Text = aText;
		EditText = aEditText;
		BtnText = aBtnText;
		IconRes = aIconRes;
	}
	int Type;
	String Text;
	String EditText;
	String BtnText;
	int IconRes;
}

class MultiAdapter extends BaseAdapter {
	LayoutInflater mInflater;
	ArrayList<ListItem> arSrc;

	public MultiAdapter(Context context, ArrayList<ListItem> arItem) {
		mInflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		arSrc = arItem;
	}

	public int getCount() {
		return arSrc.size();
	}

	public ListItem getItem(int position) {
		return arSrc.get(position);
	}


	public long getItemId(int position) {
		return position;
	}
	
	public int getItemViewType(int position) {
		return arSrc.get(position).Type;
	}
	
	// getView가 생성하는 뷰의 개수를 리턴한다. 항상 같은 뷰를 생성하면 1을 리턴한다.
	// 이 메서드에서 개수를 제대로 조사해 주지 않으면 다운된다.
	public int getViewTypeCount() {
		return 2;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// 최초 호출이면 항목 뷰를 생성한다. 
		// 타입별로 뷰를 다르게 디자인할 수 있으며 높이가 달라도 상관없다.
		if (convertView == null) {
			int res = 0;
			switch (arSrc.get(position).Type) {
			case 0:
				res = R.layout.textedit;
				break;
			case 1:
				res = R.layout.btnicon;
				break;
			}
			convertView = mInflater.inflate(res, parent, false);
		}
		
		// 항목 뷰를 초기화한다.
		switch (arSrc.get(position).Type) {
		case 0:
			TextView txt = (TextView)convertView.findViewById(R.id.text);
			txt.setText(arSrc.get(position).Text);
			EditText edit = (EditText)convertView.findViewById(R.id.edit);
			edit.setText(arSrc.get(position).EditText);
			break;
		case 1:
			Button btn = (Button)convertView.findViewById(R.id.btn);
			btn.setText(arSrc.get(position).BtnText);
			ImageView img = (ImageView)convertView.findViewById(R.id.img);
			img.setImageResource(arSrc.get(position).IconRes);
			break;
		}

		return convertView;
	}
}

