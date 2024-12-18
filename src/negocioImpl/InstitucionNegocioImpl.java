package negocioImpl;
import entidad.Institucion;
import negocio.InstitucionNegocio;

import java.util.ArrayList;

import daoImpl.InstitucionDaoImpl;


public class InstitucionNegocioImpl implements InstitucionNegocio{

	InstitucionDaoImpl dao = new InstitucionDaoImpl();
	@Override
	public boolean insert(Institucion institucion) {
		
		boolean estado = false;
		
		if(institucion.getNombre().trim().length() > 0  ) {
			estado = dao.insert(institucion);
		}
		
		
		return estado;
	}
	
	
	
	@Override
	public ArrayList<Institucion> listar() {

		return dao.listar();
	}



	@Override
	public Institucion traerInstitucion(int id) {
		
		return dao.traerInstitucion(id);
	}



	@Override
	public boolean delete(Institucion institucion) {
		boolean estado = false;
		
		if(institucion.getId() > 0 ) 
		{
			estado = dao.delete(institucion);
		}
		return estado;
	}



	@Override
	public boolean update(Institucion institucion) {
		
		boolean estado = false;
		
		if(institucion.getNombre().trim().length() > 0 && institucion.getId() > 0  ) {
			estado = dao.update(institucion);
		}
		
		
		return estado;
	}

}
