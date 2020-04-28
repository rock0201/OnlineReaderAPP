package com.example.onlinereader;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Book_Read extends AppCompatActivity {
    private String[] joke;
    private int index;
    private TextView textView_title;
    private TextView textView_content;
    private Button button_next;
    private Button button_previous;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_read);
        button_next = (Button) findViewById(R.id.button_next);
        button_previous = (Button) findViewById(R.id.button_pre);
        textView_title = (TextView) findViewById(R.id.editText_title);
        textView_content = (TextView) findViewById(R.id.editText_content);
        textView_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        InputStream input=getResources().openRawResource(R.raw.jokes);
        Reader reader=new InputStreamReader(input);
        StringBuffer stringBuffer=new StringBuffer();


        char b[]=new char[1024];
        int len=-1;
        try {
            while ((len = reader.read(b))!= -1){
                stringBuffer.append(b);
            }
        }catch (IOException e){
            Log.e("笑话","IOException");
        }
        String s=(stringBuffer.delete(0,3)).toString();
        joke=s.split("\\*\\*");
        setIndex();
        textView_title.setText(joke[index]);
        textView_content.setText(joke[index+1]);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index+=2;
                if(index==joke.length){
                    setIndex();
                }
                while(joke[index].length()>10){
                    index+=1;
                }
                textView_title.setText(joke[index]);
                textView_content.setText(joke[index+1]);
            }
        });
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index-=2;
                if(index==0)
                    setIndex();
                while(joke[index].length()>10){
                    index-=1;
                }
                textView_title.setText(joke[index]);
                textView_content.setText(joke[index+1]);
            }
        });
    }
    private void setIndex(){
        index=(int)(Math.random()*joke.length);
        if(index%2==1)
            index++;
        index=index%joke.length;
        while(joke[index].length()>10){
            index+=1;
        }
    }

}
