package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCuenta;
import es.uma.interneteros.ejb.GestionTransaccion;
import es.uma.interneteros.ejb.exceptions.CuentaException;
import es.uma.interneteros.ejb.exceptions.TransaccionException;
import es.uma.interneteros.jpa.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.*;

@Named(value= "transaccion")
@RequestScoped
public class BackingTransaccion {

    @Inject
    private GestionTransaccion trans;
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

    public String realizarTransaccion() throws TransaccionException, CuentaException, HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
       Cuenta origen = cuentas.BuscarCuenta(IBAN_origen);
       Cuenta destino = cuentas.BuscarCuenta(IBAN_destino);

        Transaccion t = new Transaccion(Integer.parseInt(cantidad),origen,destino);
        trans.CrearTransaccion(t);
        return "Transferencia_correcta.xhtml";
    }

}
