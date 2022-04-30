package es.uma.ejb;

import es.uma.jpa.Persona_autorizada;
import es.uma.exceptions.PersonaAutorizadaException;

public interface GestionPersonaAutorizada {

    public void CrearPersonaAutorizada(Persona_autorizada c);

    public void ActualizarPersonaAutorizada(Persona_autorizada c) throws PersonaAutorizadaException;

    public Persona_autorizada BuscarPersonaAutorizada(int id) throws PersonaAutorizadaException;

    public void MarcarPersonaAutorizada(Persona_autorizada c, String s) throws PersonaAutorizadaException;

}
