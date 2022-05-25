package es.uma.interneteros.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Individual extends Cliente{


    @Column(nullable = false)
    private String Nombre;
    @Column(nullable = false)
    private String Apellidos;
    private Date Fecha_nacimiento;

    public Individual(String ID, int identificacion, String tipo_cliente, Date fecha_Alta, Date fecha_Baja, String direccion, String ciudad, int c_postal, String pais, String estado, String nombre, String apellidos) {
        super(ID, identificacion, tipo_cliente, fecha_Alta, fecha_Baja, direccion, ciudad, c_postal, pais, estado);
        Nombre = nombre;
        Apellidos = apellidos;
    }

    public Individual(){

    }

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