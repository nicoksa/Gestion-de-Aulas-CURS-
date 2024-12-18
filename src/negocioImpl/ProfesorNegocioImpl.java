package negocioImpl;

import java.util.ArrayList;

import entidad.Profesor;
import negocio.ProfesorNegocio;
import daoImpl.ProfesorDaoImpl;

public class ProfesorNegocioImpl implements ProfesorNegocio{
	
	ProfesorDaoImpl dao = new ProfesorDaoImpl();
	

	@Override
	public boolean insert(Profesor profesor) {
		boolean estado = false;
		
		if(profesor.getNombre().trim().length() > 0 && profesor.getDni().trim().length() > 0 && profesor.getApellido().trim().length() > 0 
				&& profesor.getTelefono().trim().length() > 0 && profesor.getEmail().trim().length() > 0 ) {
			estado = dao.insert(profesor);
		}
		
		
		return estado;
	}

	@Override
	public boolean delete(Profesor profesor) {
		boolean estado = false;
		
		if(profesor.getDni().trim().length() > 0 ) 
		{
			estado = dao.delete(profesor);
		}
		return estado;
	}

	@Override
	public boolean update(Profesor profesor) {
		boolean estado = false;
		
		if(profesor.getNombre().trim().length() > 0 && profesor.getDni().trim().length() > 0 && profesor.getApellido().trim().length() > 0 
				&& profesor.getTelefono().trim().length() > 0 && profesor.getEmail().trim().length() > 0 ) {
			estado = dao.update(profesor);
		}
		
		
		return estado;
	}

	@Override
	public ArrayList<Profesor> listar() {
		
		return dao.listar();
	}

	@Override
	public Profesor traerProfesor(String dni) {
		
		return dao.traerProfesor(dni);
	}

	@Override
	public ArrayList<Profesor> buscarPorNombre(String busqueda) {
		
		return dao.buscarPorNombre(busqueda);
	}

	@Override
	public ArrayList<Profesor> listarinactivos() {
		return dao.listarinactivos();
	}

	@Override
	public boolean reactivarProfesor(Profesor profesor) {
		boolean estado = false;
		if(profesor.getDni().trim().length() > 0 ) 
		{
			estado = dao.reactivarProfesor(profesor);
		}
		return estado;	}
	

}
