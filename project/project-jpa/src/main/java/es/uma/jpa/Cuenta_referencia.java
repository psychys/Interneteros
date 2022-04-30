package es.uma.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

//preguntar si hace falta el mappedBy
@Entity
public class
Cuenta_referencia extends Cuenta{

	@Id
	@Column(name = "RowId")
	private String rowId;
	@Column(nullable = false)
	private String nombre_banco;
	private String sucursal;
	private String pais;
	@Column( nullable = false)
	private int saldo;
	@Temporal(TemporalType.DATE)
	private Date fecha_apertura;
	private String estado;
	@OneToOne(mappedBy ="c_ref")
	private Segregated c_fintech_segregada;
	@ManyToOne
	private Divisa divisa;

	@OneToMany(mappedBy = "cuenta_referencia")
	private Set<DepositadaPooledReferencia> DepositadaReferencia = new HashSet<DepositadaPooledReferencia>();
	
	
	public Cuenta_referencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuenta_referencia(String rowId, String nombre_banco, String sucursal, String pais, int saldo, Date fecha_apertura, String estado, Segregated c_fintech_segregada, Divisa divisa, Set<DepositadaPooledReferencia> depositadaReferencia) {
		this.rowId = rowId;
		this.nombre_banco = nombre_banco;
		this.sucursal = sucursal;
		this.pais = pais;
		this.saldo = saldo;
		this.fecha_apertura = fecha_apertura;
		this.estado = estado;
		this.c_fintech_segregada = c_fintech_segregada;
		this.divisa = divisa;
		DepositadaReferencia = depositadaReferencia;

	}

	public Set<DepositadaPooledReferencia> getDepositadaReferencia() {
		return DepositadaReferencia;
	}

	public void setDepositadaReferencia(Set<DepositadaPooledReferencia> depositadaReferencia) {
		DepositadaReferencia = depositadaReferencia;
	}

	public String getNombre_banco() {
		return nombre_banco;
	}
	public void setNombre_banco(String nombre_banco) {
		this.nombre_banco = nombre_banco;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public Date getFecha_apertura() {
		return fecha_apertura;
	}
	public void setFecha_apertura(Date fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Segregated getC_fintech_segregada() {
		return c_fintech_segregada;
	}
	public void setC_fintech_segregada(Segregated c_fintech_segregada) {
		this.c_fintech_segregada = c_fintech_segregada;
	}
	public Divisa getDivisa() {
		return divisa;
	}
	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Cuenta_referencia that = (Cuenta_referencia) o;
		return saldo == that.saldo && Objects.equals(rowId, that.rowId) && Objects.equals(nombre_banco, that.nombre_banco) && Objects.equals(sucursal, that.sucursal) && Objects.equals(pais, that.pais) && Objects.equals(fecha_apertura, that.fecha_apertura) && Objects.equals(estado, that.estado) && Objects.equals(c_fintech_segregada, that.c_fintech_segregada) && Objects.equals(divisa, that.divisa) && Objects.equals(DepositadaReferencia, that.DepositadaReferencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), rowId, nombre_banco, sucursal, pais, saldo, fecha_apertura, estado, c_fintech_segregada, divisa, DepositadaReferencia);
	}

	@Override
	public String toString() {
		return "Cuenta_referencia{" +
				"rowId='" + rowId + '\'' +
				", nombre_banco='" + nombre_banco + '\'' +
				", sucursal='" + sucursal + '\'' +
				", pais='" + pais + '\'' +
				", saldo=" + saldo +
				", fecha_apertura=" + fecha_apertura +
				", estado='" + estado + '\'' +
				", c_fintech_segregada=" + c_fintech_segregada +
				", divisa=" + divisa +
				", DepositadaReferencia=" + DepositadaReferencia +
				'}';
	}
}
