package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCuenta;
import es.uma.interneteros.ejb.GestionDivisa;
import es.uma.interneteros.ejb.GestionTransaccion;
import es.uma.interneteros.ejb.exceptions.CuentaException;
import es.uma.interneteros.ejb.exceptions.DivisaException;
import es.uma.interneteros.ejb.exceptions.TransaccionException;
import es.uma.interneteros.jpa.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.*;
import java.sql.DatabaseMetaData;
import java.util.Date;

@Named(value= "transaccion")
@RequestScoped
public class BackingTransaccion {

    @Inject
    private GestionTransaccion trans;

    @Inject
    private GestionDivisa div;
    @Inject
    private InfoSesion sesion;
    @Inject
    private GestionCuenta cuentas;

    private String IBAN_origen;
    private String IBAN_destino;

    private String cantidad;

    public String getIBAN_origen() {
        return IBAN_origen;
    }

    public void setIBAN_origen(String IBAN_origen) {
        this.IBAN_origen = IBAN_origen;
    }

    public String getIBAN_destino() {
        return IBAN_destino;
    }

    public void setIBAN_destino(String IBAN_destino) {
        this.IBAN_destino = IBAN_destino;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String realizarTransaccion() throws TransaccionException, CuentaException,DivisaException {
       String res = null;
       Cuenta origen = cuentas.BuscarCuenta(IBAN_origen);
       Cuenta destino = cuentas.BuscarCuenta(IBAN_destino);
       String id_cliente = sesion.getUsuario().getId();

       String id_cuenta_cliente = ((Cuenta_Fintech) origen).getCliente().getID();
       if(id_cliente.equals(id_cuenta_cliente) ){
           try{
               Transaccion t = new Transaccion(Integer.parseInt(cantidad), origen, destino, new Date(), "transferencia");
               trans.CrearTransaccion(t);//,div.BuscarDivisaCliente("EUR"));
               res = "Transferencia_correcta.xhtml";
           }catch(TransaccionException e){
               res = "Sin_Dinero.xhtml";
           }

       }else{
           res = "vista_cliente.xhtml";
       }
        return res;
    }

    public String realizarTransaccionAdmin() throws TransaccionException, CuentaException, HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException, DivisaException {
        Cuenta origen = cuentas.BuscarCuenta(IBAN_origen);
        Cuenta destino = cuentas.BuscarCuenta(IBAN_destino);


            Transaccion t = new Transaccion(Integer.parseInt(cantidad), origen, destino, new Date(), "transferencia");
            trans.CrearTransaccion(t);//,div.BuscarDivisa("EUR",sesion.getUsuario()));
        return "Transferencia_correcta.xhtml";
    }

}
