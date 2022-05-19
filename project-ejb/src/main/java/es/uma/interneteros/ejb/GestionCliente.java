package es.uma.interneteros.ejb;

import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.ClienteException;

public interface GestionCliente {

    //@Requisitos({"RF2"})
    public void AltaCliente(Usuario admin, Cliente c) throws ClienteException;

    //@Requisitos({"RF3"})
    public void ActualizarCliente(Usuario admin, Cliente c) throws ClienteException;

    public Cliente BuscarCliente(int id) throws ClienteException;

    //@Requisitos({"RF4"})
    public void MarcarCliente(Cliente c,String s, Usuario admin) throws ClienteException;

}
