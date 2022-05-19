package es.uma.interneteros.jpa;

import javax.persistence.*;

@Entity
public class AutorizacionEmpresaAutorizado {

    @EmbeddedId
    private AutorizacionId id;

    @ManyToOne
    @MapsId("ID_empresa")
    @JoinColumn(name = "EMPRESA_ID")
    private Empresa empresa;

    @ManyToOne
    @MapsId("ID_autorizado")
    @JoinColumn(name = "AUTORIZADO_ID")
    private Persona_autorizada autorizado;

    private String tipo;


    public AutorizacionId getId() {
        return id;
    }

    public void setId(AutorizacionId id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Persona_autorizada getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Persona_autorizada autorizado) {
        this.autorizado = autorizado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        AutorizacionEmpresaAutorizado that = (AutorizacionEmpresaAutorizado) object;
        return java.util.Objects.equals(id, that.id) && java.util.Objects.equals(empresa, that.empresa) && java.util.Objects.equals(autorizado, that.autorizado) && java.util.Objects.equals(tipo, that.tipo);
    }

    public int hashCode() {
        //return Objects.hash(super.hashCode(), id, empresa, autorizado, tipo);
        return id.hashCode() + empresa.hashCode() + empresa.hashCode() + tipo.hashCode();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "AutorizacionEmpresaAutorizado{" +
                "id=" + id +
                ", empresa=" + empresa +
                ", autorizado=" + autorizado +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
