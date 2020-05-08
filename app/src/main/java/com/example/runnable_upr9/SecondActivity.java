package com.example.runnable_upr9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    ProgressFragment progressFragment;
    TextView textView;
    ProgressBar progressBar;
    Button button;

    private boolean downloadSuccess;
    private boolean loginSuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        textView = findViewById(R.id.result);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.buttonRun);

        fragmentManager = getSupportFragmentManager();

        button.setOnClickListener(v -> {
            progressFragment = ProgressFragment.newInstance();
            progressFragment.show(fragmentManager, "progress");
            textView.setVisibility(View.INVISIBLE);
            final Thread thread1 = new Thread(new DownloadTask());
            Thread thread2 = new Thread(new LoginTask());

            thread1.start();
            thread2.start();

            new Thread(()->{
                try{
                    thread1.join();
                    thread2.join();
                }
                catch (InterruptedException e){
                    runOnUiThread(()->textView.setText("error"));
                    return ;
                }
                textView.setVisibility(View.VISIBLE);
                if (loginSuccess && downloadSuccess) {
                    runOnUiThread(()->textView.setText("Success!"));
                } else {
                    runOnUiThread(()->textView.setText("Failure.."));
                }
                runOnUiThread(()-> progressFragment.dismiss());
            }).start();
        });
    }

    private class DownloadTask implements Runnable {
        Random r = new Random();
        @Override
        public void run() {
            try{
                int time = r.nextInt((5 - 2) + 1) + 2;
                Thread.sleep(time * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            downloadSuccess = r.nextBoolean();
        }
    }

    private class LoginTask implements Runnable {
        Random r = new Random();
        @Override
        public void run() {
            try{
                int time = r.nextInt((5 - 3) + 1) + 3;
                Thread.sleep(time  * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            loginSuccess = r.nextBoolean();
        }
    }
}
