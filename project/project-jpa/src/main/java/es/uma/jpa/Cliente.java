package es.uma.jpa;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    @Column(unique = true)
    private int Identificacion;
    @Column(nullable = false)
    private String tipo_cliente;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date Fecha_Alta;
    @Temporal(TemporalType.DATE)
    private Date Fecha_Baja;
    @Column(nullable = false)
    private String Direccion;
    @Column(nullable = false)
    private String Ciudad;
    @Column(nullable = false)
    private int c_postal;
    @Column(nullable = false)
    private String Pais;
	private String Estado;
    
    @OneToMany (mappedBy = "cliente")
    @JoinTable(name = "cliente_fintech", 
	joinColumns = @JoinColumn(name = "Id_cliente"), 
	inverseJoinColumns = @JoinColumn(name = "fintech_iban"))
    private List<Cuenta_Fintech> c_fintech;

	@OneToOne(mappedBy ="c_cliente")
	private Usuario u_usuario;


	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(int ID, int identificacion, String tipo_cliente, Date fecha_Alta, Date fecha_Baja, String direccion, String ciudad, int c_postal,
				   String pais, String estado, List<Cuenta_Fintech> c_fintech) {
		this.ID = ID;
		Identificacion = identificacion;
		this.tipo_cliente = tipo_cliente;
		Fecha_Alta = fecha_Alta;
		Fecha_Baja = fecha_Baja;
		Direccion = direccion;
		Ciudad = ciudad;
		this.c_postal = c_postal;
		Pais = pais;
		Estado = estado;
		this.c_fintech = c_fintech;
	}
	public Cliente(int ID, int identificacion, String tipo_cliente, Date fecha_Alta, Date fecha_Baja, String direccion, String ciudad, int c_postal,
				   String pais, String estado) {
		this.ID = ID;
		Identificacion = identificacion;
		this.tipo_cliente = tipo_cliente;
		Fecha_Alta = fecha_Alta;
		Fecha_Baja = fecha_Baja;
		Direccion = direccion;
		Ciudad = ciudad;
		this.c_postal = c_postal;
		Pais = pais;
		Estado = estado;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIdentificacion() {
		return Identificacion;
	}

	public void setIdentificacion(int identificacion) {
		Identificacion = identificacion;
	}

	public String getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public Date getFecha_Alta() {
		return Fecha_Alta;
	}

	public void setFecha_Alta(Date fecha_Alta) {
		Fecha_Alta = fecha_Alta;
	}

	public Date getFecha_Baja() {
		return Fecha_Baja;
	}

	public void setFecha_Baja(Date fecha_Baja) {
		Fecha_Baja = fecha_Baja;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public int getC_postal() {
		return c_postal;
	}

	public void setC_postal(int c_postal) {
		this.c_postal = c_postal;
	}

	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public List<Cuenta_Fintech> getC_fintech() {
		return c_fintech;
	}

	public void setC_fintech(List<Cuenta_Fintech> c_fintech) {
		this.c_fintech = c_fintech;
	}

	public Usuario getU_usuario() {
		return u_usuario;
	}

	public void setU_usuario(Usuario u_usuario) {
		this.u_usuario = u_usuario;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return ID == cliente.ID && Identificacion == cliente.Identificacion && c_postal == cliente.c_postal && Objects.equals(tipo_cliente, cliente.tipo_cliente) && Objects.equals(Fecha_Alta, cliente.Fecha_Alta) && Objects.equals(Fecha_Baja, cliente.Fecha_Baja) && Objects.equals(Direccion, cliente.Direccion) && Objects.equals(Ciudad, cliente.Ciudad) && Objects.equals(Pais, cliente.Pais) && Objects.equals(Estado, cliente.Estado) && Objects.equals(c_fintech, cliente.c_fintech) && Objects.equals(u_usuario, cliente.u_usuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, Identificacion, tipo_cliente, Fecha_Alta, Fecha_Baja, Direccion, Ciudad, c_postal, Pais, Estado, c_fintech, u_usuario);
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"ID=" + ID +
				", Identificacion=" + Identificacion +
				", tipo_cliente='" + tipo_cliente + '\'' +
				", Fecha_Alta=" + Fecha_Alta +
				", Fecha_Baja=" + Fecha_Baja +
				", Direccion='" + Direccion + '\'' +
				", Ciudad='" + Ciudad + '\'' +
				", c_postal=" + c_postal +
				", Pais='" + Pais + '\'' +
				", Estado='" + Estado + '\'' +
				", c_fintech=" + c_fintech +
				", u_usuario=" + u_usuario +
				'}';
	}
}
