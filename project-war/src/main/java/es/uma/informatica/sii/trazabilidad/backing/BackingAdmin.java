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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;import java.util.Date;import java.time.LocalDateTime;

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
    Date fecha_apertura = new Date();

    public String crearCliente() throws ClienteException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        java.sql.Date d=new java.sql.Date(fecha_apertura.getTime());

        Cliente cliente = new Cliente(id,identificacion,tipo_cliente,fecha_apertura,null,direccion,ciudad,CP,pais,"activo");

        try {
            clientes.AltaCliente(sesion.getUsuario(),cliente);
        } catch (ClienteException e) {
            //FacesMessage fm = new FacesMessage("El usuario ya existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            return null;
        }

        return "CreacionClienteExitosa.xhtml";
    }

    public String eliminarCliente() throws ClienteException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        String res = null;

        try {
            Cliente cliente = clientes.BuscarCliente(id);
            if (!cliente.getEstado().equals("baja")) {
                clientes.MarcarCliente(cliente,"baja",sesion.getUsuario());

                id = cliente.getID();
                tipo = cliente.getTipo_cliente();
                ciudad = cliente.getTipo_cliente();
                direccion = cliente.getDireccion();
                identificacion = cliente.getIdentificacion();
                pais = cliente.getPais();
                CP = cliente.getC_postal();
                estado = cliente.getEstado();


                res = "EliminadoClienteExito.xhtml";
            } /*else {
                //FacesMessage fm = new FacesMessage("El usuario ya esta dado de baja");
                //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
                return null;
            }*/
        } catch (ClienteException e) {
            //FacesMessage fm = new FacesMessage("El usuario no existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            res = "Cliente.xhtml";
        }

        return res;
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

    public String eliminarCuenta() throws CuentaException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        String res = null;

        try {
            Cuenta cuenta = cuentas.BuscarCuenta(IBAN);
            if (!cuenta.getEstado().equals("baja")) {
                cuentas.MarcarCuenta(cuenta,"baja",sesion.getUsuario());

                IBAN = cuenta.getIBAN();
                SWIFT = cuenta.getSWIFT();
                estado = cuenta.getEstado();

                res = "EliminadoCuentaExito.xhtml";
            } /*else {
                //FacesMessage fm = new FacesMessage("El usuario ya esta dado de baja");
                //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
                return null;
            }*/
        } catch (CuentaException e) {
            //FacesMessage fm = new FacesMessage("El usuario no existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            res = "Login.xhtml";
        }

        return res;
    }

    private String tipo_cuenta;
    private String id_cliente;

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String crearCuenta() {
        Cuenta cuenta = new Cuenta(IBAN, SWIFT, estado);

        try {
            cuentas.CrearCuenta(cuenta,sesion.getUsuario(),tipo_cuenta,clientes.BuscarCliente(id_cliente));
        } catch (CuentaException e) {
            //FacesMessage fm = new FacesMessage("El usuario ya existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            return null;
        } catch (ClienteException e) {
            throw new RuntimeException(e);
        }

        return "CreacionCuentaExitosa.xhtml";
    }


    //METODOS Y ATRIBUTOS PARA CRUD USUARIO

    @Inject
    private GestionUsuario usuarios;

    private String contrasena;
    private boolean administrador;
    private String estadoU;

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getEstadoU() {
        return estadoU;
    }

    public void setEstadoU(String estadoU) {
        this.estadoU = estadoU;
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios;
        Query query = em.createQuery("SELECT u FROM Usuario u");
        usuarios = query.getResultList();

        return usuarios;
    }

    public String crearUsuario() {
        Usuario usuario = new Usuario(id, contrasena, administrador, estadoU);

        try {
            usuarios.AltaUsuario(usuario);
        } catch (UsuarioException e) {
            //FacesMessage fm = new FacesMessage("El usuario ya existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            return null;
        }

        return "CreacionUsuarioExitosa.xhtml";
    }

    public String eliminarUsuario() {
        try {
            Usuario usuario = usuarios.BuscarUsuario(id);
            if (!usuario.getEstado().equals("baja")) {
                usuarios.MarcarUsuario(sesion.getUsuario(),usuario,"baja");

                id = usuario.getId();
                contrasena = usuario.getContrasena();
                administrador = usuario.isAdministrador();
                estadoU = usuario.getEstado();

                return "EliminadoUsuarioExito.xhtml";
            } else {
                //FacesMessage fm = new FacesMessage("El usuario ya esta dado de baja");
                //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
                return null;
            }
        } catch (UsuarioException e) {
            //FacesMessage fm = new FacesMessage("El usuario no existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            return null;
        }

    }

    public String buscarUsuario() {
        try {
            Usuario usuario = usuarios.BuscarUsuario(id);

            id = usuario.getId();
            contrasena = usuario.getContrasena();
            administrador = usuario.isAdministrador();
            estadoU = usuario.getEstado();

            return "mostrarDatosUsuario.xhtml";

        } catch (UsuarioException e) {
            //FacesMessage fm = new FacesMessage("El usuario no existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            return null;
        }
    }

    public String editarUsuario() {
        try {
            Usuario u = usuarios.BuscarUsuario(id);

            return "editarDatosUsuario.xhtml";

        } catch (UsuarioException e) {
            //FacesMessage fm = new FacesMessage("El usuario no existe");
            //FacesContext.getCurrentInstance().addMessage("Usuario:id", fm);
            return null;
        }
    }

    public String confirmEditarUsuario() throws UsuarioException {

        Usuario u = usuarios.BuscarUsuario(id);

        u.setId(id);
        u.setContrasena(contrasena);
        u.setAdministrador(administrador);
        u.setEstado(estadoU);

        usuarios.ActualizarUsuario(sesion.getUsuario(), u);

        return "mostrarDatosUsuario.xhtml";
    }

}








