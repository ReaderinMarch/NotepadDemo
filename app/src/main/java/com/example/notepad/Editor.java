package com.example.notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author lfh
 * @project Notepad
 * @package_name com.example.notepad
 * @date 20-9-23
 * @time 上午9:56
 * @year 2020
 * @month 09
 * @month_short 九月
 * @month_full 九月
 * @day 23
 * @day_short 星期三
 * @day_full 星期三
 * @hour 09
 * @minute 56
 */
public class Editor extends Activity implements View.OnClickListener{
    private Button btn_save;
    private Button btn_cancel;
    private Button btn_delete;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);
        btn_save = findViewById(R.id.save);
        btn_cancel = findViewById(R.id.cancel);
        btn_delete = findViewById(R.id.delete);
        editText = findViewById(R.id.editText);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        System.out.println(content+"!!!!!!");
        if(content!=null){
            editText.setText(getIntent().getStringExtra("content"));
        }
        System.out.println(content+"!!!!!!");
        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

    }
    public static String getRandomFileName(){
        String string  = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Random random = new Random();
        int randomNumber = (int) (random.nextDouble()*(99999-10000+1))+10000;
        return "untitled_"+randomNumber + "_" +string+".txt";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                File file = new File(Environment.getExternalStorageDirectory(),getRandomFileName());
                editText = findViewById(R.id.editText);
                String textString = editText.getText().toString();
                System.out.println(textString);
                try(FileOutputStream fileOutputStream =  new FileOutputStream(file)){
                    fileOutputStream.write(textString.getBytes());
                    Toast.makeText(this,"Sucess!!",Toast.LENGTH_LONG).show();
                    System.out.println("Sucess!!");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.delete:
                try {
                    File file1 = new File(getIntent().getStringExtra("key"));
                    if(file1.delete()){
                        Toast.makeText(this,"Delete!!!",Toast.LENGTH_SHORT).show();
                        System.out.println(file1.getName() + " Delete!!!");
                    }else{
                        Toast.makeText(this,"Error!!!",Toast.LENGTH_SHORT).show();
                        System.out.println("Error!!!");
                    };
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.cancel:
                finish();
                break;
        }
    }
}
