package com.estebannaranjo.proyectocartas.Model;

import com.google.gson.annotations.Expose;

/**
 * Clase con el modelo del Jugador
 * @author Esteban
 *
 */
public class Jugador {
    @Expose
    private String nick;
    @Expose
    private String password;
    @Expose
    private int partidas_ganadas;
    @Expose
    private int partidas_perdidas;
    @Expose
    private int partidas_empatadas;

    public Jugador(String nick, String password, int partidas_ganadas, int partidas_perdidas, int partidas_empatadas) {
        this.nick = nick;
        this.password = password;
        this.partidas_ganadas = partidas_ganadas;
        this.partidas_perdidas = partidas_perdidas;
        this.partidas_empatadas = partidas_empatadas;
    }
    public Jugador(String nick, String password) {
        this.nick = nick;
        this.password = password;
        this.partidas_ganadas = 0;
        this.partidas_perdidas = 0;
        this.partidas_empatadas = 0;
    }
    public Jugador() {
        this.partidas_ganadas = 0;
        this.partidas_perdidas = 0;
        this.partidas_empatadas = 0;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public int getPartidas_perdidas() {
        return partidas_perdidas;
    }

    public int getPartidas_empatadas() {
        return partidas_empatadas;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public void setPartidas_perdidas(int partidas_perdidas) {
        this.partidas_perdidas = partidas_perdidas;
    }

    public void setPartidas_empatadas(int partidas_empatadas) {
        this.partidas_empatadas = partidas_empatadas;
    }

    @Override
    public String toString() {
        return "Jugador [nick=" + nick + ", password=" + password + ", partidas_ganadas=" + partidas_ganadas
                + ", partidas_perdidas=" + partidas_perdidas + ", partidas_empatadas=" + partidas_empatadas + "]";
    }


}
