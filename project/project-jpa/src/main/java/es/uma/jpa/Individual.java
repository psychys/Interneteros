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

}