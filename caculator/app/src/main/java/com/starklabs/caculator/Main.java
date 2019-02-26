package com.starklabs.caculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener
{
    int i=-1;
    boolean isResult=false;
    Button  bt1;
    Button  bt2;
    Button  bt3;
    Button  bt4;
    Button  bt5;
    Button  bt6;
    Button  bt7;
    Button  bt8;
    Button  bt9;
    Button bt10;
    Button bt11;
    Button bt12;
    Button  bt13;
    Button  bt14;
    Button  bt15;
    Button  bt16;
    Button  bt17;
    Button  bt18;
    Button  bt19;
    Button  bt20;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView) findViewById(R.id.t1);
        t2=(TextView) findViewById(R.id.t2);
        bt1=(Button)findViewById(R.id.b1);
        bt2=(Button)findViewById(R.id.b2);
        bt3=(Button)findViewById(R.id.b3);
        bt4=(Button)findViewById(R.id.b4);
        bt5=(Button)findViewById(R.id.b5);
        bt6=(Button)findViewById(R.id.b6);
        bt7=(Button)findViewById(R.id.b7);
        bt8=(Button)findViewById(R.id.b8);
        bt9=(Button)findViewById(R.id.b9);
        bt10=(Button)findViewById(R.id.b10);
        bt11=(Button)findViewById(R.id.b11);
        bt12=(Button)findViewById(R.id.b12);
        bt13=(Button)findViewById(R.id.b13);
        bt14=(Button)findViewById(R.id.b14);
        bt15=(Button)findViewById(R.id.b15);
        bt16=(Button)findViewById(R.id.b16);
        bt17=(Button)findViewById(R.id.b17);
        bt18=(Button)findViewById(R.id.b18);
        bt19=(Button)findViewById(R.id.b19);
        bt20=(Button)findViewById(R.id.b20);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
        bt11.setOnClickListener(this);
        bt12.setOnClickListener(this);
        bt13.setOnClickListener(this);
        bt14.setOnClickListener(this);
        bt15.setOnClickListener(this);
        bt16.setOnClickListener(this);
        bt17.setOnClickListener(this);
        bt18.setOnClickListener(this);
        bt19.setOnClickListener(this);
        bt20.setOnClickListener(this);

        registerForContextMenu(t1);
        registerForContextMenu(t2);

    }

    @Override
    public void onClick(View v) {
        double x=0, result=0.0, y=0;
        Button button1=null;
        double res=0;
        if (v.getId() == R.id.b5 || v.getId() == R.id.b6 || v.getId() == R.id.b7 || v.getId() == R.id.b9 || v.getId() == R.id.b10 || v.getId() == R.id.b11 || v.getId() == R.id.b13 || v.getId() == R.id.b14 || v.getId() == R.id.b15 || v.getId() == R.id.b17 || v.getId() == R.id.b18 || v.getId() == R.id.b19) {
            button1 = (Button) findViewById(v.getId());
            if(t1.getText().toString().equals("Invalid"))
            {
                t1.setText("");
            }
            else if(isResult==true)
            {
                t2.setText(t1.getText());
                t1.setText("");
                isResult=false;
            }
            t1.append(button1.getText().toString());
        }

        else if (v.getId() == R.id.b3 || v.getId() == R.id.b4 || v.getId() == R.id.b8 || v.getId() == R.id.b12 || v.getId() == R.id.b16) {

                isResult = false;
                t2.setText(t1.getText());
                t1.setText("");
                i=v.getId();
        }
        else if(v.getId()==R.id.b20)
        {
            try{
                x=Double.parseDouble(t2.getText().toString());
                y=Double.parseDouble(t1.getText().toString());
                t2.setText(t1.getText());
                if(i==R.id.b16)
                {
                    result=x+y;
                    t1.setText(""+result);
                    isResult=true;
                }
                else if(i==R.id.b12)
                {
                    result=x-y;
                    t1.setText(""+result);
                    isResult=true;
                }
                else if(i==R.id.b8)
                {
                    result=x*y;
                    t1.setText(""+result);
                    isResult=true;
                }
                else if(i==R.id.b4)
                {
                    result=x/y;
                    t1.setText(""+result);
                    isResult=true;
                }
                else if(i==R.id.b3)
                {
                    result=x%y;
                    t1.setText(""+result);
                    isResult=true;
                }
                else {
                    t1.setText("Invalid");
                    isResult=false;
                }

            }
            catch(Exception e)
            {
                t1.setText("Invalid");
                isResult=false;
            }

        }
        else if(v.getId()==R.id.b1)
        {
            t1.setText("");
            t2.setText("");
        }
        else if(v.getId()==R.id.b2)
        {
            String t=t1.getText().toString();
            try{
                String temp=t.substring(0,t.length()-1);
                t1.setText(temp);
            }
            catch(Exception e)
            {

            }
            finally {
                if(t.length()==1)
                {
                    t1.setText("");
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item1)
        {
            Toast.makeText(this,"Calculator by Stark Labs",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Main.this,CustomDialog.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Would you like to exit?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Main.this.finish();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    closeOptionsMenu();
                }
            });
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mi= getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.item1)
        {
            Toast.makeText(this,"Calculator by Stark Labs",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Main.this,CustomDialog.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Would you like to exit?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Main.this.finish();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    closeContextMenu();
                }
            });
            alert.show();
        }

        return super.onContextItemSelected(item);
    }
}


