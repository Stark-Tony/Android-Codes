package com.starklabs.groupmessage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add, send,edit,del;
    EditText text;
    Spinner spinner;
    LinearLayout ll;
    ArrayList <String> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add);
        send = (Button) findViewById(R.id.send);
        spinner =(Spinner) findViewById(R.id.spinner);
        text = (EditText) findViewById(R.id.text);
        send= (Button)findViewById(R.id.send);
        del= (Button)findViewById(R.id.del);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE}, 1);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,cont.class);
                startActivity(intent);
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = openOrCreateDatabase("msg", MODE_PRIVATE, null);
                    Cursor c = db.rawQuery("select pno from tab where gpname='" + spinner.getSelectedItem().toString() + "'", null);
                    ArrayList<String> contacts = new ArrayList<String>();
                    if (c.moveToFirst()) {
                        do {
                            contacts.add(c.getString(0));
                        } while (c.moveToNext());
                    }
                    for (int i = 0; i < contacts.size(); i++) {
                        SmsManager sm = SmsManager.getDefault();
                        sm.sendTextMessage(contacts.get(i), null, text.getText().toString(), null, null);
                        Toast.makeText(MainActivity.this, "Sent", Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

       del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    
                        Intent intent= new Intent(MainActivity.this,for_delete.class);
                        startActivity(intent);
            }
        });

       /* edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }



    @Override
    protected void onStart() {
        super.onStart();
        boolean check=true;
        try
        {
            SQLiteDatabase db=openOrCreateDatabase("msg",MODE_PRIVATE,null);
            Cursor c= db.rawQuery("select distinct gpname from tab",null);
            if(c.moveToFirst())
            {
                list1= new ArrayList<String>();
                do {
                    list1.add(c.getString(0));
                }while(c.moveToNext());
                ArrayAdapter <String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                db.close();
            }
            else
            {
                spinner.setAdapter(null);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "problem", Toast.LENGTH_SHORT).show();
        }

    }
}


