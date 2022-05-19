package es.uma.interneteros.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private boolean administrador;
    @Column
    private String estado;

    public Usuario(int id, String contrasena ,boolean administrador, Cliente cliente, String estado) {
        this.id = id;
        this.contrasena = contrasena;
        this.administrador = administrador;
        this.c_cliente = cliente;
        this.estado = estado;
    }
    public Usuario(int id, String contrasena ,boolean administrador, String estado) {
        this.id = id;
        this.contrasena = contrasena;
        this.administrador = administrador;
        this.estado = estado;
    }

    public Usuario() {

    }



    @OneToOne //(mappedBy ="u_usuario")
    private Cliente c_cliente;

    @OneToOne //(mappedBy ="uu_usuario")
    private Persona_autorizada persona_autorizada;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public Cliente getC_cliente() {
        return c_cliente;
    }

    public void setC_cliente(Cliente c_cliente) {
        this.c_cliente = c_cliente;
    }

    public Persona_autorizada getPersona_autorizada() {
        return persona_autorizada;
    }

    public void setPersona_autorizada(Persona_autorizada persona_autorizada) {
        this.persona_autorizada = persona_autorizada;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Usuario usuario = (Usuario) object;
        return id == usuario.id && administrador == usuario.administrador && java.util.Objects.equals(contrasena, usuario.contrasena) && java.util.Objects.equals(estado, usuario.estado) && java.util.Objects.equals(c_cliente, usuario.c_cliente) && java.util.Objects.equals(persona_autorizada, usuario.persona_autorizada);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, contrasena, administrador, estado, c_cliente, persona_autorizada);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Usuario{" +
                "id=" + id +
                ", contrasena='" + contrasena + '\'' +
                ", administrador=" + administrador +
                ", estado='" + estado + '\'' +
                ", c_cliente=" + c_cliente +
                ", persona_autorizada=" + persona_autorizada +
                '}';
    }
}
