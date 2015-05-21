package ru.rsdev.smscontroller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class EditBoiler extends ActionBarActivity {

    Button button;
    Button button2;
    ListView listView;
    private SQLiteDatabase database;
    ArrayList<String> listBoiler = new ArrayList<String>();

    private static final String TABLE_NAME = "BoilerTable";
    private static final String ID = "_id";
    private static final String NAME = "Name";

    EditBoilerBD boiler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_boiler);

        button =  (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.listView);
        boiler = new EditBoilerBD();
        //GetDataInBD();


        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listBoiler));
        //listView.setAdapter(new ArrayAdapter<String>(this, R.layout.edit_list_item,listBoiler));
        //listView.setTextFilterEnabled(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Toast.makeText(getApplication(),listBoiler.get(listView.getCheckedItemPosition()),Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void onResume() {
        GetDataInBD();

        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listBoiler));
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listBoiler));
        listView.setTextFilterEnabled(true);
        //myArrayAdapter = new MyArrayAdapter(this, R.layout.edit_list_item, android.R.id.text1, listBoiler);

        //listView.setAdapter(myArrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        super.onResume();
    }

    private void GetDataInBD()
    {
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this);
        database = dbOpenHelper.openDataBase();

        listBoiler.clear();
        String sqlQuery = "SELECT "+ NAME + " FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(sqlQuery, new  String[] {});

        if (cursor.moveToFirst()) {

            int nameColIndex = cursor.getColumnIndex(NAME);
            do {
                listBoiler.add(cursor.getString(nameColIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_boiler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ClickButton(View view) {
        switch (view.getId()){
            case R.id.button:
                Toast.makeText(getApplication(),"button1",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddNewBoilerActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:

                ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this);
                database = dbOpenHelper.openDataBase();
                String name = listBoiler.get(listView.getCheckedItemPosition());
                database.delete(TABLE_NAME, "Name = '" + name + "'", null);
                GetDataInBD();
                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listBoiler));
                listView.setTextFilterEnabled(true);

                break;
        }





    }
}
