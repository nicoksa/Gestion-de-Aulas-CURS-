package dao;

import entidad.Carrera;
import java.util.ArrayList;

public interface CarreraDao {

	public ArrayList <Carrera> listar();
	public boolean insert(Carrera carrera);
	public Carrera traerCarrera(int id);
	public boolean update (Carrera carrera);
	public boolean delete (Carrera carrera);
}
