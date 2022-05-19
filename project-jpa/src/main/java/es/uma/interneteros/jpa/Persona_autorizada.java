package es.uma.interneteros.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Persona_autorizada {

    @Id
    private int ID;
    @Column(nullable = false, unique = true)
    private int identificacion;
    @Column( nullable = false)
    private String nombre;
    @Column( nullable = false)
    private String apellidos;
    @Column( nullable = false)
    private String direccion;
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;
    @Temporal(TemporalType.DATE)
    private Date fecha_fin;

	@OneToMany(mappedBy = "autorizado")
	private Set<AutorizacionEmpresaAutorizado> AutorizacionSet = new HashSet<AutorizacionEmpresaAutorizado>();

	@OneToOne(mappedBy ="persona_autorizada")
	private Usuario uu_usuario;

	public Persona_autorizada() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona_autorizada(int id, int identificacion, String nombre, String apellidos, String direccion) {
		this.ID = id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Usuario getUu_usuario() {
		return uu_usuario;
	}

	public void setUu_usuario(Usuario uu_usuario) {
		this.uu_usuario = uu_usuario;
	}
	public Set<AutorizacionEmpresaAutorizado> getAutorizacionSet() {
		return AutorizacionSet;
	}

	public void setAutorizacionSet(Set<AutorizacionEmpresaAutorizado> autorizacionSet) {
		AutorizacionSet = autorizacionSet;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Persona_autorizada that = (Persona_autorizada) object;
		return ID == that.ID && identificacion == that.identificacion && java.util.Objects.equals(nombre, that.nombre) && java.util.Objects.equals(apellidos, that.apellidos) && java.util.Objects.equals(direccion, that.direccion) && java.util.Objects.equals(fecha_nacimiento, that.fecha_nacimiento) && java.util.Objects.equals(estado, that.estado) && java.util.Objects.equals(fecha_inicio, that.fecha_inicio) && java.util.Objects.equals(fecha_fin, that.fecha_fin) && java.util.Objects.equals(AutorizacionSet, that.AutorizacionSet) && java.util.Objects.equals(uu_usuario, that.uu_usuario);
	}

	public int hashCode() {
		//return Objects.hash(super.hashCode(), ID, identificacion, nombre, apellidos, direccion, fecha_nacimiento, estado, fecha_inicio, fecha_fin, AutorizacionSet, uu_usuario);
		return Integer.hashCode(ID) + Integer.hashCode(identificacion) + nombre.hashCode() + apellidos.hashCode() + direccion.hashCode() + fecha_nacimiento.hashCode() + estado.hashCode() + AutorizacionSet.hashCode() + uu_usuario.hashCode();
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Persona_autorizada{" +
				"ID=" + ID +
				", identificacion=" + identificacion +
				", nombre='" + nombre + '\'' +
				", apellidos='" + apellidos + '\'' +
				", direccion='" + direccion + '\'' +
				", fecha_nacimiento=" + fecha_nacimiento +
				", estado='" + estado + '\'' +
				", fecha_inicio=" + fecha_inicio +
				", fecha_fin=" + fecha_fin +
				", AutorizacionSet=" + AutorizacionSet +
				", uu_usuario=" + uu_usuario +
				'}';
	}
}
