package negocioImpl;

import java.util.ArrayList;

import entidad.Reserva;
import negocio.ReservaNegocio;
import daoImpl.ReservaDaoImpl;

public class ReservaNegocioImpl implements ReservaNegocio {
	
	ReservaDaoImpl dao = new ReservaDaoImpl(); //CREO OBJETO DAO DE TIPO RESERVADAOIMPL
	
	
	// METODOS
	
	public ArrayList<Reserva> listar() { // LLAMADA ALA PROCEDIMIENTO LISTAR DEL DAO Y DEVUELVE UNA LISTA
		
		return dao.listar();
	}

	
	
	
	@Override
	public boolean insert(Reserva reserva) { //RECIBE UNA RESERVA, COMPRUEBA LOS DATOS SI ESTAN CORRECTOS LLAMA AL RPOCEDIMIENTO INSERTAR DEL DAO LE MANDA LA
											 // RESERVA Y RECIBE UN BOOLEANO Y LO RETORNA
		boolean estado = false;
		
		if(reserva.getProfesor().getDni().trim().length() > 0 && reserva.getMateria().getId() > 0 && reserva.getAdministrador().getDni().trim().length() > 0
				&& reserva.getAula().getId() > 0 )  {
			estado = dao.insert(reserva);
		}
				
		return estado;
	}

	
	@Override
	public boolean darBaja(Reserva reserva) {
		boolean estado = false;
		
		if(reserva.getId() > 0) {
			estado = dao.darBaja(reserva);
		}
		
		return estado;
	}

	
	
	@Override
	public ArrayList<Reserva> reporteReservas(java.sql.Date fechaInicio,java.sql.Date fechaFin) {
		
		return dao.reporteReservas(fechaInicio, fechaFin);
	}

}
