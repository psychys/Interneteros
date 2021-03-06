package es.uma.interneteros.test;
import es.uma.interneteros.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Map;


public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia, Map<String, String> properties) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia,properties);
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Cliente cliente = new Cliente("1","123", "cliente",new Date(),null, "calle platano 5", "Malaga", "20749", "España", "activo");
		em.persist(cliente);

		Cuenta cuenta = new Cuenta("123A","123","activa");
		em.persist(cuenta);

		Divisa divisa = new Divisa("USD", "Dolar", "$");
		em.persist(divisa);

		Usuario usuario = new Usuario("1", "123", false,"activo");
		em.persist(usuario);

		Persona_autorizada pa = new Persona_autorizada(123, 123, "Juan", "Perez", "calle platano");
		em.persist(pa);

		Cuenta_Fintech c_fin = new Cuenta_Fintech("123", "123", "activo", new Date());
		em.persist(c_fin);

		Usuario admin = new Usuario("000", "123", true, "activo");
		em.persist(admin);

		Usuario usuario_login = new Usuario("777", "123", false, "activo");
		em.persist(usuario_login);

		/*
		Date d = new Date(22,2,23);
		Date d1 = new Date(20,5,18);
		Cuenta_Fintech cuentafin1 = new Cuenta_Fintech("1234567", "1234567","activo", d, d1, "Pooled");
		Cuenta_Fintech cuentafin2 = new Cuenta_Fintech("0987654","0987654", "activo", d , d1, "Pooled");


		em.persist(cuentafin1);
		em.persist(cuentafin2);

		List<Cuenta_Fintech> l = new ArrayList<Cuenta_Fintech>();
		l.add(cuentafin1);
		List<Cuenta_Fintech> l2 = new ArrayList<Cuenta_Fintech>();
		l.add(cuentafin2);
		Cliente cliente1 = new Cliente(23, 1234567, "cliente", d, d1, "Calle palillo 23",
				"Malaga", 29650, "España", "Activo", l);
		Cliente cliente2 = new Cliente(24, 87654321, "cliente", d, d1, "Calle paco 23",
				"Malaga", 29651, "España", "Activo", l2);

		Usuario admin = new Usuario(1 ,"123", true );
		Usuario usu = new Usuario(2, "123", false, cliente1);
		Usuario usu2 = new Usuario(2, "123", false, cliente2);

		em.persist(cliente1);
		em.persist(cliente2);
		em.persist(usu);
		em.persist(usu2);
		em.persist(admin);


		Divisa dolar = new Divisa("USD", "Dolar", "$", 1.07 );
		Divisa yen = new Divisa("JPY" , "Yen", "¥", 0.007);
		Divisa libra = new Divisa("GBP", "Libra Esterlina", "£",1.18 );

		em.persist(dolar);
		em.persist(yen);
		em.persist(libra);
		*/

		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
