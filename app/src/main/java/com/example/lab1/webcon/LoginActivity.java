package com.example.lab1.webcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView rtLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rtLog = (TextView)findViewById(R.id.rtLog);

        rtLog.setText("Olá " + getIntent().getExtras().getString("nome"));
    }
}
