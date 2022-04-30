package es.uma.exceptions;

public class TransaccionException extends Exception{

    public TransaccionException(){

    }

    public TransaccionException(String msg){
        super(msg);
    }
}
