package es.uma.interneteros.ejb;

import es.uma.interneteros.jpa.Cuenta_referencia;
import es.uma.interneteros.jpa.Divisa;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.DivisaException;

public interface GestionDivisa {

    public void CrearDivisa(Divisa d, Usuario u) throws DivisaException;

    public void ActualizarDivisa(Divisa d, Usuario u) throws DivisaException;

    public Divisa BuscarDivisa(String abreviatura, Usuario u) throws DivisaException;

    public void EliminarDivisa(Divisa d, Usuario u) throws DivisaException;

    public Divisa BuscarDivisaCliente(String eur) throws DivisaException;

    public void CambioDivisa(Cuenta_referencia c ,String d) throws DivisaException;
}
