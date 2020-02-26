package com.estebannaranjo.proyectocartas.Model;

import com.google.gson.annotations.Expose;

/**
 * Clase con el modelo de la Partida
 * @author Esteban
 *
 */
public class Partida {
    @Expose
    private int idpartida;
    @Expose
    private int idsesion;

    public Partida(int idsesion) {
        this.idsesion = idsesion;
    }


    public Partida(int idpartida, int idsesion) {
        this.idpartida = idpartida;
        this.idsesion = idsesion;
    }

    public Partida() {
    }

    public int getIdpartida() {
        return idpartida;
    }

    public int getIdsesion() {
        return idsesion;
    }

    public void setIdpartida(int idpartida) {
        this.idpartida = idpartida;
    }

    public void setIdsesion(int idsesion) {
        this.idsesion = idsesion;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "idpartida=" + idpartida +
                ", idsesion=" + idsesion +
                '}';
    }
}
