package es.uma.interneteros.ejbtests;

import java.util.Date;

import java.util.logging.Logger;

import javax.naming.NamingException;

import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionUsuario;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.ejb.exceptions.UsuarioException;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Usuario;
import org.junit.Before;
import org.junit.Test;
//import es.uma.informatica.sii.anotaciones.Requisitos;


import static org.junit.Assert.*;


public class UsuarioPrueba {

    private static final Logger LOG = Logger.getLogger(UsuarioPrueba.class.getCanonicalName());

    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "eBuryTest";
    private static final String USUARIO_EJB = "java:global/classes/UsuarioEJB";

    private GestionUsuario gestionUsuario;

    @Before
    public void setup() throws NamingException  {
        gestionUsuario = (GestionUsuario) SuiteTest.ctx.lookup(USUARIO_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    //@Requisito 2
    @Test
    public void testAltaUsuario() throws UsuarioException {
        Usuario admin = new Usuario(000,"123", true, "activo");
        Usuario usuario = new Usuario(2,"123", false, "activo");

        gestionUsuario.AltaUsuario(admin, usuario);

    }

    //@Requisito 3
    @Test
    public void testActualizarUsuario() throws UsuarioException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Usuario usuario = gestionUsuario.BuscarUsuario(1);

        assertEquals(usuario.getContrasena() ,"123");

        String cont = usuario.getContrasena();
        usuario.setContrasena("321");
        gestionUsuario.ActualizarUsuario(admin,usuario);

        usuario = gestionUsuario.BuscarUsuario(1);

        assertNotEquals("No se ha actualizado el cliente", usuario.getContrasena(), cont);

    }

    @Test
    public void testMarcarCliente() throws UsuarioException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Usuario usuario = gestionUsuario.BuscarUsuario(1);

        String estado = usuario.getEstado();
        gestionUsuario.MarcarUsuario(admin, usuario,"baja");

        usuario = gestionUsuario.BuscarUsuario(1);

        assertNotEquals("No se ha marcado el cliente", usuario.getEstado(), estado);

    }

    @Test
    public void testLoginAdministrador() throws  UsuarioException{
        Usuario admin = new Usuario(000, "123", true, "activo");

        boolean res = gestionUsuario.LoginAdministrador(admin,000,"123");

        assertEquals("Iniciada sesion correcta",res, true);
    }

    @Test
    public void testLoginCliente() throws  UsuarioException{
        Usuario usuario_login = new Usuario(777, "123", false, "activo");

        boolean res = gestionUsuario.LoginCliente(usuario_login,777,"123");

        assertEquals("Iniciada sesion correcta",res, true);
    }

}

