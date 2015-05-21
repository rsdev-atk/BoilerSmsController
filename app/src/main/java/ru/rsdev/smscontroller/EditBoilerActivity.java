package ru.rsdev.smscontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class EditBoilerActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener
{
    EditText editName,editNumber,editParam1,editParam2,editParam3,editParam4;
    SeekBar seekBar1,seekBar2, seekBar3, seekBar4;
    Button btnSend;
    TextView textView,textView2,textView3,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_boiler2);

        editName = (EditText) findViewById(R.id.editName);
        editNumber = (EditText) findViewById(R.id.editNumber);
        editParam1 = (EditText) findViewById(R.id.editParam1);
        editParam2 = (EditText) findViewById(R.id.editParam2);
        editParam3 = (EditText) findViewById(R.id.editParam3);
        editParam4 = (EditText) findViewById(R.id.editParam4);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        btnSend = (Button) findViewById(R.id.btnSend);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);


        Intent intent = getIntent();
        String boilerName = intent.getStringExtra("nameBoiler");
        String boilerNumber = intent.getStringExtra("numberBoiler");
        String Param1 = intent.getStringExtra("param1");
        String Param2 = intent.getStringExtra("param2");
        String Param3 = intent.getStringExtra("param3");
        String Param4 = intent.getStringExtra("param4");

        editName.setText(boilerName);
        editNumber.setText(boilerNumber);
        editParam1.setText(Param1);
        editParam2.setText(Param2);
        editParam3.setText(Param3);
        editParam4.setText(Param4);

        seekBar1.setProgress(Integer.valueOf(Param1));
        seekBar2.setProgress(Integer.valueOf(Param2)-30);
        seekBar3.setProgress(Integer.valueOf(Param3)-5);
        seekBar4.setProgress(Integer.valueOf(Param4));

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_boiler, menu);
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {

        editParam1.setText(String.valueOf(seekBar1.getProgress()));
        editParam2.setText(String.valueOf(seekBar2.getProgress()+30));
        editParam3.setText(String.valueOf(seekBar3.getProgress()+5));
        editParam4.setText(String.valueOf(seekBar4.getProgress()));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        editParam1.setText(String.valueOf(seekBar1.getProgress()));
        editParam2.setText(String.valueOf(seekBar2.getProgress()+30));
        editParam3.setText(String.valueOf(seekBar3.getProgress()+5));
        editParam4.setText(String.valueOf(seekBar4.getProgress()));

    }

    public void ClickButton(View view) {
        switch (view.getId()){
            case R.id.btnSend:
                //Send sms
                SmsManager smsManager = SmsManager.getDefault();
                String smsNumber = editNumber.getText().toString();
                //String smsText = "Тестовое сообщние из Android";

                String smsText = textView.getText().toString() + " " + editParam1.getText().toString() + " " +
                                 textView2.getText().toString() + " " + editParam2.getText().toString() + " " +
                                 textView3.getText().toString() + " " + editParam3.getText().toString() + " " +
                                 textView4.getText().toString() + " " + editParam4.getText().toString() + " ";



                //smsText = EntityUtils.toString(httpEntity, "UTF-8");

                smsManager.sendTextMessage(smsNumber, null, smsText, null, null);


                break;
        }

    }
}
