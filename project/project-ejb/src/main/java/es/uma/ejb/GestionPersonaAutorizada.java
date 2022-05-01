package es.uma.ejb;

import es.uma.jpa.Cuenta_Fintech;
import es.uma.jpa.Persona_autorizada;
import es.uma.exceptions.PersonaAutorizadaException;
import es.uma.jpa.Usuario;

public interface GestionPersonaAutorizada {

    public void CrearPersonaAutorizada(Persona_autorizada c, Usuario admin) throws PersonaAutorizadaException;

    //@Requisitos({"RF6"})
    public void AniadirPersonaAutorizada(Persona_autorizada pa, Cuenta_Fintech c_fin, Usuario admin) throws PersonaAutorizadaException;

    //@Requisitos({"RF7"})
    public void ActualizarPersonaAutorizada(Persona_autorizada c, Usuario admin) throws PersonaAutorizadaException;

    public Persona_autorizada BuscarPersonaAutorizada(int id, Usuario admin) throws PersonaAutorizadaException;

    //@Requisitos({"RF8"})
    public void MarcarPersonaAutorizada(Persona_autorizada c, String s, Usuario admin) throws PersonaAutorizadaException;

}
