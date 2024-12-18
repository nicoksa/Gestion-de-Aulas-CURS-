package dao;

import java.util.ArrayList;

import entidad.Profesor;

public interface ProfesorDao{
	
	public boolean insert (Profesor profesor);
	public boolean delete (Profesor profesor);
	public boolean update (Profesor profesor);
	public ArrayList<Profesor> listar();
	public ArrayList<Profesor> listarinactivos();
	public Profesor traerProfesor(String id);
	public ArrayList <Profesor> buscarPorNombre(String busqueda);
	public boolean reactivarProfesor(Profesor profesor);
}
