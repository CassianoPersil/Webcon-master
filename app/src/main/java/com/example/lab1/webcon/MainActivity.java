package com.example.lab1.webcon;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ctLogin, ctSenha;
    Button btLogar;
    TextView rtCad;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rtCad = (TextView) findViewById(R.id.rtCad);
        ctLogin = (EditText) findViewById(R.id.ctLogin);
        ctSenha = (EditText) findViewById(R.id.ctSenha);
        btLogar = (Button) findViewById(R.id.btLogar);
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    String login = ctLogin.getText().toString();
                    String senha = ctSenha.getText().toString();

                    if (login.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Há Campo(s) Vazio(s)", Toast.LENGTH_SHORT).show();
                    } else {
                        url = "http://192.168.1.5/login/logar.php";
                        parametros = "email=" + login + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }
                }
            }
        });
        rtCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cad = new Intent(MainActivity.this, CadActivity.class);
                startActivity(cad);
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
            if (resultado.contains("Login_Ok")) {
                Intent intent = new Intent(MainActivity.this, MenudeAnimais.class);
                intent.putExtra("id", dados[1]);
                intent.putExtra("nome", dados[2]);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Bem vindo!", Toast.LENGTH_SHORT).show();
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
