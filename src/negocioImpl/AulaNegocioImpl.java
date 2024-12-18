package negocioImpl;
import entidad.Aula;
import negocio.AulaNegocio;

import java.util.*;

import daoImpl.AulaDaoImpl;

public class AulaNegocioImpl implements AulaNegocio{

	AulaDaoImpl dao = new AulaDaoImpl();
	
	
	@Override
	public boolean insert(Aula aula) {
		boolean estado = false;
		
		if(aula.getId() > 0 && aula.getNombre().trim().length() > 0  && aula.getCapacidad() > 0) {
			estado = dao.insert(aula);
		}
		
		
		return estado;
	}


	@Override
	public ArrayList<Aula> listar() {
		
		return dao.listar();
	}


	@Override
	public Aula traerAula(int id) {
		
		return dao.traerAula(id);
	}


	@Override
	public boolean delete(Aula aula) {
		boolean estado = false;
		
		if(aula.getId() > 0 ) 
		{
			estado = dao.delete(aula);
		}
		return estado;
	}


	@Override
	public boolean update(Aula aula) {
		boolean estado = false;
		if(aula.getId() > 0 && aula.getNombre().trim().length() > 0  && aula.getCapacidad() > 0) {
			estado = dao.update(aula);
		}
		return estado;
	}
	
	public List<Aula> filtrarAulasDisponibles(int capacidadMinima, Date fecha, java.sql.Time  horaInicio, java.sql.Time horaFinal) {
	    return dao.filtrarAulasDisponibles(capacidadMinima, fecha, horaInicio, horaFinal);
	}

}
