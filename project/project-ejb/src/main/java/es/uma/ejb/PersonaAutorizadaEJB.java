package es.uma.ejb;


import es.uma.jpa.Persona_autorizada;
import es.uma.exceptions.PersonaAutorizadaException;
import es.uma.jpa.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonaAutorizadaEJB implements GestionPersonaAutorizada{

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    @Override
    public void CrearPersonaAutorizada(Persona_autorizada c, Usuario admin) throws PersonaAutorizadaException{
        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, c.getID());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }


        if (personaExiste != null) {
            throw new PersonaAutorizadaException("PersonaAutorizada ya existe");
        }

        em.persist(c);
    }

    @Override
    public void ActualizarPersonaAutorizada(Persona_autorizada c, Usuario admin) throws PersonaAutorizadaException {
        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, c.getID());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }


        if (personaExiste == null) {
            throw new PersonaAutorizadaException("PersonaAutorizada no existente");
        }

        em.merge(c);

    }

    //@Requisitos({"RF7"})
    @Override
    public Persona_autorizada BuscarPersonaAutorizada(int id, Usuario admin) throws PersonaAutorizadaException {
        Persona_autorizada c = em.find(Persona_autorizada.class, id);

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }


        if(c == null){
            throw new PersonaAutorizadaException("Persona autorizada no existente");
        }
        return c;
    }

    //@Requisitos({"RF8"})
    @Override
    public void MarcarPersonaAutorizada(Persona_autorizada c, String estado, Usuario admin) throws PersonaAutorizadaException {

        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, c.getID());

        if (!admin.isAdministrador()){
            throw new PersonaAutorizadaException("No eres administrador");
        }

        if(c == null) {
            throw new PersonaAutorizadaException("Persona autorizada no existente");
        }

        c.setEstado(estado);

        em.merge(c);

    }



}
