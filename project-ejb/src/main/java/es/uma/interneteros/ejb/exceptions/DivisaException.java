package es.uma.interneteros.ejb.exceptions;

public class DivisaException extends Exception{

    public DivisaException(){

    }

    public DivisaException(String msg){
        super(msg);
    }

}