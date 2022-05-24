package es.uma.interneteros.ejb;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Cuenta;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.CuentaException;

public interface GestionCuenta {
    @Requisitos({"RF5"})
    public void CrearCuenta(Cuenta c, Usuario u, String tipo, Cliente cliente) throws CuentaException;

    public void ActualizarCuenta(Cuenta c,Usuario u) throws CuentaException;

    public Cuenta BuscarCuenta(String IBAN) throws CuentaException;

    @Requisitos({"RF9"})
    public void MarcarCuenta(Cuenta c, String estado, Usuario u) throws CuentaException;

}
