package com.starklabs.groupmessage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class for_delete extends AppCompatActivity {

    Button fdel;
    ArrayList <String> list1;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_delete);

        fdel=(Button) findViewById(R.id.fdel);
        spin=(Spinner) findViewById(R.id.spin);

        try
        {
            SQLiteDatabase db=openOrCreateDatabase("msg",MODE_PRIVATE,null);
            Cursor c= db.rawQuery("select distinct gpname from tab",null);
            if(c.moveToFirst()) {
                list1 = new ArrayList<String>();
                do {
                    list1.add(c.getString(0));
                } while (c.moveToNext());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(dataAdapter);
            db.close();
        }
        catch(Exception e)
        {

        }

        fdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String str= spin.getSelectedItem().toString();
                    SQLiteDatabase db=openOrCreateDatabase("msg",MODE_PRIVATE,null);
                    db.execSQL("delete from tab where gpname='"+str+"'");
                    db.close();
                }
                catch (Exception e)
                {

                }
                finally {
                    finish();
                }

            }
        });
    }
}
