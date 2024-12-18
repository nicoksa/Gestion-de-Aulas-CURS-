package dao;

import entidad.Reserva;
import java.util.ArrayList;
import java.sql.*;

//INTERFAZ PARA DEFINIR LOS MÉTODOS DE ACCESO A DATOS RELACIONADOS CON LA CLASE RESERVA
public interface ReservaDao {

	public ArrayList <Reserva> listar();
	public boolean insert(Reserva reserva); //RECIBE UN OBJETO DE TIPO RESERVA
	public boolean darBaja(Reserva reserva);
	public ArrayList <Reserva> reporteReservas(Date fechaInicio,Date fechaFin); //RECIBE 2 OBJETOS DE TIPO FECHA
}
