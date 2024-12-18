package dao;

import java.util.ArrayList;

import entidad.Materia;
import entidad.Profesor;

public interface MateriaDao {

	public ArrayList <Materia> listar();
	public ArrayList <Materia> listarfiltrado(int idmateria);
	public int insert(Materia materia);
	public Materia traerMateria(int id);
	public boolean update (Materia materia);
	public boolean delete (Materia materia);
}
