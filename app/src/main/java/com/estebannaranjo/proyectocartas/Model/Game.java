package com.estebannaranjo.proyectocartas.Model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase con el modelo del juego o clase Game
 * @author Esteban
 *
 */
public class Game {
    private static final int CPU = 1;
    private static final int JUGADOR = 2;
    private static final int EMPATE = 3;
    @Expose
    private int idsesion;
    @Expose
    private ArrayList<Carta> cartasUsuario;
    @Expose
    private ArrayList<Carta> cartasCPU;
    @Expose
    private ArrayList<String> features;
    @Expose
    private int turno;
    @Expose
    private int idcartaCPU;
    @Expose
    private String featureCPU;
    @Expose
    private int idcartaJugador;
    @Expose
    private String featureJugador;
    @Expose
    private int mano;
    @Expose
    private int puntosJugador;
    @Expose
    private int puntosCPU;
    @Expose
    private int resultFinal;



    public Game(int idsesion,int turno, int idcartaCPU, String featureCPU, int idcartaJugador, String featureJugador, int mano,
                int puntosJugador, int puntosCPU, int resultFinal) {
        this.idsesion = idsesion;
        this.features =  llenarFeatures();
        this.turno = turno;
        this.idcartaCPU = idcartaCPU;
        this.featureCPU = featureCPU;
        this.idcartaJugador = idcartaJugador;
        this.featureJugador = featureJugador;
        this.mano = mano;
        this.puntosJugador = puntosJugador;
        this.puntosCPU = puntosCPU;
        this.resultFinal = resultFinal;
    }



    public Game(int idsesion, ArrayList<Carta> cartasUsuario, ArrayList<Carta> cartasCPU, ArrayList<String> features,
                int turno, int idcartaCPU, String featureCPU, int idcartaJugador, String featureJugador, int mano,
                int puntosJugador, int puntosCPU, int resultFinal) {
        this.idsesion = idsesion;
        this.cartasUsuario = cartasUsuario;
        this.cartasCPU = cartasCPU;
        this.features =  llenarFeatures();
        this.turno = turno;
        this.idcartaCPU = idcartaCPU;
        this.featureCPU = featureCPU;
        this.idcartaJugador = idcartaJugador;
        this.featureJugador = featureJugador;
        this.mano = mano;
        this.puntosJugador = puntosJugador;
        this.puntosCPU = puntosCPU;
        this.resultFinal = resultFinal;
    }
    public Game(int idsesion) {
        this.idsesion = idsesion;
        this.cartasUsuario = new ArrayList<>();
        this.cartasCPU = new ArrayList<>();
        this.features =  llenarFeatures();
        this.turno = 0;
        this.idcartaCPU= 0;
        this.featureCPU="";
        this.idcartaJugador= 0;
        this.featureJugador="";
        this.mano= 1;
        this.puntosJugador=0;
        this.puntosCPU=0;
        this.resultFinal= 0;
        this.featureCPU="";
    }
    public Game() {
        this.cartasUsuario = new ArrayList<>();
        this.cartasCPU = new ArrayList<>();
        this.features =  llenarFeatures();
        this.turno = 0;
        this.idcartaCPU= 0;
        this.featureCPU="";
        this.idcartaJugador= 0;
        this.featureJugador="";
        this.mano= 1;
        this.puntosJugador=0;
        this.puntosCPU=0;
        this.resultFinal= 0;

    }

    public Game(int idsesion,ArrayList<Carta> cartasUsuario,ArrayList<Carta> cartasCPU,int turno) {
        this.features = llenarFeatures();
        this.idsesion = idsesion;
        this.cartasUsuario = cartasUsuario;
        this.cartasCPU = cartasCPU;
        this.turno = turno;
        this.idcartaCPU= 0;
        this.featureCPU="";
        this.idcartaJugador= 0;
        this.featureJugador="";
        this.mano= 1;
        this.puntosJugador=0;
        this.puntosCPU=0;
        this.resultFinal= 0;
    }

    /**
     * Crea un numero aleatorio entre 1 y 2 para sortear el turno
     */
    public void raffle() {
        Random r = new Random();
        this.turno = r.nextInt(JUGADOR)+CPU;
    }


    /**
     * Hace la jugada de la carta.
     * Funcionamiento : si el jugador le toca jugar carta porque su turno es JUGADOR ,idcartaJugador y feature ya estan llenos , cpu juega carta ,se hace el resultado y el turno es de cpu
     * si el jugador juega carta pero el turno es de CPU , idcartaJugador y feature estan vacios por lo que cpu juega carta y le pasa turno.
     */
    public void jugada() {
        if(turno==JUGADOR ) {
            if(idcartaCPU!=0 && idcartaJugador!=0) {
                resultMano();
            }else {
                jugadaCPU();
                resultMano();
            }
            cartasUsuario.remove(getIndexCarta());
            this.turno = CPU;

        }else {
            jugadaCPU();
            if(idcartaJugador!=0) {
                resultMano();
            }
            this.turno =JUGADOR;
        }

    }

    /**
     * Hace el resultado de la mano , reinicia cartas y aumenta mano
     */
    private void resultMano() {
        //comprobamos que carta ha ganado , ponemos el resultado
        if(featureCPU.equals(featureJugador)) {
            Carta cpu = getCartaCPU();
            Carta jugador = getCartaJugador();
            System.out.println(cpu.getIdcarta()+" y "+cpu.getConsumo());
            System.out.println(jugador.getIdcarta()+" y "+jugador.getConsumo());
            if(featureJugador.equals("motor")) {
                if(cpu.getMotor()>jugador.getMotor()) {
                    puntosCPU++;
                }else if(cpu.getMotor()<jugador.getMotor()) {
                    puntosJugador++;
                }
            }else if(featureJugador.equals("potencia")){
                if(cpu.getPotencia()>jugador.getPotencia()) {
                    puntosCPU++;
                }else if(cpu.getPotencia()<jugador.getPotencia()) {
                    puntosJugador++;
                }
            }else if(featureJugador.equals("velocidad")){
                if(cpu.getVelocidad()>jugador.getVelocidad()) {
                    puntosCPU++;
                }else if(cpu.getVelocidad()<jugador.getVelocidad()) {
                    puntosJugador++;
                }
            }else if(featureJugador.equals("cilindros")){
                if(cpu.getCilindros()>jugador.getCilindros()) {
                    puntosCPU++;
                }else if(cpu.getCilindros()<jugador.getCilindros()) {
                    puntosJugador++;
                }
            }else if(featureJugador.equals("revoluciones")){
                if(cpu.getRevoluciones()<jugador.getRevoluciones()) {
                    puntosCPU++;
                }else if(cpu.getRevoluciones()>jugador.getRevoluciones()) {
                    puntosJugador++;
                }
            }else if(featureJugador.equals("consumo")){
                if(cpu.getConsumo()<jugador.getConsumo()) {
                    puntosCPU++;
                }else if(cpu.getConsumo()>jugador.getConsumo()) {
                    puntosJugador++;
                }
            }
        }
        else {
            System.out.println("features no coinciden");
        }
        //reiniciamos idcartas , idfeatures
        this.idcartaCPU= 0;
        this.featureCPU="";
        this.idcartaJugador= 0;
        this.featureJugador="";
        //aumentamos mano
        mano++;
        //cuando se quedan sin cartas se acaba ;
        if(cartasUsuario.size()==0 && cartasCPU.size()==0 || mano==7) {
            resultFinal();
        }
    }

    /**Obtiene el index del array en que se posiciona la carta de la CPU
     * @return - int
     */
    private int getIndexCarta() {
        int index=0;
        for(int i =0 ;i<cartasCPU.size();i++) {
            if(cartasCPU.get(i).getIdcarta()==idcartaJugador) {
                index =i;
                break;
            }
        }

        return index;
    }

    /**Obtiene la carta de la CPU
     * @return Carta
     */
    private Carta getCartaCPU() {
        Carta carta = new Carta();
        for(int i =0 ;i<cartasCPU.size();i++) {
            if(cartasCPU.get(i).getIdcarta()==idcartaCPU) {
                carta = cartasCPU.get(i);
                break;
            }
        }

        return carta;
    }

    /**Obtiene la carta del jugador
     * @return Carta
     */
    private Carta getCartaJugador() {
        Carta carta = null;
        for(int i =0 ;i<cartasUsuario.size();i++) {
            if(cartasUsuario.get(i).getIdcarta()==idcartaJugador) {
                carta = cartasUsuario.get(i);
                break;
            }
        }

        return carta;
    }


    /**
     * Hace el resultado final de la partida
     */
    private void resultFinal() {
        if(puntosCPU>puntosJugador) {
            this.resultFinal=CPU;
        }else if(puntosCPU<puntosJugador) {
            this.resultFinal=JUGADOR;
        }else {
            this.resultFinal=EMPATE;
        }
    }


    /**
     * Este metodo es el que hace la accion de jugar carta por parte de la CPU , en dificultad easy
     */
    private void jugadaCPU() {
        Random r = new Random();
        int index = r.nextInt(cartasCPU.size());
        Carta carta;
        carta = cartasCPU.get(index);
        this.idcartaCPU = carta.getIdcarta();
        cartasCPU.remove(index);
        if(this.featureJugador.equals("motor")) {
            this.featureCPU = "motor";
        }else if(this.featureJugador.equals("cilindros")){
            this.featureCPU = "cilindros";
        }else if(this.featureJugador.equals("potencia")){
            this.featureCPU = "potencia";
        }else if(this.featureJugador.equals("revoluciones")){
            this.featureCPU = "revoluciones";
        }else if(this.featureJugador.equals("velocidad")){
            this.featureCPU = "velocidad";
        }else if(this.featureJugador.equals("consumo")){
            this.featureCPU = "consumo";
        }else {
            String feature = this.features.get(r.nextInt(features.size()));
            if(feature.equals("motor")) {
                this.featureCPU = "motor";
            }else if(feature.equals("cilindros")) {
                this.featureCPU = "cilindros";
            }else if(feature.equals("potencia")) {
                this.featureCPU = "potencia";
            }else if(feature.equals("revoluciones")) {
                this.featureCPU = "revoluciones";
            }else if(feature.equals("velocidad")) {
                this.featureCPU = "velocidad";
            }else if(feature.equals("consumo")) {
                this.featureCPU = "consumo";
            }
        }
    }

    /**Baraja y reparte las 6 cartas para cada jugador
     * @param cartas - Arraylist
     */
    public void repartirCartas(ArrayList<Carta> cartas) {
        Random r = new Random();
        ArrayList<Carta> aux = new ArrayList<>();
        int random;
        boolean usado;

        for(int i = 0;i<cartas.size();i++) {
            random = r.nextInt(cartas.size());
            usado = false;
            if(this.cartasUsuario.size()<6) {
                for(int x = 0;x<aux.size();x++) {
                    if(aux.get(x).getIdcarta()==cartas.get(random).getIdcarta()) {
                        usado = true;

                    }
                }
                if(!usado) {
                    this.cartasUsuario.add(cartas.get(random));
                    aux.add(cartas.get(random));


                }
            }
        }

        for(int j = 0;j<cartas.size();j++) {
            random = r.nextInt(cartas.size());
            usado = false;
            if(this.cartasCPU.size()<6) {
                for(int c = 0;c<aux.size();c++) {
                    if(aux.get(c).getIdcarta()==cartas.get(random).getIdcarta()) {
                        usado = true;

                    }
                }
                if(!usado) {
                    this.cartasCPU.add(cartas.get(random));
                    aux.add(cartas.get(random));
                }
            }else {
                return;
            }
        }
    }

    /**Llena el arraylist de las caracteristicas
     * @return ArrayList
     */
    public ArrayList<String> llenarFeatures() {
        ArrayList<String> featuresX = new ArrayList<>();
        featuresX.add("motor");
        featuresX.add("cilindros");
        featuresX.add("potencia");
        featuresX.add("revoluciones");
        featuresX.add("velocidad");
        featuresX.add("consumo");
        return featuresX;
    }

    public static int getCpu() {
        return CPU;
    }
    public static int getJugador() {
        return JUGADOR;
    }
    public static int getEmpate() {
        return EMPATE;
    }
    public int getIdsesion() {
        return idsesion;
    }
    public ArrayList<Carta> getCartasUsuario() {
        return cartasUsuario;
    }
    public ArrayList<Carta> getCartasCPU() {
        return cartasCPU;
    }
    public ArrayList<String> getFeatures() {
        return features;
    }
    public int getTurno() {
        return turno;
    }
    public int getIdcartaCPU() {
        return idcartaCPU;
    }
    public String getFeatureCPU() {
        return featureCPU;
    }
    public int getIdcartaJugador() {
        return idcartaJugador;
    }
    public String getFeatureJugador() {
        return featureJugador;
    }
    public int getMano() {
        return mano;
    }
    public int getPuntosJugador() {
        return puntosJugador;
    }
    public int getPuntosCPU() {
        return puntosCPU;
    }
    public int getResultFinal() {
        return resultFinal;
    }
    public void setIdsesion(int idsesion) {
        this.idsesion = idsesion;
    }
    public void setCartasUsuario(ArrayList<Carta> cartasUsuario) {
        this.cartasUsuario = cartasUsuario;
    }
    public void setCartasCPU(ArrayList<Carta> cartasCPU) {
        this.cartasCPU = cartasCPU;
    }
    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public void setIdcartaCPU(int idcartaCPU) {
        this.idcartaCPU = idcartaCPU;
    }
    public void setFeatureCPU(String featureCPU) {
        this.featureCPU = featureCPU;
    }
    public void setIdcartaJugador(int idcartaJugador) {
        this.idcartaJugador = idcartaJugador;
    }
    public void setFeatureJugador(String featureJugador) {
        this.featureJugador = featureJugador;
    }
    public void setMano(int mano) {
        this.mano = mano;
    }
    public void setPuntosJugador(int puntosJugador) {
        this.puntosJugador = puntosJugador;
    }
    public void setPuntosCPU(int puntosCPU) {
        this.puntosCPU = puntosCPU;
    }
    public void setResultFinal(int resultFinal) {
        this.resultFinal = resultFinal;
    }
    @Override
    public String toString() {
        return "Game [idsesion=" + idsesion + ", cartasUsuario=" + cartasUsuario + ", cartasCPU=" + cartasCPU
                + ", features=" + features + ", turno=" + turno + ", idcartaCPU=" + idcartaCPU + ", featureCPU="
                + featureCPU + ", idcartaJugador=" + idcartaJugador + ", featureJugador=" + featureJugador + ", mano="
                + mano + ", puntosJugador=" + puntosJugador + ", puntosCPU=" + puntosCPU + ", resultFinal="
                + resultFinal + "]";
    }


    /**
     * Esta clase unicamente contiene los dos arrays de las cartas de jugador y de la CPU
     * @author Esteban
     *
     */
    public class Cartas {
        ArrayList<Carta> cartasUsuario ;
        ArrayList<Carta> cartasCPU ;
        public Cartas(ArrayList<Carta> cartasUsuario, ArrayList<Carta> cartasCPU) {
            super();
            this.cartasUsuario = cartasUsuario;
            this.cartasCPU = cartasCPU;
        }
        public Cartas() {
        }
        public ArrayList<Carta> getCartasUsuario() {
            return cartasUsuario;
        }
        public ArrayList<Carta> getCartasCPU() {
            return cartasCPU;
        }
        public void setCartasUsuario(ArrayList<Carta> cartasUsuario) {
            this.cartasUsuario = cartasUsuario;
        }
        public void setCartasCPU(ArrayList<Carta> cartasCPU) {
            this.cartasCPU = cartasCPU;
        }
        @Override
        public String toString() {
            return "Cartas [cartasUsuario=" + cartasUsuario + ", cartasCPU=" + cartasCPU + "]";
        }




    }

}
