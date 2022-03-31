/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaletatv_nf;
import escaletatv_nf.Programa;
/**
 * Clase Pelicula hereda de Programa
 * @since 8/03/2022 
 * @version 1.0
 * @author Nico
 */
public class Pelicula extends Programa{
    private String director;
    private boolean estreno;
    /**
     * Constructor Pelicula de 3 parámetros
     * @param nombre de Pelicula
     * @param director es nombre de Director
     * @param estreno  nos dice si se ha estrenado o no
     */
    public Pelicula(String nombre, String director, boolean estreno){
        super(nombre,2);
        this.director = director;
        this.estreno = estreno;
    }
    /**
     * Getter de director 
     * @return String con nombre del director
     */
    public String getDirector() {
        return director;
    }
    /**
     * Setter de director
     * @param director nombre del director 
     */
    public void setDirector(String director) {
        this.director = director;
    }
    /**
     * Método isEstreno que nos devolverá el valor de estreno
     * @return true o false 
     */
    public boolean isEstreno() {
        return estreno;
    }
    /**
     * Setter para estreno
     * @param estreno true o false
     */
    public void setEstreno(boolean estreno) {
        this.estreno = estreno;
    }
    /**
     * Método programar
     * Comprueba si es estreno o no
     * Si es estreno comprobara que haya hueco en la hora correcta
     * Si no hay hueco nos avisará de no haber hueco
     * Si no es estreno comprobará si hay hueco en la hora pasada por parámetro
     * Si hay hueco la introduce en la escaleta 
     * Si no hay hueco avisa al usuario de no haber hueco
     * @param escaleta array de tipo Programa
     * @param hora hora a la que queremos programar
     * @return true o false si hay hueco o no
     */
    @Override
    public boolean programar(Programa [] escaleta, int hora){
        boolean hueco = false;
        //Comprobar si es de estreno, se programa entre las 21 y 24
        if(this.estreno ){
            if(hora>=21 && hora <=23){
                //Comprobamos si es a las 23 para no pasarnos en el array y continuar a las 00
                if(hora==23){
                    if(escaleta[hora] == null && escaleta[0] == null){
                        escaleta[hora]=this;
                        escaleta[0]=this;
                        hueco=true;
                    }
                    else{
                    hueco = false;
                    System.out.println("No hay hueco entre las 21 y las 24");
                    }
                }
                else if(escaleta[hora] == null && escaleta[hora+1] == null){
                    escaleta[hora]=this;
                    escaleta[hora+1]=this;
                    hueco=true;
                }
                else{
                    hueco = false;
                    System.out.println("No hay hueco entre las 21 y las 24");
                }
            }
            else{
                hueco=false;
                System.out.println("Horario incorrecto debe ser entre 21 y 24");
            }
        }
        //No es estreno-> a cualquier hora
        else{
            //Comprobamos si es a las 23 para no pasarnos en el array y continuar a las 00
            if(hora==23){
                if(escaleta[hora] == null && escaleta[0] == null){
                    escaleta[hora]=this;
                    escaleta[0]=this;
                    hueco=true;
                } 
                else{
                    hueco=false;
                    System.out.println("No hay hueco a esa hora");
                }
            }
            else if(escaleta[hora] == null && escaleta[hora+1] == null){
                escaleta[hora]=this;
                escaleta[hora+1]=this;
                hueco=true;
            } 
            else{
                hueco=false;
                System.out.println("No hay hueco a esa hora");
            }
        }

        return hueco;
    }
    /**
     * Método toString para describir nuestro objeto
     * @return String con datos del objeto Pelicula
     */
    @Override 
    public String toString(){
        return(this.getNombre() +" con una duracion de " + this.getDuracion()+
                "\nDirector " + this.getDirector() + " Estreno : " + (this.isEstreno() ? "Si" : "No"));
    }
}
