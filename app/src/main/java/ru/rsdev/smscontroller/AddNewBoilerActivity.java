package ru.rsdev.smscontroller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewBoilerActivity extends ActionBarActivity {

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_boiler);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_boiler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ClickButton(View view) {

        String nameBoiler = editText.getText().toString();
        String numberBoiler = editText2.getText().toString();
        String str[] = new String[]{nameBoiler,numberBoiler};

        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this);
        SQLiteDatabase database = dbOpenHelper.openDataBase();


        ContentValues cv = new ContentValues();
        cv.put("Name", str[0]);
        cv.put("Param1", "0");
        cv.put("Param2", "0");
        cv.put("Param3", "0");
        cv.put("Param4", "0");
        cv.put("SmsNumber", str[1]);

        long rowID = database.insert("BoilerTable", null, cv);
        Toast.makeText(getApplication(),R.string.add_new_boiler, Toast.LENGTH_SHORT).show();

        editText.setText("");
        editText2.setText("");


    }
}
