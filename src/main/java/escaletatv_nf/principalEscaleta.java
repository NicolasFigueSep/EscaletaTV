/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package escaletatv_nf;

import java.util.InputMismatchException;
import java.util.Scanner;
import escaletatv_nf.Programa;
import escaletatv_nf.Pelicula;
import escaletatv_nf.Documental;
import java.util.ArrayList;
/**
 * Programa principal para crear nuevos programas y programarlos en la escaleta además de poder visualizar la escaleta.
 * @since 8/03/2022 
 * @version 1.0
 * @author Nico
 */
public class principalEscaleta {
    static Scanner entrada =  new Scanner(System.in);
    static Programa [] escaleta = new Programa[24];
    static ArrayList <Programa> programas = new ArrayList<Programa> ();
    /**
     * Metodo principal con bucle de interacción con el usuario
     * @param args 
     */
    public static void main(String[] args) {
        //prueba();
        do{
        
        }while(!seleccion(menu()));
    }
    /**
     * Metodo prueba() que no se usa en todo el programa para hacer pequeñas pruebas de funcionamiento de programar.
     */
   
    public static void prueba(){
        Programa p= new Programa("ee", 3);
        p.programar(escaleta, 0);
        for (int i = 0; i < escaleta.length; i++) {
            try{
                System.out.println(escaleta[i].getNombre());
            }catch(Exception e){
                System.out.println("VACIO");
            } 
        }
    }
    /**
     * Metodo menu para visualizar las opciones al usuario.Pregunta las veces que haga falta hasta que el usuario introduzca tipo correcto.
     * @return int que tendra el valor de la opcion seleccionada por el usuario 
     */
    public static int menu() {
           int seleccion=10;
           boolean salir=false;
           System.out.println("");
           System.out.println("\t        .:MENÚ ESCALETA:.");
           System.out.println("\t ________________________________");
           System.out.println("\t|                                |");
           System.out.println("\t|   1.-Dar de alta programa      |");
           System.out.println("\t|   2.-Programar programa        |");
           System.out.println("\t|   3.-Visualizar escaleta       |");
           System.out.println("\t|   4.-Salir                     |");
           System.out.println("\t|________________________________|");
           System.out.println("");
           do{
               System.out.println("Introduzca selección: ");
               try{
                   seleccion = entrada.nextInt();
                   salir=true;
               }catch(InputMismatchException e){
                   System.out.println("ERROR: "+e.getClass().getName() + e.getMessage());//Con esto podemos ver que mensaje por defecto tiene este tipo de error                
                   salir=false;
                   entrada.nextLine();
               }    
           }while(!salir);        
           return seleccion;
    }
    /**
     * Método seleccion.Será un condicional para hacer llamadas a los métodos necesarios.
     * @param selec opcion seleccionada por el usuario
     * @return true o false si ha seleccionado salir del programa o no
     */
    public static boolean seleccion(int selec){
        boolean salir =false;
        switch(selec){
           
            case 1:{
                System.out.println("Ha seleccionado Dar de alta programa");
                nuevoPrograma();
                break;
            }
            case 2:{
                System.out.println("Ha seleccionado Programar programa");
                programarPrograma();
                break;
            }
            case 3:{
                System.out.println("Ha seleccionado Visualizar escaleta");
                enseniaEscaleta();
                break;
            }    
            case 4:{
                System.out.println("Ha seleccionado Salir del programa");
                salir = true;
                break;
            }    
            
            default:{
                System.out.println("Seleccion fuera de rango o invalida");
                break;
            }
        }
        return salir;
    }
    /**
     * Metodo nuevoPrograma para dar de alta un nuevo Programa tipo Documental o Pelicula. 
    */
    public static void nuevoPrograma(){
        int selec;
        //Docus
        String nombre;
        int duracion;
        String tema;
        boolean redif;
        //Pelis
        String director;
        boolean estreno;
        do{
            System.out.println("¿Qué Programa va a dar de alta (Introducir 1 o 2) ?");
            menuProgs();
            try{
                selec=entrada.nextInt();            
            }catch(InputMismatchException e){
                System.out.println("ERROR: "+e.getClass().getName() + e.getMessage());//Con esto podemos ver que mensaje por defecto tiene este tipo de error       
                selec=-1;//para que vuelva a preguntar
                entrada.nextLine();//para que no entre en bucle infinito
            }
        }while(selec!=1 && selec!=2);
        
        System.out.println("Ha seleccionado " + (selec==1 ? "Documental " : "Pelicula"));
        entrada.nextLine();
        if(selec ==1){
            nombre = pedirCadena("Nombre");
            do{
                duracion =  pedirEnt("Duración");
                //Control de mínimo 1 hora de duracion para los documentales
                if(duracion<1){
                    System.out.println("No puede durar menos de 1");
                }
            }while(duracion<1);
            
            entrada.nextLine();
            tema = pedirCadena("Tema");
            redif = pedirBulean("Redifusión");
            programas.add(new Documental(nombre,duracion,tema,redif));
        }
        else if (selec ==2 ){
            nombre = pedirCadena("Nombre");
            director =  pedirCadena("Director");
            estreno = pedirBulean("Estreno");
            programas.add(new Pelicula(nombre,director,estreno));
        }
    }
    /**
     * Metodo para dar de alta nuevos programas, se contralara que sea peli o docu : 1 o 2 dependiendo si es docu o peli  lo añadira al arraylist de programas 
     */
    public static void menuProgs(){
           System.out.println("\t ________________________________");
           System.out.println("\t|                                |");
           System.out.println("\t|      1.-Documental             |");
           System.out.println("\t|      2.-Pelicula               |");
           System.out.println("\t|________________________________|");
           System.out.println("");
    }
    /**
     * Primero pediremos la posicion en el ArrayList del programa, pedimos una hora y si el metodo no consigue programar y continuara preguntando.
     */
    public static void programarPrograma(){
        int posProg = pedirProg();
        boolean salir=false;
        //Comprobacion para no meternos en una posicion que no debemos
        if(posProg==-1){
            salir=true;
        }
        int hora;
        int aux=0;
        if(!salir){
            do{
                /*
                    Si no es la primera vez que pasamos por aqui preguntamos al usuario 
                    si quiere volver al menu principal
                */
                if(aux>0){
                    System.out.println("¿Quieres volver al inicio?");
                    entrada.nextLine();
                    salir= pedirBulean("Si o No");
                }
                if(!salir){
                    hora=pedirEnt("Hora a programar");
                    salir=programas.get(posProg).programar(escaleta, hora);
                }
                aux++;
            }while(!salir);
        } 
    }
    /**
     * Enseñamos array:
     * Si no esta vacio pondrá hora y nombre del programa .Si esta vacion lo recogerá el catch y lo pintará vacío.
     */
    public static void enseniaEscaleta(){
        for (int i = 0; i < escaleta.length; i++) {
            try{
                System.out.println(i+":00 --->"+escaleta[i].getNombre());
            }catch(Exception e){
                System.out.println(i+":00 ---> ");
            }
        }
    }

    /**
     * Metodo para ver si esta el programa introducido
     * @param nombre del Programa
     * @return  true o false dependiendo si está ese Programa dado de alta
     */
    public static boolean comprobProg(String nombre){
        boolean esta=false;
        for (int i = 0; i <programas.size(); i++) {
            if(nombre.equals(programas.get(i).getNombre())){
                esta=true;
            }
        }
        if(!esta){
            System.out.println("Programa Introducido no existe");
        }
        return esta;
    }
    
    /**
     * Devolvemos la posicion en la lista del programa 
     * @param nombre nombre de programa
     * @return posicion del Programa 
     */
    public static int dondeProg(String nombre){
        int pos=-1;
        for (int i = 0; i < programas.size(); i++) {
            if(nombre.equals(programas.get(i).getNombre())){
                pos=i;
            }
        }
        return pos;
    }
    
    /**
     * Metodo para usar comprob y donde a la vez.Pedimos el nombre del programa si no está seguirá preguntando por el.Si lo encuentra saldrá y recogeremos su posición.Por último devolveremos la posición.
     * @return posicion del Programa
     */
    public static int pedirProg(){
        String nombre="";
        int posicion=-1;
        int aux=0;
        boolean salir=false;
        boolean existe=true;
        entrada.nextLine();
         do{
             //Si no es la primera vez que tenemos que preguntas por nombre damos opcion de salir
             if(aux>0){
                 salir= pedirBulean("<S> Si quieres volver al inicio");
                 existe=false;//Ponemos a que no existe sin saber que ha introducido el usuario
             }
             if(!salir){
                 nombre =pedirCadena("Nombre Programa");
                 salir=comprobProg(nombre);
                 if(salir){
                     existe=true;//si es true es que existe por lo que cambiamos el valor
                 }
             }
            aux++;
        }while(!salir);
         if(existe){//si existe pillamos posicion
         //llamamos dondeProg(nombre) para ver donde esta
             posicion=dondeProg(nombre);
         }

        return posicion;
    }
     
    /**
     * Metodo para pedir Ints con control de excepciones 
     * @param pedir Cadena a pedir al usuario
     * @return entero introducido por el usuario
     */
    public static int pedirEnt(String pedir){
        int numero=-1;
        boolean salir=false;
        do{
            System.out.println("Introducir " + pedir+ ": ");
            try{
                numero = entrada.nextInt();
                salir=true;
            }catch(InputMismatchException e){
                System.out.println("ERROR: "+e.getClass().getName() + e.getMessage());//Con esto podemos ver que mensaje por defecto tiene este tipo de error        
                salir=false;
                entrada.nextLine();
            }     
        }while(!salir);
        return numero;
    }  
    /**
     * Método para pedir Strings
     * @param pedir Cadena a pedir al usuario
     * @return cadena carácteres introducido por el usuario
     */
    public static String pedirCadena(String pedir){
        String cadena;        
        System.out.println("Introducir " + pedir + ": ");
        cadena = entrada.nextLine();
        return cadena;
    }
    /**
     * Método para pedir Si o No
     * @param pedir Cadena a pedir al usuario
     * @return true o false introducido por el usuario
     */
    public static boolean pedirBulean(String pedir){
        boolean respuesta;
        String cadena;
        do{
            System.out.println("Introducir " + pedir + " (S/N): ");
            cadena = entrada.nextLine().toLowerCase();
            if(!cadena.equals("s") && !cadena.equals("n")){
                System.out.println("ERROR NI <S> NI <N>  Vuelva a intentarlo"); 
            }
        }while(!cadena.equals("s") && !cadena.equals("n"));
        
        respuesta = cadena.equals("s") ? true : false;
        return respuesta;
    }
}
