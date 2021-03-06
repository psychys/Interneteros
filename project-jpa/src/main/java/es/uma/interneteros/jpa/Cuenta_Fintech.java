package es.uma.interneteros.jpa;

import java.sql.RowId;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Cuenta_Fintech extends Cuenta{
	/*@Id
	@Column(name = "RowId")
	private String rowId;*/
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fecha_apertura;
	@Temporal(TemporalType.DATE)
	private Date fecha_cierre;
	private String clasificacion;
	@ManyToOne (cascade = {CascadeType.PERSIST})
	private Cliente cliente;

	private String tipo;
	
	public Cuenta_Fintech() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cuenta_Fintech(String IBAN, String SWIFT, String estado,
						  Date fecha_apertura){
		super(IBAN, SWIFT, estado);
		this.fecha_apertura = fecha_apertura;
	}
	public Cuenta_Fintech(String IBAN, String SWIFT, String estado,
						  Date fecha_apertura, String tipo, Cliente cliente){
		super(IBAN, SWIFT, estado,tipo);
		this.fecha_apertura = fecha_apertura;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getFecha_apertura() {
		return fecha_apertura;
	}
	public void setFecha_apertura(Date fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}
	public Date getFecha_cierre() {
		return fecha_cierre;
	}
	public void setFecha_cierre(Date fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/*public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}*/

	/*@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Cuenta_Fintech that = (Cuenta_Fintech) o;
		return Objects.equals(rowId, that.rowId) && Objects.equals(fecha_apertura, that.fecha_apertura) && Objects.equals(fecha_cierre, that.fecha_cierre) && Objects.equals(clasificacion, that.clasificacion) && Objects.equals(cliente, that.cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), rowId, fecha_apertura, fecha_cierre, clasificacion, cliente);
	}

	@Override
	public String toString() {
		return "Cuenta_Fintech{" +
				"rowId='" + rowId + '\'' +
				", fecha_apertura=" + fecha_apertura +
				", fecha_cierre=" + fecha_cierre +
				", clasificacion='" + clasificacion + '\'' +
				", cliente=" + cliente +
				'}';
	}*/

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Cuenta_Fintech that = (Cuenta_Fintech) object;
		return java.util.Objects.equals(fecha_apertura, that.fecha_apertura) && java.util.Objects.equals(fecha_cierre, that.fecha_cierre) && java.util.Objects.equals(clasificacion, that.clasificacion) && java.util.Objects.equals(cliente, that.cliente);
	}

	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), fecha_apertura, fecha_cierre, clasificacion, cliente);
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Cuenta_Fintech{" +
				"fecha_apertura=" + fecha_apertura +
				", fecha_cierre=" + fecha_cierre +
				", clasificacion='" + clasificacion + '\'' +
				", cliente=" + cliente +
				'}';
	}
}
