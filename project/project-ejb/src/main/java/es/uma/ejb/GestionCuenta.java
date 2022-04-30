package es.uma.ejb;

import es.uma.jpa.Cuenta;
import es.uma.jpa.Usuario;
import es.uma.exceptions.CuentaException;

public interface GestionCuenta {
    //@Requisitos({"RF5"})
    public void CrearCuenta(Cuenta c,Usuario u) throws CuentaException;

    public void ActualizarCuenta(Cuenta c,Usuario u) throws CuentaException;

    public Cuenta BuscarCuenta(String IBAN) throws CuentaException;

    //@Requisitos({"RF9"})
    public void MarcarCuenta(Cuenta c, String estado, Usuario u) throws CuentaException;

}
