import java.util.Date;

import java.util.logging.Logger;

import javax.naming.NamingException;

import es.uma.ejb.GestionCliente;
import es.uma.ejb.GestionUsuario;
import es.uma.exceptions.ClienteException;
import es.uma.exceptions.UsuarioException;
import es.uma.jpa.Cliente;
import es.uma.jpa.Usuario;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class ClientePrueba {
	
	private static final Logger LOG = Logger.getLogger(ClientePrueba.class.getCanonicalName());

	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "eBuryTest";
	private static final String CLIENTE_EJB = "java:global/classes/ClienteEJB";
	private static final String USUARIO_EJB = "java:global/classes/UsuarioEJB";

	private GestionCliente gestionCliente;
	private GestionUsuario gestionUsuario;

	@Before
	public void setup() throws NamingException  {
		gestionCliente = (GestionCliente) SuiteTest.ctx.lookup(CLIENTE_EJB);
		gestionUsuario = (GestionUsuario) SuiteTest.ctx.lookup(USUARIO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	//@Requisito 2
	@Test
	public void testAltaCliente() throws ClienteException {
		Usuario admin = new Usuario(000,"123", true);
		Cliente cliente = new Cliente(2,456, "cliente",new Date(),null, "calle platano 5", "Malaga", 20749, "España", "activo");

		gestionCliente.AltaCliente(admin, cliente);

		assertNotNull("No se ha creado el cliente", gestionCliente.BuscarCliente(cliente.getID()));

	 }

	//@Requisito 3
	@Test
	public void testActualizarCliente() throws ClienteException {

		Usuario admin = new Usuario(000, "123", true);
		Cliente cliente = gestionCliente.BuscarCliente(1);

		assertEquals(cliente.getDireccion() ,"calle platano 5");

		String direccion = cliente.getDireccion();
		cliente.setDireccion("calle coco 25");
		gestionCliente.ActualizarCliente(admin, cliente);

		cliente = gestionCliente.BuscarCliente(1);

		assertNotEquals("No se ha actualizado el cliente", cliente.getDireccion(), direccion);

	}

	@Test
	public void testMarcarCliente() throws ClienteException {

		Usuario admin = new Usuario(000, "123", true);
		Cliente cliente = gestionCliente.BuscarCliente(1);

		String estado = cliente.getEstado();
		gestionCliente.MarcarCliente(cliente,"baja",admin);

		cliente = gestionCliente.BuscarCliente(1);

		assertNotEquals("No se ha marcado el cliente", cliente.getEstado(), estado);

	}

}
