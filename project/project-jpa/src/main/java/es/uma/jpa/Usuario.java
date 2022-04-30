package es.uma.jpa;

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

    public Usuario(int id, String contrasena ,boolean administrador, Cliente cliente) {
        this.id = id;
        this.contrasena = contrasena;
        this.administrador = administrador;
        this.c_cliente = cliente;
    }
    public Usuario(int id, String contrasena ,boolean administrador) {
        this.id = id;
        this.contrasena = contrasena;
        this.administrador = administrador;

    }

    public Usuario() {

    }



    @OneToOne //(mappedBy ="u_usuario")
    private Cliente c_cliente;

    @OneToOne //(mappedBy ="uu_usuario")
    private Persona_autorizada persona_autorizada;


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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && administrador == usuario.administrador && Objects.equals(contrasena, usuario.contrasena) && Objects.equals(c_cliente, usuario.c_cliente) && Objects.equals(persona_autorizada, usuario.persona_autorizada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contrasena, administrador, c_cliente, persona_autorizada);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", contrasena='" + contrasena + '\'' +
                ", administrador=" + administrador +
                ", c_cliente=" + c_cliente +
                ", persona_autorizada=" + persona_autorizada +
                '}';
    }
}