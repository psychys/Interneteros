package es.uma.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaccion")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_unico;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha_instruccion;
    @Column(nullable = false)
    private int cantidad;
    @Temporal(TemporalType.DATE)
    private Date fecha_ejecucion;
    @Column(nullable = false)
    private String tipo;
    private boolean internacional;
    @ManyToOne
    private Divisa emisor;
    @ManyToOne
    private Divisa receptor;
    @ManyToOne
    private Cuenta origen;
    @ManyToOne
    private Cuenta destino;
    
	public Transaccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_unico() {
		return id_unico;
	}
	public void setId_unico(int id_unico) {
		this.id_unico = id_unico;
	}
	public Date getFecha_instruccion() {
		return fecha_instruccion;
	}
	public void setFecha_instruccion(Date fecha_instruccion) {
		this.fecha_instruccion = fecha_instruccion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha_ejecucion() {
		return fecha_ejecucion;
	}
	public void setFecha_ejecucion(Date fecha_ejecucion) {
		this.fecha_ejecucion = fecha_ejecucion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isInternacional() {
		return internacional;
	}
	public void setInternacional(boolean internacional) {
		this.internacional = internacional;
	}
	public Divisa getEmisor() {
		return emisor;
	}
	public void setEmisor(Divisa emisor) {
		this.emisor = emisor;
	}
	public Divisa getReceptor() {
		return receptor;
	}
	public void setReceptor(Divisa receptor) {
		this.receptor = receptor;
	}
	public Cuenta getOrigen() {
		return origen;
	}
	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}
	public Cuenta getDestino() {
		return destino;
	}
	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((emisor == null) ? 0 : emisor.hashCode());
		result = prime * result
				+ ((fecha_ejecucion == null) ? 0 : fecha_ejecucion.hashCode());
		result = prime
				* result
				+ ((fecha_instruccion == null) ? 0 : fecha_instruccion
						.hashCode());
		result = prime * result + id_unico;
		result = prime * result + (internacional ? 1231 : 1237);
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
		result = prime * result
				+ ((receptor == null) ? 0 : receptor.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Transaccion other = (Transaccion) obj;
		if (cantidad != other.cantidad)
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (emisor == null) {
			if (other.emisor != null)
				return false;
		} else if (!emisor.equals(other.emisor))
			return false;
		if (fecha_ejecucion == null) {
			if (other.fecha_ejecucion != null)
				return false;
		} else if (!fecha_ejecucion.equals(other.fecha_ejecucion))
			return false;
		if (fecha_instruccion == null) {
			if (other.fecha_instruccion != null)
				return false;
		} else if (!fecha_instruccion.equals(other.fecha_instruccion))
			return false;
		if (id_unico != other.id_unico)
			return false;
		if (internacional != other.internacional)
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		if (receptor == null) {
			if (other.receptor != null)
				return false;
		} else if (!receptor.equals(other.receptor))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaccion [id_unico=" + id_unico + ", fecha_instruccion="
				+ fecha_instruccion + ", cantidad=" + cantidad
				+ ", fecha_ejecucion=" + fecha_ejecucion + ", tipo=" + tipo
				+ ", internacional=" + internacional + ", emisor=" + emisor
				+ ", receptor=" + receptor + ", origen=" + origen
				+ ", destino=" + destino + "]";
	}
	
	    
    
    
    

}