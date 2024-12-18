package negocioImpl;

import java.util.ArrayList;

import entidad.Materia;
import negocio.MateriaNegocio;
import daoImpl.MateriaDaoImpl;
import entidad.Profesor;

public class MateriaNegocioImpl implements MateriaNegocio{

	MateriaDaoImpl dao = new MateriaDaoImpl();
	@Override
	public ArrayList<Materia> listar() {
		
		return dao.listar();
	}

	@Override
	public int insert(Materia materia) {
		int estado = -1;
		
		if(materia.getNombre().trim().length() > 0 && materia.getInstitucion().getId() > 0 && materia.getCarrera().getId() > 0) {
			estado = dao.insert(materia);
		}
	
		return estado;
	}

	@Override
	public Materia traerMateria(int id) {
		
		return dao.traerMateria(id);
	}

	@Override
	public boolean update(Materia materia) {
		boolean estado = false;
		if(materia.getNombre().trim().length() > 0 && materia.getInstitucion().getId() > 0 && materia.getCarrera().getId() > 0 ) 
		{
			estado = dao.update(materia);
		}
		
		return estado;
	}

	@Override
	public boolean delete(Materia materia) {
		boolean estado = false;
		
		if(materia.getId() > 0) {
			estado = dao.delete(materia);
		}
		return estado;
	}

	@Override
	public ArrayList<Materia> listarfiltrado(int idmateria) {
		
		return dao.listarfiltrado(idmateria);
	}

}
