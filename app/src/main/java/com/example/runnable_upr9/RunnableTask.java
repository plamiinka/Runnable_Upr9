package com.example.runnable_upr9;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RunnableTask extends AppCompatActivity {

    Handler handler;
    EditText editEmail;
    EditText editPassword;
    TextView textViewResult;
    Button buttonGo;
    Button buttonStart;
    Button buttonPause;
    int seconds = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runnable_layout);

        handler = new Handler();

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        textViewResult = findViewById(R.id.textViewResult);
        buttonGo = findViewById(R.id.buttonGO);
        buttonStart = findViewById(R.id.buttonSTART);
        buttonPause = findViewById(R.id.buttonPause);

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                textViewResult.setText("Current time: " + ++seconds);
                handler.postDelayed(this, 1000);
            }
        };

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new LoginTask(new DataValidator()));
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(r);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(r);
            }
        });
    }

    private class LoginTask implements Runnable {
        private DataValidator validator;

        LoginTask(DataValidator validator){
            this.validator = validator;
        }

        @Override
        public void run() {
            if(validator.isValidEmail(editEmail.getText().toString()) && validator.isValidPassword(editPassword.getText().toString())){
                textViewResult.setText("Success!");
            }
            else {
                textViewResult.setText("Unsuccessful login!");
            }
        }
    }
}
