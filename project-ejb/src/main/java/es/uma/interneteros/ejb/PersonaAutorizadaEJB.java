package es.uma.interneteros.ejb;


import es.uma.interneteros.jpa.*;
import es.uma.interneteros.ejb.exceptions.PersonaAutorizadaException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonaAutorizadaEJB implements GestionPersonaAutorizada{

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    @Override
    public void CrearPersonaAutorizada(Persona_autorizada pa, Usuario admin) throws PersonaAutorizadaException{
        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, pa.getID());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }


        if (personaExiste != null) {
            throw new PersonaAutorizadaException("PersonaAutorizada ya existe");
        }

        em.persist(pa);
    }

    //@Requisitos({"RF6"})
   /* @Override
    public void AniadirPersonaAutorizada(Persona_autorizada pa, Cuenta_Fintech c_fin, Usuario admin) throws PersonaAutorizadaException{
        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, pa.getID());
        Cuenta_Fintech cuentaExiste = em.find(Cuenta_Fintech.class, c_fin.getIBAN());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }

        if (personaExiste == null) {
            throw new PersonaAutorizadaException("PersonaAutorizada no existente");
        }

        if (cuentaExiste == null) {
            throw new PersonaAutorizadaException("Cuenta no existente");
        }

        Usuario u = c_fin.getCliente().getU_usuario();

        if (u == null) {
            throw new PersonaAutorizadaException("No hay un usuario enlazado a esta cuenta fintech");
        }

        u.setPersona_autorizada(pa);

        em.merge(u);

    }
*/
    @Override
    public void ActualizarPersonaAutorizada(Persona_autorizada pa, Usuario admin) throws PersonaAutorizadaException {
        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, pa.getID());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }


        if (personaExiste == null) {
            throw new PersonaAutorizadaException("PersonaAutorizada no existente");
        }

        em.merge(pa);

    }

    //@Requisitos({"RF7"})
    @Override
    public Persona_autorizada BuscarPersonaAutorizada(int id, Usuario admin) throws PersonaAutorizadaException {
        Persona_autorizada pa = em.find(Persona_autorizada.class, id);

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }


        if(pa == null){
            throw new PersonaAutorizadaException("Persona autorizada no existente");
        }
        return pa;
    }

    //@Requisitos({"RF8"})
    @Override
    public void MarcarPersonaAutorizada(Persona_autorizada pa, String estado, Usuario admin) throws PersonaAutorizadaException {

        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, pa.getID());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }

        if(personaExiste == null) {
            throw new PersonaAutorizadaException("Persona autorizada no existente");
        }

        pa.setEstado(estado);

        em.merge(pa);

    }

}
