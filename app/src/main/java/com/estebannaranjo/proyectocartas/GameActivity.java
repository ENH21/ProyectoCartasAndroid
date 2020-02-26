package com.estebannaranjo.proyectocartas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.estebannaranjo.proyectocartas.ApiRest.APIService;
import com.estebannaranjo.proyectocartas.ApiRest.APIUtils;
import com.estebannaranjo.proyectocartas.Model.Carta;
import com.estebannaranjo.proyectocartas.Model.Game;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Esta es la actividad que gestiona la pantalla de juego , la partida
 * @author Esteban
 *
 */
public class GameActivity extends AppCompatActivity {
    private APIService mAPIService;
    private final String TAG = this.getClass().getSimpleName();
    private Button btnMotor;
    private Button btnCilindros;
    private Button btnVelocidad;
    private Button btnPotencia;
    private Button btnConsumo;
    private Button btnRevoluciones;
    private Button btnFeatureCpu;
    private Button btnSiguiente;
    private Button btnAtras;
    private Button btnGo;
    private ImageView ivJugador;
    private TextView tvPuntosJugador;
    private TextView tvPuntosCpu;
    private TextView tvTurno;
    private TextView tvMano;
    private TextView tvResult;
    private int idcartaActual;
    private int indexFoto;
    private String feature;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mAPIService = APIUtils.getAPIService();
        btnCilindros = findViewById(R.id.btnCilindros);
        btnVelocidad = findViewById(R.id.btnVelocidad);
        btnPotencia = findViewById(R.id.btnPotencia);
        btnMotor = findViewById(R.id.btnMotor);
        btnConsumo = findViewById(R.id.btnConsumo);
        btnRevoluciones = findViewById(R.id.btnRevoluciones);
        btnFeatureCpu = findViewById(R.id.btnFeatureCpu);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnAtras = findViewById(R.id.btnAtras);
        btnGo = findViewById(R.id.btnGo);
        ivJugador = findViewById(R.id.ivJugador);
        tvPuntosJugador = findViewById(R.id.tvPuntosJugador);
        tvPuntosCpu = findViewById(R.id.tvPuntosCpu);
        tvTurno = findViewById(R.id.tvTurno);
        tvMano = findViewById(R.id.tvMano);
        tvResult = findViewById(R.id.tvResult);
        feature="";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gameS = extras.getString("game");
            Gson gson = new Gson();
            game = gson.fromJson(gameS , Game.class);
        }

        tvMano.setText(String.valueOf(game.getMano()));
        tvTurno.setText(getTurnoString());
        tvPuntosCpu.setText(String.valueOf(game.getPuntosCPU()));
        tvPuntosJugador.setText(String.valueOf(game.getPuntosJugador()));

        idcartaActual = game.getCartasUsuario().get(0).getIdcarta();
        String foto = "drawable/_"+ idcartaActual;
        int iResource = getResources().getIdentifier(foto,null,getPackageName());
        ivJugador.setImageResource(iResource);
        indexFoto=0;


        /**
         * Muestra la foto de la carta anterior
         */
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indexFoto==0){
                    idcartaActual = game.getCartasUsuario().get(game.getCartasUsuario().size()-1).getIdcarta();
                    String foto = "drawable/_"+ idcartaActual;
                    int iResource = getResources().getIdentifier(foto,null,getPackageName());
                    ivJugador.setImageResource(iResource);
                    indexFoto=game.getCartasUsuario().size()-1;
                }else{
                    indexFoto--;
                    idcartaActual = game.getCartasUsuario().get(indexFoto).getIdcarta();
                    String foto = "drawable/_"+ idcartaActual;
                    int iResource = getResources().getIdentifier(foto,null,getPackageName());
                    ivJugador.setImageResource(iResource);
                }

            }
        });

        /**
         * Muestra la foto de la carta anterior llamando al metodo clickSiguiente()
         */
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSiguiente();
            }
        });

        /**
         * Llama al metodo del endpoint que juega la carta , si es turno del jugador pone la carta y si es turno de la CPU le da paso a que juegue carta
         */
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("go : "+game.getCartasUsuario());
                if(game.getTurno()==1){
                    dojugar(game.getIdsesion(),game.getTurno(),game.getIdcartaCPU(),game.getFeatureCPU(),game.getIdcartaJugador(),
                            game.getFeatureJugador(),game.getMano(),game.getPuntosJugador(),game.getPuntosCPU(),game.getResultFinal());
                }else{
                    if(!feature.equals("")) {
                        game.setIdcartaJugador(idcartaActual);
                        if(!game.getFeatureCPU().equals("")){
                            game.setFeatureJugador(game.getFeatureCPU());
                        }else{
                            game.setFeatureJugador(feature);
                        }
                        dojugar(game.getIdsesion(),game.getTurno(),game.getIdcartaCPU(),game.getFeatureCPU(),game.getIdcartaJugador(),
                                game.getFeatureJugador(),game.getMano(),game.getPuntosJugador(),game.getPuntosCPU(),game.getResultFinal());
                    }else{
                        Toast.makeText(GameActivity.this, "Selecciona caracteristica",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



        btnMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feature = "motor";
                resetButtons();
                btnMotor.setTextColor(Color.BLACK);
                btnMotor.setTextSize(20);

            }
        });
        btnRevoluciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feature = "revoluciones";
                resetButtons();
                btnRevoluciones.setTextColor(Color.BLACK);
                btnRevoluciones.setTextSize(20);

            }
        });
        btnConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feature = "consumo";
                resetButtons();
                btnConsumo.setTextColor(Color.BLACK);
                btnConsumo.setTextSize(20);

            }
        });
        btnPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feature = "potencia";
                resetButtons();
                btnPotencia.setTextColor(Color.BLACK);
                btnPotencia.setTextSize(20);

            }
        });
        btnCilindros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feature = "cilindros";
                resetButtons();
                btnCilindros.setTextColor(Color.BLACK);
                btnCilindros.setTextSize(20);

            }
        });
        btnVelocidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feature = "velocidad";
                resetButtons();
                btnVelocidad.setTextColor(Color.BLACK);
                btnVelocidad.setTextSize(20);

            }
        });


    }

    /**Este es el metodo del endpoint que juega carta
     * @param idsesion
     * @param turno
     * @param idcartaCPU
     * @param featureCPU
     * @param idcartaJugador
     * @param featureJugador
     * @param mano
     * @param puntosJugador
     * @param puntosCPU
     * @param resultFinal
     */
    public void dojugar(int idsesion,int turno, int idcartaCPU, String featureCPU, int idcartaJugador, String featureJugador, int mano,
                        int puntosJugador, int puntosCPU, int resultFinal) {
        mAPIService.jugarCarta(idsesion,turno,idcartaCPU,featureCPU,idcartaJugador,featureJugador,mano,puntosJugador,puntosCPU,resultFinal).enqueue(new Callback<Game>() {

            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if(response.isSuccessful()) {
                    game=response.body();
                    resetButtons();
                    updateTextos();
                    System.out.println("res: "+game.getCartasUsuario());

                    if(game.getResultFinal()!=0){
                        deshabilitarBotones();
                        if(game.getResultFinal()==1){
                            tvResult.setText("PERDISTE");
                        }else if(game.getResultFinal()==2){
                            tvResult.setText("GANASTE");
                        }else{
                            tvResult.setText("EMPATE");
                        }
                    }else{
                        idcartaActual = game.getCartasUsuario().get(0).getIdcarta();
                        if(game.getTurno()==1){
                            clickSiguiente();
                            System.out.println("ah: "+game.getCartasUsuario());
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e(TAG, "Error al jugar carta. " + t.getMessage());

            }
        });
    }


    /**
     * actualiza los textos de mano , turno , caracteristica cpu y puntos
     */
    private void updateTextos(){
        tvMano.setText(String.valueOf(game.getMano()));
        tvTurno.setText(getTurnoString());
        tvPuntosCpu.setText(String.valueOf(game.getPuntosCPU()));
        tvPuntosJugador.setText(String.valueOf(game.getPuntosJugador()));
        if(game.getFeatureCPU().equals("")){
            btnFeatureCpu.setText("Feature");
        }else{
            btnFeatureCpu.setText(game.getFeatureCPU());
        }
    }


    /**
     * Resetea los botones al estado original
     */
    private void resetButtons(){
        btnMotor.setTextColor(Color.WHITE);
        btnMotor.setTextSize(14);
        btnCilindros.setTextColor(Color.WHITE);
        btnCilindros.setTextSize(14);
        btnVelocidad.setTextColor(Color.WHITE);
        btnVelocidad.setTextSize(14);
        btnPotencia.setTextColor(Color.WHITE);
        btnPotencia.setTextSize(14);
        btnConsumo.setTextColor(Color.WHITE);
        btnConsumo.setTextSize(14);
        btnRevoluciones.setTextColor(Color.WHITE);
        btnRevoluciones.setTextSize(14);
    }


    /**
     * Muestra la carta siguiente
     */
    private void clickSiguiente(){
        if(indexFoto==game.getCartasUsuario().size()-1 || indexFoto==game.getCartasUsuario().size()){
            idcartaActual = game.getCartasUsuario().get(0).getIdcarta();
            String foto = "drawable/_"+ idcartaActual;
            int iResource = getResources().getIdentifier(foto,null,getPackageName());
            ivJugador.setImageResource(iResource);
            indexFoto=0;

        }else if(game.getCartasUsuario().size()==1){
            indexFoto=0;
            idcartaActual = game.getCartasUsuario().get(indexFoto).getIdcarta();
            String foto = "drawable/_"+ idcartaActual;
            int iResource = getResources().getIdentifier(foto,null,getPackageName());
            ivJugador.setImageResource(iResource);
            deshabilitarBotones();
        }else{
            indexFoto++;
            idcartaActual = game.getCartasUsuario().get(indexFoto).getIdcarta();
            String foto = "drawable/_"+ idcartaActual;
            int iResource = getResources().getIdentifier(foto,null,getPackageName());
            ivJugador.setImageResource(iResource);
        }
    }




    /**
     * Deshabilita los botones , usado cuando se ha acabado la partida
     */
    private void deshabilitarBotones(){
        btnMotor.setClickable(false);
        btnCilindros.setClickable(false);
        btnVelocidad.setClickable(false);
        btnPotencia.setClickable(false);
        btnConsumo.setClickable(false);
        btnRevoluciones.setClickable(false);
        btnSiguiente.setClickable(false);
        btnAtras.setClickable(false);
    }


    /**Transforma el turno que es entero al correspondiente String
     * @return Devuelve quien empieza en formato texto
     */
    private String getTurnoString() {
        if (game.getTurno()==1){
            return "CPU";
        }else{
            return "Tu turno";
        }
    }



}
