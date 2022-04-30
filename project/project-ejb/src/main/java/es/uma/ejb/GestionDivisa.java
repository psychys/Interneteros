package es.uma.ejb;

import es.uma.jpa.Divisa;
import es.uma.jpa.Usuario;
import es.uma.exceptions.DivisaException;

public interface GestionDivisa {

    public void CrearDivisa(Divisa d, Usuario u) throws DivisaException;

    public void ActualizarDivisa(Divisa d, Usuario u) throws DivisaException;

    public Divisa BuscarDivisa(String abreviatura, Usuario u) throws DivisaException;

    public void EliminarDivisa(Divisa d, Usuario u) throws DivisaException;
}
