package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionCuenta;
import es.uma.interneteros.ejb.GestionUsuario;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.ejb.exceptions.CuentaException;
import es.uma.interneteros.ejb.exceptions.UsuarioException;
import es.uma.interneteros.jpa.*;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.Date;
import java.util.List;

@Named(value= "admin")
@RequestScoped
public class BackingAdmin {

    @Inject
    private GestionCliente clientes;

    @Inject
    private GestionCuenta cuentas;

    @Inject
    private InfoSesion sesion;

    @PersistenceContext
    EntityManager em;

    @Resource
    private UserTransaction user;

    private String id;

    private String nombre;
    private String apellido;
    private String ciudad;
    private String pais;
    private String CP;
    private String identificacion;
    private String direccion;

    private String tipo_cliente;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public String buscarClienteAdmin() throws ClienteException {
        try {
            Individual c = clientes.BuscarCliente(id);

            if (c != null) {
                return "mostrarDatosCliente.xhtml";
            }
        } catch (ClienteException e) {
            FacesMessage fm = new FacesMessage("Error al buscar el cliente");
            FacesContext.getCurrentInstance().addMessage("Cliente:cliente", fm);
        }
        return null;
    }
    public String mostrarPais() throws ClienteException {
        return clientes.BuscarCliente(id).getPais();
    }

    public String mostrarCiudad() throws ClienteException {
        return clientes.BuscarCliente(id).getCiudad();
    }

    public Date mostrarFecha() throws ClienteException {
        return clientes.BuscarCliente(id).getFecha_Alta();
    }

    public String mostrarCP() throws ClienteException {
        return clientes.BuscarCliente(id).getC_postal();
    }

    public String mostrarIdentificacion() throws ClienteException {
        return clientes.BuscarCliente(id).getIdentificacion();
    }

    public String mostrarDireccion() throws ClienteException {
        return clientes.BuscarCliente(id).getDireccion();
    }

    public String mostrarNombre() throws ClienteException {
        return clientes.BuscarCliente(id).getNombre();

       /* String id = sesion.getUsuario().getId();

        Query q = (Query) em.createQuery("Select id FROM Individual id where id.ID = :id ");

       return q.toString();
       */

    }

    public String mostrarApellido() throws ClienteException {
        return clientes.BuscarCliente(id).getApellidos();
    }

    public String editarClienteAdmin() throws ClienteException {
        try {
            Individual c = clientes.BuscarCliente(id);

            if (c != null) {
                return "editarDatosCliente.xhtml";
            }
        } catch (ClienteException e) {
            FacesMessage fm = new FacesMessage("Error al buscar el cliente");
            FacesContext.getCurrentInstance().addMessage("Cliente:cliente", fm);
        }
        return null;
    }

    public String confirmarEdit() throws ClienteException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Cliente cliente = clientes.BuscarCliente(id);
        cliente.setID(id);
        cliente.setDireccion(direccion);
        cliente.setCiudad(ciudad);
        cliente.setC_postal(CP);
        cliente.setPais(pais);
        Usuario u = sesion.getUsuario();

        user.begin();
        em.merge(cliente);
        user.commit();

        return "mostrarDatosCliente.xhtml";
    }

    public String crearCliente() throws ClienteException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Cliente cliente = new Cliente();
            cliente.setID(id);
            cliente.setTipo_cliente(tipo_cliente);
            cliente.setIdentificacion(identificacion);
            cliente.setDireccion(direccion);
            cliente.setCiudad(ciudad);
            cliente.setC_postal(CP);
            cliente.setPais(pais);
            user.begin();
            em.persist(cliente);
            user.commit();
            return "CreacionClienteExitosa.xhtml";

     
    }

    //METODOS Y ATRIBUTOS PARA CRUD CUENTA

    private String IBAN;
    private String SWIFT;
    private String estado;
    private String tipo;

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String mostrarSWIFT() throws CuentaException {
        return cuentas.BuscarCuenta(IBAN).getSWIFT();
    }

    public String mostrarestado() throws CuentaException {
        return cuentas.BuscarCuenta(IBAN).getEstado();
    }

    public String getEstado() {
        return estado;
    }

    public String getSWIFT() {
        return SWIFT;
    }

    public void setSWIFT(String SWIFT) {
        this.SWIFT = SWIFT;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String buscarCuentaAdmin() {
        try {
            Cuenta c = cuentas.BuscarCuenta(IBAN);

            if (c != null) {
                return "mostrarDatosCuenta.xhtml";
            }
        } catch (CuentaException e) {
            FacesMessage fm = new FacesMessage("Error al buscar el cuenta");
            FacesContext.getCurrentInstance().addMessage("Cuenta:cuenta", fm);
        }
        return null;
    }

    public String editarCuentaAdmin() throws ClienteException {
        try {
            Cuenta c = cuentas.BuscarCuenta(IBAN);

            if (c != null) {
                return "editarDatosCuenta.xhtml";
            }
        } catch (CuentaException e) {
            FacesMessage fm = new FacesMessage("Error al buscar la cuenta");
            FacesContext.getCurrentInstance().addMessage("Cuenta:cuenta", fm);
        }
        return null;
    }

    public String confirmarEditCuenta() throws CuentaException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Cuenta cuenta = cuentas.BuscarCuenta(IBAN);
        cuenta.setIBAN(IBAN);
        cuenta.setSWIFT(SWIFT);
        cuenta.setEstado(estado);


        Usuario u = sesion.getUsuario();

        user.begin();
        em.merge(cuenta);
        user.commit();

        return "mostrarDatosCuenta.xhtml";
    }


    //METODOS Y ATRIBUTOS PARA CRUD USUARIO

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios;
        Query query = em.createQuery("SELECT u FROM Usuario u");
        usuarios = query.getResultList();

        return usuarios;
    }

}
        /*
    public void eliminarCliente() throws ClienteException {
        Cliente cliente = clientes.BuscarCliente(id);
        if (cliente.getEstado().equals("baja")) {
            FacesMessage fm = new FacesMessage("El cliente ya esta dado de baja.");
            FacesContext.getCurrentInstance().addMessage("Cliente:client", fm);
        } else {
            clientes.MarcarCliente(cliente, "baja", admin);
        }
    }*/







