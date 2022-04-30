package es.uma.ejb;


import es.uma.jpa.Persona_autorizada;
import es.uma.exceptions.PersonaAutorizadaException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class PersonaAutorizadaEJB implements GestionPersonaAutorizada{

    //@PersistenceContext(name="Trazabilidad")
    private EntityManager em;

    @Override
    public void CrearPersonaAutorizada(Persona_autorizada c) {

    }

    @Override
    public void ActualizarPersonaAutorizada(Persona_autorizada c) throws PersonaAutorizadaException {
        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, c);
        if (personaExiste == null) {
            throw new PersonaAutorizadaException("PersonaAutorizada no existente");
        }

        em.merge(c);

    }

    @Override
    public Persona_autorizada BuscarPersonaAutorizada(int id) throws PersonaAutorizadaException {
        Persona_autorizada c = em.find(Persona_autorizada.class, id);
        if(c == null){
            throw new PersonaAutorizadaException("Persona autorizada no existente");
        }
        return c;
    }

    @Override
    public void MarcarPersonaAutorizada(Persona_autorizada c, String estado) throws PersonaAutorizadaException {

        Persona_autorizada personaExiste = em.find(Persona_autorizada.class, c);

        if(c == null) {
            throw new PersonaAutorizadaException("Persona autorizada no existente");
        }

        c.setEstado(estado);
        em.merge(c);

    }

}
