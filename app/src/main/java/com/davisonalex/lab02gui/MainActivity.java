package com.davisonalex.lab02gui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b;
    Button dialogue;
    TextView tv;

    EditText et;
    TextView output;
    int ct;
    TextView countdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        tv = findViewById(R.id.text);
        et = findViewById(R.id.field);
        ct = 0;
        countdown = findViewById(R.id.countdown);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prev = String.valueOf(tv.getText());
                String next = String.valueOf(et.getText());
                tv.setText(next);
                if(!prev.equals(next))
                    ct++;
            }
        });
        dialogue = findViewById(R.id.hmm);
        output = findViewById(R.id.talk);
        String[] lines = getResources().getStringArray(R.array.messages);

        dialogue.setOnClickListener(new View.OnClickListener() {
            int idx = 0;
            @Override
            public void onClick(View v) {
                String msg = lines[idx];
                if(idx==17) {
                    System.out.println(msg);
                    msg = String.format(msg, ct);
                }
                output.setText(msg);
                idx++;
                if(idx==lines.length)
                    idx = 0;
                TimeThread tt = new TimeThread(MainActivity.this);
                tt.start();
            }
        });

    }
}

class TimeThread extends Thread{
    private Activity act;
    private int min;
    private String sec;
    public TimeThread(Activity view){
        super();
        act = view;
    }
    public void run(){
        min = 2;
        sec = "00";
        Boolean flag = true;
        //tv.setText("2:00");

        while(flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(sec.equals("00")){
                if(min==0)
                    flag = false;
                else{
                    min -= 1;
                    sec = "59";
                }
            }
            else{

                int seconds = Integer.parseInt(sec);
                seconds -= 1;
                if(seconds<10)
                    sec = "0"+seconds;
                else
                    sec = ""+seconds;
            }
            act.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    TextView tv = act.findViewById(R.id.countdown);
                    tv.setText(min+":"+sec);

                }
            });

        }
        System.exit(0);
    }
}