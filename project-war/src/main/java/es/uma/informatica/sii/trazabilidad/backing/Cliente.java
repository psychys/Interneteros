package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value= "Cliente")
@RequestScoped
public class Cliente {


    @Inject
    private GestionCliente clientes;
    @Inject
    private InfoSesion sesion;

    private Cliente cliente;

    public String getNombre(){
        return cliente.getNombre();
    }

    public String getApellido(){
        return cliente.getApellido();
    }

    public String getIdentificacion(){
        return cliente.getIdentificacion();
    }

    public Date getFecha(){
        return cliente.getFecha();
    }

    public String getDireccion(){
        return cliente.getDireccion();
    }

    public String getCiudad(){
        return cliente.getCiudad();
    }

    public int getCP(){
        return cliente.getCP();
    }

    public String getPais(){
        return cliente.getPais();
    }

}
