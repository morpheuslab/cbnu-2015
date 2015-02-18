package kr.co.company.providertest1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.widget.EditText;
import android.widget.Toast;


public class ProviderTest1Activity extends Activity {
    /** Called when the activity is first created. */
	EditText name;
	EditText phone;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] projection = new String[]{
                People.NAME,
                People.NUMBER
             };

        Cursor c = this.getContentResolver().query(Contacts.Phones.CONTENT_URI, projection, null, null, People.NAME + " ASC");
        c.moveToFirst();
        int nameColumn = c.getColumnIndex(People.NAME);
        int phoneColumn = c.getColumnIndex(People.NUMBER);
        if( c.getCount() > 0 ) {
        	do{
        		String name = c.getString(nameColumn);
        		String phoneNumber = c.getString(phoneColumn);
            
        		Toast.makeText(this, name+":"+phoneNumber, Toast.LENGTH_SHORT).show();
        	} while(c.moveToNext());
        }
    }

}