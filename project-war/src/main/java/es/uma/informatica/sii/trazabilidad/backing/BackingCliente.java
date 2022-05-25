package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;
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

@Named(value= "cliente")
@RequestScoped
public class BackingCliente {

    @Inject
    private GestionCliente clientes;
    @Inject
    private InfoSesion sesion;

    private Individual c;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String pais;
    private int cp;
    private String direccion;
    private Date fecha;
    private int identificacion;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String mostrarPais() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getPais();
    }

    public String mostrarCiudad() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getCiudad();
    }

    public Date mostrarFecha() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getFecha_Alta();
    }

    public int mostrarCP() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getC_postal();
    }

    public int mostrarIdentificacion() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getIdentificacion();
    }

    public String mostrarDireccion() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getDireccion();
    }

    /*public String mostrarNombre() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId());
    }

    public String mostrarApellido() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getDireccion();
    }*/
/*
    public int mostrarSaldo() throws ClienteException {
       List l = clientes.BuscarCliente(sesion.getUsuario().getId()).getC_fintech();
        Cuenta_referencia c = (Cuenta_referencia) l.get(1);
        return c.getSaldo();
    }

*/

}
