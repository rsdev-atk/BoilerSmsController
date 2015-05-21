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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends ActionBarActivity {

    ListView list;
    TextView empty;
    ArrayList<Boiler> data;
    private SQLiteDatabase database;

    private static final String TABLE_NAME = "BoilerTable";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    EditBoilerBD boiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        list = (ListView) findViewById(R.id.list);
        empty = (TextView) findViewById(R.id.empty);
        data = new ArrayList<Boiler>();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intentEdit = new Intent(MainActivity.this, EditBoilerActivity.class);

                String nameBoiler = data.get(position).get("txtBoilerName").toString();
                String numberBoiler = data.get(position).get("txtSmsNumber").toString();

                String param1 = data.get(position).get("txtParamEdit1").toString();
                String param2 = data.get(position).get("txtParamEdit2").toString();
                String param3 = data.get(position).get("txtParamEdit3").toString();
                String param4 = data.get(position).get("txtParamEdit4").toString();

                intentEdit.putExtra("nameBoiler", nameBoiler);
                intentEdit.putExtra("numberBoiler", numberBoiler);

                intentEdit.putExtra("param1", param1);
                intentEdit.putExtra("param2", param2);
                intentEdit.putExtra("param3", param3);
                intentEdit.putExtra("param4", param4);

                startActivity(intentEdit);
            }
            });

    }

    protected void onResume() {
        GetDataInBD();

        ListAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item,
                new String[] { Boiler.txtBoilerName, Boiler.txtParamEdit1,Boiler.txtParamEdit2,Boiler.txtParamEdit3,Boiler.txtParamEdit4,Boiler.txtSmsNumber,Boiler.txtData },
                new int[] {R.id.txtBoilerName, R.id.txtParamEdit1, R.id.txtParamEdit2, R.id.txtParamEdit3, R.id.txtParamEdit4,R.id.txtSmsNumber, R.id.txtData });
        list.setAdapter(adapter);


        if(data.size()>0)
            empty.setText("");

        super.onResume();
    }

    private void GetDataInBD()
    {
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this);
        database = dbOpenHelper.openDataBase();

        data.clear();


        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(sqlQuery, new  String[] {});

        if (cursor.moveToFirst()) {

            int nameColIndex = cursor.getColumnIndex("Name");
            int param1ColIndex = cursor.getColumnIndex("Param1");
            int param2ColIndex = cursor.getColumnIndex("Param2");
            int param3ColIndex = cursor.getColumnIndex("Param3");
            int param4ColIndex = cursor.getColumnIndex("Param4");
            int numberColIndex = cursor.getColumnIndex("SmsNumber");
            int dataColIndex = cursor.getColumnIndex("Data");


            do {
                String name = cursor.getString(nameColIndex);
                String param1 = cursor.getString(param1ColIndex);
                String param2 = cursor.getString(param2ColIndex);
                String param3 = cursor.getString(param3ColIndex);
                String param4 = cursor.getString(param4ColIndex);
                String number = cursor.getString(numberColIndex);
                String dataTime = cursor.getString(dataColIndex);

                data.add(new Boiler(name, param1, param2, param3, param4 ,number, dataTime));
            } while (cursor.moveToNext());
        }
        cursor.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.editBoiler) {
            //Toast.makeText(getApplication(),"Press",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EditBoiler.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
