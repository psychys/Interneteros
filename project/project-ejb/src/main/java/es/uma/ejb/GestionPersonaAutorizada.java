package es.uma.ejb;

import es.uma.jpa.Persona_autorizada;
import es.uma.exceptions.PersonaAutorizadaException;
import es.uma.jpa.Usuario;

public interface GestionPersonaAutorizada {

    public void CrearPersonaAutorizada(Persona_autorizada c, Usuario admin) throws PersonaAutorizadaException;

    public void ActualizarPersonaAutorizada(Persona_autorizada c, Usuario admin) throws PersonaAutorizadaException;

    public Persona_autorizada BuscarPersonaAutorizada(int id, Usuario admin) throws PersonaAutorizadaException;

    public void MarcarPersonaAutorizada(Persona_autorizada c, String s, Usuario admin) throws PersonaAutorizadaException;

}
