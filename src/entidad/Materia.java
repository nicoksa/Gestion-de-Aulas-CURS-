package entidad;

public class Materia {
		
	private int id;
	private Carrera carrera;
	private Institucion institucion;
	private String nombre;
	
	public Materia() {}
	
	public Materia (int id) {
		this.id = id;
	}
	
	
	public Materia(int id, Carrera carrera,Institucion institucion, String nombre) {
		super();
		this.id = id;
		this.carrera = carrera;		
		this.nombre = nombre;
		this.institucion = institucion;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
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
