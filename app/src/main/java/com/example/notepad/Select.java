package com.example.notepad;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lfh
 * @project Notepad
 * @package_name com.example.notepad
 * @date 20-9-20
 * @time 下午2:07
 * @year 2020
 * @month 09
 * @month_short 九月
 * @month_full 九月
 * @day 20
 * @day_short 星期日
 * @day_full 星期日
 * @hour 14
 * @minute 07
 */
public class Select extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private Button btn_new;
    private Button btn_exit;
    private EditText editText;
    private byte[] buffer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_interface);
        listView=findViewById(R.id.listView);
        File file = Environment.getExternalStorageDirectory();
        File[] files = file.listFiles();
        if(files!=null){
            List<String> filenames =getFilesAllName(files);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.text, filenames);
            listView.setAdapter(adapter);
        }
        btn_exit=findViewById(R.id.exit);
        btn_new = findViewById(R.id.newFile);
        btn_exit.setOnClickListener(this);
        btn_new.setOnClickListener(this);
        listView.setOnItemClickListener(this);

    }

    public static List<String> getFilesAllName(File[] files){
            List<String> s =new ArrayList<>();
            int length = files.length;
            for(int i =0;i<length;i++){
                s.add(files[i].getAbsolutePath());
            }
            return s;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newFile:
                Intent intent = new Intent(this,Editor.class);
                startActivity(intent);

            case R.id.exit:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        File file = Environment.getExternalStorageDirectory();
        File[] files = file.listFiles();
        List<String> filenames =getFilesAllName(files);

        Intent intent = new Intent(this,Editor.class);
        intent.putExtra("key",filenames.get(i));
        editText=findViewById(R.id.editText);
        try(FileInputStream fileInputStream = new FileInputStream(filenames.get(i))) {
            buffer= new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            System.out.println(filenames.get(i)+"+++++++++");
            intent.putExtra("content",new String(buffer));
            System.out.println("Read finish");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
