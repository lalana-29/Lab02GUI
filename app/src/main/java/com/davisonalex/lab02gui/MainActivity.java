package com.davisonalex.lab02gui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        tv = findViewById(R.id.text);
        et = findViewById(R.id.field);
        ct = 0;
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
            }
        });
    }
}