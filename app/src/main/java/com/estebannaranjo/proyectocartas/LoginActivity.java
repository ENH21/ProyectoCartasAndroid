package com.estebannaranjo.proyectocartas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.estebannaranjo.proyectocartas.ApiRest.APIService;
import com.estebannaranjo.proyectocartas.ApiRest.APIUtils;
import com.estebannaranjo.proyectocartas.Model.Jugador;
import com.estebannaranjo.proyectocartas.Model.Sesion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Actividad que controla la identificacion de usuario
 */
public class LoginActivity extends AppCompatActivity {
    private APIService mAPIService;
    private EditText etNick;
    private EditText etPassword;
    private Button btnEntrar;
    private Button btnRegistrarse;
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etNick = findViewById(R.id.etNick);
        etPassword = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
        mAPIService = APIUtils.getAPIService();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick1 = etNick.getText().toString();
                String password1 = etPassword.getText().toString();
                if(!nick1.equals("")&& !password1.equals("")){

                    dologin(nick1,password1);
                    etNick.setText("");
                    etPassword.setText("");
                }else{
                    Toast.makeText(LoginActivity.this, "Introduce nick y password",
                            Toast.LENGTH_LONG).show();
                }


            }
        });
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick1 = etNick.getText().toString();
                String password1 = etPassword.getText().toString();
                if(!nick1.equals("")&& !password1.equals("")){
                    doregistrarse(nick1,password1);
                    etNick.setText("");
                    etPassword.setText("");
                }else{
                    Toast.makeText(LoginActivity.this, "Introduce nick y password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /**Metodo del endpoint que hace el login
     * @param nick
     * @param password
     */
    public void dologin(final String nick , final String password) {

        mAPIService.login(nick,password).enqueue(new Callback<Sesion>() {

            @Override
            public void onResponse(Call<Sesion> call, Response<Sesion> response) {
                if(response.isSuccessful()) {

                    if(response.body()==null){
                        Toast.makeText(LoginActivity.this, "No existe el usuario con esa contraseña",
                                Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Bienvenido "+nick+"",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.putExtra("nick", response.body().getIdjugador());
                        intent.putExtra("idsesion", response.body().getIdsesion());
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onFailure(Call<Sesion> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "No coinciden usuario y contraseña",
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Error al hacer login. " + t.getMessage());

            }
        });
    }

    /**Metodo del endpoint que se registra , que seria crear jugador nuevo
     * @param nick
     * @param password
     */
    public void doregistrarse(final String nick , final String password) {

        mAPIService.crearJugador(nick,password).enqueue(new Callback<Jugador>() {

            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Bienvenido "+nick+"",
                            Toast.LENGTH_LONG).show();
                    dologin(nick,password);

                }else if(response.code()==400){
                    Toast.makeText(LoginActivity.this, "El usuario ya existe",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
                public void onFailure(Call<Jugador> call, Throwable t) {
                    Log.e(TAG, "Error al registrarse. " + t.getMessage());

            }
        });
    }

}
