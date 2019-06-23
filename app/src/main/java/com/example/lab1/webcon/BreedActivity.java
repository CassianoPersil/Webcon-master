package com.example.lab1.webcon;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BreedActivity extends AppCompatActivity {

    Spinner spinner;
    EditText ctQnt;
    String url = "";
    String parametros = "";
    Button btCr;
    int aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed);

        spinner = findViewById(R.id.spraca);
        ctQnt = findViewById(R.id.rtQtd);
        btCr = findViewById(R.id.btCr);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.breedOptions, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        aux = 1;
                        break;
                    case 1:
                        aux = 2;
                        break;
                    case 2:
                        aux = 3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btCr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parametros = "user=" + getIntent().getExtras().getString("id") + "&breed=" + aux + "&amount=" + ctQnt.getText();
                url = "http://192.168.1.5/appbov/creation/insertCreation.php";
                new SolicitaDados().execute(url);
            }
        });


    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            String dados[] = resultado.split(",");
            if (resultado.contains("Sucesso!")) {
                Toast.makeText(getBaseContext(), "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Erro na base dos dados", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
