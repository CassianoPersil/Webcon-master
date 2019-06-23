package com.example.lab1.webcon;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadActivity extends AppCompatActivity {


    Button btCad;
    EditText ctLogin, ctSenha, ctRepSenha, ctEmail;


    String url = "";
    String parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        btCad = (Button)findViewById(R.id.btSalvar);
        ctEmail = (EditText)findViewById(R.id.ctEmail);
        ctLogin = (EditText)findViewById(R.id.ctLogin);
        ctSenha = (EditText)findViewById(R.id.ctSenha);
        ctRepSenha = (EditText)findViewById(R.id.ctRepSenha);

        btCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()){

                    String email = ctEmail.getText().toString();
                    String login = ctLogin.getText().toString();
                    String senha = ctSenha.getText().toString();
                    String repSenha = ctRepSenha.getText().toString();
                    if(email.isEmpty()  || login.isEmpty() || senha.isEmpty() || repSenha.isEmpty()){
                        Toast.makeText(getBaseContext(), "Há Campo(s) Vazio(s)", Toast.LENGTH_SHORT).show();
                    }else if(!senha.equals(repSenha)){
                        Toast.makeText(getBaseContext(), "Senhas não coincidem!", Toast.LENGTH_SHORT).show();
                    }else{
                        url = "http://192.168.1.5/appbov/user/insert_user.php";
                        parametros = "nome=" + login + "&email=" + email + "&senha=" + senha;
                        new SolicitaDados().execute(url);
                    }
                }

            }
        });
    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPostExecute(String resultado){
            if (resultado != null && !resultado.isEmpty() && resultado.contains("E-mail_Erro")) {
                Toast.makeText(getApplicationContext(), "Este e-email ja esta cadastrado", Toast.LENGTH_SHORT).show();
            }else if (resultado != null && !resultado.isEmpty() && resultado.contains("Registro_Ok")){
                Toast.makeText(getApplicationContext(),"Registro concluido com sucesso!", Toast.LENGTH_SHORT).show();
                Intent abreInicio = new Intent(CadActivity.this, MainActivity.class);
                startActivity(abreInicio);
            }else{
                Toast.makeText(getApplicationContext(), "Ocorreu um erro: "+resultado, Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
