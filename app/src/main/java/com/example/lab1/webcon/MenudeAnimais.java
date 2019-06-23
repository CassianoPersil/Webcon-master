package com.example.lab1.webcon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

public class MenudeAnimais extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menude_animais);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menude_animais, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Nelore) {
            url = "http://192.168.1.5/appbov/breed/selectBreed.php";
            parametros = "breed=" + 2;
            new SolicitaDados().execute(url);
        } else if (id == R.id.Guzera) {
            url = "http://192.168.1.5/appbov/breed/selectBreed.php";
            parametros = "breed=" + 1;
            new SolicitaDados().execute(url);
        } else if (id == R.id.Sindi) {
            url = "http://192.168.1.5/appbov/breed/selectBreed.php";
            parametros = "breed=" + 3;
            new SolicitaDados().execute(url);
        }else if(id == R.id.nav_breed){
            Intent intent = new Intent(getBaseContext(), BreedActivity.class);
            intent.putExtra("id", getIntent().getExtras().getString("id"));
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            String dados[] = resultado.split(";");
            if(Integer.parseInt(dados[0]) == 1){
                Intent intent = new Intent(getBaseContext(), GuzeraActivity.class);
                intent.putExtra("gerais", dados[1]);
                intent.putExtra("ambientais", dados[2]);
                intent.putExtra("peso", dados[3]);
                startActivity(intent);
            }else if(Integer.parseInt(dados[0]) == 2){
                Intent intent = new Intent(getBaseContext(), NeloreActivity.class);
                intent.putExtra("gerais", dados[1]);
                intent.putExtra("ambientais", dados[2]);
                intent.putExtra("peso", dados[3]);
                intent.putExtra("instalacoes", dados[4]);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getBaseContext(), SindiActivity.class);
                intent.putExtra("gerais", dados[1]);
                intent.putExtra("ambientais", dados[2]);
                intent.putExtra("peso", dados[3]);
                intent.putExtra("instalacoes", dados[4]);
                startActivity(intent);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
