package com.estebannaranjo.proyectocartas.Model;

import com.google.gson.annotations.Expose;

/**
 * Clase con el modelo de la Carta
 * @author Esteban
 *
 */
public class Carta {
    @Expose
    private int idcarta;
    @Expose
    private String marca;
    @Expose
    private String modelo;
    @Expose
    private int motor;
    @Expose
    private int cilindros;
    @Expose
    private int potencia;
    @Expose
    private int revoluciones;
    @Expose
    private int velocidad;
    @Expose
    private double consumo;

    public Carta(String marca, String modelo, int motor, int cilindros, int potencia, int revoluciones, int velocidad,
                 double consumo) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.cilindros = cilindros;
        this.potencia = potencia;
        this.revoluciones = revoluciones;
        this.velocidad = velocidad;
        this.consumo = consumo;
    }

    public Carta(int idcarta,String marca, String modelo, int motor, int cilindros, int potencia, int revoluciones, int velocidad,
                 double consumo) {
        this.idcarta = idcarta;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.cilindros = cilindros;
        this.potencia = potencia;
        this.revoluciones = revoluciones;
        this.velocidad = velocidad;
        this.consumo = consumo;
    }

    public Carta() {

    }


    public int getIdcarta() {
        return idcarta;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getMotor() {
        return motor;
    }

    public int getCilindros() {
        return cilindros;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getRevoluciones() {
        return revoluciones;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMotor(int motor) {
        this.motor = motor;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setRevoluciones(int revoluciones) {
        this.revoluciones = revoluciones;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "idcarta=" + idcarta +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", motor=" + motor +
                ", cilindros=" + cilindros +
                ", potencia=" + potencia +
                ", revoluciones=" + revoluciones +
                ", velocidad=" + velocidad +
                ", consumo=" + consumo +
                '}';
    }
}
