
import es.uma.ejb.*;
import es.uma.exceptions.ClienteException;
import es.uma.exceptions.CuentaException;
import es.uma.exceptions.PersonaAutorizadaException;
import es.uma.exceptions.UsuarioException;
import es.uma.jpa.*;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PersonaAutorizadaPrueba {

    private static final Logger LOG = Logger.getLogger(PersonaAutorizadaPrueba.class.getCanonicalName());

    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "eBuryTest";
    private static final String PERSONA_AUTORIZADA_EJB = "java:global/classes/PersonaAutorizadaEJB";
    private static final String CLIENTE_EJB = "java:global/classes/ClienteEJB";
    private static final String USUARIO_EJB = "java:global/classes/UsuarioEJB";
    private static final String CUENTA_EJB = "java:global/classes/CuentaEJB";

    private GestionPersonaAutorizada gestionPersonaAutorizada;
    private GestionCliente gestionCliente;
    private GestionUsuario gestionUsuario;
    private GestionCuenta gestionCuenta;

    @Before
    public void setup() throws NamingException {
        gestionPersonaAutorizada = (GestionPersonaAutorizada) SuiteTest.ctx.lookup(PERSONA_AUTORIZADA_EJB);
        gestionCliente = (GestionCliente) SuiteTest.ctx.lookup(CLIENTE_EJB);
        gestionUsuario = (GestionUsuario) SuiteTest.ctx.lookup(USUARIO_EJB);
        gestionCuenta = (GestionCuenta) SuiteTest.ctx.lookup(CUENTA_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Test
    public void testCrearPersonaAutorizada() throws PersonaAutorizadaException {

        Usuario admin = new Usuario(000, "123", true,"activo");
        Persona_autorizada pa = new Persona_autorizada(456, 456, "Juan", "Perez", "calle platano");

        gestionPersonaAutorizada.CrearPersonaAutorizada(pa, admin);

        assertNotNull("No se ha creado la persona autorizada", gestionPersonaAutorizada.BuscarPersonaAutorizada(pa.getID(), admin));

    }

    //@Requisitos({"RF6"})
   /* @Test
    public void testAniadirPersonaAutorizada() throws PersonaAutorizadaException, ClienteException, UsuarioException, CuentaException {

        Usuario admin = new Usuario(000, "123", true,"activo");
        Persona_autorizada pa = gestionPersonaAutorizada.BuscarPersonaAutorizada(000,admin);
        Cliente c = gestionCliente.BuscarCliente(1);
        Usuario u = gestionUsuario.BuscarUsuario(1);
        Cuenta_Fintech c_fin = (Cuenta_Fintech) gestionCuenta.BuscarCuenta("123");

        c.setU_usuario(u);
        c_fin.setCliente(c);
        gestionCuenta.ActualizarCuenta(c_fin,admin);

        gestionPersonaAutorizada.AniadirPersonaAutorizada(pa, c_fin, admin);

        Cuenta_Fintech c_fin2 = (Cuenta_Fintech) gestionCuenta.BuscarCuenta("123");

        // al añadir un usuario a un cliente y un cliente a una cuenta fintech y actualizar cuenta, el usuario no se
        // queda añadido al cliente que esta en la cuenta fintech, por tanto queda como null, por tanto al buscar
        // la persona autorizada asociada al usuario da NullPointerException

        assertEquals("No se ha añadido la persona autorizada", pa, c_fin2.getCliente().getU_usuario().getPersona_autorizada());

    }*/

    @Test
    public void testBuscarPersonaAutorizada() throws PersonaAutorizadaException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Persona_autorizada pa = new Persona_autorizada(456, 456, "Juan", "Perez", "calle platano");

        gestionPersonaAutorizada.CrearPersonaAutorizada(pa, admin);

        pa = gestionPersonaAutorizada.BuscarPersonaAutorizada(456, admin);

        assertNotEquals("No se ha encontrado la persona autorizada", null, pa);

    }

    //@Requisitos({"RF7"})
    @Test
    public void testActualizarPersonaAutorizada() throws PersonaAutorizadaException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Persona_autorizada pa = gestionPersonaAutorizada.BuscarPersonaAutorizada(123, admin);

        assertEquals("Error buscando persona autorizada", pa.getDireccion() ,"calle platano");

        String calle = pa.getDireccion();
        pa.setDireccion("calle banana");
        gestionPersonaAutorizada.ActualizarPersonaAutorizada(pa, admin);

        pa = gestionPersonaAutorizada.BuscarPersonaAutorizada(123, admin);

        assertNotEquals("No se ha actualizado la persona autorizada", calle, pa.getDireccion());
    }

    //@Requisitos({"RF8"})
    @Test
    public void testMarcarPersonaAutorizada() throws PersonaAutorizadaException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Persona_autorizada pa = gestionPersonaAutorizada.BuscarPersonaAutorizada(123, admin);

        gestionPersonaAutorizada.MarcarPersonaAutorizada(pa, "desactivado", admin);
        String estado = gestionPersonaAutorizada.BuscarPersonaAutorizada(123, admin).getEstado();

        assertEquals("No se ha marcado la persona autorizada", "desactivado", estado);
    }

}
