package es.uma.interneteros.ejb;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.interneteros.jpa.Divisa;
import es.uma.interneteros.jpa.Transaccion;
import es.uma.interneteros.ejb.exceptions.TransaccionException;
import es.uma.interneteros.jpa.Usuario;

import javax.transaction.*;
import java.util.List;

public interface GestionTransaccion {


    public void CrearTransaccion(Transaccion t/*,Divisa divisa*/) throws TransaccionException;
    @Requisitos({"RF13"})
    public void CrearTransaccionAdministrador(Usuario u,Transaccion t) throws TransaccionException;

    //public void ActualizarTransaccion(Transaccion t) throws TransaccionException;

    @Requisitos({"RF18"})
    public void cambioDivisa(Usuario u, Transaccion t, Divisa d) throws TransaccionException;

    public List<Transaccion> obtenerTransacciones();

    public Transaccion BuscarTransaccion(int id) throws TransaccionException;

    public boolean EsAutorizada(Transaccion t) throws TransaccionException;

    public void BorrarTransaccion(Transaccion t,String s) throws TransaccionException;

}
