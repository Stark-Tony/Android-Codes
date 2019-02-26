package com.starklabs.musicplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Main extends AppCompatActivity {

    Button button1,button2,button3;
    ImageView image;
    TextView text,text2,text3;
    SeekBar seekbar;
    MediaPlayer mp;
    byte arr[];
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        image=(ImageView)findViewById(R.id.imgview);
        text=(TextView)findViewById(R.id.tview1);
        text2=(TextView)findViewById(R.id.tview2);
        text3=(TextView)findViewById(R.id.tview3);
        seekbar=(SeekBar)findViewById(R.id.seekbar);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {

                   if (mp.isPlaying()) {
                       mp.pause();
                   }

               }
               catch (Exception e)
               {

               }
               finally {
                   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                   intent.setType("audio/*");
                   startActivityForResult(intent, 1);
               }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp.start();

                }
                catch(Exception e)
                {
                    Toast.makeText(Main.this, "No audio selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   mp.pause();
               }
               catch (Exception e)
               {

               }

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int p=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                   try {
                       seekbar.setProgress(p);
                       mp.seekTo(p);
                   }
                   catch (Exception e)
                   {

                   }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK)
        {
                image.setVisibility(View.VISIBLE);
            try {
                mp= new MediaPlayer();
                mp.setDataSource(this,data.getData());
                mp.prepare();
                int total_sec=0,total_min=0;
                int t=mp.getDuration();
                total_sec=t/1000;
                if(total_sec>59)
                {
                    total_min=total_sec/60;
                    total_sec%=60;
                }
                if(total_sec<10)
                    text3.setText(total_min+":"+"0"+total_sec);
                else
                    text3.setText(total_min+":"+total_sec);
                mp.start();
                seekbar.setMax(mp.getDuration());
                text.setText(data.getDataString().trim());
                CountDownTimer ct=new CountDownTimer(mp.getDuration(),10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        seekbar.setProgress(mp.getCurrentPosition());
                        int p=seekbar.getProgress();
                        int min=0,sec=0;
                        sec=p/1000;
                        if(sec>59)
                        {
                            min=sec/60;
                            sec=sec%60;
                        }
                        if(sec<10)
                            text2.setText(min+":"+"0"+sec);
                        else
                            text2.setText(min+":"+sec);
                    }
                    @Override
                    public void onFinish() {

                    }
                };
                ct.start();
                try {
                    MediaMetadataRetriever ret = new MediaMetadataRetriever();
                    ret.setDataSource(this, data.getData());
                    arr = ret.getEmbeddedPicture();
                    Bitmap songImage = BitmapFactory.decodeByteArray(arr, 0, arr.length);
                    image.setImageBitmap(songImage);
                    text.setText(ret.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
                }
                catch (Exception e)
                {
                    image.setVisibility(View.INVISIBLE);
                    text.setText("Unknown");
                }
            } catch (Exception e) {
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }

    }
}
