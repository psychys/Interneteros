/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.trazabilidad.backing;

import es.uma.interneteros.ejb.GestionUsuario;
import es.uma.interneteros.ejb.UsuarioEJB;
import es.uma.interneteros.ejb.exceptions.UsuarioException;
import es.uma.interneteros.jpa.Usuario;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "login")
@RequestScoped
public class Login {

    @Inject
    private GestionUsuario usuarioEJB;
    @Inject
    private InfoSesion sesion;

    private Usuario usuario;

    /**
     * Creates a new instance of login
     */
    public Login() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String entrar() {
        try {
            boolean logeado = usuarioEJB.LoginCliente(usuario, usuario.getId(), usuario.getContrasena());
            sesion.setUsuario(usuario);
            if (logeado) {
                return "index.xhtml";
            }
        } catch (UsuarioException e) {
            FacesMessage fm = new FacesMessage("Error al logearse");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        }

        /*
        catch (UsuarioException e) {
            FacesMessage fm = new FacesMessage("La contraseña no es correcta");
            FacesContext.getCurrentInstance().addMessage("login:pass", fm);
        } catch (UsuarioException e) {
            FacesMessage fm = new FacesMessage("La cuenta existe pero no está activa");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (UsuarioException e) {
            FacesMessage fm = new FacesMessage("Error: " + e);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        */
        return null;
    }

}
