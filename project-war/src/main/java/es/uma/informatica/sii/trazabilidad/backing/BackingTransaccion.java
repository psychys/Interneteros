package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionTransaccion;
import es.uma.interneteros.ejb.exceptions.TransaccionException;
import es.uma.interneteros.jpa.Cuenta;
import es.uma.interneteros.jpa.Cuenta_referencia;
import es.uma.interneteros.jpa.Pooled;
import es.uma.interneteros.jpa.Transaccion;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value= "transaccion")
@RequestScoped
public class BackingTransaccion {

    @Inject
    private GestionTransaccion trans;
    @Inject
    private InfoSesion sesion;

    private String id = sesion.getUsuario().getC_cliente().getID();

    private String IBAN_destino;

    private Cuenta origen ;

    private Cuenta destino;

    private int cantidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIBAN_destino() {
        return IBAN_destino;
    }

    public void setIBAN_destino(String id_destino) {
        this.IBAN_destino = id_destino;
    }

    public Cuenta getOrigen() {
        return origen;
    }

    public void setOrigen(Pooled origen) {
        this.origen = origen;
    }

    public Cuenta getDestino() {
        return destino;
    }

    public void setDestino(Pooled destino) {
        this.destino = destino;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String realizarTransaccion() throws TransaccionException {
        Transaccion t = new Transaccion(cantidad,origen,destino);
        trans.CrearTransaccion(t);
        return "Transferencia_correcta.xhtml";
    }

}
