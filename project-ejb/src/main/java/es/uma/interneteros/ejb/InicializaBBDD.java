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

        Usuario comprobacion = em.find(Usuario.class, "1");

        if(comprobacion != null){
            return;
        }
    /*


        Cuenta_referencia c = new Cuenta_referencia("123456789B", "Santander", "Malaga", "España", 10.000, new Date(), "activo");
        em.persist(c);

        Segregated seg = new Segregated(, c);
        em.persist(seg);

        List l = new ArrayList();
        l.add(seg);
*/

        Individual i = new Individual("1", "123", "cliente", new Date(), null, "calle platano 5", "Malaga", "20749", "España", "activo","Carlos","Pendejo");
        em.persist(i);


        Individual m = new Individual("2", "1234", "cliente", new Date(), null, "calle platano 5", "Malaga", "20749", "España", "activo","Pedro","Navaja");
        em.persist(m);

        Cuenta cuenta = new Cuenta("123A", "123", "activa","base");
        em.persist(cuenta);


        Usuario usuario = new Usuario("1", "123", false, i,"activo");
        //usuario.setC_cliente(i);
        em.persist(usuario);

        Usuario usuario2 = new Usuario("2", "1234", false,m, "activo");
        em.persist(usuario2);

        Persona_autorizada pa = new Persona_autorizada(123, 123, "Juan", "Perez", "calle platano");
        em.persist(pa);

        Cuenta_Fintech c_fin = new Cuenta_Fintech("123", "123", "activo", new Date(),"fintech",null);
        c_fin.setCliente(i);
        em.persist(c_fin);

//Primera cuenta para transacciones
        Cuenta_referencia c_ref1 = new Cuenta_referencia("ES1492", "1234", "activo", "Banco de pesas", 400,"referencia");
        em.persist(c_ref1);

        Pooled p1 = new Pooled("ES1493","1234","activo", new Date(),"pooled");
        p1.setFecha_apertura(new Date());
        em.persist(p1);

        Segregated s1 = new Segregated("ES1496","1234","activa",new Date(),c_ref1,"segregated",i);
        em.persist(s1);

        List<Cuenta_Fintech> l = new ArrayList<>();
        l.add(s1);
        i.setC_fintech(l);
        em.merge(i);

        DepositadaId id = new DepositadaId();
        id.setIban_pooled(p1.getIBAN());
        id.setIban_referencia(c_ref1.getIBAN());
        id.setSaldo(c_ref1.getSaldo());

        DepositadaPooledReferencia d = new DepositadaPooledReferencia(id,p1,c_ref1,c_ref1.getSaldo()) ;
        em.persist(d);
// Segunda cuenta para transacciones
        Cuenta_referencia c_ref2 = new Cuenta_referencia("ES1494", "1234", "activo", "Banco de pesas", 1000,"referencia");
        em.persist(c_ref2);

        Segregated s2 = new Segregated("ES1497","1234","activa",new Date(),c_ref2,"segregated",m);
        em.persist(s2);

        List<Cuenta_Fintech> l2 = new ArrayList<>();
        l2.add(s2);
        m.setC_fintech(l2);
        em.merge(m);

        Pooled p2 = new Pooled("ES1495","1234","activo", new Date(),"pooled");
        p2.setFecha_apertura(new Date());
        em.persist(p2);

        DepositadaId id2 = new DepositadaId();
        id.setIban_pooled(p2.getIBAN());
        id.setIban_referencia(c_ref2.getIBAN());
        id.setSaldo(c_ref2.getSaldo());

        Usuario admin = new Usuario("000", "123", true, "activo");
        em.persist(admin);

        Usuario usuario_login = new Usuario("777", "123", false, "activo");
        em.persist(usuario_login);

        Cuenta_referencia quesada = new Cuenta_referencia("ES2000", "1234", "activo", "PAD THAI WOK", 50000,"referencia");
        em.persist(quesada);

        Divisa dolares = new Divisa("USD", "Dolar", "$");
        em.persist(dolares);

        Divisa libras = new Divisa("GBD","Libra","£");
        em.persist(libras);

        Divisa euros = new Divisa("EUR","Euro","€");
        em.persist(euros);

        quesada.setDivisa(euros);
        em.merge(quesada);

        c_ref1.setDivisa(euros);
        em.merge(c_ref1);

        c_ref2.setDivisa(euros);
        em.merge(c_ref2);
    }
    
}
