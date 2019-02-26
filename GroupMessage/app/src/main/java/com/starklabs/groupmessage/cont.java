package com.starklabs.groupmessage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cont extends AppCompatActivity {

    LinearLayout ll;
    Button done,more;
    EditText name;
    ArrayList <TextView> list;
    int count=0;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);
        ll = findViewById(R.id.ll);
        done=findViewById(R.id.done);
        more=findViewById(R.id.more);
        name=findViewById(R.id.name);
        list= new ArrayList<TextView>();
        db=openOrCreateDatabase("msg",MODE_PRIVATE,null);
        db.execSQL("create table if not exists tab (name varchar(100),pno varchar(100),gpname varchar(50))");
        db.close();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals(null))
                {
                    more.setHint("Please Enter Name of Group");
                }
                else
                {

                    for(int i=0;i<list.size();i++)
                    {
                        String n,p,t;
                        String str[]=list.get(i).getText().toString().split(":");
                        n=str[0];
                        p=str[1];
                        t=name.getText().toString();
                        db=openOrCreateDatabase("msg",MODE_PRIVATE,null);
                        db.execSQL("insert into tab values('"+n+"','"+p+"','"+t+"')");
                    }

                    db.close();
                    Intent in= new Intent(cont.this,MainActivity.class);
                    startActivity(in);

                }

            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name = null, cNumber = null;
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            Uri contactData = data.getData();
            Cursor c = managedQuery(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                try {
                    if (hasPhone.equalsIgnoreCase("1")) {
                        Cursor phones = getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                null, null);
                        phones.moveToFirst();
                        cNumber = phones.getString(phones.getColumnIndex("data1"));

                    }
                    name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    TextView tv = new TextView(this);
                    tv.setText(name + ":" + cNumber);
                    ll.addView(tv);
                    list.add(tv);
                } catch (Exception ex) {
                    Toast.makeText(this, "Error in selecting contacts", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

}
