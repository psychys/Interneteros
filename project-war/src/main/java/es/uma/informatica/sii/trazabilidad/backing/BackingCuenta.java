package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionCuenta;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Cuenta_referencia;
import es.uma.interneteros.jpa.Individual;
import es.uma.interneteros.jpa.Usuario;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named(value= "cuenta")
@RequestScoped
public class BackingCuenta {

    @Inject
    private GestionCuenta cuentas;
    @Inject
    private InfoSesion sesion;
/*
    public int mostrarSaldo() throws ClienteException {
       List l = clientes.BuscarCliente(sesion.getUsuario().getId()).getC_fintech();
        Cuenta_referencia c = (Cuenta_referencia) l.get(1);
        return c.getSaldo();
    }
*/

}
