import es.uma.jpa.Cuenta;
import es.uma.jpa.Usuario;
import es.uma.ejb.GestionCuenta;
import es.uma.exceptions.CuentaException;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CuentaPrueba {

    private static final Logger LOG = Logger.getLogger(CuentaPrueba.class.getCanonicalName());
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "eBuryTest";
    private static final String CUENTA_EJB = "java:global/classes/CuentaEJB";


    private GestionCuenta gestionCuenta;

    @Before
    public void setup() throws NamingException {
        gestionCuenta = (GestionCuenta) SuiteTest.ctx.lookup(CUENTA_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    //@Requisitos({"RF5"})
    @Test
    public void testCrearCuenta() throws CuentaException {

        Usuario admin = new Usuario(000, "123", true);
        Cuenta cuenta = new Cuenta("456B", "123", "abierta");

        gestionCuenta.CrearCuenta(cuenta, admin);

        assertEquals("No se ha creado la cuenta", cuenta, gestionCuenta.BuscarCuenta("456B"));

    }

    @Test
    public void testBuscarCuenta() throws CuentaException {

        Usuario admin = new Usuario(000, "123", true);
        Cuenta cuenta = new Cuenta("456B","123","activa");

        gestionCuenta.CrearCuenta(cuenta,admin);

        cuenta = gestionCuenta.BuscarCuenta("456B");

        assertNotEquals("No se ha encontrado la cuenta", null, cuenta);

    }

    @Test
    public void testActualizarCuenta() throws CuentaException {

        Usuario admin = new Usuario(000, "123", true);
        Cuenta cuenta = gestionCuenta.BuscarCuenta("123A");

        assertEquals(cuenta.getSWIFT() ,"123");

        String swift = cuenta.getSWIFT();
        cuenta.setSWIFT("456");
        gestionCuenta.ActualizarCuenta(cuenta,admin);

        cuenta = gestionCuenta.BuscarCuenta("123A");

        assertNotEquals("No se ha actualizado la cuenta", swift , cuenta.getSWIFT());

    }

    //@Requisitos({"RF9"})
    @Test
    public void testMarcarCuenta() throws CuentaException {

        Usuario admin = new Usuario(000, "123", true);
        Cuenta cuenta = gestionCuenta.BuscarCuenta("123A");

        String estado = cuenta.getEstado();
        gestionCuenta.MarcarCuenta(cuenta,"cerrada",admin);

        cuenta = gestionCuenta.BuscarCuenta("123A");

        assertNotEquals("No se ha marcado la cuenta", cuenta.getEstado(), estado);

    }
}