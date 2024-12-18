package negocioImpl;

import entidad.Profesores_x_Materias;

import java.util.ArrayList;

import daoImpl.PxMDaoImpl;
import negocio.PxMNegocio;

public class PxMNegocioImpl implements PxMNegocio{

	PxMDaoImpl dao = new PxMDaoImpl();
	
	@Override
	public boolean insert(Profesores_x_Materias pxm) {
		boolean estado = false;
		
		if(pxm.getProfesor().getDni().trim().length() > 0 && pxm.getMateria().getId() > 0) {
			estado = dao.insert(pxm);
		} 
				
		return estado;
	}

	@Override
	public ArrayList<Profesores_x_Materias> listar() {
		
		return dao.listar();
	}

	@Override
	public boolean update(Profesores_x_Materias pxmNuevo, Profesores_x_Materias pxmActual) {
		boolean estado = false;
		
		if(pxmNuevo.getProfesor().getDni().trim().length() > 0 && pxmNuevo.getMateria().getId() > 0 &&
				pxmActual.getProfesor().getDni().trim().length() > 0 && pxmActual.getMateria().getId() > 0) {
			estado = dao.update(pxmNuevo,pxmActual);
		} 
				
		return estado;
	}

}
