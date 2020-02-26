package com.estebannaranjo.proyectocartas.ApiRest;

/**
 * Esta clase contiene la url base y el metodo para devolver la conexion
 */
public class APIUtils {
    private APIUtils() {
    }

    public static final String BASE_URL = "http://192.168.100.4:8080";


    public static APIService getAPIService() {
        return APIRestClient.getInstance(BASE_URL).getRetrofit().create(APIService.class);
    }
}