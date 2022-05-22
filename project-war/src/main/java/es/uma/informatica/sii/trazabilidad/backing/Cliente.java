package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value= "Cliente")
@RequestScoped
public class Cliente {


    @Inject
    private GestionCliente clientes;
    @Inject
    private InfoSesion sesion;



    private Cliente cliente;






}
