package com.estebannaranjo.proyectocartas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.estebannaranjo.proyectocartas.ApiRest.APIService;
import com.estebannaranjo.proyectocartas.ApiRest.APIUtils;
import com.estebannaranjo.proyectocartas.Model.Game;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Actividad que gestiona la pantalla principal
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    String nick;
    int idsesion;
    private Button btnJugar;
    private Toolbar toolbar;
    private APIService mAPIService;

    PreferencesActivity preferencesActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJugar = findViewById(R.id.btnJugar);
        toolbar = findViewById(R.id.appbar);
        preferencesActivity = new PreferencesActivity();
        mAPIService = APIUtils.getAPIService();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int idsesion = extras.getInt("idsesion");
            String nick = extras.getString("nick");
            this.idsesion = idsesion;
            this.nick = nick;
        }
        toolbar.setTitle(" "+nick);
        toolbar.setLogo(R.drawable.user);
        setSupportActionBar(toolbar);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dojugar(nick,idsesion);

            }
        });

    }


    /**Metodo del endpoint que empieza partida
     * @param nick
     * @param idsesion
     */
    public void dojugar(String nick ,int idsesion) {
        mAPIService.startGame(nick,idsesion).enqueue(new Callback<Game>() {

            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    intent.putExtra("game", json);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e(TAG, "Error al empezar partida. " + t.getMessage());

            }
        });
    }


    /**
     * Metodo que hace la gestion de los ajustes del sistema
     */
    private void SharePreferences() {
            SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            boolean cr7icon = preferencias.getBoolean("cr7", false);

            if(cr7icon){
                toolbar.setLogo(R.drawable.cr);


            }else{
                toolbar.setLogo(R.drawable.user);

            }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.preferenciasMenu :
                startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharePreferences();
    }
}