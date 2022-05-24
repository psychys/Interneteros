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

@Named(value= "cliente")
@RequestScoped
public class BackingCliente {

    @Inject
    private GestionCliente clientes;
    @Inject
    private InfoSesion sesion;

    private Individual c;

    public BackingCliente(Individual c) {
        this.c = c;
    }

    public BackingCliente() {
    }

    public Individual getC() {
        return c;
    }

    public void setC(Individual c) {
        this.c = c;
    }


}
