package entidad;

public class Carrera {

	private int id;
	private Institucion institucion;
	private String nombre;
	
	
	
	public Carrera() {}
	
	public Carrera(int id, Institucion institucion, String nombre) {
		super();
		this.id = id;
		this.institucion = institucion;
		this.nombre = nombre;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Institucion getInstitucion() {
		return institucion;
	}
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
