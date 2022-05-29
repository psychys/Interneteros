package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionCuenta;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.ejb.exceptions.CuentaException;
import es.uma.interneteros.jpa.*;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Query;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Typed;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Named(value= "cliente")
@RequestScoped
public class BackingCliente {

    @Inject
    private GestionCliente clientes;
    @Inject
    private GestionCuenta cuentas;
    @Inject
    private InfoSesion sesion;

    @PersistenceContext
    private EntityManager em;

    private Individual c;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String pais;
    private int cp;
    private String direccion;
    private Date fecha;
    private int identificacion;
    private String IBAN;

    private int saldo;

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

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

    public String mostrarCP() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getC_postal();
    }

    public String mostrarIdentificacion() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getIdentificacion();
    }

    public String mostrarDireccion() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getDireccion();
    }

    public String mostrarNombre() throws ClienteException {
       // return clientes.BuscarCliente(sesion.getUsuario().getId()).getNombre();

        String ident = sesion.getUsuario().getId();

        TypedQuery<Individual> q = em.createQuery("Select id FROM Individual id where id.ID = :id ",Individual.class);
        q.setParameter("id",ident);
        List<Individual> l = q.getResultList();
        return l.get(0).getNombre();

    }


    public String mostrarApellido() throws ClienteException {
        return clientes.BuscarCliente(sesion.getUsuario().getId()).getApellidos();
    }


    public String mostrarSaldo() throws ClienteException, CuentaException {

        String res= "vista_cliente.xhtml";
        boolean ok=false;

        ok = comprobarCliente(IBAN,sesion.getUsuario().getId());

        if(ok) {
            Segregated aux = (Segregated) cuentas.BuscarCuenta(this.IBAN);
            Cuenta_referencia c = aux.getC_ref();

            String id = sesion.getUsuario().getId();


            setSaldo(c.getSaldo());

            res="Saldo.xhtml";
        }
        return res;
    }

    public String res(){
        String ident = sesion.getUsuario().getId();

        TypedQuery<Individual> q = em.createQuery("Select id FROM Individual id where id.ID = :id ",Individual.class);
        q.setParameter("id",ident);
        List<Individual> l = q.getResultList();
        return l.get(0).getNombre();
    }

    public boolean comprobarCliente(String iban , String id) throws CuentaException {

        boolean res=false;

        TypedQuery<Cuenta_Fintech> q = em.createQuery("Select c FROM Cuenta_Fintech c  where c.IBAN = :iban ",Cuenta_Fintech.class);
        q.setParameter("iban",iban);
        Cuenta_Fintech l = q.getSingleResult();
        Cliente c = l.getCliente();
        //Tenemos el id del cliente de la cuenta en concreto.
        if(id.equals(c.getID())){
            res=true;
        }


        return res;
    }

/*
    public String mostrarSaldo() throws CuentaException {

        //String res= null;
        boolean ok=false;

        ok = comprobarCliente(IBAN,sesion.getUsuario().getId());

        if(ok) {
            Cuenta_referencia c = (Cuenta_referencia) cuentas.BuscarCuenta(IBAN);

            this.saldo = c.getSaldo();

             return "Saldo.xhtml";
        } else {
            return "vista_cliente.xhtml";
        }

    }


    public boolean comprobarCliente(String iban , String id) throws CuentaException {

        Cuenta_referencia c = (Cuenta_referencia) cuentas.BuscarCuenta(iban);

        //Tenemos el id del cliente de la cuenta en concreto.
        if(c.getC_fintech_segregada() == null || c.getC_fintech_segregada().getCliente() == null || c.getC_fintech_segregada().getCliente().getU_usuario() == null || c.getC_fintech_segregada().getCliente().getU_usuario().getId() == null) {
            return false;
        } else {
            String cliente = c.getC_fintech_segregada().getCliente().getU_usuario().getId();
            return id.equals(cliente);
        }

    }*/


}
