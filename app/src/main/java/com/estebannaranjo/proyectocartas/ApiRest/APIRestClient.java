package com.estebannaranjo.proyectocartas.ApiRest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Esta clase es la encargada de realizar la conexion con la api rest
 */
public class APIRestClient {
    private static APIRestClient instance;
    private String baseUrl;
    private Retrofit retrofit;



    private APIRestClient() {
    }

    /**Crea la conexion haciendo uso de retrofit y pasando como parametro la url base
     * @param baseUrl
     */
    private APIRestClient(String baseUrl) {
        this.baseUrl = baseUrl;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**Esta clase obtiene la instancia de la conexion
     * @param baseUrl
     * @return - instancia conexion
     */
    public static APIRestClient getInstance(String baseUrl) {
        if(instance == null) {
            synchronized (APIRestClient.class) {
                if(instance == null) {
                    instance = new APIRestClient(baseUrl);
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}