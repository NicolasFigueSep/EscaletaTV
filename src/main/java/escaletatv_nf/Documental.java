/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaletatv_nf;

import escaletatv_nf.Programa;

/**
 * Clase Documental que hereda de Programa
 * @since 8/03/2022 
 * @version 1.0
 * @author Nico
 */
public class Documental extends Programa {

    private String tema;
    private boolean redifusion;//Indica si es la primera vez que se emite
    /**
     * 
     * @param nombre del Documental
     * @param duracion del Documental
     * @param tema nombre tipo de Documental
     * @param redifusion booleano si es redifusion o no
     */
    public Documental(String nombre, int duracion, String tema, boolean redifusion) {
        super(nombre, duracion);
        this.tema = tema;
        this.redifusion = redifusion;
    }
    /**
     * Getter de tema
     * @return tema Cadena descriptiva del tipo ed Documental
     */
    public String getTema() {
        return tema;
    }
    /**
     * Setter de tema
     * @param tema Cadena descriptiva del tipo de Documental
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
    /**
     * Método isRedifusion que nos devuelve el valor de redifusion
     * @return true o false
     */
    public boolean isRedifusion() {
        return redifusion;
    }
    /**
     * Setter de redifusion
     * @param redifusion booleano de Documental
     */
    public void setRedifusion(boolean redifusion) {
        this.redifusion = redifusion;
    }
    /**
     * Método programar para introducir Documental en escaleta a la hora deseada
     * Recorre la escaleta para comprobar si hay hueco a la hora deseada
     * Si hay hueco y es de tema violencia tendrá que comprobar si hay hueco a la hora de Violencia
     * Si no hay hueco avisará al usuario
     * Si hay hueco y no es del tema de violencia pero redifusion buscara hueco a cualquier hora
     * Si no hay hueco avisará al usuario
     * @param escaleta Matriz de tipo Programa
     * @param hora hora a la que hayq ue intentar introducir el Documental
     * @return true o false si hay hueco y se ha podido introducir o no
     */
    @Override
    public boolean programar(Programa[] escaleta, int hora) {
        boolean hueco = true;
        int aux=hora;
        for (int i = 0; i < this.getDuracion(); i++) {
            if (escaleta[hora + i] != null) {
                hueco = false;
                System.out.println("No hay hueco a esa hora");
            }
            if(hora==23){
                hora=-1;//Para que la siguiente vuelta empiece a las 00 en el ARRAY
            }
            
        }
        /*
            Recuperamos la hora anterior para no saltarnos posiciones
        */
        hora=aux;
        /*
            Si es de tema violencia solo entre doce y 5 de la mañana
            mirar si hay hueco entre las doce y 5 no se puede en otra hora
         */
        if (this.getTema().equalsIgnoreCase("violencia") && hueco) {
            if (hora >= 0 && hora <= 5) {
                for (int i = 0; i < this.getDuracion(); i++) {
                    escaleta[hora + i] = this;
                    if(hora==23){
                        hora=-1;//Para que la siguiente vuelta empiece a las 00 en el ARRAY
                    }
                    
                }
            } else {
                hueco = false;
                System.out.println("A esa hora no se puede programar con tema Violencia");
            }
        } //Buscar hueco a cualquier hora
        else if (this.isRedifusion() && hueco) {
            for (int i = 0; i < this.getDuracion(); i++) {
                escaleta[hora + i] = this;
                if(hora==23){
                    hora=-1;//Para que la siguiente vuelta empiece a las 00 en el ARRAY
                }
                
            }
        } //si es la primera vez sera entre las 10 y las 18h
        else if (!this.isRedifusion() && hueco) {
            if (hora >= 10 && hora <= 18) {
                for (int i = 0; i < this.getDuracion(); i++) {
                    escaleta[hora + i] = this;
                }
            } else {
                hueco = false;
                System.out.println("Debe ser entre las 10 y las 18 ");
            }
        }
        return hueco;
    }
    /**
     * metodo toString para obtener una descripción del obejto
     * @return Cadena de Caracteres
     */
    @Override
    public String toString() {
        return (this.getNombre() + " con una duracion de " + this.getDuracion()
                + "\nTema " + this.getTema() + " Redifusion : " + (this.isRedifusion() ? "Si" : "No"));
    }
}
