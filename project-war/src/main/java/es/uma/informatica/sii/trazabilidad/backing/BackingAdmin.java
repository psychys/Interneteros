package es.uma.informatica.sii.trazabilidad.backing;


import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionUsuario;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.ejb.exceptions.UsuarioException;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Cuenta_referencia;
import es.uma.interneteros.jpa.Individual;
import es.uma.interneteros.jpa.Usuario;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Named(value= "admin")
@RequestScoped
public class BackingAdmin {
    private EntityManager em;

    @Inject
    private GestionCliente clientes;

    @Inject
    private InfoSesion sesion;

    private String id;

    private String nombre;
    private String apellido;
    private String ciudad;
    private String pais;
    private String CP;
    private String identificacion;
    private String direccion;

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

    public String buscarClienteAdmin() throws ClienteException {
        try {
            Individual c = clientes.BuscarCliente(id);

            if (c != null) {
                return "mostrarDatosCliente.xhtml";
            }
        } catch (ClienteException e) {
            FacesMessage fm = new FacesMessage("Error al buscar el cliente");
           // FacesContext.getCurrentInstance().addMessage("login:user", fm);
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

    public int mostrarCP() throws ClienteException {
        return clientes.BuscarCliente(id).getC_postal();
    }

    public int mostrarIdentificacion() throws ClienteException {
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
            // FacesContext.getCurrentInstance().addMessage("login:user", fm);
        }
        return null;
    }

    public String confirmarEdit() throws ClienteException {
        clientes.ActualizarCliente(sesion.getUsuario(),clientes.BuscarCliente(id));
        return "mostrarDatosCliente.xhtml";
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
    }

    public void crearCliente() throws ClienteException {
        cliente.setTipo_cliente("cliente");
        cliente.setFecha_Alta(new Date());
        cliente.setEstado("activo");
        clientes.AltaCliente(admin, cliente);
    }

    public void editarCliente() throws ClienteException {
        Cliente cliente = clientes.BuscarCliente(id);
        cliente.setDireccion(direccion);
        cliente.setCiudad(ciudad);
        cliente.setC_postal(cp);
        cliente.setPais(pais);
        clientes.ActualizarCliente(admin, cliente);
    }
*/



