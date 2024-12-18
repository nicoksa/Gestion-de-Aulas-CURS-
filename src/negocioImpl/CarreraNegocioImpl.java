package negocioImpl;

import java.util.ArrayList;

import entidad.Carrera;
import negocio.CarreraNegocio;
import daoImpl.CarreraDaoImpl;


public class CarreraNegocioImpl implements CarreraNegocio{
	
	CarreraDaoImpl dao = new CarreraDaoImpl();

	@Override
	public ArrayList<Carrera> listar() {
		
		return dao.listar();
	}

	@Override
	public boolean insert(Carrera carrera) {
		
		boolean estado = false;
		
		if(carrera.getNombre().trim().length() > 0 && carrera.getInstitucion().getId() > 0 ) {
			estado = dao.insert(carrera);
		}
		
		
		return estado;
	}

	@Override
	public Carrera traerCarrera(int id) {
		
		return dao.traerCarrera(id);
	}

	@Override
	public boolean update(Carrera carrera) {
		
		boolean estado = false;
		
		if(carrera.getNombre().trim().length() > 0 && carrera.getInstitucion().getId() > 0 && carrera.getId() > 0) {
			estado = dao.update(carrera);
		}
		return estado;
	}

	@Override
	public boolean delete(Carrera carrera) {
		boolean estado = false;
		
		if(carrera.getId() > 0) {
			estado = dao.delete(carrera);
		}
		return estado;
	}
	
	

}
