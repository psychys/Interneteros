
import es.uma.ejb.GestionDivisa;
import es.uma.exceptions.DivisaException;
import org.junit.Before;
import org.junit.Test;

import es.uma.jpa.Divisa;
import es.uma.jpa.Usuario;
import org.junit.function.ThrowingRunnable;

import javax.naming.NamingException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class DivisaPrueba {

    private static final Logger LOG = Logger.getLogger(DivisaPrueba.class.getCanonicalName());

    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "eBuryTest";
    private static final String DIVISA_EJB = "java:global/classes/DivisaEJB";

    private GestionDivisa gestionDivisa;

    @Before
    public void setup() throws NamingException {
        gestionDivisa = (GestionDivisa) SuiteTest.ctx.lookup(DIVISA_EJB);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Test
    public void testCrearDivisa() throws DivisaException {

        Usuario admin = new Usuario(000, "123", true,"activo");
        Divisa divisa = new Divisa("EUR", "Euro", "€", 1);

        gestionDivisa.CrearDivisa(divisa, admin);

        assertEquals("No se ha creado la divisa", divisa, gestionDivisa.BuscarDivisa("EUR", admin));

    }

    @Test
    public void testBuscarDivisa() throws DivisaException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Divisa divisa = new Divisa("EUR", "Euro", "€", 1);

        gestionDivisa.CrearDivisa(divisa, admin);

        divisa = gestionDivisa.BuscarDivisa("EUR", admin);

        assertNotEquals("No se ha encontrado la divisa", null, divisa);

    }

    @Test
    public void testActualizarDivisa() throws DivisaException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Divisa divisa = gestionDivisa.BuscarDivisa("USD", admin);

        assertEquals(divisa.getCambio_euro() ,1.07, 0);

        Double cE = divisa.getCambio_euro();
        divisa.setCambio_euro(1.2);
        gestionDivisa.ActualizarDivisa(divisa, admin);

        divisa = gestionDivisa.BuscarDivisa("USD", admin);

        assertNotEquals("No se ha actualizado la divisa", cE, divisa.getCambio_euro());
    }

    @Test
    public void testEliminarDivisa() throws DivisaException {

        Usuario admin = new Usuario(000, "123", true, "activo");
        Divisa divisa = gestionDivisa.BuscarDivisa("USD", admin);

        gestionDivisa.EliminarDivisa(divisa, admin);

        assertThrows("No hubo excepcion", DivisaException.class,
        ()->{
            gestionDivisa.BuscarDivisa(divisa.getAbreviatura(), admin);
        });
    }

}
