package com.example.lab1.webcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SindiActivity extends AppCompatActivity {

    Spinner spinner;
    TextView titulo, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sindi);

        spinner = findViewById(R.id.spinner_Sindi);
        titulo = findViewById(R.id.tituloDescSindi);
        descricao = findViewById(R.id.descSindi);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.spinner_opcoes2, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        titulo.setText("Características Gerais");
                        descricao.setText(getIntent().getExtras().getString("gerais"));
                        break;
                    case 1:
                        titulo.setText("Características Ambientais");
                        descricao.setText(getIntent().getExtras().getString("ambientais"));
                        break;
                    case 2:
                        titulo.setText("Peso");
                        descricao.setText(getIntent().getExtras().getString("peso"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
