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
    private String nombre;
    private String apellido;
    private String ciudad;
    private String pais;
    private String cp;
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

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
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
}
