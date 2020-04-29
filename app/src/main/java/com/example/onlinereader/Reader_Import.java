package com.example.onlinereader;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Reader_Import extends AppCompatActivity {

    private static final String TAG = "Import";
    private EditText mInput;
    private Button mWrite, mRead;
    private TextView tv1, tv2;
    private String a, b, c, d;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_import);
        initView();
        writeTxt();
    }

    public void initView() {
        mInput = findViewById(R.id.ed_input);
        mWrite = findViewById(R.id.btn_write);
        mRead = findViewById(R.id.btn_read);
        tv1 = findViewById(R.id.tv_tv1);
        tv2 = findViewById(R.id.tv_tv2);

        mRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取文件
                readTxt();
            }
        });

    }

    public void writeTxt() {
        //新建文件夹
        String folderName = "User";
        File sdCardDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), folderName);

        if (!sdCardDir.exists()) {
            if (!sdCardDir.mkdirs()) {

                try {
                    sdCardDir.createNewFile();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        try {
            //新建文件jia
            File saveFile = new File(sdCardDir, "user.txt");

            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            // FileOutputStream outStream =null;
            //outStream = new FileOutputStream(saveFile);

            final FileOutputStream outStream = new FileOutputStream(saveFile);


            mWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = mInput.getText().toString().trim();
                    try {
                        outStream.write(str.getBytes());
                        outStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "onClick: --------------" + e.toString());
                    }

                    Toast.makeText(Reader_Import.this, "写入成功", Toast.LENGTH_SHORT).show();
                    tv1.setText(str);

                }
            });
            //outStream.write("测试写入文件".getBytes());
            //outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readTxt() {

        BufferedReader bre = null;
        String str = null;
        try {
            String file = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS) + "/User/user.txt";
            bre = new BufferedReader(new FileReader(file));//此时获取到的bre就是整个文件的缓存流
            while ((str = bre.readLine()) != null) { // 判断最后一行不存在，为空结束循环

                Log.e(TAG, "readTxt: a------------" + str);


                String[] arr = str.split("\\s+");
                for (String ss : arr) {
                    a = arr[0];
                }
                tv2.setText(str);
            }

        } catch (Exception e) {
            Log.e(TAG, "readTxt: ---------------" + e.toString());

        }

    }



}
