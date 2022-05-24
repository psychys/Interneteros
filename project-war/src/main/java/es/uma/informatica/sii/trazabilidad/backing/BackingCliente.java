package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Individual;
import es.uma.interneteros.jpa.Usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value= "backingcliente")
@RequestScoped
public class BackingCliente {


    @Inject
    private GestionCliente clientes;
    @Inject
    private InfoSesion sesion;

    private Individual cliente;
    
    public BackingCliente() throws ClienteException {
        Usuario usu = sesion.getUsuario();
            this.cliente = (Individual) clientes.BuscarCliente(usu.getId());

    }

    public String getNombre(){
        return cliente.getNombre();
    }

    public String getApellido(){
        return cliente.getApellidos();
    }

    public int getIdentificacion(){
        return cliente.getIdentificacion();
    }

    public Date getFecha(){
        return cliente.getFecha_Alta();
    }

    public String getDireccion(){
        return cliente.getDireccion();
    }

    public String getCiudad(){
        return cliente.getCiudad();
    }

    public int getCP(){
        return cliente.getC_postal();
    }

    public String getPais(){
        return cliente.getPais();
    }

}
