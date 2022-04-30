package es.uma.ejb;

import es.uma.jpa.Divisa;
import es.uma.jpa.Transaccion;
import es.uma.exceptions.TransaccionException;
import es.uma.jpa.Usuario;

import java.util.List;

public interface GestionTransaccion {

    public void CrearTransaccion(Transaccion t) throws TransaccionException;

    //public void ActualizarTransaccion(Transaccion t) throws TransaccionException;

    public void cambioDivisa(Usuario u, Transaccion t, Divisa d) throws TransaccionException;

    public List<Transaccion> obtenerTransacciones();

    public Transaccion BuscarTransaccion(int id) throws TransaccionException;

    public boolean EsAutorizada(Transaccion t) throws TransaccionException;

    public void BorrarTransaccion(Transaccion t,String s) throws TransaccionException;

}
