/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.trazabilidad.backing;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

import es.uma.interneteros.ejb.GestionUsuario;
import es.uma.interneteros.ejb.exceptions.UsuarioException;
import es.uma.interneteros.jpa.Usuario;

/**
 *
 * @author francis
 */
@Named(value = "registro")
@RequestScoped
public class Registro {
	
	private static final String PARAM_VALIDACION="codigoValidacion";
	private static final String PARAM_CUENTA = "cuenta";

    //@Inject
    @EJB
    private GestionUsuario usuarioEJB;

    private Usuario usuario;
    private Usuario admin;
    private String repass;

    private String cuenta;
    private String codigoValidacion;

    private String mensajeValidacion;
    private boolean validacionOK;

    private boolean registroOK;

    public boolean isRegistroOK() {
        return registroOK;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Registro() {
        usuario = new Usuario();
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getAdmin() {
        return admin;
    }

    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }

    public String registrarUsuario() {
        try {
            if (!usuario.getContrasena().equals(repass)) {
                FacesMessage fm = new FacesMessage("Las contrase??as deben coincidir");
                FacesContext.getCurrentInstance().addMessage("registro:repass", fm);
                return null;
            }

           /* HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
            		.getExternalContext()
            		.getRequest();
            
            String thisUri = request.getRequestURL().toString();
            
            int ultimaBarra = thisUri.lastIndexOf('/');
            if (ultimaBarra < 0) {
            	FacesMessage fm = new FacesMessage("Error interno de URL");
                FacesContext.getCurrentInstance().addMessage(null, fm);
                return null;
            }*/

            usuario.setAdministrador(false);
            usuario.setEstado("activo");
            usuarioEJB.AltaUsuario(usuario);
            //registroOK = true;
            return "index.xhtml";
            
        } catch (UsuarioException e) {
            FacesMessage fm = new FacesMessage("Existe un usuario con la misma cuenta");
            FacesContext.getCurrentInstance().addMessage("registro:user", fm);
            
        }
        return null;
    }

}
