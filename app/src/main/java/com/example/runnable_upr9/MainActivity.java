package com.example.runnable_upr9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    EditText editText;
    Button button, buttonNext, buttonRunnable;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        buttonNext = findViewById(R.id.buttonNext);
        buttonRunnable = findViewById(R.id.buttonRunnable);

        fragmentManager = getSupportFragmentManager();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String time = editText.getText().toString();
                runner.execute(time);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        buttonRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RunnableTask.class);
                startActivity(i);
            }
        });
    }

    public class AsyncTaskRunner extends AsyncTask<String, String, String> {
        private ProgressFragment progressFragment;

        @Override
        protected void onPreExecute() {
            progressFragment = ProgressFragment.newInstance();
            progressFragment.show(fragmentManager, "progress");
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("AsyncTask started...");
            String resp;
            try {
                int time = Integer.parseInt(strings[0]) * 1000;
                Thread.sleep(time);
                resp = "Slept for " + strings[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            textView.setText("Completed");
            textView.setTextColor(Color.GREEN);
            textView.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            progressFragment.setProgress(Integer.parseInt(values[0]));
        }

    }
}