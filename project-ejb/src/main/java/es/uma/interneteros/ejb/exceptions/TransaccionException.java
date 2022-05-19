package es.uma.interneteros.ejb.exceptions;

public class TransaccionException extends Exception{

    public TransaccionException(){

    }

    public TransaccionException(String msg){
        super(msg);
    }
}
