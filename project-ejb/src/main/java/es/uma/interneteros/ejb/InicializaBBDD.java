package es.uma.interneteros.ejb;

import es.uma.interneteros.jpa.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
@Startup
public class InicializaBBDD {

    @PersistenceContext(unitName="project")
    private EntityManager em;


    @PostConstruct
    public void inicializar () {

        /*Usuario comprobacion = em.find(Usuario.class, "1");

        if(comprobacion != null){
            return;
        }*/
        /*
        Divisa divisa = new Divisa("USD", "Dolar", "$", 1.07);
        em.persist(divisa);

        Cuenta_referencia c = new Cuenta_referencia("123456789B", "Santander", "Malaga", "España", 10.000, new Date(), "activo");
        em.persist(c);

        Segregated seg = new Segregated(1, c);
        em.persist(seg);

        List l = new ArrayList();
        l.add(seg);
*/
        Cliente cliente = new Cliente("1", 123, "cliente", new Date(), null, "calle platano 5", "Malaga", 20749, "España", "activo"/*,l*/);
        em.persist(cliente);

        Individual i = new Individual("1","Carlos","Pendejo");
        em.persist(i);

        Cuenta cuenta = new Cuenta("123A", "123", "activa");
        em.persist(cuenta);


        Usuario usuario = new Usuario("1", "123", false, "activo");
        em.persist(usuario);

        Persona_autorizada pa = new Persona_autorizada(123, 123, "Juan", "Perez", "calle platano");
        em.persist(pa);

        Cuenta_Fintech c_fin = new Cuenta_Fintech("123", "123", "activo", new Date(), new Date(), "");
        em.persist(c_fin);

        Usuario admin = new Usuario("000", "123", true, "activo");
        em.persist(admin);

        Usuario usuario_login = new Usuario("777", "123", false, "activo");
        em.persist(usuario_login);


    }


}
