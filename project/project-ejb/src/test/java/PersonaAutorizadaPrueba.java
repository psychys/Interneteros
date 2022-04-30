
import es.uma.ejb.GestionPersonaAutorizada;
import es.uma.exceptions.PersonaAutorizadaException;
import org.junit.Before;
import org.junit.Test;

import es.uma.jpa.Persona_autorizada;
import es.uma.jpa.Usuario;
import org.junit.function.ThrowingRunnable;

import javax.naming.NamingException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PersonaAutorizadaPrueba {

    private static final Logger LOG = Logger.getLogger(PersonaAutorizadaPrueba.class.getCanonicalName());

    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "eBuryTest";
    private static final String PERSONA_AUTORIZADA_EJB = "java:global/classes/PersonaAutorizadaEJB";

    private GestionPersonaAutorizada gestionPersonaAutorizada;

    @Before
    public void setup() throws NamingException {
        gestionPersonaAutorizada = (GestionPersonaAutorizada) SuiteTest.ctx.lookup(PERSONA_AUTORIZADA_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Test
    public void testCrearPersonaAutorizada() throws PersonaAutorizadaException {

        Usuario admin = new Usuario(000, "123", true,"activo");
        Persona_autorizada pa = new Persona_autorizada(456, 456, "Juan", "Perez", "calle platano");

        gestionPersonaAutorizada.CrearPersonaAutorizada(pa, admin);

        assertEquals("No se ha creado la persona autorizada", pa, gestionPersonaAutorizada.BuscarPersonaAutorizada(456, admin));

    }

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
