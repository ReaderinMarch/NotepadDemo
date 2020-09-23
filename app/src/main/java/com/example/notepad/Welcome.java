package com.example.notepad;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lfh
 * @project Notepad
 * @package_name com.example.notepad
 * @date 20-9-20
 * @time 下午2:30
 * @year 2020
 * @month 09
 * @month_short 九月
 * @month_full 九月
 * @day 20
 * @day_short 星期日
 * @day_full 星期日
 * @hour 14
 * @minute 30
 */
public class Welcome extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences preferences = getSharedPreferences(
                getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        System.out.println("First:"+preferences.getInt(getString(R.string.preference_file_key), 1));
        if (preferences.getInt(getString(R.string.preference_file_key), 1) == 1){
            editor.putInt(getString(R.string.preference_file_key),
                    preferences.getInt(getString(R.string.preference_file_key), 1)+1);
            editor.apply();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("delay1");
                    Intent selectIntent = new Intent(getApplicationContext(),Select.class);
                    startActivity(selectIntent);
                }
            },2000);
            System.out.println("delay2");
        }
        else if (preferences.getInt(getString(R.string.preference_file_key), 1) > 1) {
            Intent selectIntent = new Intent(this,Select.class);
            startActivity(selectIntent);

        }
        System.out.println("Second:"+preferences.getInt(getString(R.string.preference_file_key), 1));

    }




}
