package entidad;

public class Profesores_x_Materias {
	
	private Profesor profesor;
	private Materia materia;
		
	
	public Profesores_x_Materias(Profesor profesor, Materia materia) {
		super();
		this.profesor = profesor;
		this.materia = materia;
	}
	
	
	
	
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	
	
	
}
