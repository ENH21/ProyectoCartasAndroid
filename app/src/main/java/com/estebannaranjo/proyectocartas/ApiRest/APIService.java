package com.estebannaranjo.proyectocartas.ApiRest;

import com.estebannaranjo.proyectocartas.Model.Carta;
import com.estebannaranjo.proyectocartas.Model.Game;
import com.estebannaranjo.proyectocartas.Model.Jugador;
import com.estebannaranjo.proyectocartas.Model.Sesion;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interfaz encargada de crear los endpoints en cliente
 */
public interface APIService {

    /**Endpoint para hacer login
     * @param idjugador
     * @param password
     * @return - Sesion
     */
    @POST("ProyectoCartas/api/game/login")
    @FormUrlEncoded
    Call<Sesion> login(@Field("idjugador")String idjugador ,@Field("password")String password );

    /**Endpoint para crear jugador
     * @param nick
     * @param password
     * @return -Jugador
     */
    @POST("ProyectoCartas/api/jugador/crear")
    @FormUrlEncoded
    Call<Jugador> crearJugador(@Field("nick")String nick , @Field("password")String password );

    /**Endpoint para empezar partida
     * @param nick
     * @param idsesion
     * @return - Game
     */
    @POST("ProyectoCartas/api/game/start")
    @FormUrlEncoded
    Call<Game> startGame(@Field("nick")String nick , @Field("idsesion")int idsesion );

    /**Endpoint para jugar carta
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
     * @return - Game
     */
    @POST("ProyectoCartas/api/game/play")
    @FormUrlEncoded
    Call<Game> jugarCarta(@Field("idsesion")int idsesion,
                          @Field("turno")int turno,
                          @Field("idcartaCPU")int idcartaCPU,
                          @Field("featureCPU")String featureCPU,
                          @Field("idcartaJugador")int idcartaJugador,
                          @Field("featureJugador")String featureJugador,
                          @Field("mano")int mano,
                          @Field("puntosJugador")int puntosJugador,
                          @Field("puntosCPU")int puntosCPU,
                          @Field("resultFinal")int resultFinal);

}
