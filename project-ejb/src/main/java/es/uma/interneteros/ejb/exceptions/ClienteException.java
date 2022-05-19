package es.uma.interneteros.ejb.exceptions;

public class ClienteException extends Exception{

    public ClienteException(){

    }
    public ClienteException(String msg){
        super(msg);
    }

}
