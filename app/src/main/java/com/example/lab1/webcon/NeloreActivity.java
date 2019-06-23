package com.example.lab1.webcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class NeloreActivity extends AppCompatActivity {

    Spinner spinner;
    TextView titulo, descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nelore);

        spinner = findViewById(R.id.spinner_nelore);
        titulo = findViewById(R.id.tituloDescNelore);
        descricao = findViewById(R.id.descNelore);

        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.spinner_opcoes, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        titulo.setText("Características Gerais");
                        descricao.setText("-Animais Nelore apresentam estado geral sadio e vigoroso. A ossatura é leve, " +
                                "robusta e forte, com musculatura compacta e bem distribuída. A masculinidade e a feminilidade são " +
                                "acentuadas. Apresenta resistência natural a parasitas, devido às características de seus pelos, que impedem " +
                                "ou dificultam a penetração de pequenos insetos na superfície da pele ou que aí tentam se fixar. " +
                                "Um fator importante é o baixo nível de metabolismo: alimenta-se menos e por isso gera menos calor.");
                        break;
                    case 1:
                        titulo.setText("Características Ambientais");
                        descricao.setText("-O Nelore é muito resistente ao calor devido à sua superfície corporal ser maior em relação ao corpo e por " +
                                "possuir maior número de glândulas sudoríparas.\n" +
                                "- As características de seus pelos também facilitam o processo de troca com o ambiente.\n" +
                                "- Os animais se destacam pela maior superfície corporal, em relação ao seu peso, o que significa uma " +
                                "área maior para irradiação do calor.\n");
                        break;
                    case 2:
                        titulo.setText("Instalações");
                        descricao.setText("-O curral deve ser construído em terreno firme e seco, preferencialmente plano, não sujeito à erosão.\n" +
                                "-Os conchos será útil no fornecimento de sal mineral de maneira contínua, orienta-se construir os cochos de " +
                                "madeira, com cobertura e colocado um em cada piquete, localizados estrategicamente em locais altos.\n" +
                                "-As cercas deverão ser de arame liso, com 4 fios, estacas de 2 em 2 metros e mourões de 15 em 15 metros.\n");
                        break;
                    case 3:
                        titulo.setText("Peso");
                        descricao.setText("-Uma vaca adulta chega a pesar 600kg, enquanto um touro pode chegar facilmente aos 1200 kg.");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
