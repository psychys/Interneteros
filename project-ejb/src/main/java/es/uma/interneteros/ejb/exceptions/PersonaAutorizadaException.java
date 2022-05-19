package es.uma.interneteros.ejb.exceptions;

public class PersonaAutorizadaException extends Exception {

    public PersonaAutorizadaException(){

    }

    public PersonaAutorizadaException(String msg){
        super(msg);
    }

}
