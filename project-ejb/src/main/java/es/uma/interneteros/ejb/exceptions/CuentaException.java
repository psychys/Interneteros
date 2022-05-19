package es.uma.interneteros.ejb.exceptions;

public class CuentaException extends Exception{

    public CuentaException(){

    }
    public CuentaException(String msg){
        super(msg);
    }
}
