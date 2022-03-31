/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaletatv_nf;

/**
 * Superclase Programa para
 * definir un programa de televisión
 * @since 8/03/2022 
 * @version 1.0
 * @author Nico
 */
public class Programa {
    private String nombre;
    private int duracion;
    /**
     * Constructor de 2 parámetros 
     * @param nombre del programa
     * @param duracion en horas del programa
     */
    public Programa(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }
    /**
     * Constructor de 1 parámetro 
     * @param nombre  del programa 
     */
    public Programa(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter de nombre
     * @return nombre del programa 
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter de nombre
     * @param nombre del programa
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter de Duracion
     * @return duracion del programa
     */
    public int getDuracion() {
        return duracion;
    }
    /**
     * Setter de Duracion
     * @param duracion del programa 
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    /**
     * Método programar para introducir en escaleta dependiendo de la hora
     * Tendrá un bucle for para saber si hay hueco a la hora pasada por parámetro
     * Tendrá un condicional, si hay hueco introduciremos mismo objeto a la escaleta a la hora pasada por parámetro
     * @param escaleta Matriz de tipo Programa
     * @param hora duracion del programa 
     * @return true o false si hay hueco o no 
     */
    public boolean programar(Programa [] escaleta, int hora){
        boolean hueco = true;
        int aux=hora;
        for (int i = 0; i < this.getDuracion(); i++) {
            if(escaleta[hora+i] !=null){
                hueco=false;
            }
            if(hora==23){
                    hora=-1;//Para que la siguiente vuelta empiece a las 00 en el ARRAY
            }
        }
        hora=aux;//recuperamos la hora anterior
        if(hueco){
            for (int i = 0; i < duracion; i++) {
                escaleta[hora+i] = this;
                 if(hora==23){
                    hora=-1;//Para que la siguiente vuelta empiece a las 00 en el ARRAY
                 }
            }
        }
        return hueco;
    }
    /**
     * Método toString para tener una descripción del objeto Programa
     * @return String con descripción del Programa 
     */
    @Override
    public String toString(){
        return(this.getNombre() +" con una duracion de " + this.getDuracion());
                
    }
}
