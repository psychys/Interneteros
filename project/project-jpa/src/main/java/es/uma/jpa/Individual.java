package es.uma.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Individual extends Cliente{


    @Column(nullable = false)
    private String Nombre;
    @Column(nullable = false)
    private String Apellidos;
    private Date Fecha_nacimiento;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public Date getFecha_nacimiento() {
        return Fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        Fecha_nacimiento = fecha_nacimiento;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Individual that = (Individual) object;
        return java.util.Objects.equals(Nombre, that.Nombre) && java.util.Objects.equals(Apellidos, that.Apellidos) && java.util.Objects.equals(Fecha_nacimiento, that.Fecha_nacimiento);
    }

    public int hashCode() {
        //return Objects.hash(super.hashCode(), Nombre, Apellidos, Fecha_nacimiento);
        return Nombre.hashCode() + Apellidos.hashCode() + Fecha_nacimiento.hashCode();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Individual{" +
                "Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Fecha_nacimiento=" + Fecha_nacimiento +
                '}';
    }
}