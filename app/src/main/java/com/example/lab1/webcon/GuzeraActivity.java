package com.example.lab1.webcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class GuzeraActivity extends AppCompatActivity {

    Spinner spinner;
    TextView titulo, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guzera);

        spinner = findViewById(R.id.spinner_guzera);
        titulo = findViewById(R.id.tituloDescGuzera);
        descricao = findViewById(R.id.descGuzera);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.spinner_opcoes2, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        titulo.setText("Características Gerais");
                        descricao.setText("-Porte grande com pelagem que varia do cinza claro ao cinza escuro, havendo tons pardos e prateados;\n" +
                                "-A cabeça é relativamente curta, larga e expressiva, com perfil subcôncavo a retilíneo; A fronte é moderadamente larga, " +
                                "subcôncava ou quase plana;");
                        break;
                    case 1:
                        titulo.setText("Características Ambientais");
                        descricao.setText("-Pele preta, bem pigmentada, com membros bem desenvolvidos e musculados, permitem ao guzerá resistir a longas " +
                                "caminhadas sob o sol tropical, à procura de água e alimento. Adapta-se no Nordeste brasileiro, desde áreas férteis litorâneas, " +
                                "no agreste, até o sertão semiárido.");
                        break;
                    case 2:
                        titulo.setText("Peso");
                        descricao.setText("-O ganho em peso dos animais da raça é muito bom, ultrapassando com facilidade médias superiores a " +
                                "1.000 gramas/dia no confinamento.");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
