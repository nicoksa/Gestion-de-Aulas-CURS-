package negocio;

import java.util.ArrayList;

import entidad.Materia;


public interface MateriaNegocio {
	public ArrayList <Materia> listar ();
	public ArrayList <Materia> listarfiltrado(int idmateria);
	public int insert (Materia materia);
	public Materia traerMateria(int id);
	public boolean update (Materia materia);
	public boolean delete (Materia materia);

}
