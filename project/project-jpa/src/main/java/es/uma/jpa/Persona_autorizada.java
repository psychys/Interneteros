package es.uma.jpa;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result
				+ ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((fecha_fin == null) ? 0 : fecha_fin.hashCode());
		result = prime * result
				+ ((fecha_inicio == null) ? 0 : fecha_inicio.hashCode());
		result = prime
				* result
				+ ((fecha_nacimiento == null) ? 0 : fecha_nacimiento.hashCode());
		result = prime * result + identificacion;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona_autorizada other = (Persona_autorizada) obj;
		if (ID != other.ID)
			return false;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fecha_fin == null) {
			if (other.fecha_fin != null)
				return false;
		} else if (!fecha_fin.equals(other.fecha_fin))
			return false;
		if (fecha_inicio == null) {
			if (other.fecha_inicio != null)
				return false;
		} else if (!fecha_inicio.equals(other.fecha_inicio))
			return false;
		if (fecha_nacimiento == null) {
			if (other.fecha_nacimiento != null)
				return false;
		} else if (!fecha_nacimiento.equals(other.fecha_nacimiento))
			return false;
		if (identificacion != other.identificacion)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Persona_autorizada [ID=" + ID + ", identificacion="
				+ identificacion + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", direccion=" + direccion
				+ ", fecha_nacimiento=" + fecha_nacimiento + ", estado="
				+ estado + ", fecha_inicio=" + fecha_inicio + ", fecha_fin="
				+ fecha_fin + "]";
	}
    
    

}
