package entidad;

public class Profesor {
	
	private String dni;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private int estado;
	
	public Profesor() {}
	
	public Profesor(String dni) {
		this.dni = dni;
	}
	
	
	public Profesor(String dni, String nombre, String apellido, String telefono, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.estado = 1;
	}
	
	
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Profesor [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", email=" + email + ", estado=" + estado + "]";
	}
	
	
	
}
